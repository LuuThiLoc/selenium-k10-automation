package tests.order;

import driver.DriverFactory;
import model.components.order.StandardComputerComponent;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test_flows.order.OrderComputerFlow;
import url.Urls;


public class BuyingStandardComputerTest implements Urls {

    @Test
    public void testBuyingStandardComputer(){
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            driver.get(Urls.BASE_URL.concat("/build-your-own-computer"));
            OrderComputerFlow<StandardComputerComponent> orderComputerFlow = new OrderComputerFlow(driver, StandardComputerComponent.class);
            orderComputerFlow.buildCompSpecAndAddToCart();

        } catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();
    }

}
