package com.expense.Expense_Tracker.DAO;
import com.expense.Expense_Tracker.utils.DBConnection;
import java.sql.*;
public class CategoryDAO {
    public static void showCategories() {
        // SQL query to get expense type categories
        String sql = "SELECT category_id, category_name FROM categories WHERE type='expense'";
        // Establishing database connection and executing query
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            // Display header
            System.out.println("\nExpense Categories:");
            System.out.println("ID | Name");
            // Reading each row from the result set
            while (rs.next()) {
                System.out.println( rs.getInt("category_id") + "  | " +  rs.getString("category_name"));
            }
        } catch (SQLException e) {
            // Handles database related errors
            e.printStackTrace();
        }
    }
}


