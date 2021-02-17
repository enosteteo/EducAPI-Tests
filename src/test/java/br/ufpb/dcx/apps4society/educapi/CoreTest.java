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

    public static void setUp(String path) {
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

    static ValidatableResponse postSample(String json, Integer statusCode) {
        return given().header("Content-Type", contentType)
                .body(json)
                .when().post()
                .then().assertThat()
                .statusCode(statusCode);
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
