package ru.yandex.praktikum.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;

public class AccountPage {
    @FindBy(how = How.XPATH, using = ".//p[text()='Конструктор']")
    private SelenideElement constructorButton;
    @FindBy(how = How.XPATH, using = ".//div[@class='AppHeader_header__logo__2D0X2']")
    private SelenideElement logoButton;
    @FindBy(how = How.XPATH, using = ".//a[text()='Профиль']")
    private SelenideElement profileTab;
    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement exitButton;
    @FindBy(how = How.XPATH, using = ".//p[text()='Личный кабинет']")
    private SelenideElement accountHeader;

    public SelenideElement getExitButton() {
        return exitButton;
    }

    public SelenideElement getAccountHeader() {
        return accountHeader;
    }

    public void clickExitButton() {
        exitButton.click();
    }

    public void clickConstructorButton() {
        constructorButton.click();
    }

    public void clickLogo() {
        logoButton.click();
    }

    public void checkProfileTab() {
        profileTab.shouldHave((exactText("Профиль"))).shouldBe(visible);
    }
}
