// 5. utils/ScreenshotUtils.java
package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class ScreenshotUtils {
    public static String takeScreenshot(String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);
            String destination = "reports/screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
            FileUtils.copyFile(source, new File(destination));
            System.out.println("Screenshot captured for " + testName + " at: " + destination);
            return destination;
        } catch (Exception e) {
            System.out.println("Screenshot failed: " + e.getMessage());
            return null;
        }
    }
}