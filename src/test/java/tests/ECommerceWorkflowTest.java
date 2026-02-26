// 15. test/tests/ECommerceWorkflowTest.java  (Full E2E with System.out after every step)
package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.*;
import utils.DriverManager;

import static org.testng.Assert.*;

public class ECommerceWorkflowTest extends BaseTest {

    @Test(retryAnalyzer = listeners.RetryAnalyzer.class)
    public void completeECommerceWorkflow() throws InterruptedException {
        System.out.println("=== STARTING FULL E-COMMERCE WORKFLOW ===");

        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "secret_sauce");
        System.out.println("Step: Valid login completed");

        ProductsPage productsPage = new ProductsPage();
        assertTrue(productsPage.isProductsPageDisplayed());


        // Sort
        productsPage.sortBy("Name (A to Z)");
        productsPage.sortBy("Price (low to high)");

        // Add multiple products
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Sauce Labs Bike Light");
        productsPage.addToCart("Sauce Labs Fleece Jacket");



        HeaderPage headerPage = new HeaderPage();
        assertEquals(headerPage.getCartItemCount(), 3, "Cart count not updated correctly after adding 3 items");
        System.out.println("Step: 3 products added & cart count verified");

        // Go to cart & remove
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

        System.out.println("=== E-COMMERCE WORKFLOW TEST PASSED SUCCESSFULLY ===");
    }


}