package com.expense.Expense_Tracker.DAO;

import com.expense.Expense_Tracker.models.Expense;
import com.expense.Expense_Tracker.utils.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {

    // ================= ADD EXPENSE =================
    public boolean addExpense(int userId, double amount,
                              int categoryId, int methodId,
                              String description, LocalDate date) {

        String sql = "INSERT INTO expenses (user_id, amount, category_id, method_id, description, date) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setDouble(2, amount);
            ps.setInt(3, categoryId);
            ps.setInt(4, methodId);
            ps.setString(5, description);
            ps.setDate(6, Date.valueOf(date));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ================= SHOW CATEGORIES =================
    public void showCategories() {

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

    // ================= SHOW PAYMENT METHODS =================
    public void showPaymentMethods() {

        String sql = "SELECT method_id, method_name FROM payment_methods";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\nSelect Payment Method:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("method_id") + ". " +
                        rs.getString("method_name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= VIEW ALL EXPENSES =================
    public List<Expense> getAllExpenses() {

        List<Expense> list = new ArrayList<>();

        String sql =
                "SELECT e.expense_id, e.amount, c.category_name, e.description, e.date " +
                "FROM expenses e " +
                "JOIN categories c ON e.category_id = c.category_id " +
                "ORDER BY e.date DESC";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Expense(
                        rs.getInt("expense_id"),
                        rs.getDouble("amount"),
                        rs.getString("category_name"),
                        rs.getString("description"),
                        rs.getDate("date").toLocalDate()
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
