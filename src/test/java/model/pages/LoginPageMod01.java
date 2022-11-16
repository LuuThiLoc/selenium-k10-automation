package model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageMod01 {
    private final WebDriver driver;
    private static final By usernameSel = By.id("username");
    private static final By passwordSel = By.cssSelector("#password");
    private static final By loginBtnSel = By.cssSelector("button[type='submit']");

    public LoginPageMod01(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement usernameElem() {
        return driver.findElement(usernameSel);
    }

    public WebElement passwordElem() {
        return driver.findElement(passwordSel);
    }

    public WebElement loginBtnElem() {
        return driver.findElement(loginBtnSel);
    }

}
