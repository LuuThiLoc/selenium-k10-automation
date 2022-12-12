package tests;

import driver.DriverFactory;
import model.pages.LoginPageMod03;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class LoginMod03Test implements Urls {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate to the target page
            driver.get(HEROKU_BASE_URL.concat(LOGIN_SLUG));

            // Login with creds
            LoginPageMod03 loginPageMod03 = new LoginPageMod03(driver);
            loginPageMod03
                    .inputUsernameElem("Mary")
                    .inputPasswordElem("12345678")
                    .clickOnLoginBtnElem();

            // Debug purpose ONLY
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
