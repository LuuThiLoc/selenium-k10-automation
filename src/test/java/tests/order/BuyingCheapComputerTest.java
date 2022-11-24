package tests.order;

import driver.DriverFactory;
import model.components.order.CheapComputerComponent;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test_flows.order.OrderComputerFlow;
import url.Urls;


public class BuyingCheapComputerTest implements Urls {

    @Test
    public void testBuyingCheapComputer(){
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            driver.get(Urls.BASE_URL.concat("/build-your-cheap-own-computer"));
            OrderComputerFlow<CheapComputerComponent> orderComputerFlow = new OrderComputerFlow(driver, CheapComputerComponent.class);
            orderComputerFlow.buildCompSpecAndAddToCart();
        } catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();
    }

}
