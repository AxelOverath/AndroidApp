package com.example.recipeappkotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.recipeappkotlin.databinding.ActivityDetailBinding
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var recipeDatabase: RecipeDatabase // Reference to Room database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Room database
        // Initialize the Room database using singleton
        recipeDatabase = RecipeDatabase.getInstance(applicationContext)


        // Set the Toolbar as the ActionBar
        setSupportActionBar(binding.toolbar)

        // Enable the back button (up button)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Get the data from the intent
        val title = intent.getStringExtra("title")
        val ingredients = intent.getStringExtra("ingredients")
        val instructions = intent.getStringExtra("instructions")
        val servings = intent.getStringExtra("servings")

        // Set the title and content
        binding.recipeTitle.text = title
        binding.ingredientsList.text = ingredients
        binding.instructions.text = instructions

        // Handle like button click
        binding.likeButton.setOnClickListener {
            if (title != null && ingredients != null && instructions != null && servings != null)  {
                saveRecipeToDatabase(title, ingredients, instructions, servings)
            } else {
                Toast.makeText(this, "Error: Missing recipe data!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveRecipeToDatabase(title: String, ingredients: String, instructions: String, servings: String) {
        lifecycleScope.launch {
            // Create a new recipe entity
            val recipe = Recipe(
                title = title,
                ingredients = ingredients,
                instructions = instructions,
                servings = servings
            )

            // Insert the recipe into the database
            recipeDatabase.recipeDao().insert(recipe)

            // Show confirmation to the user
            Toast.makeText(this@DetailActivity, "Recipe saved to favorites!", Toast.LENGTH_SHORT).show()
        }
    }

    // Handle the back button press
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
