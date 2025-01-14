package com.example.recipeappkotlin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Recipe::class],
    version = 3,
    exportSchema = false
)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getInstance(context: Context): RecipeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    "favorite_recipe_database"
                )
                    .fallbackToDestructiveMigration() // Handle schema changes during development
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
