package model.components.order;

import model.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BaseItemDetailsComponent extends Component {
    private final static By basePriceSel = By.cssSelector(".product-price");
    private final static By quantitySel = By.cssSelector(".qty-input");
    private final static By allOptionsSel = By.cssSelector(".option-list input");
    private final static By addToCartBtnSel = By.cssSelector(".add-to-cart-button"); //or: [id^='add-to-cart-button']

    public BaseItemDetailsComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public Double basePrice(){
        String productPriceText = component.findElement(basePriceSel).getText().trim();
        return Double.parseDouble(productPriceText);
    }

    public void inputQuantity(int quantity){
        WebElement quantityElem = findElement(quantitySel);
        quantityElem.clear();
        quantityElem.sendKeys(String.valueOf(quantity));
    }

    public void unselectAllDefaultOptions(){
        List<WebElement> allOptionsElem = findElements(allOptionsSel);
        allOptionsElem.forEach(option -> {
            if(option.getAttribute("checked")!= null){
                option.clear();
            }
        });
    }

    public void clickOnAddToCartBtn(){
        findElement(addToCartBtnSel).click();
    }
}
