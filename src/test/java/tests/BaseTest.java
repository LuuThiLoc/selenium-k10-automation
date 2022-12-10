package tests;

import driver.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BaseTest {
    private static final List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverThread;
    private String browserName;

    protected WebDriver getDriver(){
        return driverThread.get().getDriver(browserName);
    }

    @BeforeTest
    @Parameters({"browser"})
    public void initBrowserSession(String browserName) {
        this.browserName = browserName;
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactory webDriverThread = new DriverFactory();
            webDriverThreadPool.add(webDriverThread);
            return webDriverThread;
        });
    }

    @AfterTest(alwaysRun = true)
    public void closeBrowserSession() {
        driverThread.get().closeBrowserSession();
    }

    @AfterMethod
    public void captureScreenshot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {

            // 1. Get test method name
            String methodName = result.getName();

            // 2. Get taken time
            // testMethodName - yyyy-m-d-hr-mm-sec.png
            Calendar calendar = new GregorianCalendar();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH) + 1;
            int d = calendar.get(Calendar.DATE);
            int hr = calendar.get(Calendar.HOUR);
            int mm = calendar.get(Calendar.MINUTE);
            int sec = calendar.get(Calendar.SECOND);
            String fileName = methodName + "-" + y + "-" + m + "-" + d + "-" + hr + "-" + mm + "-" + sec + ".png";

            try {
                // 3. Take screenshot
                File screenshotBase64Data = ((TakesScreenshot) driverThread.get().getDriver(browserName)).getScreenshotAs(OutputType.FILE);

                // 4. Save
                String fileLocation = System.getProperty("user.dir") + "\\screenshot\\" + fileName;
                FileUtils.copyFile(screenshotBase64Data, new File(fileLocation));

                // 5. Attach to allure report
                Path content = Paths.get(fileLocation);
                try (InputStream inputStream = Files.newInputStream(content)) {
                    Allure.addAttachment(fileName, inputStream);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
