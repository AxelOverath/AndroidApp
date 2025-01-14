package com.example.recipeappkotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeappkotlin.databinding.ActivityLikedRecipesBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class LikedRecipesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLikedRecipesBinding
    private lateinit var recipeDatabase: RecipeDatabase
    private lateinit var likedRecipesAdapter: LikedRecipesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLikedRecipesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hide the action bar
        supportActionBar?.hide()

        // Initialize the Room database
        recipeDatabase = RecipeDatabase.getInstance(applicationContext)

        // Set up the RecyclerView with the delete logic
        likedRecipesAdapter = LikedRecipesAdapter { recipe ->
            deleteRecipeFromFavorites(recipe)
        }

        binding.recyclerViewLikedRecipes.apply {
            layoutManager = LinearLayoutManager(this@LikedRecipesActivity)
            adapter = likedRecipesAdapter
        }

        // Fetch all liked recipes from the database
        fetchLikedRecipes()

        // Set up BottomNavigationView
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.nav_settings -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                    true
                }
                R.id.nav_liked_recipes -> {
                    true
                }
                else -> false
            }
        }
    }

    private fun fetchLikedRecipes() {
        lifecycleScope.launch {
            val likedRecipes = recipeDatabase.recipeDao().getAllRecipes()
            likedRecipesAdapter.submitList(likedRecipes)
        }
    }

    private fun deleteRecipeFromFavorites(recipe: Recipe) {
        lifecycleScope.launch {
            // Delete the recipe from the database
            recipeDatabase.recipeDao().deleteRecipe(recipe)

            // Refresh the list
            fetchLikedRecipes()
        }
    }
}

