pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()

        // Add JitPack repository
        maven(url = "https://jitpack.io") {
            content {
                // Optionally, include specific groups if needed
                includeGroupByRegex("com.github.therealbush:translator:1.1.1")
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "RecipeAppKotlin"
include(":app")
