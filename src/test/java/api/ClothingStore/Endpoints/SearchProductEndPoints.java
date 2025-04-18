package api.ClothingStore.Endpoints;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

/**
 * This class contains endpoint methods for searching and retrieving products
 * from the Clothing Store API.
 */
public class SearchProductEndPoints {

    // Route instance to access endpoint URLs from properties
    static Routes route = new Routes();

    /**
     * Sends a POST request to search for a specific product using a form parameter.
     *
     * @param productName the name or keyword of the product to search for
     * @return the API response
     */
    public static Response postSearchProduct(String productName) {
        String endpoint = route.getURL().getProperty("PostToSearchProduct");

        Response response = given()
            .formParam("search_product", productName)
            .log().all() // Logs request details for debugging
            .when()
            .post(endpoint);

        return response;
    }

    /**
     * Sends a GET request to retrieve all products (or brands) from the API.
     *
     * @return the API response
     */
    public static Response getAllProducts() {
        String endpoint = route.getURL().getProperty("GetAllBrands");

        Response response = given()
            .when()
            .get(endpoint);

        return response;
    }
}
