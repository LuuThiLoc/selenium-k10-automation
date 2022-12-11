package driver;

import org.apache.commons.exec.OS;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;
import java.time.Duration;

public class DriverFactory {
    private WebDriver driver;

    public WebDriver getDriver(String browserName) {

        String currentProjectLocation = System.getProperty("user.dir");
        String chromeDriverLocation;

        if (OS.isFamilyMac()) {
            chromeDriverLocation = "/src/test/resources/drivers/chromedriver.exe";
        } else if (OS.isFamilyWindows()) {
            chromeDriverLocation = "\\src\\test\\resources\\drivers\\chromedriver.exe.exe";
        } else {
            throw new RuntimeException("[ERR] Couldn't detect the OS");
        }

        String chromeAbsoluteLocation = currentProjectLocation.concat(chromeDriverLocation);
        System.setProperty("webdriver.chrome.driver", chromeAbsoluteLocation);

        if (driver == null) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setPlatform(Platform.ANY);

            if(browserName == null){
                throw new IllegalArgumentException("[ERR] Browser name can not be null!");
            }

            switch (browserName) {
                case "chrome":
//                    driver = new ChromeDriver();
                    desiredCapabilities.setBrowserName("chrome");
                    break;
                case "firefox":
//                    driver = new FirefoxDriver();
                    desiredCapabilities.setBrowserName("firefox");
                    break;
                case "safari":
//                    driver = new SafariDriver();
                    desiredCapabilities.setBrowserName("safari");
                    break;
                default:
                    throw new IllegalArgumentException(browserName + " is not supported!");
            }

            try{
                String hub = "http://localhost:4444/wd/hub";
                URL urlHub = new URL(hub);
                driver = new RemoteWebDriver(urlHub, desiredCapabilities);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15L));
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return driver;
    }

    public static WebDriver getChromeDriver() {
        String currentProjectLocation = System.getProperty("user.dir");
        String chromeDriverLocation;

        if (OS.isFamilyMac()) {
            chromeDriverLocation = "/src/test/resources/drivers/chromedriver.exe";
        } else if (OS.isFamilyWindows()) {
            chromeDriverLocation = "\\src\\test\\resources\\drivers\\chromedriver.exe.exe";
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