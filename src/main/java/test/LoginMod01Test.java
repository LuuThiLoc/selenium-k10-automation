package test;

import driver.DriverFactory;
import model.pages.LoginPageMod01;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class LoginMod01Test implements Urls {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate to the target page
            driver.get(HEROKU_BASE_URL.concat(LOGIN_SLUG));

            // Login with creds
            LoginPageMod01 loginPageMod01 = new LoginPageMod01(driver);
            loginPageMod01.usernameElem().sendKeys("Mary");
            loginPageMod01.passwordElem().sendKeys("12345678");
            loginPageMod01.loginBtnElem().click();

            // Debug purpose ONLY
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
