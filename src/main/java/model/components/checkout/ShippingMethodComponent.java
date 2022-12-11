package model.components.checkout;

import model.components.Component;
import model.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ComponentCssSelector(value="#opc-shipping_method")
public class ShippingMethodComponent extends Component {

    private final static By continueBtnSel = By.cssSelector(".shipping-method-next-step-button");

    public ShippingMethodComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void clickOnContinueBtn(){
        WebElement continueBtnElem = findElement(continueBtnSel);
        continueBtnElem.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtnElem));
    }

    public void selectShippingMethod(String methodValue){
        String xpathSel = "//label[contains(text()," + "\""+ methodValue +"\")]";  //truyền động methodValue
        findElement(By.xpath(xpathSel)).click();
    }


}
