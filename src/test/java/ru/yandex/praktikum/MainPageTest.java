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
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.util.Credentials;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainPageTest {
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
        user = User.getRandomUserCredentials(10);
        userClient = new UserClient();
    }
    @After
    public void tearDown(){
        userClient.delete(accessToken);
        Selenide.closeWebDriver();
    }
    @Test
    @DisplayName("Войти по кнопке «Войти в аккаунт» на главной")
    @Description("Войти по кнопке «Войти в аккаунт» на главной")
    public void shouldLoginViaMainPageTest(){
        userClient.create(user);
        accessToken = userClient.login(Credentials.from(user)).extract().path("accessToken");
        MainPage mainPage = open(URL, MainPage.class);
        mainPage.clickSignInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillForm(user.getEmail(),user.getPassword());
        HomePage homePage = page(HomePage.class);
        String buttonText = String.valueOf(homePage.getOrderButton().shouldHave(text("Оформить заказ")));
        String textToCheck = "Оформить заказ";
        assertThat(buttonText, containsString(textToCheck));
    }
    @Test
    @DisplayName("Войти через кнопку «Личный кабинет»")
    @Description("Войти через кнопку «Личный кабинет»")
    public void shouldLoginViaAccountPageTest(){
        userClient.create(user);
        accessToken = userClient.login(Credentials.from(user)).extract().path("accessToken");
        MainPage mainPage = open(URL, MainPage.class);
        mainPage.clickAccountButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillForm(user.getEmail(),user.getPassword());
        HomePage homePage = page(HomePage.class);
        String buttonText = String.valueOf(homePage.getOrderButton().shouldHave(text("Оформить заказ")));
        String textToCheck = "Оформить заказ";
        assertThat(buttonText, containsString(textToCheck));
    }
}
