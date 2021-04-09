package br.ufpb.dcx.apps4society.educapi;

import br.ufpb.dcx.apps4society.educapi.JSONBuilder.UsersBuilder;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.*;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;

public class CoreTest {
    protected static String token;

    public static String urlBase = "http://localhost:8080/";
    public static String contentType = "application/json";
    public static String urlAPI = urlBase + "v1/api/";

    public static void setUp(String path) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        baseURI = "http://localhost:8080";
        port = 8080;
        basePath = "/v1/api" + path;
    }

    public static void login(String json) {
        token = given().header("Content-Type", contentType).body(json)
                .when().post("auth/login")
                .then().assertThat()
                .statusCode(SC_OK).body("token", notNullValue()).extract().path("token");
    }
    public String getToken(String user) {
        login(user);
        return token;
    }

    public static void createAnUser(String userJson) {
        int code = postWithJsonAndUri(userJson, "users/")
                .extract().statusCode();
        userIsCreated(code);
    }

    public static ValidatableResponse postWithJsonAndExpectHttpCode(String json, Integer statusCode) {
        return given().header("Content-Type", contentType)
                .body(json)
                .when().post()
                .then().assertThat()
                .statusCode(statusCode);
    }

    public static ValidatableResponse postWithJsonAndUri(String json, String uriRequest) {
        return given().header("Content-Type", contentType)
                .body(json)
                .when().post(uriRequest)
                .then().assertThat();
    }

    public static void createUserWithUnicEmail() {
        String json = new UsersBuilder()
                .aUser()
                .withUnicEmail()
                .withValidName()
                .build();

        String usersUri = urlAPI + "users/";
        int code = postWithJsonAndUri(json, usersUri)
                .extract().statusCode();
        userIsCreated(code);
    }

    private static void userIsCreated(int code) {
        boolean created = false;
        if(code == SC_CREATED) {
            created = true;
        } else if (code == SC_NO_CONTENT) {
            created = true;
        }
        Assertions.assertTrue(created);
    }


//
//
//    protected static JSONObject postJson;
//    protected static JSONObject pathJson;
//
//    public static void testGet(int getStatusCode, String itemPath,String item){
//        given().headers("Authorization", token, "Content-Type", contentType)
//                .when().get(urlBase)
//                .then().statusCode(getStatusCode).body(itemPath,hasItem(item));
//    }
//
//    public static void testPost(int postStatusCode, JSONObject postJson){
//        given().headers("Authorization", token, "Content-Type", contentType)
//                .body(postJson).post(urlBase)
//                .then()
//                .assertThat().statusCode(postStatusCode);
//    }
//
//    public static void testPath(int pathStatusCode, JSONObject pathJson, String pathIndex){
//
//        given()
//                .headers("Authorization", token,"Content-Type", contentType)
//                .body(pathJson)
//                .patch(urlBase + pathIndex)
//                .then()
//                .assertThat()
//                .statusCode(pathStatusCode);
//    }
//
//    public static void testDelete(int deleteStatusCode,String deletePath){
//
//        given().headers("Authorization", token, "Content-Type", contentType)
//                .delete(urlBase + deletePath)
//                .then().assertThat().statusCode(deleteStatusCode);
//    }
}
