package base;

import org.testng.annotations.*;
import utils.ConfigReader;
import utils.DBUtils;
import utils.DriverManager;

import java.sql.SQLException;

public class BaseTest {

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) {
        DriverManager.initializeDriver(browser);               // Static call
        DriverManager.getDriver().get(ConfigReader.getProperty("url"));
        System.out.println("===== TEST SETUP COMPLETE - Browser: " + browser +
                " | Thread: " + Thread.currentThread().getName() +
                " | URL: " + ConfigReader.getProperty("url") + " =====");
    }
    @BeforeClass
    public void setUpDatabase() throws SQLException {
        DBUtils.connectToDB();  // Connect once before all tests in this class
        System.out.println("Database connected for validation");
    }

    @AfterClass
    public void tearDownDatabase() throws SQLException {
        DBUtils.closeDB();  // Close once after all tests
        System.out.println("Database connection closed");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();                           // Static call
        System.out.println("===== TEST TEARDOWN COMPLETE - Thread: " + Thread.currentThread().getName() + " =====");
    }


}