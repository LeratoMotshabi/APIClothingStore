package api.ClothingStore.TestCases;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.testng.annotations.Test;

import api.ClothingStore.Endpoints.SearchProductEndPoints;
import io.restassured.response.Response;

/**
 * This class contains test cases for searching products in the Clothing Store API.
 */
public class SearchProductTestCases {

    // Endpoint handler instance for search-related API calls
    static SearchProductEndPoints searchProductEndPoints = new SearchProductEndPoints();

    /**
     * Test to search for a randomly selected product from a predefined list.
     * Sends a POST request with the selected product name.
     */
    @Test
    public void searchProductByKeyword() {
        // List of sample product keywords to test with
        List<String> products = Arrays.asList(
            "jean",
            "tshirt",
            "top",
            "Dress",
            "shirt",
            "sleeveless"
        );

        // Select a product at random
        String selectedProduct = products.get(new Random().nextInt(products.size()));

        // Send search request
        Response response = SearchProductEndPoints.postSearchProduct(selectedProduct);

        // Print the search keyword and response
        System.out.println("Searched for: " + selectedProduct);
        System.out.println("Response:\n" + response.getBody().asString());
    }

    /**
     * Test to retrieve all products or brands.
     * Sends a GET request to fetch all available entries.
     */
    @Test
    public void searchAllProducts() {
        // Send request to fetch all products
        Response response = SearchProductEndPoints.getAllProducts();

        // Print the complete response
        System.out.println("All products response:\n" + response.getBody().asString());
    }
}
