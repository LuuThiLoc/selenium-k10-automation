package model.pages;

import model.components.Component;
import model.components.global.BarNotificationComponent;
import model.components.global.footer.FooterComponent;
import model.components.global.header.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage extends Component {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        super(driver, driver.findElement(By.tagName("html")));
        this.driver = driver;
    }

    public HeaderComponent headerComp(){
        return findComponent(HeaderComponent.class, driver);
    }

    public FooterComponent footerComp(){
        return findComponent(FooterComponent.class, driver);
    }

    public BarNotificationComponent barNotificationComp(){
        return findComponent(BarNotificationComponent.class, driver);
    }

//    public PageFooterComponent pageFooter(){
//        return findComponent(PageFooterComponent.class, driver);
//    }

}
