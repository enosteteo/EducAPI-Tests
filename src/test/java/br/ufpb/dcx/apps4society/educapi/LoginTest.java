package br.ufpb.dcx.apps4society.educapi;

import br.ufpb.dcx.apps4society.educapi.JSONBuilder.UsersBuilder;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.ufpb.dcx.apps4society.educapi.CoreTest.contentType;
import static io.restassured.RestAssured.*;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.Matchers.notNullValue;

public class LoginTest {
    protected String urlTest;

    @BeforeEach
    void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        baseURI = "http://localhost:8080";
        basePath = "/v1/api";
        urlTest = "/auth/login";
    }

    private ValidatableResponse login(String json, Integer statusCode) {
        return given().header("Content-Type", contentType)
                .body(json)
                .when().post(urlTest)
                .then().assertThat()
                .statusCode(statusCode);
    }

    @Test
    public void LoginWithCreatedUser() {
        String json = new UsersBuilder().aUser().build();
        login(json, SC_OK).body("token", notNullValue());
    }

    @Test
    public void LoginWithUncreatedUser() {
        String json = new UsersBuilder().aUser().withUncreatedEmail().build();
        login(json, SC_UNAUTHORIZED);
    }
}
