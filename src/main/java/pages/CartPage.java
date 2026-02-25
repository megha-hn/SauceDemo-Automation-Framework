package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverManager;

import java.util.List;

public class CartPage {

    // Cart icon / badge (usually on top bar)
    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    // Checkout button
    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    // Continue shopping link
    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingLink;

    // List of cart items
    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    // Remove buttons (one per item)
    @FindBy(xpath = "//button[text()='Remove']")
    private List<WebElement> removeButtons;

    // Item name in cart
    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemNames;

    // Item price in cart
    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemPrices;

    public CartPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    // Actions
    public void clickCheckout() {
        checkoutButton.click();
    }

    public void clickContinueShopping() {
        continueShoppingLink.click();
    }

    public void removeItemByIndex(int index) {
        if (index >= 0 && index < removeButtons.size()) {
            removeButtons.get(index).click();
        }
    }

    public void removeAllItems() {
        for (WebElement removeBtn : removeButtons) {
            removeBtn.click();
        }
    }

    // Verifications / Getters
    public String getCartItemCount() {
        if (cartBadge.isDisplayed()) {
            return cartBadge.getText();
        }
        return "0";
    }

    public int getNumberOfItemsInCart() {
        return cartItems.size();
    }

    public String getItemNameAtIndex(int index) {
        if (index >= 0 && index < itemNames.size()) {
            return itemNames.get(index).getText();
        }
        return "";
    }

    public String getItemPriceAtIndex(int index) {
        if (index >= 0 && index < itemPrices.size()) {
            return itemPrices.get(index).getText();
        }
        return "";
    }

    public boolean isCartEmpty() {
        return cartItems.isEmpty() && !cartBadge.isDisplayed();
    }

    public boolean isCheckoutButtonDisplayed() {
        return checkoutButton.isDisplayed();
    }
}
