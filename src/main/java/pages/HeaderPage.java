// 8. pages/HeaderPage.java
package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverManager;
import utils.WaitUtils;

public class HeaderPage {
    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    public HeaderPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void goToCart() {
        cartLink.click();
        System.out.println("Step: Navigated to Cart via header");
    }

    public int getCartItemCount() {
        try {
            WaitUtils.waitForVisibility(cartBadge);  // ← add this wait!
            String count = cartBadge.getText().trim();
            System.out.println("Step: Cart badge visible, count = " + count);
            return Integer.parseInt(count);
        } catch (Exception e) {
            System.out.println("Step: Cart badge NOT visible or empty → assuming count = 0 | Exception: " + e.getMessage());
            return 0;
        }
    }
}