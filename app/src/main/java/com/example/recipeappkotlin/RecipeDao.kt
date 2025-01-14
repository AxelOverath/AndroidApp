package com.example.recipeappkotlin

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeDao {

    @Insert
   suspend fun insert(recipe: Recipe)

    @Query("SELECT * FROM fav_table")
    suspend fun getAllRecipes(): List<Recipe>

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)
}