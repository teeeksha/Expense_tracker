package com.expense.Expense_Tracker.DAO;

import com.expense.Expense_Tracker.utils.DBConnection;
import java.sql.*;

public class PaymentMethodDAO {

    public static void showMethods() {

        String sql = "SELECT method_id, method_name FROM payment_methods";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\nPayment Methods:");
            System.out.println("ID | Method");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("method_id") + "  | " +
                        rs.getString("method_name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
