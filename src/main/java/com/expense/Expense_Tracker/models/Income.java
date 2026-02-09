package com.expense.Expense_Tracker.models;

import java.time.LocalDate;

// Model class for Income data
public class Income {

    private int incomeId;     // Income ID from database
    private int userId;       // User who owns this income
    private double amount;    // Income amount
    private String source;    // Income source
    private LocalDate date;   // Income date

    // Constructor used while fetching income from database
    public Income(int incomeId, int userId, double amount, String source, LocalDate date) {
        this.incomeId = incomeId;
        this.userId = userId;
        this.amount = amount;
        this.source = source;
        this.date = date;
    }

    // Constructor used while inserting new income
    public Income(int userId, double amount, String source, LocalDate date) {
        this.userId = userId;
        this.amount = amount;
        this.source = source;
        this.date = date;
    }

    // Getter methods
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


