package com.example.recipeappkotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.recipeappkotlin.databinding.ActivitySettingsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

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
                    startActivity(Intent(this, LikedRecipesActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // Initialize the language Spinner
        val languageSpinner: Spinner = binding.languageSelector

        // Set listener for language selection
        languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                when (position) {
                    0 -> setLocale("en") // English
                    1 -> setLocale("nl") // Dutch
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        // Set the spinner's selected item based on the saved language
        val sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val savedLanguage = sharedPreferences.getString("Selected_Language", "en")
        languageSpinner.setSelection(if (savedLanguage == "nl") 1 else 0)
    }

    private fun setLocale(languageCode: String) {
        val currentLocale = resources.configuration.locales[0].language
        if (currentLocale == languageCode) {
            // If the selected language is already set, do nothing
            return
        }

        // Change locale settings in the app
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        config.setLayoutDirection(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        // Save the selected language in SharedPreferences
        val sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("Selected_Language", languageCode)
            apply()
        }

        // Restart the activity to apply the changes
        recreate() // Reload the activity to apply the new language
    }
}
