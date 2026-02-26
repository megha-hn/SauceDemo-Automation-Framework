package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.ConfigReader;
import utils.DriverManager;

public class BaseTest {

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) {
        DriverManager.initializeDriver(browser);  // ← static call
        DriverManager.getDriver().get(ConfigReader.getProperty("url"));
        System.out.println("===== TEST SETUP COMPLETE - Browser: " + browser + " | URL: " + ConfigReader.getProperty("url") + " =====");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();  // ← static call
        System.out.println("===== TEST TEARDOWN COMPLETE =====");
    }
}