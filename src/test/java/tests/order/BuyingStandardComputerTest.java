package tests.order;

import model.components.order.StandardComputerComponent;
import org.testng.annotations.Test;
import test_flows.order.OrderComputerFlow;
import tests.BaseTest;
import url.Urls;


public class BuyingStandardComputerTest extends BaseTest implements Urls {

    @Test
    public void testBuyingStandardComputer() {

        driver.get(Urls.BASE_URL.concat("/build-your-own-computer"));
        OrderComputerFlow<StandardComputerComponent> orderComputerFlow
                = new OrderComputerFlow(driver, StandardComputerComponent.class);
        orderComputerFlow.buildCompSpecAndAddToCart();
    }

}
