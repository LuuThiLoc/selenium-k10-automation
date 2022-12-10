package model.components.order;

import io.qameta.allure.Step;
import model.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class ComputerEssentialComponent extends BaseItemDetailsComponent {

    public ComputerEssentialComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public abstract String selectProcessorType(String type);

    public abstract String selectRAMType(String type);

    @Step("Select HDD with value {type}")
    public String selectHDD(String type){
        return selectCompOption(type);
    }

    @Step("Select OS with value {type}")
    public String selectOS(String type){
        return selectCompOption(type);
    }

    @Step("Select Computer Option with value {type}")
    public String selectCompOption(String type) {

        String selectorStr = "//label[contains(text(),\"" + type + "\")]";
        By optionSel = By.xpath(selectorStr);
        WebElement optionElem = null;

        try {
            optionElem = component.findElement(optionSel);
        } catch (Exception ignored) {}
        // try - catch: nếu có lỗi và throw ra Exception thì if() vẫn run
        // ignored ~ e (alias)
        // (Exception ignored) {}: ko care đến việc throw ra E gì trong catch block

        if (optionElem != null) {
            optionElem.click();
            return optionElem.getText();
        } else {
            throw new RuntimeException("The option: " + type + " is not existing to select!");
        }
    }
}
