package ru.yandex.praktikum;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.client.UserClient;
import ru.yandex.praktikum.model.User;
import ru.yandex.praktikum.pages.HomePage;
import ru.yandex.praktikum.pages.LoginPage;
import ru.yandex.praktikum.pages.RegisterPage;
import ru.yandex.praktikum.util.Credentials;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class RegisterPageTest {
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
    }

    @After
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Успешно зарегистрироваться")
    @Description("Успешно зарегистрироваться")
    public void shouldRegisterSuccessfullyTest() {
        String registerPageUrl = URL + "register";
        user = User.getRandomUserCredentials(10);
        RegisterPage registerPage = open(registerPageUrl, RegisterPage.class);
        registerPage.fillForm(user.getName(), user.getEmail(), user.getPassword());
        LoginPage loginPage = page(LoginPage.class);
        accessToken = userClient.loginUser(Credentials.from(user)).extract().path("accessToken");
        loginPage.fillForm(user.getEmail(), user.getPassword());
        HomePage homePage = page(HomePage.class);
        homePage.showOrderButton();
        userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля")
    @Description("Ошибка для некорректного пароля. Минимальный пароль — шесть символов")
    public void shouldShowErrorIfPasswordIsLessThanSixSymbolsTest() {
        String registerPageUrl = URL + "register";
        user = User.getRandomUserCredentials(5);
        accessToken = userClient.loginUser(Credentials.from(user)).extract().path("accessToken");
        RegisterPage registerPage = open(registerPageUrl, RegisterPage.class);
        registerPage.fillForm(user.getName(), user.getEmail(), user.getPassword());
        registerPage.showErrorMessage();
    }
}
