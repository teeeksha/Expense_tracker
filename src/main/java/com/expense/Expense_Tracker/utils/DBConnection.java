package com.expense.Expense_Tracker.utils;
import java.sql.Connection;
import java.sql.DriverManager;
// Utility class to manage database connection
public class DBConnection {
    // Database URL, username and password
    private static final String URL =
            "jdbc:mysql://localhost:3306/expense_tracker";
    private static final String USER = "root";
    private static final String PASSWORD = "Teeksha23@";
    // Method to create and return database connection
    public static Connection getConnection() {
        try {
            // Establish connection with MySQL database
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database Connected Successfully!");
            return con;
        } catch (Exception e) {
            // Handle connection error
            e.printStackTrace();
            return null;
        }
    }
}
