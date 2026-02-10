package com.expense.Expense_Tracker.models;

import java.time.LocalDate;
public class Expense {
    // Unique ID of expense (from database)
    private int expenseId;
    // Amount spent
    private double amount;
    // Category name of expense (Food, Travel, etc.)
    private String category;
    // Short description of expense
    private String description;
    // Date when expense was made
    private LocalDate date;
    // Constructor used while fetching data from database
    public Expense(int expenseId, double amount, String category, String description, LocalDate date) 
    {
      this.expenseId = expenseId;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }
    // Getter methods to access private variables
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

