package tests.order;

import model.components.order.StandardComputerComponent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.CreditCardType;
import test_data.DataObjectBuilder;
import test_data.PaymentMethodType;
import test_data.order.ComputerData;
import test_flows.order.OrderComputerFlow;
import tests.BaseTest;
import url.Urls;

import java.security.SecureRandom;


public class BuyingStandardComputerTest extends BaseTest implements Urls {

    @Test(dataProvider = "computerData")
    public void testBuyingStandardComputer(ComputerData computerData) {

        driver.get(Urls.BASE_URL.concat("/build-your-own-computer"));

        int randomQuantity = new SecureRandom().nextInt(100) + 2; // quantity(2, 100)
        OrderComputerFlow<StandardComputerComponent> orderComputerFlow
                = new OrderComputerFlow<>(driver, StandardComputerComponent.class, computerData, randomQuantity);

        orderComputerFlow.buildCompSpecAndAddToCart();
        orderComputerFlow.verifyShoppingCartPage();
        orderComputerFlow.agreeTOSAndCheckout();
        orderComputerFlow.inputBillingAddress();
        orderComputerFlow.inputShippingAddress();
        orderComputerFlow.selectShippingMethod();
        orderComputerFlow.selectPaymentMethod(PaymentMethodType.CREDIT_CARD);
        orderComputerFlow.inputPaymentInfo(CreditCardType.VISA);
        orderComputerFlow.confirmOrder();
    }

    @DataProvider
    public ComputerData[] computerData() {
        String fileLocation = "/src/test/java/test_data/order/StandardComputerDataList.json";
        return DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
    }
}
