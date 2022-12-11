package model.components.checkout;

import model.components.Component;
import model.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import test_data.CreditCardType;

@ComponentCssSelector(value="#opc-payment_info")
public class PaymentInformationComponent extends Component {

    private static final By creditCardDropdownSel = By.cssSelector("#CreditCardType");
    private static final By cardHolderNameSel = By.cssSelector("#CardholderName");
    private static final By cardNumSel = By.cssSelector("#CardNumber");
    private static final By expireMonthDropdownSel = By.cssSelector("#ExpireMonth");
    private static final By expireYearDropdownSel = By.cssSelector("#ExpireYear");
    private static final By cardCodeSel = By.cssSelector("#CardCode");
    private static final By continueBtnSel = By.cssSelector("input[onclick='PaymentInfo.save()']");
    public PaymentInformationComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectCreditCardType(CreditCardType creditCardType){
        Select select = new Select(findElement(creditCardDropdownSel));

        switch (creditCardType){
            case VISA:
                select.selectByVisibleText("Visa");
                break;
            case MASTER_CARD:
                select.selectByVisibleText("Master card");
                break;
            case DISCOVER:
                select.selectByVisibleText("Discover");
                break;
            case AMEX:
                select.selectByVisibleText("Amex");
                break;
        }
    }

    public void inputCardHolderName(String value){
        findElement(cardHolderNameSel).sendKeys(value);
    }

    public void inputCardNumName(String value){
        findElement(cardNumSel).sendKeys(value);
    }

    public void selectExpiredMonth(String month){
        Select select = new Select(findElement(expireMonthDropdownSel));
        select.selectByVisibleText(month);
    }

    public void selectExpiredYear(String year){
        Select select = new Select(findElement(expireYearDropdownSel));
        select.selectByVisibleText(year);
    }
    public void inputCardCodeName(String value){
        findElement(cardCodeSel).sendKeys(value);
    }

    public void clickOnContinueBtn(){
        WebElement continueBtnElem = findElement(continueBtnSel);
        continueBtnElem.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtnElem));
    }

}
