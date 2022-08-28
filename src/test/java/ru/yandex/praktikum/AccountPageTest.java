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
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class AccountPageTest {
    //User user;
    UserClient userClient;
    String accessToken;
    private final static String URL = "https://stellarburgers.nomoreparties.site/";

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        //Configuration.browserSize = "1920x1080";
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
        User user = User.getRandomUserCredentials(10);
        //UserClient userClient;
        userClient = new UserClient();
        userClient.createUser(user);
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
        User user = User.getRandomUserCredentials(10);
        //UserClient userClient;
        userClient = new UserClient();
        userClient.createUser(user);
        userClient.loginUser(Credentials.from(user));
        MainPage mainPage = open(URL, MainPage.class);
        mainPage.clickSignInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillForm(user.getEmail(), user.getPassword());
        HomePage homePage = page(HomePage.class);
        homePage.clickAccountButton();
        AccountPage accountPage = page(AccountPage.class);
        accountPage.clickLogo();
        $(byXpath(".//div/header/nav/a/p")).shouldHave(text("Личный кабинет"));
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Выход из аккаунта")
    public void shouldExitFromAccountTest() {
        User user = User.getRandomUserCredentials(10);
        //UserClient userClient;
        userClient = new UserClient();
        userClient.createUser(user);
        userClient.loginUser(Credentials.from(user));
        MainPage mainPage = open(URL, MainPage.class);
        mainPage.clickSignInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillForm(user.getEmail(), user.getPassword());
        HomePage homePage = page(HomePage.class);
        homePage.clickAccountButton();
        AccountPage accountPage = page(AccountPage.class);
        accountPage.clickExitButton();
        LoginPage loginPage1 = page(LoginPage.class);
        loginPage1.getPageHeader().shouldHave(text("Вход"));
    }

}
