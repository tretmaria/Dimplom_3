package ru.yandex.praktikum.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPage {
    @FindBy(how = How.XPATH, using = ".//a[text()='Восстановить пароль']")
    private SelenideElement forgotPasswordButton;

    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement loginButton;


    @FindBy(how = How.XPATH, using = ".//fieldset/div/div/input")
    private SelenideElement emailField;
    @FindBy(how = How.XPATH, using = ".//div/main/div/form/button")
    private SelenideElement recoveryButton;

    public void clickForgotPasswordButton() {
        forgotPasswordButton.click();
    }

    public void clickRecoveryButton() {
        recoveryButton.click();
    }

    public void clickLoginButton() {
        loginButton.click();
    }

}
