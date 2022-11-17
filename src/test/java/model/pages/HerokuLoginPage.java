package model.pages;

import model.components.LoginFormComponent;
import org.openqa.selenium.WebDriver;

public class HerokuLoginPage extends BasePage {

    public HerokuLoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginFormComponent loginFormComp(WebDriver driver){
        return findComponent(LoginFormComponent.class, driver);
    }
}
