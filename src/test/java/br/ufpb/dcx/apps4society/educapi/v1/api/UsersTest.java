package br.ufpb.dcx.apps4society.educapi.v1.api;

import br.ufpb.dcx.apps4society.educapi.CoreTest;
import br.ufpb.dcx.apps4society.educapi.JSONBuilder.UsersBuilder;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.ufpb.dcx.apps4society.educapi.CoreTest.*;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class UsersTest {
    protected String urlTest;

    @BeforeEach
    public void setUp() {
        CoreTest.setUp("/users");
    }

    @Test
    public void createAnUser() {
        UsersBuilder users = new UsersBuilder();

        String json = users
                .aUser()
                .withValidName()
                .build();

        postWithJsonAndExpectHttpCode(json, SC_CREATED)
                .body("id", notNullValue(), "name", is(users.name),
                        "email", is(users.email), "password", is(users.password));
    }
}

class UsersAuthTest {
    @BeforeEach
    void setUp() { CoreTest.setUp("");}

    @Test
    public void viewAnUser() {
        UsersBuilder user = new UsersBuilder();
        String loginJson = user.aUser().build();

        String userJson = user.aUser().withValidName().build();
        createAnUser(userJson);

        String token = new CoreTest().getToken(loginJson);

        getWithToken(token)
                .body("id", notNullValue(), "name", is(user.name),
                        "email", is(user.email));
    }

    private ValidatableResponse getWithToken(String token) {
                return RestAssured.given().headers("Authorization", token, "Content-Type", contentType)
                .when().get("/auth/users")
                .then().statusCode(SC_OK).body("e", is("e"));
    }
}
