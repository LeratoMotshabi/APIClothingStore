package api.ClothingStore.Endpoints;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;

/**
 * This class handles login-related API endpoints including:
 * - Valid login
 * - Invalid login
 * - Deleting login sessions or tokens
 */
public class UserLoginEndPoints {

    // Route instance to fetch endpoint URLs from configuration
    static Routes route = new Routes();

    /**
     * Sends a POST request to log in with valid credentials.
     *
     * @param email    the user's email
     * @param password the user's password
     * @return the API response
     */
    public static Response sendValidLogin(String email, String password) {
        String loginUrl = route.getURL().getProperty("Login");

        return given()
            .formParam("email", email)
            .formParam("password", password)
            .when()
            .post(loginUrl);
    }

    /**
     * Sends a POST request to log in with invalid credentials.
     *
     * @param email    the user's email
     * @param password the user's password
     * @return the API response
     */
    public static Response sendInvalidLogin(String email, String password) {
        String loginUrl = route.getURL().getProperty("Login");

        return given()
            .formParam("email", email)
            .formParam("password", password)
            .when()
            .post(loginUrl);
    }

    /**
     * Sends a DELETE request to remove a login session (if supported).
     *
     * @return the API response
     */
    public static Response deleteLogin() {
        String loginUrl = route.getURL().getProperty("Login");

        return given()
            .when()
            .delete(loginUrl);
    }
}
