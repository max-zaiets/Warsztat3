package pl.coderslab.workshop3.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {

    private static String DB_URL = "jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf8";
    private static String DB_USER = "root";
    private static String DB_PASSWORD = "coderslab";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
