package api.ClothingStore.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.ClothingStore.Endpoints.UserLoginEndPoints;
import io.restassured.response.Response;

/**
 * Test class for User Login functionality including:
 * - Valid login
 * - Invalid login
 * - Invalid method (delete)
 */
public class UserLoginTestCases {

    /**
     * Test for login with valid credentials.
     */
    @Test
    public void userLoginValidDetails() {
        Response response = UserLoginEndPoints.sendInvalidLogin("your_email@email.com", "your_password");
        response.then().extract().response();

        System.out.println("Response Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200, "Expected HTTP status 200 for successful login.");
        Assert.assertTrue(response.getBody().asString().contains("User exists!"),
                "Expected login success message. Actual: " + response.getBody().asString());
    }

    /**
     * Test for login with invalid credentials.
     */
    @Test
    public void userLoginInvalidDetails() {
        Response response = UserLoginEndPoints.sendInvalidLogin("invalidemail@email.com", "invalidPassword");
        response.then().extract().response();

        System.out.println("Response Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        Assert.assertTrue(response.getBody().asString().contains("User not found!"),
                "Expected failure message. Actual: " + response.getBody().asString());
    }

    /**
     * Test for sending an invalid HTTP method (DELETE) to login endpoint.
     */
    @Test
    public void deleteLoginDetails() {
        Response response = UserLoginEndPoints.deleteLogin();
        response.then().extract().response();

        System.out.println("Response Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

       // Assert.assertEquals(response.getStatusCode(), 405, "Expected HTTP 405 Method Not Allowed.");
        Assert.assertTrue(response.getBody().asString().contains("405"),
                "Expected method not allowed message. Actual: " + response.getBody().asString());
    }
}
