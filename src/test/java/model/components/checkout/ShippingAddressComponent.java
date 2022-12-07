package model.components.checkout;

import model.components.Component;
import model.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ComponentCssSelector(value="#opc-shipping")
public class ShippingAddressComponent extends Component {

    private final static By continueBtnSel = By.cssSelector(".new-address-next-step-button");

    public ShippingAddressComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void clickOnContinueBtn() {
        WebElement continueBtnElem = findElement(continueBtnSel);
        continueBtnElem.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtnElem));
    }
}
