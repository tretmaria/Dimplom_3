package ru.yandex.praktikum.client;

import io.restassured.response.ValidatableResponse;
import io.qameta.allure.Step;
import ru.yandex.praktikum.model.User;
import ru.yandex.praktikum.util.Credentials;

import static io.restassured.RestAssured.given;

public class UserClient extends RestAssuredClient {
    private static final String USER_PATH = "api/auth";

    @Step("Create a user")
    public ValidatableResponse create(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(USER_PATH + "/register")
                .then();
    }

    @Step("Login a user")
    public ValidatableResponse login(Credentials credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(USER_PATH + "/login")
                .then();
    }

    @Step("Delete a courier")
    public ValidatableResponse delete(String accessToken) {
        return given()
                .spec(getBaseSpec())
                .header("Authorization", accessToken)
                .when()
                .delete(USER_PATH + "/user/")
                .then();
    }
}
