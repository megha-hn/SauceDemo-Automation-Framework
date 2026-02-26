// 9. pages/CartPage.java
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverManager;
import utils.WaitUtils;

public class CartPage {
    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    public CartPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void removeProduct(String productName) {
        String id = "remove-" + productName.toLowerCase()
                .replace(" ", "-")
                .replace("'", "")
                .replace("(", "")
                .replace(")", "")
                .replace(".", "");
        WebElement removeBtn = DriverManager.getDriver().findElement(By.id(id));
        WaitUtils.waitForClickable(removeBtn);
        removeBtn.click();
        System.out.println("Step: Removed from cart -> " + productName);
    }

    public void checkout() {
        WaitUtils.waitForClickable(checkoutButton);
        checkoutButton.click();
        System.out.println("Step: Clicked Checkout button");
    }

    public void continueShopping() {
        WaitUtils.waitForClickable(continueShoppingButton);
        continueShoppingButton.click();
        System.out.println("Step: Clicked Continue Shopping");
    }
}