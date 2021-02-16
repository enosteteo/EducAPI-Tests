package br.ufpb.dcx.apps4society.educapi;

import org.junit.jupiter.api.Test;

import static br.ufpb.dcx.apps4society.educapi.CoreTest.urlBase;
import static io.restassured.RestAssured.when;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.is;

public class SmokeTest {

    @Test
    public void WelcomeToAPI() {
        when()
                .get(urlBase)
                .then()
                .statusCode(SC_OK)
                .body(is("Welcome to EducAPI! | VERSION: v1.0.4"));
    }
}
