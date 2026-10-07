package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

/**
 * API automation using RestAssured against the public reqres.in test API.
 * Demonstrates: GIVEN/WHEN/THEN style, status codes, JSON path extraction,
 * request/response specifications, and CRUD (GET/POST/PUT/DELETE).
 */
public class UserApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void getSingleUser_shouldReturn200AndCorrectId() {
        given()
            .pathParam("id", 2)
        .when()
            .get("/users/{id}")
        .then()
            .statusCode(200)
            .body("data.id", equalTo(2))
            .body("data.email", containsString("@"));
    }

    @Test
    public void getUserList_shouldReturnNonEmptyArray() {
        given()
        .when()
            .get("/users?page=2")
        .then()
            .statusCode(200)
            .body("data.size()", greaterThan(0));
    }

    @Test
    public void createUser_shouldReturn201WithGeneratedId() {
        String requestBody = "{ \"name\": \"Shivam\", \"job\": \"QA Engineer\" }";

        Response response =
            given()
                .contentType(ContentType.JSON)
                .body(requestBody)
            .when()
                .post("/users")
            .then()
                .statusCode(201)
                .body("name", equalTo("Shivam"))
                .body("job", equalTo("QA Engineer"))
                .extract().response();

        String generatedId = response.jsonPath().getString("id");
        assertEquals(generatedId != null, true, "Expected an 'id' to be generated");
    }

    @Test
    public void updateUser_shouldReturn200() {
        String requestBody = "{ \"name\": \"Shivam\", \"job\": \"Senior QA Engineer\" }";

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .put("/users/2")
        .then()
            .statusCode(200)
            .body("job", equalTo("Senior QA Engineer"));
    }

    @Test
    public void deleteUser_shouldReturn204() {
        given()
        .when()
            .delete("/users/2")
        .then()
            .statusCode(204);
    }
}
