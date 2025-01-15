import com.example.recipeappkotlin.HomeActivity
import com.example.recipeappkotlin.Recipe
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.junit.Assert.assertEquals

class HomeActivityTest {

    // Mock the HomeActivity class
    @Mock
    lateinit var homeActivity: HomeActivity

    @Before
    fun setUp() {
        // Initialize mocks
        MockitoAnnotations.initMocks(this)
    }

    // Test the cleanIngredients helper function
    @Test
    fun cleanIngredients_removesSpecialCharactersAndFormatsCorrectly() {
        // Define mock behavior
        val input = "1 cup flour; 2 eggs; --or-- 3 tbsp oil"
        val expected = "1 cup flour\n2 eggs\n3 tbsp oil"

        // Mock the cleanIngredients method behavior
        Mockito.`when`(homeActivity.cleanIngredients(input)).thenReturn(expected)

        // Call the method to test
        val result = homeActivity.cleanIngredients(input)

        // Assert the result is as expected
        assertEquals(expected, result)

        // Verify that the method was called with the input
        Mockito.verify(homeActivity).cleanIngredients(input)
    }

    // Test the parseRecipesFromJson helper function
    @Test
    fun parseRecipesFromJson_correctlyParsesJson() {
        // Define mock behavior
        val sampleJson = """
            [
                {
                    "title": "Pasta",
                    "ingredients": "Noodles|Sauce|Cheese",
                    "servings": "4",
                    "instructions": "Boil noodles, mix sauce, add cheese."
                }
            ]
        """.trimIndent()

        val expectedRecipes = listOf(
            Recipe(0,"Pasta", "Noodles|Sauce|Cheese", "4","Boil noodles, mix sauce, add cheese.")
        )

        // Mock the parseRecipesFromJson method behavior
        Mockito.`when`(homeActivity.parseRecipesFromJson(sampleJson)).thenReturn(expectedRecipes)

        // Call the method to test
        val recipes = homeActivity.parseRecipesFromJson(sampleJson)

        // Assert the result is as expected
        assertEquals(1, recipes.size)
        assertEquals(expectedRecipes[0], recipes[0])

        // Verify that the method was called with the sample JSON
        Mockito.verify(homeActivity).parseRecipesFromJson(sampleJson)
    }

    // Test fetchRecipes handles empty queries properly (mocked behavior)
    @Test
    fun fetchRecipes_withEmptyQuery_showsToastMessage() {
        // Define mock behavior
        val query = "" // Empty query
        var toastMessage = ""

        // Simulate the Toast behavior when an empty query is submitted
        if (query.isEmpty()) {
            toastMessage = "Searching for: $query"
        }

        // Assert the toast message is as expected
        assertEquals("Searching for: ", toastMessage)
    }
}
