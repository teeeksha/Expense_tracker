package com.expense.Expense_Tracker.DAO;
import com.expense.Expense_Tracker.models.Expense;
import com.expense.Expense_Tracker.utils.DBConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class ExpenseDAO {
    // Adds a new expense record into the database
    public boolean addExpense(int userId, double amount,
                              int categoryId, int methodId,
                              String description, LocalDate date) {
        // SQL query to insert expense data
        String sql = "INSERT INTO expenses (user_id, amount, category_id, method_id, description, date) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        // Creating database connection and prepared statement
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            // Setting values to query parameters
            ps.setInt(1, userId);
            ps.setDouble(2, amount);
            ps.setInt(3, categoryId);
            ps.setInt(4, methodId);
            ps.setString(5, description);
            ps.setDate(6, Date.valueOf(date));

            // Returns true if record is inserted successfully
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            // Handles SQL related errors
            e.printStackTrace();
        }
        return false;
    }
    // Displays all expense categories from database
    public void showCategories() {
        // Query to fetch expense type categories
        String sql = "SELECT category_id, category_name FROM categories WHERE type='expense'";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\nExpense Categories:");
            System.out.println("ID | Name");
            // Reading category records
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
    // Displays available payment methods
    public void showPaymentMethods() {
        // Query to fetch payment methods
        String sql = "SELECT method_id, method_name FROM payment_methods";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\nSelect Payment Method:");
            // Reading payment methods
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
    // Fetches all expense records and returns them as a list
    public List<Expense> getAllExpenses() {
        // List to store expense objects
        List<Expense> list = new ArrayList<>();
        // SQL query using join to fetch expense details
        String sql =
                "SELECT e.expense_id, e.amount, c.category_name, e.description, e.date " +
                "FROM expenses e " +
                "JOIN categories c ON e.category_id = c.category_id " +
                "ORDER BY e.date DESC";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            // Converting each record into Expense object
            while (rs.next()) {
                list.add(new Expense(
                        rs.getInt("expense_id"),
                        rs.getDouble("amount"),
                        rs.getString("category_name"),
                        rs.getString("description"),
                        rs.getDate("date").toLocalDate()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Returning list of expenses
        return list;
    }
}

