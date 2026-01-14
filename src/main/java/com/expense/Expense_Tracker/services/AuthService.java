package com.expense.Expense_Tracker.services;

import com.expense.Expense_Tracker.DAO.UserDAO;

public class AuthService {

    private UserDAO userDAO = new UserDAO();

    public String login(int userId) {

        if (userDAO.userExists(userId)) {
            return userDAO.getUserNameById(userId);
        }

        return null;
    }
}