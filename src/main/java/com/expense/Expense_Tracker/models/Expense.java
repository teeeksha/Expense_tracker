package com.expense.Expense_Tracker.models;

import java.time.LocalDate;

public class Expense {

    private int expenseId;
    private double amount;
    private String category;
    private String description;
    private LocalDate date;

    public Expense(int expenseId, double amount,
                   String category, String description,
                   LocalDate date) {

        this.expenseId = expenseId;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }
}
