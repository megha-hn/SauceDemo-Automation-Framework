// 10. pages/CheckoutPage.java
package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverManager;
import utils.WaitUtils;

public class CheckoutPage {
    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(className = "complete-header")
    private WebElement confirmationHeader;

    public CheckoutPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void fillShippingInfo(String firstName, String lastName, String zip) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        postalCodeField.sendKeys(zip);
        System.out.println("Step: Filled shipping info -> " + firstName + " " + lastName + " " + zip);
    }

    public void continueToOverview() {
        WaitUtils.waitForClickable(continueButton);
        continueButton.click();
        System.out.println("Step: Clicked Continue (to overview)");
    }

    public void finishPurchase() {
        WaitUtils.waitForClickable(finishButton);
        finishButton.click();
        System.out.println("Step: Clicked Finish purchase");
    }

    public String getOrderConfirmation() {
        WaitUtils.waitForVisibility(confirmationHeader);
        String msg = confirmationHeader.getText();
        System.out.println("Step: Order confirmation message -> " + msg);
        return msg;
    }
}