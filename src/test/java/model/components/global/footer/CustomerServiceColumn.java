package model.components.global.footer;

import model.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = ".column.customer-service")
public class CustomerServiceColumn extends FooterColumnComponent {
    public CustomerServiceColumn(WebDriver driver, WebElement component) {
        super(driver, component);
    }

}
