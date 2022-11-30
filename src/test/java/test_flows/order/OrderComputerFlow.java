package test_flows.order;

import model.components.order.ComputerEssentialComponent;
import model.pages.ComputerItemDetailsPage;
import org.openqa.selenium.WebDriver;

public class OrderComputerFlow <T extends ComputerEssentialComponent>{
    // Refer type T o nhieu class khac nhau, nen dua T vao class, ko chỉ ở class này dùng thôi
    // T - Generic Type: IS-A ComputerEssentialComponent

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent; //T extends ComputerEssentialComponent

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
    }

    public void buildCompSpecAndAddToCart(){
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.computerComp(computerEssentialComponent);
        computerEssentialComp.selectProcessorType("2.2GHz"); // type: without spaces
        computerEssentialComp.selectRAMType("4GB");

        try{
            Thread.sleep(5000);
        }catch (Exception e){}
    }

}
