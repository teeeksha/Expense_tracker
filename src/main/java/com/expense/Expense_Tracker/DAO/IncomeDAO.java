package com.expense.Expense_Tracker.DAO;

import com.expense.Expense_Tracker.models.Income;
import com.expense.Expense_Tracker.utils.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IncomeDAO {

    // Adds a new income record into the database
    public boolean addIncome(Income income) {

        // SQL query to insert income data
        String sql = """
            INSERT INTO income (user_id, amount, source, date)
            VALUES (?, ?, ?, ?)
        """;

        // Creating database connection and prepared statement
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Setting values from Income object
            ps.setInt(1, income.getUserId());   // user who earned income
            ps.setDouble(2, income.getAmount()); // income amount
            ps.setString(3, income.getSource()); // income source
            ps.setDate(4, Date.valueOf(income.getDate())); // income date

            // Returns true if income is added successfully
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            // Handles SQL related errors
            e.printStackTrace();
        }
        return false;
    }

    // Fetches all income records for a specific user
    public List<Income> getIncomeByUser(int userId) {

        // List to store income records
        List<Income> list = new ArrayList<>();

        // SQL query to fetch income user-wise
        String sql = """
            SELECT income_id, amount, source, date
            FROM income
            WHERE user_id = ?
            ORDER BY date DESC
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Setting user id to filter records
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            // Converting each record into Income object
            while (rs.next()) {
                list.add(new Income(
                        rs.getInt("income_id"),   // unique income id
                        userId,                   // user id
                        rs.getDouble("amount"),   // income amount
                        rs.getString("source"),   // income source
                        rs.getDate("date").toLocalDate() // income date
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Returning list of income records
        return list;
    }
}
