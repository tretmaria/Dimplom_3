package ru.yandex.praktikum.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ConstructorPage {
    @FindBy(how = How.XPATH, using = ".//h1[text()='Соберите бургер']")
    private SelenideElement createBurgerButton;
    @FindBy(how = How.CSS, using = ".AppHeader_header__link_active__1IkJo")
    private SelenideElement activeConstructorTab;
    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    private SelenideElement bunsTab;
    @FindBy(how = How.XPATH, using = ".//div[2]/ul[1]/a[2]/p")
    private SelenideElement bun;
    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    private SelenideElement saucesTab;
    @FindBy(how = How.XPATH, using = ".//div[2]/ul[2]/a[1]/p")
    private SelenideElement sauce;
    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    private SelenideElement fillingsTab;
    @FindBy(how = How.XPATH, using = ".//div[2]/ul[3]/a[3]/p")
    private SelenideElement filling;
    @FindBy(how = How.CSS, using = ".tab_tab_type_current__2BEPc")
    private SelenideElement activeTab;

    public SelenideElement getCreateBurgerButton() {
        return createBurgerButton;
    }

    public SelenideElement getBun() {
        return bun;
    }

    public SelenideElement getSauce() {
        return sauce;
    }

    public SelenideElement getFilling() {
        return filling;
    }

    public void clickBunsTab() {
        bunsTab.click();
    }

    public boolean checkBunsTab() {
        return activeTab.getText().contentEquals("Булки");
    }

    public void clickSaucesTab() {
        saucesTab.click();
    }

    public boolean checkSaucesTab() {
        return activeTab.getText().contentEquals("Соусы");
    }

    public void clickFillingsTab() {
        fillingsTab.click();
    }

    public boolean checkFillingsTab() {
        return activeTab.getText().contentEquals("Начинки");
    }
}
