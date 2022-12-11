package model.components.checkout;

import model.components.Component;
import model.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ComponentCssSelector(value = "#opc-payment_method")
public class PaymentMethodComponent extends Component {

    private static final By CODSel = By.cssSelector("input[value='Payments.CashOnDelivery']");
    private static final By checkMoneyOrderSel = By.cssSelector("input[value='Payments.CheckMoneyOrder']");
    private static final By creditCardSel = By.cssSelector("input[value='Payments.Manual']");
    private static final By purchaseOrderSel = By.cssSelector("input[value='Payments.PurchaseOrder']");
    private static final By continueBtnSel = By.cssSelector("input[onclick='PaymentMethod.save()']");

    public PaymentMethodComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectCODMethod() {
        findElement(CODSel).click();
    }

    public void selectCheckMoneyOrderMethod() {
        findElement(checkMoneyOrderSel).click();
    }

    public void selectCreditCardMethod() {
        findElement(creditCardSel).click();
    }

    public void selectPurchaseOrderMethod() {
        findElement(purchaseOrderSel).click();
    }

    public void clickOnContinueBtnMethod() {
        WebElement continueBtnElem = findElement(continueBtnSel);
        continueBtnElem.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtnElem));
    }
}
