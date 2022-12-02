package tests.order;

import model.components.order.CheapComputerComponent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.DataObjectBuilder;
import test_data.order.ComputerData;
import test_flows.order.OrderComputerFlow;
import tests.BaseTest;
import url.Urls;


public class BuyingCheapComputerTest extends BaseTest implements Urls {

    // Data Driven
    @Test(dataProvider = "computerData")
    public void testBuyingCheapComputer(ComputerData computerData) {
        driver.get(Urls.BASE_URL.concat("/build-your-cheap-own-computer"));

        OrderComputerFlow<CheapComputerComponent> orderComputerFlow
                = new OrderComputerFlow<>(driver, CheapComputerComponent.class, computerData);
        orderComputerFlow.buildCompSpecAndAddToCart();
        System.out.println(computerData);
    }

    @DataProvider
    public ComputerData[] computerData() {
        String fileLocation = "/src/test/java/test_data/order/CheapComputerDataList.json";
        return DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
    }
}
