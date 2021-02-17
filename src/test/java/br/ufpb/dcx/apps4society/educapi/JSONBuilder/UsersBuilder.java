package br.ufpb.dcx.apps4society.educapi.JSONBuilder;

import org.json.JSONObject;

import java.util.Random;

public class UsersBuilder {
    public String name = "TestName" + new Random().nextInt(100);
    public String email = generateEmail();
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

    public UsersBuilder withUncreatedEmail() {
        user.put("email", generateEmail());
        return this;
    };

    public UsersBuilder withUnicEmail() {
        user.put("email", "createdUser@mail.com");
        return this;
    }

    public String build() {
        return user.toString();
    }

    public String generateEmail() {
        String pt1 = "pt1" + new Random().nextInt(999);
        String pt2 = "m2" + new Random().nextInt(99);
        String pt3 = "a3d" + new Random().nextInt(9999);
        String mail = pt1 + pt2 + pt3 + "@mail.com";
        return mail;
    }
}
