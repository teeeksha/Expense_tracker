package com.expense.Expense_Tracker.services;

import com.expense.Expense_Tracker.DAO.ExpenseDAO;
import com.expense.Expense_Tracker.models.Expense;

import java.time.LocalDate;
import java.util.List;

public class ExpenseService {

    private ExpenseDAO expenseDAO = new ExpenseDAO();

    public boolean addExpense(int userId,
                              double amount,
                              int categoryId,
                              int methodId,
                              String description) {

        Expense expense = new Expense(
                userId,
                amount,
                categoryId,
                methodId,
                description,
                LocalDate.now()
        );

        return expenseDAO.addExpense(expense);
    }

    public List<Expense> getAllExpenses(int userId) {
        return expenseDAO.getExpensesByUser(userId);
    }
}

