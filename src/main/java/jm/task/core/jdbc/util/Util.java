package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соединения с БД
    private Connection connection = null;
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "serkali88";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединение с базой данных создано");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Соединение отсутствует");
        }
        return connection;
    }

}