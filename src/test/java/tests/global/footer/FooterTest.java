package tests.global.footer;

import org.testng.Assert;
import org.testng.annotations.Test;
import test_flows.global.footer.FooterTestFlow;
import tests.BaseTest;
import url.Urls;

public class FooterTest extends BaseTest {

    @Test
    public void testHomePageFooter() {
        driver.get(Urls.BASE_URL);
        Assert.fail("Demo taking screenshot when test is failed!");
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
