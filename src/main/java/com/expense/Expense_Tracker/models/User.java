package com.expense.Expense_Tracker.models;
public class User {
private int userId;   // Unique user ID
private String name;  // User name

    // Constructor to set user details
    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    // Getter methods
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
