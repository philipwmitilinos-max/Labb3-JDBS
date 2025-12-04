package se.iths.philip.jdbcexercises;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppInfo {
    private static AppInfo instance;
    private Properties properties;

    private AppInfo() {
        properties = new Properties();
        try (InputStream input = ClassLoader.getSystemResourceAsStream("application.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AppInfo getInstance() {
        if (instance == null) {
            instance = new AppInfo();
        }
        return instance;
    }

    public String getProperties(String key) {
        return properties.getProperty(key);
    }
}
