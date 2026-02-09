package com.expense.Expense_Tracker.DAO;

import com.expense.Expense_Tracker.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Fetches username using user ID
    public String getUserNameById(int userId) {

        String sql = "SELECT username FROM users WHERE user_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("username");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "User"; // Default name if user not found
    }

    // Checks whether user exists in database
    public boolean userExists(int userId) {

        String sql = "SELECT user_id FROM users WHERE user_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            return rs.next(); // true if record exists

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
