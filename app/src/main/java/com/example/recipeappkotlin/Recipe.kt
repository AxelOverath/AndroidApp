package com.example.recipeappkotlin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_table")
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "titel") val title: String,
    @ColumnInfo(name = "ingredients") val ingredients: String,
    @ColumnInfo(name = "servings") val servings: String,
    @ColumnInfo(name = "instructions") val instructions: String,
    val cleanedIngredients: String = "" // Default empty string
)

