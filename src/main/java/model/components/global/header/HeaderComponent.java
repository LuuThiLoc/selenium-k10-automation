package model.components.global.header;

import model.components.Component;
import model.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = ".header")
public class HeaderComponent extends Component {

    public HeaderComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
    private static final By shoppingCartLinkSel = By.cssSelector("#topcartlink");

    public void clickOnShoppingCartLink() {
        WebElement shoppingCartLinkElem = findElement(shoppingCartLinkSel);
        scrollUpToElement(shoppingCartLinkElem);
        shoppingCartLinkElem.click();
    }
}
