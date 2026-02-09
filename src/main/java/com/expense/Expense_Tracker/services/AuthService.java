package com.expense.Expense_Tracker.services;
import com.expense.Expense_Tracker.DAO.UserDAO;
public class AuthService {

    private UserDAO userDAO = new UserDAO(); // DAO object to access user data
    public String login(int userId) {

        // Check if user exists in database
        if (userDAO.userExists(userId)) {
            // Return username if user is found
            return userDAO.getUserNameById(userId);
        }

        // Return null if user does not exist
        return null;
    }
}
