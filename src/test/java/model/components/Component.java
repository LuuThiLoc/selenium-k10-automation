package model.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Constructor;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Component {
    public Component(WebDriver driver, WebElement component) {
        this.driver = driver;
        this.component = component;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20L));
    }

    protected WebDriver driver;
    protected WebElement component;
    protected WebDriverWait wait;

    public WebElement findElement(By by) {
        return this.component.findElement(by);
    }

    public List<WebElement> findElements(By by) {
        return this.component.findElements(by);
    }

    public <T extends Component> T findComponent(Class<T> componentClass, WebDriver driver) {
        return findComponents(componentClass, driver).get(0);
    }

    public <T extends Component> List<T> findComponents(Class<T> componentClass, WebDriver driver) {

        // Get Component Selector
        String cssSelector;

        try {
            cssSelector = componentClass.getAnnotation(ComponentCssSelector.class).value();
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERR] The component MUST have a selector");
        }

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(cssSelector)));
        List<WebElement> results = component.findElements(By.cssSelector(cssSelector));

        // Define component class constructor params
        Constructor<T> constructor;
        Class<?>[] params = new Class[]{WebDriver.class, WebElement.class};
        try {
            constructor = componentClass.getConstructor(params);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "[ERR] The component MUST have constructor with params: " + Arrays.toString(params));
        }

        // Convert to components: stream > map > collect to list
        List<T> components = results.stream().map(webElement -> { // Lambda Expression - Functional Anonymous
            try {
                return constructor.newInstance(driver, webElement); // return bên trong Func là 1 component: biến đổi
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null; // return ra bên ngoài null
        }).collect(Collectors.toList()); // sau khi tách ra để biến đổi thì collect lại và convert sang List

        return components;
    }
}
