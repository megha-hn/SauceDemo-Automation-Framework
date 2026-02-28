// 3. utils/WaitUtils.java
package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
    private static WebDriverWait getWait() {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
    }

    public static void waitForVisibility(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
        System.out.println("Wait completed - element is visible");
    }

    public static void waitForClickable(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
        System.out.println("Wait completed - element is clickable");
    }
}