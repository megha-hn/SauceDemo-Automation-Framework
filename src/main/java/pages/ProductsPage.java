package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.DriverManager;

import java.util.List;

public class ProductsPage {
    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(css = ".inventory_item button")
    private List<WebElement> addToCartButtons;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    public ProductsPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void sortBy(String option) {  // "Price (low to high)", "Name (A to Z)"
        new Select(sortDropdown).selectByVisibleText(option);
    }

    public void addProductToCart(int index) {
        addToCartButtons.get(index).click();
    }

    public String getCartCount() {
        return cartBadge.getText();
    }
}
