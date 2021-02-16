package br.ufpb.dcx.apps4society.educapi;

import br.ufpb.dcx.apps4society.educapi.JSONBuilder.UsersBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.ufpb.dcx.apps4society.educapi.CoreTest.contentType;
import static br.ufpb.dcx.apps4society.educapi.CoreTest.urlAPI;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.is;

public class UsersTest {
    protected String urlTest;

    @BeforeEach
    void setUp() {
        urlTest = urlAPI + "users/";
    }

    @Test
    public void createAnUser() {
        UsersBuilder users = new UsersBuilder();

        String json = users
                .aUser()
                .withValidName()
                .build();

        given().header("Content-Type", contentType)
                .body(json)
                .when().post(urlTest)
                .then().assertThat()
                .statusCode(SC_CREATED)
                .body("id", is(users.id), "name", is(users.name),
                        "email", is(users.email), "password", is(users.password));
    }
}
