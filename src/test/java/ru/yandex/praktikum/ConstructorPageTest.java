package ru.yandex.praktikum;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import ru.yandex.praktikum.pages.ConstructorPage;
import ru.yandex.praktikum.pages.MainPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class ConstructorPageTest {
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
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Переход к разделу 'Булки'")
    public void shouldBeBunsTest() {
        MainPage mainPage = open(URL, MainPage.class);
        mainPage.clickConstructorButton();
        ConstructorPage constructorPage = page(ConstructorPage.class);
        constructorPage.checkBunsTab();
        $(byXpath(".//div[2]/ul[1]/a[2]/p")).shouldHave(text("Краторная булка"));
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Переход к разделу 'Соусы'")
    public void shouldBeSaucesTest() {
        MainPage mainPage = open(URL, MainPage.class);
        mainPage.clickConstructorButton();
        ConstructorPage constructorPage = page(ConstructorPage.class);
        constructorPage.clickSaucesTab();
        constructorPage.checkSaucesTab();
        $(byXpath(".//div[2]/ul[2]/a[1]/p")).shouldHave(text("Соус Spicy-X"));
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Переход к разделу 'Начинки'")
    public void shouldBeFillingsTest() {
        MainPage mainPage = open(URL, MainPage.class);
        mainPage.clickConstructorButton();
        ConstructorPage constructorPage = page(ConstructorPage.class);
        constructorPage.clickFillingsTab();
        constructorPage.checkFillingsTab();
        $(byXpath(".//div[2]/ul[3]/a[3]/p")).shouldHave(text("Биокотлета из марсианской Магнолии"));
    }
}
