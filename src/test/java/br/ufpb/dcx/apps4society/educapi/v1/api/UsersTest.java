package br.ufpb.dcx.apps4society.educapi.v1.api;

import br.ufpb.dcx.apps4society.educapi.CoreTest;
import br.ufpb.dcx.apps4society.educapi.JSONBuilder.UsersBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.ufpb.dcx.apps4society.educapi.CoreTest.*;
import static org.apache.http.HttpStatus.SC_CREATED;
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

        postSample(json, SC_CREATED)
                .body("id", notNullValue(), "name", is(users.name),
                        "email", is(users.email), "password", is(users.password));
    }
}
