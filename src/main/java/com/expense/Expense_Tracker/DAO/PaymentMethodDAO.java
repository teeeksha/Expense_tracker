package com.expense.Expense_Tracker.DAO;

import com.expense.Expense_Tracker.utils.DBConnection;
import java.sql.*;

public class PaymentMethodDAO {

    // Displays all available payment methods
    public static void showMethods() {

        // SQL query to fetch payment methods
        String sql = "SELECT method_id, method_name FROM payment_methods";

        // Create connection, statement and execute query
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            // Display heading
            System.out.println("\nPayment Methods:");
            System.out.println("ID | Method");

            // Read and print each payment method
            while (rs.next()) {
                System.out.println(
                        rs.getInt("method_id") + "  | " +
                        rs.getString("method_name")
                );
            }

        } catch (SQLException e) {
            // Handle database errors
            e.printStackTrace();
        }
    }
}
