package model.components.cart;

import model.components.Component;
import model.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ComponentCssSelector(value = ".cart-footer .totals")
public class TotalComponent extends Component {

    private static final By priceTableRowSel = By.cssSelector("table tr");
    private static final By priceTypeSel = By.cssSelector(".cart-total-left");
    private static final By priceValueSel = By.cssSelector(".cart-total-right");
    private static final By tosSel = By.cssSelector("#termsofservice");
    private static final By checkoutBtnSel = By.cssSelector("#checkout");
    public TotalComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public Map<String, Double> priceCategories(){
        Map<String, Double> priceCategories = new HashMap<>();
        List<WebElement> priceTableRowElemList = findElements(priceTableRowSel);
        for (WebElement tableRowElem : priceTableRowElemList) {
            WebElement priceTypeElem = tableRowElem.findElement(priceTypeSel);
            WebElement priceValueElem = tableRowElem.findElement(priceValueSel);
            String priceType = priceTypeElem.getText().trim().replaceAll(":","");
            Double priceValue = Double.parseDouble(priceValueElem.getText().trim());
            priceCategories.put(priceType, priceValue);
        }

        return priceCategories;
    }

    public void agreeTOS(){
        WebElement agreeTOSElem = findElement(tosSel);
        if (!agreeTOSElem.isSelected()) agreeTOSElem.click();
    }

    public void clickOnCheckoutBtn(){
        findElement(checkoutBtnSel).click();
    }
}
