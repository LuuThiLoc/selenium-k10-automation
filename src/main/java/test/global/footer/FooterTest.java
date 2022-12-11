package test.global.footer;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test.BaseTest;
import test_flows.global.footer.FooterTestFlow;
import url.Urls;

public class FooterTest extends BaseTest {

    @Test(groups = {"smoke"})
    public void testHomePageFooter() {
        WebDriver driver = getDriver();
        driver.get(Urls.BASE_URL);
//        Assert.fail("Demo taking screenshot when test is failed!");
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterComponent();
    }

    @Test
    public void testCategoriesPageFooter() {

    }

    @Test
    public void testLoginPageFooter() {

    }
}
