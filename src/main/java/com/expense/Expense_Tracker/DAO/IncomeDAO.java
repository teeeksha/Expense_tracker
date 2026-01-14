package com.expense.Expense_Tracker.DAO;

import com.expense.Expense_Tracker.models.Income;
import com.expense.Expense_Tracker.utils.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IncomeDAO {

    // ADD INCOME
    public boolean addIncome(Income income) {

        String sql = """
            INSERT INTO income (user_id, amount, source, date)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, income.getUserId());
            ps.setDouble(2, income.getAmount());
            ps.setString(3, income.getSource());
            ps.setDate(4, Date.valueOf(income.getDate()));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // VIEW ALL INCOME (USER WISE)
    public List<Income> getIncomeByUser(int userId) {

        List<Income> list = new ArrayList<>();

        String sql = """
            SELECT income_id, amount, source, date
            FROM income
            WHERE user_id = ?
            ORDER BY date DESC
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Income(
                        rs.getInt("income_id"),
                        userId,
                        rs.getDouble("amount"),
                        rs.getString("source"),
                        rs.getDate("date").toLocalDate()
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
