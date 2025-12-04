package se.iths.philip.jdbcexercises.view;

import java.io.InputStream;
import java.util.Properties;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private String url;
    private String user;
    private String password;

    private DatabaseConnection() {
        loadProperties();
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private void loadProperties() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            Properties props =
        }
    }
}
