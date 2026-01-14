package com.expense.Expense_Tracker.DAO;

import com.expense.Expense_Tracker.utils.DBConnection;
import java.sql.*;

public class CategoryDAO {

    public static void showCategories() {

        String sql = "SELECT category_id, category_name FROM categories WHERE type='expense'";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\nExpense Categories:");
            System.out.println("ID | Name");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("category_id") + "  | " +
                        rs.getString("category_name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
