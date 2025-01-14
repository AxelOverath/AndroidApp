package com.example.recipeappkotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recipeappkotlin.databinding.ActivitySettingsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up BottomNavigationView
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.nav_settings -> {
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
    }
}
