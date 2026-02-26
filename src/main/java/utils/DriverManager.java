package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    // ThreadLocal to give each thread its own WebDriver instance
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void initializeDriver(String browser) {
        if (driverThreadLocal.get() == null) {
            WebDriver driver;

            switch (browser.toLowerCase().trim()) {
                case "firefox":
                    driver = new FirefoxDriver();
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--inprivate");         // Edge private mode
                    edgeOptions.addArguments("--start-maximized");
                    driver = new EdgeDriver(edgeOptions);
                    break;

                default:  // Chrome
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--incognito");
                    chromeOptions.addArguments("--start-maximized");
                    chromeOptions.addArguments("--remote-debugging-pipe");  // keep this from previous fix
                    driver = new ChromeDriver(chromeOptions);
                    break;
            }

            driver.manage().window().maximize();
            driverThreadLocal.set(driver);

            System.out.println("Driver initialized successfully for browser: " + browser
                    + " in thread: " + Thread.currentThread().getName());
        }
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
            System.out.println("Driver quit successfully in thread: " + Thread.currentThread().getName());
        }
    }
}