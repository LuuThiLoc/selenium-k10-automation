package model.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = "#login")
public class LoginFormComponent extends Component{
    private static final By usernameSel = By.id("username");
    private static final By passwordSel = By.cssSelector("#password");
    private static final By loginBtnSel = By.cssSelector("button[type='submit']");

    public LoginFormComponent(WebDriver driver, WebElement component) {
        super(driver, component);
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
