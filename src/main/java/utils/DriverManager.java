package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverManager {

    // ThreadLocal ensures each parallel thread has its own WebDriver
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            throw new IllegalStateException("Driver not initialized for this thread. Call initializeDriver first.");
        }
        return driver;
    }

    public static void initializeDriver(String browser) {
        // Only initialize if not already done for this thread
        if (driverThreadLocal.get() != null) {
            return;
        }

        WebDriver driver;

        browser = browser.toLowerCase().trim();

        if (browser.equals("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--inprivate");           // Private mode
            options.addArguments("--start-maximized");
            driver = new EdgeDriver(options);
            System.out.println("EdgeDriver initialized (InPrivate mode) in thread: " + Thread.currentThread().getName());
        } else {
            // Default: Chrome
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("--start-maximized");
            options.addArguments("--remote-debugging-pipe"); // Helps avoid port/crash issues
            driver = new ChromeDriver(options);
            System.out.println("ChromeDriver initialized (Incognito mode) in thread: " + Thread.currentThread().getName());
        }

        driver.manage().window().maximize();
        driverThreadLocal.set(driver);
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