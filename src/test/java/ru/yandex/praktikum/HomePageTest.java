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
import ru.yandex.praktikum.pages.AccountPage;
import ru.yandex.praktikum.pages.HomePage;
import ru.yandex.praktikum.pages.LoginPage;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.util.Credentials;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class HomePageTest {
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
    public void tearDown() {

        userClient.deleteUser(accessToken);
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Переход в личный кабинет после входа")
    @Description("Переход в личный кабинет после входа")
    public void shouldEnterAccountTest() {
        accessToken = userClient.createUser(user).extract().path("accessToken");
        userClient.loginUser(Credentials.from(user));
        MainPage mainPage = open(URL, MainPage.class);
        mainPage.clickSignInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillForm(user.getEmail(), user.getPassword());
        HomePage homePage = page(HomePage.class);
        homePage.clickAccountButton();
        AccountPage accountPage = page(AccountPage.class);
        String buttonText = String.valueOf(accountPage.getExitButton().shouldHave(text("Выход")));
        String textToCheck = "Выход";
        assertThat(buttonText, containsString(textToCheck));
    }
}
