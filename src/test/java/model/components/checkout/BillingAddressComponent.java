package model.components.checkout;

import model.components.Component;
import model.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

@ComponentCssSelector(value="#opc-billing")
public class BillingAddressComponent extends Component {

    private final static By inputAddressDropdownSel = By.id("billing-address-select");
    private final static By firstnameSel = By.id("BillingNewAddress_FirstName");
    private final static By lastnameSel = By.id("BillingNewAddress_LastName");
    private final static By emailSel = By.id("BillingNewAddress_Email");
    private final static By selectCountryDropdownSel = By.id("BillingNewAddress_CountryId");
    private final static By selectStateDropdownSel = By.id("BillingNewAddress_StateProvinceId");
    private final static By loadingStateProgressBarSel = By.id("states-loading-progress");
    private final static By citySel = By.id("BillingNewAddress_City");
    private final static By add1Sel = By.id("BillingNewAddress_Address1");
    private final static By zipCodeSel = By.id("BillingNewAddress_ZipPostalCode");
    private final static By phoneNoSel = By.id("BillingNewAddress_PhoneNumber");
    private final static By continueBtnSel = By.cssSelector(".new-address-next-step-button");

    public BillingAddressComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectInputNewAddress(){
        if (!findElements(inputAddressDropdownSel).isEmpty()){
            Select select = new Select(findElement(inputAddressDropdownSel));
            select.selectByVisibleText("New Address");
        }

    }
    public void inputFirstName(String value){
        if(!value.isEmpty()) findElement(firstnameSel).sendKeys(value);
    }

    public void inputLastName(String value){
        if(!value.isEmpty()) findElement(lastnameSel).sendKeys(value);
    }

    public void inputEmail(String value){
        if(!value.isEmpty()) findElement(emailSel).sendKeys(value);
    }

    public void selectCountry(String value){
        Select select = new Select(findElement(selectCountryDropdownSel));
        select.selectByVisibleText(value);
        wait.until(ExpectedConditions.invisibilityOf(findElement(loadingStateProgressBarSel)));
    }

    public void selectState(String value){
        Select select = new Select(findElement(selectStateDropdownSel));
        select.selectByVisibleText(value);
    }

    public void inputCity(String value){
        if(!value.isEmpty()) findElement(citySel).sendKeys(value);
    }

    public void inputAddress1(String value){
        if(!value.isEmpty()) findElement(add1Sel).sendKeys(value);
    }

    public void inputZipCode(String value){
        if(!value.isEmpty()) findElement(zipCodeSel).sendKeys(value);
    }

    public void inputPhoneNumber(String value){
        if(!value.isEmpty()) findElement(phoneNoSel).sendKeys(value);
    }

    public void clickOnContinueBtn(){
        WebElement continueBtnElem = findElement(continueBtnSel);
        continueBtnElem.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtnElem));
    }
}
