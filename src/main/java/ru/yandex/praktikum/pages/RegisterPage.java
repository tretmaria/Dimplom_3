package ru.yandex.praktikum.pages;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class RegisterPage {
    Faker faker = new Faker();
    @FindBy(how = How.XPATH, using = ".//fieldset[1]/div/div/input")
    private SelenideElement nameField;
    @FindBy(how = How.XPATH, using = ".//fieldset[2]/div/div/input")
    private SelenideElement emailField;

    @FindBy(how = How.NAME, using = "Пароль")
    private SelenideElement passwordField;
    private SelenideElement registerButton = $x(".//div/main/div/form/button");
    private SelenideElement signInButton = $(byLinkText(("Войти")));

    @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    private SelenideElement errorMessage;

    public void clickRegisterButton() {
        registerButton.click();
    }

    public void clickSignInButton() {
        signInButton.click();
    }

    public void showErrorMessage() {
        errorMessage.shouldHave((exactText("Некорректный пароль"))).shouldBe(visible);
    }


    public void fillForm(String name, String email, String password) {
        nameField.setValue(name);
        emailField.setValue(email);
        passwordField.setValue(password);
        registerButton.click();
    }

}
