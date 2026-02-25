package base;

import org.testng.annotations.*;
import utils.DriverManager;
import utils.ConfigReader;
import listeners.TestListener;
import org.testng.ITestResult;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional String browser) {
        if (browser == null) browser = ConfigReader.getProperty("browser");
        DriverManager.initDriver(browser);
        DriverManager.getDriver().get(ConfigReader.getProperty("url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        DriverManager.quitDriver();
    }
}