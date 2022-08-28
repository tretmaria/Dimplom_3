package ru.yandex.praktikum.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage {
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement signInButton;
    @FindBy(how = How.XPATH, using = ".//p[text()='Личный Кабинет']")
    private SelenideElement accountButton;
    @FindBy(how = How.XPATH, using = ".//p[text()='Конструктор']")
    private SelenideElement constructorButton;
    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    private SelenideElement bunsTab;
    @FindBy(how = How.CSS, using = ".tab_tab_type_current__2BEPc")
    private SelenideElement currentTab;
    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement orderButton;

    public SelenideElement getOrderButton() {
        return orderButton;
    }

    public void clickSignInButton() {
        signInButton.click();
    }

    public void clickAccountButton() {
        accountButton.click();
    }

    public void clickConstructorButton() {
        constructorButton.click();
    }

    public boolean checkBunsTab() {
        return currentTab.getText().contentEquals("Булки");
    }
}
