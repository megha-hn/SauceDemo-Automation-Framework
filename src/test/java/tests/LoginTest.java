// 14. test/tests/LoginTest.java  (Data-driven with Excel)
package tests;

import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DBUtils;
import utils.ExcelUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @DataProvider(name = "loginDataFromDB")
    public Object[][] getLoginDataFromDB() throws SQLException {
        ResultSet rs = DBUtils.executeQuery("SELECT username, password FROM users WHERE email LIKE '%@example.com%'");
        List<Object[]> data = new ArrayList<>();
        while (rs.next()) {
            data.add(new Object[] { rs.getString("username"), rs.getString("password") });
        }
        return data.toArray(new Object[0][]);
    }

    @Test(dataProvider = "loginDataFromDB")
    public void testLoginScenarios(String username, String password) {
        // Your existing login test, using username and password from DB
    }
}