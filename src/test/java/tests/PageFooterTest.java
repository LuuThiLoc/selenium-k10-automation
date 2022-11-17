package tests;

import driver.DriverFactory;
import model.pages.HerokuLoginPage;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class PageFooterTest implements Urls {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate to the target page
            driver.get(HEROKU_BASE_URL.concat(LOGIN_SLUG));

            // Login with creds
            HerokuLoginPage loginPage = new HerokuLoginPage(driver);
//            System.out.println(loginPage.pageFooter().getLinkText());

            // Debug purpose ONLY
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
