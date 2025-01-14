package com.example.recipeappkotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeappkotlin.databinding.ListItemRecipeBinding

class RecipeAdapter(private val onItemClick: (Recipe) -> Unit) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private var recipes = listOf<Recipe>()

    fun submitList(newRecipes: List<Recipe>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ListItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipes.size

    class RecipeViewHolder(
        private val binding: ListItemRecipeBinding,
        private val onItemClick: (Recipe) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            // Bind recipe title
            binding.titleTextView.text = recipe.title
            // Bind recipe ingredients
            binding.ingredientsTextView.text = recipe.cleanedIngredients
            // Bind servings information
            binding.servingsTextView.text = recipe.servings
            // Set click listener for the recipe item
            binding.root.setOnClickListener { onItemClick(recipe) }
        }
    }
}
