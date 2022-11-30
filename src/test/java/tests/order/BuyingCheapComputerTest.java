package tests.order;

import model.components.order.CheapComputerComponent;
import org.testng.annotations.Test;
import test_flows.order.OrderComputerFlow;
import tests.BaseTest;
import url.Urls;


public class BuyingCheapComputerTest extends BaseTest implements Urls {

    @Test
    public void testBuyingCheapComputer() {

        driver.get(Urls.BASE_URL.concat("/build-your-cheap-own-computer"));
        OrderComputerFlow<CheapComputerComponent> orderComputerFlow
                = new OrderComputerFlow(driver, CheapComputerComponent.class);
        orderComputerFlow.buildCompSpecAndAddToCart();
    }

}
