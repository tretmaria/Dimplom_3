package ru.yandex.praktikum;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import ru.yandex.praktikum.client.UserClient;
import ru.yandex.praktikum.model.User;
import ru.yandex.praktikum.pages.*;
import ru.yandex.praktikum.util.Credentials;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class LoginPageTest {
    private UserClient userClient;
    private User user;
    private String accessToken;
    private final static String URL = "https://stellarburgers.nomoreparties.site/";

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        //Configuration.browserSize = "1920x1080";
        userClient = new UserClient();
        user = User.getRandomUserCredentials(10);

    }

    @After
    public void tearDown() {
        userClient.deleteUser(accessToken);
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Войти через кнопку в форме регистрации")
    @Description("Войти через кнопку в форме регистрации")
    public void shouldLoginViaRegisterButtonTest() {
        String registerPageUrl = URL + "register";
        RegisterPage registerPage = open(registerPageUrl, RegisterPage.class);
        registerPage.clickSignInButton();
        LoginPage loginPage = page(LoginPage.class);
        accessToken = userClient.createUser(user).extract().path("accessToken");
        userClient.loginUser(Credentials.from(user));
        loginPage.fillForm(user.getEmail(), user.getPassword());
        HomePage homePage = page(HomePage.class);
        String buttonText = String.valueOf(homePage.getOrderButton().shouldHave(text("Оформить заказ")));
        String textToCheck = "Оформить заказ";
        assertThat(buttonText, containsString(textToCheck));
    }

    @Test
    @DisplayName("Войти через кнопку в форме восстановления пароля")
    @Description("Войти через кнопку в форме восстановления пароля")
    public void shouldLoginViaForgotPasswordFormTest() {
        String forgotPasswordUrl = URL + "forgot-password";
        accessToken = userClient.createUser(user).extract().path("accessToken");
        userClient.loginUser(Credentials.from(user));
        ForgotPasswordPage forgotPasswordPage = open(forgotPasswordUrl, ForgotPasswordPage.class);
        forgotPasswordPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillForm(user.getEmail(), user.getPassword());
        HomePage homePage = page(HomePage.class);
        String buttonText = String.valueOf(homePage.getOrderButton().shouldHave(text("Оформить заказ")));
        String textToCheck = "Оформить заказ";
        assertThat(buttonText, containsString(textToCheck));
    }

}
