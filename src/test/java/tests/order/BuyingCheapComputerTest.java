package tests.order;

import model.components.order.CheapComputerComponent;
import org.openqa.selenium.WebDriver;
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


public class BuyingCheapComputerTest extends BaseTest implements Urls {

    // Data Driven
    @Test(dataProvider = "computerData")
    public void testBuyingCheapComputer(ComputerData computerData) {
        WebDriver driver = getDriver();
        driver.get(Urls.BASE_URL.concat("/build-your-cheap-own-computer"));

        int randomQuantity = new SecureRandom().nextInt(100) + 2; // quantity(2, 100)

        OrderComputerFlow<CheapComputerComponent> orderComputerFlow
                = new OrderComputerFlow<>(driver, CheapComputerComponent.class, computerData, randomQuantity);

        orderComputerFlow.buildCompSpecAndAddToCart();
        orderComputerFlow.verifyShoppingCartPage();
        orderComputerFlow.agreeTOSAndCheckout();
        orderComputerFlow.inputBillingAddress();
        orderComputerFlow.inputShippingAddress();
        orderComputerFlow.selectShippingMethod();
        orderComputerFlow.selectPaymentMethod(PaymentMethodType.CREDIT_CARD);
        orderComputerFlow.inputPaymentInfo(CreditCardType.DISCOVER);
        orderComputerFlow.confirmOrder();
    }

    @DataProvider
    public ComputerData[] computerData() {
        String fileLocation = "/src/test/java/test_data/order/CheapComputerDataList.json";
        return DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
    }
}
