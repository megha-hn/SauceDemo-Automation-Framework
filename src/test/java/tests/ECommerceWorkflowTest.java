package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;
import utils.DBUtils;
import utils.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.testng.Assert.*;

public class ECommerceWorkflowTest extends BaseTest {

    @Test(retryAnalyzer = listeners.RetryAnalyzer.class)
    public void completeECommerceWorkflow() throws InterruptedException, SQLException {
        System.out.println("=== STARTING FULL E-COMMERCE WORKFLOW WITH DB VALIDATION ===");

        // Step 1: Login
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "secret_sauce");
        System.out.println("Step: Valid login completed");

        // DB Validation 1: Verify user exists in DB after login
        validateUserExistsInDB("standard_user");

        ProductsPage productsPage = new ProductsPage();
        assertTrue(productsPage.isProductsPageDisplayed(), "Products page not loaded after login");

        // Sort products
        productsPage.sortBy("Name (A to Z)");
        productsPage.sortBy("Price (low to high)");

        // Step 2: Add multiple products to cart
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Sauce Labs Bike Light");
        productsPage.addToCart("Sauce Labs Fleece Jacket");

        HeaderPage headerPage = new HeaderPage();
        assertEquals(headerPage.getCartItemCount(), 3, "Cart count not updated correctly after adding 3 items");
        System.out.println("Step: 3 products added & cart count verified");

        // DB Validation 3: Check product inventory decreased (simplified check)
        validateProductInventoryAfterAdd("Sauce Labs Backpack");

        // Go to cart & remove one item
        headerPage.goToCart();
        CartPage cartPage = new CartPage();
        cartPage.removeProduct("Sauce Labs Bike Light");
        assertEquals(headerPage.getCartItemCount(), 2, "Cart count not updated after removal");
        System.out.println("Step: Item removed & cart count verified");

        // Continue shopping & checkout
        cartPage.continueShopping();
        headerPage.goToCart();
        cartPage.checkout();

        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.fillShippingInfo("John", "Doe", "560001");
        checkoutPage.continueToOverview();
        checkoutPage.finishPurchase();

        String confirmation = checkoutPage.getOrderConfirmation();
        assertTrue(confirmation.contains("Thank you for your order!"), "Order confirmation failed");
        System.out.println("Step: End-to-end purchase flow completed & validated");

        // DB Validation 2: Validate order details exist in DB after purchase
        validateOrderInDBAfterPurchase(1);  // Assume user_id = 1 for standard_user

        System.out.println("=== E-COMMERCE WORKFLOW TEST + DB VALIDATION PASSED SUCCESSFULLY ===");
    }

    // DB Validation 1: Check user exists after login
    private void validateUserExistsInDB(String expectedUsername) throws SQLException {
        ResultSet rs = DBUtils.executeQuery(
                "SELECT username FROM users WHERE username = '" + expectedUsername + "'"
        );
        assertTrue(rs.next(), "User '" + expectedUsername + "' does not exist in DB after login");
        System.out.println("DB Validation: User '" + expectedUsername + "' exists in DB");
    }

    // DB Validation 2: Check order created after purchase
    private void validateOrderInDBAfterPurchase(int userId) throws SQLException {
        ResultSet rs = DBUtils.executeQuery(
                "SELECT order_id, user_id, total_amount, status FROM orders WHERE user_id = " + userId
        );
        assertTrue(rs.next(), "No order found in DB for user_id = " + userId + " after purchase");
        double totalAmount = rs.getDouble("total_amount");
        String status = rs.getString("status");
        System.out.println("DB Validation: order exists - ID: " + rs.getInt("order_id") +
                ", Total: " + totalAmount + ", Status: " + status);
        // You can add more assertions, e.g. assertEquals(totalAmount, 79.97, 0.01);
    }

    // DB Validation 3: Check product inventory (simplified - assumes quantity decreases)
    private void validateProductInventoryAfterAdd(String productName) throws SQLException {
        ResultSet rs = DBUtils.executeQuery(
                "SELECT quantity, available FROM products WHERE product_name = '" + productName + "'"
        );
        assertTrue(rs.next(), "product '" + productName + "' not found in DB");
        int quantity = rs.getInt("quantity");
        boolean available = rs.getBoolean("available");
        assertTrue(quantity > 0, "product quantity should be > 0 after add to cart");
        assertTrue(available, "product should still be available");
        System.out.println("DB Validation: product '" + productName + "' has quantity " + quantity + " and is available");
    }
}