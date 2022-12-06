package test_flows.order;

import model.components.cart.CartItemRowComponent;
import model.components.cart.TotalComponent;
import model.components.order.ComputerEssentialComponent;
import model.pages.ComputerItemDetailsPage;
import model.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test_data.order.ComputerData;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {
    // Refer type T o nhieu class khac nhau, nen dua T vao class, ko chỉ ở class này dùng thôi
    // T - Generic Type: IS-A ComputerEssentialComponent

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent; //T extends ComputerEssentialComponent
    private final ComputerData computerData;
    private int quantity = 1; // default value
    private double totalItemPrice;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData, int quantity) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        this.quantity = quantity;
    }

    public void buildCompSpecAndAddToCart() {
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.computerComp(computerEssentialComponent);

        // Unselect all default options
        computerEssentialComp.unselectAllDefaultOptions();

        String processorFullStr = computerEssentialComp.selectProcessorType(computerData.getProcessorType()); // type: without spaces
        double processorAddedPrice = extractAdditionalPrice(processorFullStr);
        String ramFullStr = computerEssentialComp.selectRAMType(computerData.getRam());
        double ramAddedPrice = extractAdditionalPrice(ramFullStr);
        String HDDFullStr = computerEssentialComp.selectHDD(computerData.getHdd());
        double HDDAddedPrice = extractAdditionalPrice(HDDFullStr);

        double OSAddedPrice = 0;
        if (computerData.getOs() != null) {// control test data null or not via ComputerData
            String OSFullStr = computerEssentialComp.selectOS(computerData.getOs());
            OSAddedPrice = extractAdditionalPrice(OSFullStr);
        }

        if (this.quantity != 1) {
            computerEssentialComp.inputQuantity(this.quantity);
        }

        double totalAddedPrice = processorAddedPrice + ramAddedPrice + HDDAddedPrice + OSAddedPrice;
        System.out.println("totalAddedPrice: " + totalAddedPrice);

        totalItemPrice = (computerEssentialComp.basePrice() + totalAddedPrice) * this.quantity;

        // Add to cart
        computerEssentialComp.clickOnAddToCartBtn();
        computerItemDetailsPage.barNotificationComp().waitUntilItemAddedToCart();
        computerItemDetailsPage.barNotificationComp().closeBtnBarNotification();

        // Navigate to shopping cart
        computerItemDetailsPage.headerComp().clickOnShoppingCartLink();

        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }
    }


    private double extractAdditionalPrice(String itemStr) {
        double price = 0;
        int factor = 1;
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(itemStr);
        if (matcher.find()) {
            String priceStr = matcher.group(1);
            if (priceStr.startsWith("-")) factor = -1;
            price = Double.parseDouble(matcher.group(1).replaceAll("[+-]", ""));
        }

        return price * factor;
    }

    public void verifyShoppingCartPage() {
        System.out.println("Total Item Price in prev: " + totalItemPrice);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);

        List<CartItemRowComponent> cartItemRowCompList = shoppingCartPage.cartItemRowComponentList();
        if (cartItemRowCompList.isEmpty()) {
            Assert.fail("[ERR] There is no item displayed in the shopping cart!");
        }

        double allSubTotal = 0;
        for (CartItemRowComponent cartItemRowComp : cartItemRowCompList) {
            double currentSubTotal = cartItemRowComp.subTotal();
            double expectedSubTotal = cartItemRowComp.unitPrice() * cartItemRowComp.quantityInput();
            Assert.assertEquals(currentSubTotal, expectedSubTotal, "[ERR] The SubTotal on the item is incorrect");
            allSubTotal = allSubTotal + currentSubTotal;
            System.out.println("currentSubTotal: " + currentSubTotal);
        }

        TotalComponent totalComp = shoppingCartPage.totalComp();
        Map<String, Double> priceCategories = totalComp.priceCategories();
        double checkoutSubtotal = 0;
        double checkoutOtherFeesTotal = 0;
        double checkoutTotal = 0;

        for (String priceType : priceCategories.keySet()) {
            double priceValue = priceCategories.get(priceType);
            if (priceType.startsWith("Sub-Total")) {
                checkoutSubtotal = priceValue;
            } else if (priceType.startsWith("Total")) {
                checkoutTotal = priceValue;
            } else {
                checkoutOtherFeesTotal = checkoutOtherFeesTotal + priceValue;
            }
        }

        System.out.println("allSubTotal: " + allSubTotal);
        System.out.println("checkoutTotal: " + checkoutTotal);

        // Verify checkout values
        Assert.assertEquals(allSubTotal, checkoutSubtotal, "[ERR] Checking out Subtotal value is incorrect!");
        Assert.assertEquals((checkoutSubtotal + checkoutOtherFeesTotal), checkoutTotal, "[ERR] Checking out Total value is incorrect!");
    }
}
