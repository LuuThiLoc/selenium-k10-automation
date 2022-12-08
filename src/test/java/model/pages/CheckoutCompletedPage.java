package model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletedPage extends BasePage {
    private static final By continueBtnSel = By.cssSelector(".order-completed-continue-button");

    public CheckoutCompletedPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnContinueBtn() {
        WebElement continueBtnElem = findElement(continueBtnSel);
        continueBtnElem.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtnElem));
    }
}
