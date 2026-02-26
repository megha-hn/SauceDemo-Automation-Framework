// 14. test/tests/LoginTest.java  (Data-driven with Excel)
package tests;

import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtils;
import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return ExcelUtils.getTestData("LoginData");
    }

    @Test(dataProvider = "loginData", retryAnalyzer = listeners.RetryAnalyzer.class)
    public void testLoginScenarios(String testCase, String username, String password, String expectedResult, String expectedMessage) {
        System.out.println("=== Executing Test Case: " + testCase + " ===");

        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);

        if (expectedResult.equalsIgnoreCase("Success")) {
            assertTrue(loginPage.isProductsPageLoaded(), "Session validation failed - not on inventory page");
            System.out.println("VALID LOGIN + SESSION VALIDATION PASSED for user: " + username);
        } else {
            String actualMsg = loginPage.getErrorMessage();
            assertTrue(actualMsg.contains(expectedMessage), "Error message mismatch");
            System.out.println("INVALID/LOCKED USER SCENARIO HANDLED CORRECTLY");
        }
    }
}