package driver;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class DriverFactory {
    private WebDriver driver;

    public WebDriver getDriver(String browserName) {

        String currentProjectLocation = System.getProperty("user.dir");
        String chromeDriverLocation;

        if (OS.isFamilyMac()) {
            chromeDriverLocation = "/src/test/resources/drivers/chromedriver";
        } else if (OS.isFamilyWindows()) {
            chromeDriverLocation = "\\src\\test\\resources\\drivers\\chromedriver.exe";
        } else {
            throw new RuntimeException("[ERR] Couldn't detect the OS");
        }

        String chromeAbsoluteLocation = currentProjectLocation.concat(chromeDriverLocation);
        System.setProperty("webdriver.chrome.driver", chromeAbsoluteLocation);

        if (driver == null) {
            switch (browserName) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                default:
                    throw new IllegalArgumentException(browserName + " is not supported!");
            }
        }
        return driver;
    }

    public static WebDriver getChromeDriver() {
        String currentProjectLocation = System.getProperty("user.dir");
        String chromeDriverLocation;

        if (OS.isFamilyMac()) {
            chromeDriverLocation = "/src/test/resources/drivers/chromedriver";
        } else if (OS.isFamilyWindows()) {
            chromeDriverLocation = "\\src\\test\\resources\\drivers\\chromedriver.exe";
        } else {
            throw new RuntimeException("[ERR] Couldn't detect the OS");
        }

        String chromeAbsoluteLocation = currentProjectLocation.concat(chromeDriverLocation);
        System.setProperty("webdriver.chrome.driver", chromeAbsoluteLocation);

        // Set up Chrome Options | Optional
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5L));
//        driver.manage().deleteAllCookies();
//        driver.get("chrome://settings/clearBrowserData");
//        driver.switchTo().activeElement();
//        driver.findElement(By.cssSelector("* /deep/ #clearBrowsingDataConfirm")).click();

        return driver;
    }

    public void closeBrowserSession(){
        if(driver != null){
            driver.quit();
        }
    }
}