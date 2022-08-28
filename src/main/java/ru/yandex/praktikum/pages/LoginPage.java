package ru.yandex.praktikum.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    @FindBy(how = How.XPATH, using = ".//div/main/div/h2")
    private SelenideElement loginPageHeader;
    @FindBy(how = How.XPATH, using = "//div/main/div/form/fieldset[1]/div/div/input")
    private SelenideElement emailField;
    @FindBy(how = How.NAME, using = "Пароль")
    private SelenideElement passwordField;

    private SelenideElement signInButton = $x(".//button[text()='Войти']");
    @FindBy(how = How.CSS, using = ".Auth_link__1fOlj")

    private SelenideElement registerButton;
    @FindBy(how = How.XPATH, using = ".//a[text()='Восстановить пароль']")
    private SelenideElement forgotPasswordButton;

    public void setEmail(String email) {
        emailField.setValue(email);
    }

    public void setPassword(String password) {
        passwordField.setValue(password);
    }

    public void clickRegisterButton() {
        registerButton.click();
    }

    public void clickForgotPasswordButton() {
        forgotPasswordButton.click();
    }

    public void clickSignInButton() {
        signInButton.click();
    }

    public SelenideElement getPageHeader() {
        return loginPageHeader;
    }

    public HomePage fillForm(String email, String password) {
        emailField.setValue(email);
        passwordField.setValue(password);
        signInButton.click();
        return new HomePage();
    }
}
