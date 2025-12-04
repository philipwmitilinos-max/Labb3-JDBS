package se.iths.philip.jdbcexercises;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;

    private DatabaseConnection() {
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        AppInfo appInfo = AppInfo.getInstance();
        String url = appInfo.getProperties("db.urlMydb");
        String user = appInfo.getProperties("db.userRoot");
        String pass = appInfo.getProperties("db.pass1");

        return DriverManager.getConnection(url, user, pass);
    }
}
