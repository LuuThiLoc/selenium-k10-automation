package test_flows.order;

import model.components.order.ComputerEssentialComponent;
import model.pages.ComputerItemDetailsPage;
import org.openqa.selenium.WebDriver;
import test_data.order.ComputerData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {
    // Refer type T o nhieu class khac nhau, nen dua T vao class, ko chỉ ở class này dùng thôi
    // T - Generic Type: IS-A ComputerEssentialComponent

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent; //T extends ComputerEssentialComponent
    private final ComputerData computerData;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
    }

    public void buildCompSpecAndAddToCart() {
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.computerComp(computerEssentialComponent);
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

        double totalAddedPrice = processorAddedPrice + ramAddedPrice + HDDAddedPrice + OSAddedPrice;
        System.out.println("OSAddedPrice: "+ totalAddedPrice);

        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }
    }

    private double extractAdditionalPrice(String itemStr) {
        double price = 0;
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(itemStr);
        if (matcher.find()) {
            price = Double.parseDouble(matcher.group(1).replaceAll("[+-]", ""));
        }
        return price;
    }

}
