package model.components.checkout;

import model.components.Component;
import model.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value="#opc-payment_method")
public class PaymentMethodComponent extends Component {

    public PaymentMethodComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
}
