# Recipe Finder App

## Overview

Recipe Finder is an Android application built with Kotlin, designed to make finding and saving recipes easy and convenient. The app integrates with the [API Ninja's Recipe API](https://api-ninjas.com/) to search for recipes and provides detailed views for each recipe. Users can save their favourite recipes and manage them using Room database functionality. Additionally, a settings menu is included for language customization (currently under development).

## Features

- **Search Recipes:** Find recipes using the API Ninja's Recipe API.
- **Detailed Recipe View:** View detailed information about a recipe, with a landscape mode for an enhanced viewing experience.
- **Favorite Recipes:** Save recipes you like to a favourites list, powered by Room database.
- **Remove Favorites:** Easily remove recipes from your favorites list.
- **Settings Menu:** Includes a placeholder for changing the app's language (not yet implemented).

## Getting Started

### Prerequisites

- Android Studio installed on your machine.
- Android device or emulator running Android 7.0 (Nougat) or newer.

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/AxelOverath/AndroidApp.git
   ```

2. **Open the project in Android Studio:**
   - Launch Android Studio.
   - Select "Open an existing project."
   - Navigate to the cloned repository's directory and open it.

3. **Set up the API Key:**
   - Obtain an API key from [API Ninja's](https://api-ninjas.com/).
   - Add your API key in the header of the API call.

4. **Build the project:**
   - Resolve dependencies.
   - Click on "Build" > "Make Project" in Android Studio.

5. **Run the app:**
   - Connect your Android device or start an emulator.
   - Click on "Run" > "Run 'app'" to install and launch the app.

## How to Use

1. **Search for Recipes:**
   - Enter a keyword in the search bar to find recipes.
   - Results will be fetched from the API Ninja's Recipe API.

2. **View Recipe Details:**
   - Tap on a recipe to view detailed information.
   - Rotate your device to landscape mode for an enhanced viewing experience.

3. **Like a Recipe:**
   - Tap the "Like" button on a recipe to save it to your favourite list.

4. **View and Manage Favorites:**
   - Navigate to the "Favorites" tab to see your favourite recipes.
   - Tap "Remove" on a recipe to delete it from your favourites.

5. **Settings Menu:**
   - Access the settings menu to explore language customization options (currently not functional).

## Future Improvements

- Implement language customization in the settings menu.
- Add offline support for recipe browsing.
- Enhance UI/UX with animations and better responsiveness.
- Introduce sorting and filtering options for recipes.

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature/bug fix.
3. Commit your changes and push them to your fork.
4. Open a pull request to the main repository.

## Acknowledgment
- Thanks to OpenAI for assistance in crafting the README and providing support with the project.

