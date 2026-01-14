package com.expense.Expense_Tracker.models;

import java.time.LocalDate;

public class Income {

    private int incomeId;
    private int userId;
    private double amount;
    private String source;
    private LocalDate date;

    // Constructor for fetch
    public Income(int incomeId, int userId, double amount, String source, LocalDate date) {
        this.incomeId = incomeId;
        this.userId = userId;
        this.amount = amount;
        this.source = source;
        this.date = date;
    }

    // Constructor for insert
    public Income(int userId, double amount, String source, LocalDate date) {
        this.userId = userId;
        this.amount = amount;
        this.source = source;
        this.date = date;
    }

    public int getIncomeId() {
        return incomeId;
    }

    public int getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }

    public String getSource() {
        return source;
    }

    public LocalDate getDate() {
        return date;
    }
}
