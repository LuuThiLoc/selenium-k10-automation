package model.components.global;

import model.components.Component;
import model.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ComponentCssSelector(value = "#bar-notification")
public class BarNotificationComponent extends Component { //notification: driver manage rieng, global

    public BarNotificationComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    private static final By closeBtnSel = By.cssSelector("span[class=close]");
    private static final By contentSel = By.tagName("p");

    public void closeBtnBarNotification() {
        WebElement closeBtnElem = findElement(closeBtnSel);
        closeBtnElem.click();
        wait.until(ExpectedConditions.invisibilityOf(this.component));
    }

    public void notificationContent() {
        WebElement contentElem = findElement(contentSel);
        String contentText = contentElem.getText().trim();
    }

    public void waitUntilItemAddedToCart(){
        String notificationMsg = "The product has been added to your shopping cart";
        wait.until(ExpectedConditions.textToBePresentInElementLocated(contentSel, notificationMsg ));
    }
}
