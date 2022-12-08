package model.components.checkout;

import model.components.Component;
import model.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ComponentCssSelector(value="#opc-confirm_order")
public class ConfirmOrderComponent extends Component {

    private static final By continueBtnSel = By.cssSelector("input[onclick='ConfirmOrder.save()']");
    public ConfirmOrderComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void clickOnContinueBtn(){
        WebElement continueBtnElem = findElement(continueBtnSel);
        scrollDownToElement(continueBtnElem);
        continueBtnElem.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtnElem));
    }
}
