package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui.PageHelper;
import url.Urls;

public class JsExecutor implements Urls {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate to the target page
            driver.get(HEROKU_BASE_URL.concat(FLOATING_MENU_SLUG));
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            PageHelper pageHelper = new PageHelper(jsExecutor); // truyền driver vào

            // Scroll to bottom
            pageHelper.scrollToBottom();

            // Debug purpose ONLY
            Thread.sleep(2000);

            // Scroll to top
            pageHelper.scrollToTop();

            // Debug purpose ONLY
            Thread.sleep(3000);

            // Navigate to the Dynamic Control page
            driver.get(Urls.HEROKU_BASE_URL.concat(Urls.DYNAMIC_CONTROL_SLUG));

            // Highlight border of Elem
            By checkboxFormSel = By.id("checkbox-example");
            WebElement checkboxFormElem = driver.findElement(checkboxFormSel);
            pageHelper.changeElemStyleToFocusMode(checkboxFormElem);

            // Debug purpose ONLY
            Thread.sleep(2000);

            // Remove an Element
            By inputFormSel = By.cssSelector("#input-example");
            WebElement inputFormElem = driver.findElement(inputFormSel);
            pageHelper.removeElement(inputFormElem);

            // Debug purpose ONLY
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
