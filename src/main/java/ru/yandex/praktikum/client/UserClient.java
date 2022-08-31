package ru.yandex.praktikum.client;

import io.restassured.response.ValidatableResponse;
import io.qameta.allure.Step;
import ru.yandex.praktikum.model.User;
import ru.yandex.praktikum.util.Credentials;

import static io.restassured.RestAssured.given;

public class UserClient extends RestAssuredClient {
    private static final String USER_PATH = "api/auth";

    @Step("Create a user")
    public ValidatableResponse createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(USER_PATH + "/register")
                .then();
    }

    @Step("Login a user")
    public ValidatableResponse loginUser(Credentials credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(USER_PATH + "/login")
                .then();
    }

    @Step("Delete a courier")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(accessToken)
                .when()
                .delete(USER_PATH + "/user/")
                .then();
    }
}
