package com.example.recipeappkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeappkotlin.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request

class HomeActivity : AppCompatActivity() {

    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hide the action bar
        supportActionBar?.hide()
        // Set up BottomNavigationView
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // Handle Home action (e.g., refresh the screen)
                    true
                }
                R.id.nav_settings -> {
                    // Navigate to SettingsActivity
                    startActivity(Intent(this, SettingsActivity::class.java))
                    true
                }
                R.id.nav_liked_recipes -> {
                    // Navigate to LikedRecipesActivity
                    startActivity(Intent(this, LikedRecipesActivity::class.java))
                    true
                }
                else -> false
            }
        }
        // Initialize the RecyclerView
        recipeAdapter = RecipeAdapter { recipe ->
            // Navigate to DetailActivity with pre-cleaned data
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("title", recipe.title)
                putExtra("ingredients", recipe.cleanedIngredients)
                putExtra("instructions", recipe.instructions)
                putExtra("servings", recipe.servings)
            }
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = recipeAdapter

        // Set up SearchView
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    fetchRecipes(it) // Fetch recipes using the search query
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Optional: Handle text changes if needed
                return false
            }
        })

        // Initial fetch for default recipes
        // fetchRecipes("")
    }

    // Simulate API call or replace with actual implementation
    private fun fetchRecipes(query: String) {
        // Show a Toast as a placeholder for the API call
        Toast.makeText(this, "Searching for: $query", Toast.LENGTH_SHORT).show()

        // OkHttp client
        val client = OkHttpClient()

        // API URL and headers
        val apiUrl = "https://api.api-ninjas.com/v1/recipe?query=$query"
        val request = Request.Builder()
            .url(apiUrl)
            .addHeader("X-Api-Key", "xMIO9+7msWYQcpiZOncacQ==ffW4tQ8CBg48ikEu")
            .build()

        // Execute the API call in a background thread
        Thread {
            try {
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    response.body?.string()?.let { responseBody ->
                        // Parse the JSON response
                        val recipes = parseRecipesFromJson(responseBody)

                        // Clean up ingredients
                        val cleanedRecipes = recipes.map { recipe ->
                            recipe.copy(cleanedIngredients = cleanIngredients(recipe.ingredients))
                        }

                        // Submit the cleaned recipes to the adapter on the main thread
                        runOnUiThread {
                            recipeAdapter.submitList(cleanedRecipes)
                        }
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(this, "Failed to fetch recipes", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }.start()
    }

    // Helper function to parse JSON response
    fun parseRecipesFromJson(json: String): List<Recipe> {
        val gson = Gson()
        val type = object : TypeToken<List<Recipe>>() {}.type
        return gson.fromJson(json, type)
    }


    // Function to clean up ingredients
    fun cleanIngredients(ingredients: String): String {
        var cleanedIngredients = ingredients.replace(";", "")
        cleanedIngredients = cleanedIngredients.replace("--or--", "or")
        return cleanedIngredients.split("|").joinToString("\n") { it.trim() }
    }
}


