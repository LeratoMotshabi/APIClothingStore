package api.ClothingStore.TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.ClothingStore.Endpoints.UserAccountEndPoints;
import api.ClothingStore.PayLoads.UserAccountPayLoads;
import io.restassured.response.Response;

/**
 * Test suite for verifying User Account operations:
 * - Create
 * - Read
 * - Update
 * - Delete
 */
public class UserAccountTestCases {

    Faker faker = new Faker();
    UserAccountPayLoads userAccountPayLoads = new UserAccountPayLoads();

    /**
     * Setup method to initialize fake user data using JavaFaker before tests run.
     */
    @BeforeClass
    public void setupData() {
        userAccountPayLoads.setName(faker.name().fullName());
        userAccountPayLoads.setEmail(faker.internet().emailAddress());
        userAccountPayLoads.setPassword(faker.internet().password());
        userAccountPayLoads.setTitle(faker.options().option("Mr", "Mrs", "Miss"));
        userAccountPayLoads.setBirth_date(String.valueOf(faker.number().numberBetween(1, 28)));
        userAccountPayLoads.setBirth_month(String.valueOf(faker.number().numberBetween(1, 12)));
        userAccountPayLoads.setBirth_year(String.valueOf(faker.number().numberBetween(1950, 2005)));
        userAccountPayLoads.setFirstname(faker.name().firstName());
        userAccountPayLoads.setLastname(faker.name().lastName());
        userAccountPayLoads.setCompany(faker.company().name());
        userAccountPayLoads.setAddress1(faker.address().streetAddress());
        userAccountPayLoads.setAddress2(faker.address().secondaryAddress());
        userAccountPayLoads.setCountry(faker.country().name());
        userAccountPayLoads.setZipcode(faker.address().zipCode());
        userAccountPayLoads.setState(faker.address().state());
        userAccountPayLoads.setCity(faker.address().city());
        userAccountPayLoads.setMobile_number(faker.phoneNumber().cellPhone());
    }

    /**
     * Test for creating a new user account.
     */
    @Test(priority = 0)
    public void createUserAccount() {
        Response response = UserAccountEndPoints.createUserAccount(
            userAccountPayLoads.getName(),
            userAccountPayLoads.getEmail(),
            userAccountPayLoads.getPassword(),
            userAccountPayLoads.getTitle(),
            userAccountPayLoads.getBirth_date(),
            userAccountPayLoads.getBirth_month(),
            userAccountPayLoads.getBirth_year(),
            userAccountPayLoads.getFirstname(),
            userAccountPayLoads.getLastname(),
            userAccountPayLoads.getCompany(),
            userAccountPayLoads.getAddress1(),
            userAccountPayLoads.getAddress2(),
            userAccountPayLoads.getCountry(),
            userAccountPayLoads.getZipcode(),
            userAccountPayLoads.getState(),
            userAccountPayLoads.getCity(),
            userAccountPayLoads.getMobile_number()
        );

        response.then().assertThat().statusCode(200);

        String body = response.getBody().asString();
        System.out.println("POST Response: " + body);
        System.out.println("Email used: " + userAccountPayLoads.getEmail());

        //Assert.assertTrue(body.contains("User created"), "Expected response to contain 'User created'");
    }

    /**
     * Test for reading a user account using email.
     */
    @Test(priority = 1)
    public void readUserAccount() {
        Response response = UserAccountEndPoints.readUserAccountByEmail(userAccountPayLoads.getEmail());
        response.then().assertThat().statusCode(200);

        System.out.println("READ Response: " + response.getBody().asString());
        System.out.println("Status Code: " + response.getStatusCode());
    }

    /**
     * Test for updating an existing user account.
     */
    @Test(priority = 2)
    public void updateUserAccount() {
        // Modify some details
        userAccountPayLoads.setName(faker.name().fullName());
        userAccountPayLoads.setPassword(faker.internet().password());
        userAccountPayLoads.setTitle(faker.options().option("Mr", "Mrs", "Miss"));
        userAccountPayLoads.setBirth_date(String.valueOf(faker.number().numberBetween(1, 28)));
        userAccountPayLoads.setBirth_month(String.valueOf(faker.number().numberBetween(1, 12)));
        userAccountPayLoads.setBirth_year(String.valueOf(faker.number().numberBetween(1950, 2005)));
        userAccountPayLoads.setFirstname(faker.name().firstName());
        userAccountPayLoads.setLastname(faker.name().lastName());
        userAccountPayLoads.setCompany(faker.company().name());
        userAccountPayLoads.setAddress1(faker.address().streetAddress());
        userAccountPayLoads.setAddress2(faker.address().secondaryAddress());
        userAccountPayLoads.setCountry(faker.country().name());
        userAccountPayLoads.setZipcode(faker.address().zipCode());
        userAccountPayLoads.setState(faker.address().state());
        userAccountPayLoads.setCity(faker.address().city());
        userAccountPayLoads.setMobile_number(faker.phoneNumber().cellPhone());

        Response response = UserAccountEndPoints.updateUserAccount(
            userAccountPayLoads.getEmail(),
            userAccountPayLoads.getName(),
            userAccountPayLoads.getPassword(),
            userAccountPayLoads.getTitle(),
            userAccountPayLoads.getBirth_date(),
            userAccountPayLoads.getBirth_month(),
            userAccountPayLoads.getBirth_year(),
            userAccountPayLoads.getFirstname(),
            userAccountPayLoads.getLastname(),
            userAccountPayLoads.getCompany(),
            userAccountPayLoads.getAddress1(),
            userAccountPayLoads.getAddress2(),
            userAccountPayLoads.getCountry(),
            userAccountPayLoads.getZipcode(),
            userAccountPayLoads.getState(),
            userAccountPayLoads.getCity(),
            userAccountPayLoads.getMobile_number()
        );

        System.out.println("UPDATE Response: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 after update");
    }

    /**
     * Test for deleting a user account using email and password.
     */
    @Test(priority = 3)
    public void deleteUserAccount() {
        Response response = UserAccountEndPoints.deleteUserAccountByEmail(
            userAccountPayLoads.getEmail(),
            userAccountPayLoads.getPassword()
        );

        String responseBody = response.getBody().asString();
        System.out.println("DELETE Response: " + responseBody);

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for deletion");
        Assert.assertTrue(responseBody.contains("Account deleted"), "Expected confirmation of account deletion");
    }
}
