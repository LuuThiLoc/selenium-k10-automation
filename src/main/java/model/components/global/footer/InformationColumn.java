package model.components.global.footer;

import model.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = ".column.information")
public class InformationColumn extends FooterColumnComponent{
    public InformationColumn(WebDriver driver, WebElement component) {
        super(driver, component);
    }

}
