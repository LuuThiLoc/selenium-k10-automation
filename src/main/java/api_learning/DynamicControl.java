package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.WaitForElementEnabled;
import url.Urls;

import java.time.Duration;

public class DynamicControl {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate to the target page
            driver.get(Urls.HEROKU_BASE_URL.concat(Urls.DYNAMIC_CONTROL_SLUG));

            // Define 2 forms locator
            By checkboxFormSel = By.id("checkbox-example");
            By inputFormSel = By.cssSelector("#input-example");

            // Interact with the checkbox form elems
            WebElement checkboxFormElem = driver.findElement(checkboxFormSel);
            WebElement checkboxElem = checkboxFormElem.findElement(By.tagName("input"));

            Thread.sleep(1000);

            // If the checkbox is not selected then select it
            if (!checkboxElem.isSelected()) {
                checkboxElem.click();
            }

            // Interact with the input form elems
            WebElement inputFormElem = driver.findElement(inputFormSel);
            WebElement inputFieldElem = inputFormElem.findElement(By.tagName("input"));
            WebElement inputFieldBtnElem = inputFormElem.findElement(By.tagName("button"));

            // If the text field is not enabled then click on the button
            if (!inputFieldElem.isEnabled()) {
                inputFieldBtnElem.click();
            }

            // Wait until the text field is enabled then send keys
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20L));
            wait.until(new WaitForElementEnabled(By.cssSelector("#input-example input")));

            inputFieldElem.sendKeys("This is a new text!");

            // Debug purpose only
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
