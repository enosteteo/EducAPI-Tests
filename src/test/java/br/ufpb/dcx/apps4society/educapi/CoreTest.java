package br.ufpb.dcx.apps4society.educapi;

import br.ufpb.dcx.apps4society.educapi.JSONBuilder.UsersBuilder;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

public class CoreTest {
    protected static String token;
    protected static String urlBase = "http://localhost:8080/";
    protected static String contentType = "application/json";
    protected static String urlAPI = urlBase + "v1/api/";

    public static void setUp(Object path) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        baseURI = "http://localhost:8080";
        port = 8080;
        basePath = "/v1/api" + path;
    }

    public static void login(){
        String validUser = new UsersBuilder().aUser().build();
        token = given().header("Content-Type", contentType).body(validUser)
                .when().post()
                .then().assertThat()
                .statusCode(SC_OK).body("token", notNullValue()).extract().path("token");
    }

    static ValidatableResponse post(String json, Integer statusCode) {
        return given().header("Content-Type", contentType)
                .body(json)
                .when().post()
                .then().assertThat()
                .statusCode(statusCode);
    }


}
