package com.gladunalexander.connection;

import java.sql.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Alex on 11.07.2017.
 */
public class DBUtils {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection != null){
            return connection;
        }
        try {
            Properties properties = new Properties();
            InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(inputStream);
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
