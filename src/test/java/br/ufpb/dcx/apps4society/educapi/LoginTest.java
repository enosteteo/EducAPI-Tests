package br.ufpb.dcx.apps4society.educapi;

import br.ufpb.dcx.apps4society.educapi.JSONBuilder.UsersBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.ufpb.dcx.apps4society.educapi.CoreTest.postSample;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.Matchers.notNullValue;

public class LoginTest {
    @BeforeEach
    public void setUp() {
        CoreTest.setUp("/auth/login");
    }

    @Test
    public void loginWithCreatedUser() {
        String json = new UsersBuilder()
                .aUser().build();
        postSample(json, SC_OK).body("token", notNullValue());
    }

    @Test
    public void loginWithUncreatedUser() {
        String json = new UsersBuilder()
                .aUser()
                .withUncreatedEmail().build();
        postSample(json, SC_UNAUTHORIZED);
    }
}
