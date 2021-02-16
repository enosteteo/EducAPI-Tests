package br.ufpb.dcx.apps4society.educapi.JSONBuilder;

import org.json.JSONObject;

import java.util.Random;

public class UsersBuilder {
    public String name = "TestName" + new Random().nextInt(100);
    public String email = "mail@test.com";
    public String password = "vpassword";
    public Integer id = 2;
    private final JSONObject user = new JSONObject();

    public UsersBuilder aUser() {
        user.put("email", email);
        user.put("password", password);
        return this;

    }

    public UsersBuilder withValidName() {
        user.put("name", name);
        return this;
    }

    public UsersBuilder withValidId() {
        user.put("id", id);
        return this;
    }

    public String build() {
        return user.toString();
    }
}
