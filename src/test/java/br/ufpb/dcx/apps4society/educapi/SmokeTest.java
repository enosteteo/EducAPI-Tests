package br.ufpb.dcx.apps4society.educapi;

import org.junit.jupiter.api.Test;

import static br.ufpb.dcx.apps4society.educapi.CoreTest.urlBase;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class SmokeTest {

    @Test
    public void WelcomeToAPI(){
        given().when().get(urlBase)
                .then().statusCode(200)
                .body(is("Welcome to EducAPI! | VERSION: v1.0.4"));
    }
}
