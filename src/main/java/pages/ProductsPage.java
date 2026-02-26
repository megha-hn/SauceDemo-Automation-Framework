package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.DriverManager;
import utils.WaitUtils;

public class ProductsPage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    public ProductsPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
        System.out.println("ProductsPage initialized");
    }

    public boolean isProductsPageDisplayed() {
        WaitUtils.waitForVisibility(pageTitle);
        boolean displayed = "Products".equals(pageTitle.getText().trim());
        System.out.println("Step: Products page displayed -> " + displayed);
        return displayed;
    }

    public void sortBy(String option) {  // e.g. "Name (A to Z)", "Price (low to high)"
        WaitUtils.waitForClickable(sortDropdown);
        new Select(sortDropdown).selectByVisibleText(option);
        System.out.println("Step: Sorted products by -> " + option);
        // Optional: small sleep or wait for list refresh if needed
        try { Thread.sleep(500); } catch (Exception ignored) {}
    }

    public void addToCart(String productName) {
        // Convert to data-test format: lowercase, space → -, remove special chars
        String dataTestValue = "add-to-cart-" + productName.toLowerCase()
                .replaceAll("\\s+", "-")      // space to -
                .replace("'", "")
                .replace("(", "")
                .replace(")", "")
                .replace(".", "");

        // Use data-test attribute - more reliable in SauceDemo
        String locator = "[data-test='" + dataTestValue + "']";
        WebElement addButton = DriverManager.getDriver().findElement(By.cssSelector(locator));

        WaitUtils.waitForClickable(addButton);
        addButton.click();
        System.out.println("Step: Added to cart -> " + productName);

        // Wait a tiny bit for cart badge to update (flaky DOM sometimes)
        try { Thread.sleep(800); } catch (Exception ignored) {}
    }
}