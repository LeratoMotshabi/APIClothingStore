package api.ClothingStore.Endpoints;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

/**
 * This class defines all the endpoints related to user account operations
 * such as Create, Read, Update, and Delete user accounts using form data.
 */
public class UserAccountEndPoints {

    static Routes route = new Routes();

    /**
     * Creates a new user account by sending a POST request with form parameters.
     */
    public static Response createUserAccount(
            String name, String email, String password, String title,
            String birth_date, String birth_month, String birth_year,
            String firstname, String lastname, String company,
            String address1, String address2, String country,
            String zipcode, String state, String city, String mobile_number) {

        String postUrl = route.getURL().getProperty("PostUserAccount");

        return given()
            .formParam("name", name)
            .formParam("email", email)
            .formParam("password", password)
            .formParam("title", title)
            .formParam("birth_date", birth_date)
            .formParam("birth_month", birth_month)
            .formParam("birth_year", birth_year)
            .formParam("firstname", firstname)
            .formParam("lastname", lastname)
            .formParam("company", company)
            .formParam("address1", address1)
            .formParam("address2", address2)
            .formParam("country", country)
            .formParam("zipcode", zipcode)
            .formParam("state", state)
            .formParam("city", city)
            .formParam("mobile_number", mobile_number)
        .when()
            .post(postUrl);
    }

    /**
     * Reads user account details using the email as a query parameter.
     */
    public static Response readUserAccountByEmail(String email) {
        String getUrl = route.getURL().getProperty("GetUserAccountByEmail");

        return given()
            .queryParam("email", email)
        .when()
            .get(getUrl);
    }

    /**
     * Updates an existing user account using the provided details.
     */
    public static Response updateUserAccount(
            String email, String name, String password, String title,
            String birth_date, String birth_month, String birth_year,
            String firstname, String lastname, String company,
            String address1, String address2, String country,
            String zipcode, String state, String city, String mobile_number) {

        String putUrl = route.getURL().getProperty("PutUserAccount");

        return given()
            .contentType("application/x-www-form-urlencoded")
            .queryParam("email", email)
            .formParam("name", name)
            .formParam("password", password)
            .formParam("title", title)
            .formParam("birth_date", birth_date)
            .formParam("birth_month", birth_month)
            .formParam("birth_year", birth_year)
            .formParam("firstname", firstname)
            .formParam("lastname", lastname)
            .formParam("company", company)
            .formParam("address1", address1)
            .formParam("address2", address2)
            .formParam("country", country)
            .formParam("zipcode", zipcode)
            .formParam("state", state)
            .formParam("city", city)
            .formParam("mobile_number", mobile_number)
            .log().all()
        .when()
            .put(putUrl);
    }

    /**
     * Deletes a user account by email and password using query parameters.
     */
    public static Response deleteUserAccountByEmail(String email, String password) {
        String deleteUserAccountByEmail = route.getURL().getProperty("DeleteUserAccount");

        // Check if the email is correctly set
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .queryParam("email", email)   // Ensure email is passed correctly
                .queryParam("password", password)  // Password parameter
                .log().all()
                .when()
                .delete(deleteUserAccountByEmail)
                .then()
                .log().all()
                .extract().response();

        return response;
    }
}
