package ru.yandex.praktikum.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ConstructorPage {
    @FindBy(how = How.XPATH, using = ".//h1[text()='Соберите бургер']")
    private SelenideElement createBurgerButton;
    @FindBy(how = How.CSS, using = ".AppHeader_header__link_active__1IkJo")
    private SelenideElement activeConstructorTab;

    @FindBy(how = How.XPATH, using = ".//div/main/section[1]/div[1]/div[1]")

    private SelenideElement bunsTab;
    @FindBy(how = How.XPATH, using = ".//div/main/section[1]/div[1]/div[2]")

    private SelenideElement saucesTab;

    @FindBy(how = How.XPATH, using = ".//div/main/section[1]/div[1]/div[3]")

    private SelenideElement fillingsTab;
    @FindBy(how = How.CSS, using = ".tab_tab_type_current__2BEPc")
    private SelenideElement activeTab;

    public SelenideElement getCreateBurgerButton() {
        return createBurgerButton;
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
