// 2. utils/ConfigReader.java
package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
            System.out.println("Config.properties loaded successfully");
        } catch (Exception e) {
            System.out.println("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}