package com.expense.Expense_Tracker.services;
import com.expense.Expense_Tracker.DAO.ExpenseDAO;
import com.expense.Expense_Tracker.models.Expense;
import java.time.LocalDate;
import java.util.List;

// Service layer for expense-related business logic
public class ExpenseService {
    // DAO object to interact with database
    private ExpenseDAO expenseDAO = new ExpenseDAO();
    // Method to add a new expense for a user
    public boolean addExpense(int userId,
                              double amount,
                              int categoryId,
                              int methodId,
                              String description) {
        // Create Expense object with current date
        Expense expense = new Expense(
                userId,
                amount,
                categoryId,
                methodId,
                description,
                LocalDate.now() // system current date
        );
        // Call DAO to save expense in database
        return expenseDAO.addExpense(expense);
    }
    // Method to fetch all expenses of a user
    public List<Expense> getAllExpenses(int userId) {
        return expenseDAO.getExpensesByUser(userId);
    }
}



