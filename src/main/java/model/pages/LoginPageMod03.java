package model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageMod03 {
    private final WebDriver driver;
    private static final By usernameSel = By.id("username");
    private static final By passwordSel = By.cssSelector("#password");
    private static final By loginBtnSel = By.cssSelector("button[type='submit']");

    public LoginPageMod03(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPageMod03 inputUsernameElem(String username) {
        driver.findElement(usernameSel).sendKeys(username);
        return this;
    }

    public LoginPageMod03 inputPasswordElem(String password) {
        driver.findElement(passwordSel).sendKeys(password);
        return this;
    }

    public LoginPageMod03 clickOnLoginBtnElem() {
        driver.findElement(loginBtnSel).click();
        return this;
    }

}
