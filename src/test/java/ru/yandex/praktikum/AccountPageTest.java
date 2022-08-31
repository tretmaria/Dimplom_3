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
import static com.codeborne.selenide.Selenide.*;

public class AccountPageTest {
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
    @DisplayName("Переход из личного кабинета в конструктор")
    @Description("Переход из личного кабинета в конструктор")
    public void shouldEnterConstructorViaAccountTest() {
        accessToken = userClient.createUser(user).extract().path("accessToken");
        userClient.loginUser(Credentials.from(user));
        MainPage mainPage = open(URL, MainPage.class);
        mainPage.clickSignInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillForm(user.getEmail(), user.getPassword());
        HomePage homePage = page(HomePage.class);
        homePage.clickAccountButton();
        AccountPage accountPage = page(AccountPage.class);
        accountPage.clickConstructorButton();
        ConstructorPage constructorPage = page(ConstructorPage.class);
        constructorPage.getCreateBurgerButton().shouldHave(text("Соберите бургер"));
    }

    @Test
    @DisplayName("Переход из личного кабинета на логотип Stellar Burgers")
    @Description("Переход из личного кабинета на логотип Stellar Burgers")
    public void shouldEnterConstructorViaLogoTest() {
        accessToken = userClient.createUser(user).extract().path("accessToken");
        userClient.loginUser(Credentials.from(user));
        MainPage mainPage = open(URL, MainPage.class);
        mainPage.clickSignInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillForm(user.getEmail(), user.getPassword());
        HomePage homePage = page(HomePage.class);
        homePage.clickAccountButton();
        AccountPage accountPage = page(AccountPage.class);
        accountPage.clickLogo();
        homePage.getAccountHeader().shouldHave(text("Личный кабинет"));
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Выход из аккаунта")
    public void shouldExitFromAccountTest() {
        accessToken = userClient.createUser(user).extract().path("accessToken");
        userClient.loginUser(Credentials.from(user));
        MainPage mainPage = open(URL, MainPage.class);
        mainPage.clickSignInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillForm(user.getEmail(), user.getPassword());
        HomePage homePage = page(HomePage.class);
        homePage.clickAccountButton();
        AccountPage accountPage = page(AccountPage.class);
        accountPage.clickExitButton();
        loginPage.getPageHeader().shouldHave(text("Вход"));
    }

}
