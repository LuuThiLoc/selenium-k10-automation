package test;

import driver.DriverFactory;
import model.pages.LoginPageMod02;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class LoginMod02Test implements Urls {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate to the target page
            driver.get(HEROKU_BASE_URL.concat(LOGIN_SLUG));

            // Login with creds
            LoginPageMod02 loginPageMod02 = new LoginPageMod02(driver);
            loginPageMod02.inputUsernameElem("Mary");
            loginPageMod02.inputPasswordElem("12345678");
            loginPageMod02.clickOnLoginBtnElem();

            // Debug purpose ONLY
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
