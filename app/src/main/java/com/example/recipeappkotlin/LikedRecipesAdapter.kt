package com.example.recipeappkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class LikedRecipesAdapter(private val onDeleteClicked: (Recipe) -> Unit) :
    ListAdapter<Recipe, LikedRecipesAdapter.LikedRecipeViewHolder>(RecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikedRecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_liked_recipe, parent, false)
        return LikedRecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: LikedRecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe, onDeleteClicked)
    }

    class LikedRecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val ingredientsTextView: TextView = itemView.findViewById(R.id.ingredientsTextView)
        private val servingsTextView: TextView = itemView.findViewById(R.id.servingsTextView)
        private val deleteRecipeButton: ImageButton = itemView.findViewById(R.id.deleteRecipeButton)

        fun bind(recipe: Recipe, onDeleteClicked: (Recipe) -> Unit) {
            titleTextView.text = recipe.title
            ingredientsTextView.text = recipe.ingredients
            servingsTextView.text = recipe.servings

            // Set the delete button click listener
            deleteRecipeButton.setOnClickListener {
                onDeleteClicked(recipe)
            }
        }
    }

    class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }
}

