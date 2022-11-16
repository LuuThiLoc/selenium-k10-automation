package model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageMod02 {
    private final WebDriver driver;
    private static final By usernameSel = By.id("username");
    private static final By passwordSel = By.cssSelector("#password");
    private static final By loginBtnSel = By.cssSelector("button[type='submit']");

    public LoginPageMod02(WebDriver driver) {
        this.driver = driver;
    }

    public void inputUsernameElem(String username) {
        driver.findElement(usernameSel).sendKeys(username);
    }

    public void inputPasswordElem(String password) {
        driver.findElement(passwordSel).sendKeys(password);
    }

    public void clickOnLoginBtnElem() {
        driver.findElement(loginBtnSel).click();
    }

}
