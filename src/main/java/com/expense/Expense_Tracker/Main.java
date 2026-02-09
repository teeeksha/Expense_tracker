package com.expense.Expense_Tracker;

import com.expense.Expense_Tracker.DAO.ExpenseDAO;
import com.expense.Expense_Tracker.models.Expense;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

// Main class – entry point of application
public class Main {
    public static void main(String[] args) {

        // Used to take input from user
        Scanner scanner = new Scanner(System.in);

        // DAO object to access expense related database operations
        ExpenseDAO expenseDAO = new ExpenseDAO();

        // Taking user name at runtime (for display purpose)
        System.out.print("Enter your name: ");
        String currentUserName = scanner.nextLine();

        // Fixed user id (single user system)
        int userId = 1;

        // Infinite loop to keep application running
        while (true) {

            // Menu display
            System.out.println("\n=================================");
            System.out.println(" 💰 EXPENSE TRACKER FOR " + currentUserName);
            System.out.println("=================================");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();

            // Input validation (only single digit allowed)
            if (!input.matches("\\d")) {
                System.out.println("Invalid input!");
                continue;
            }

            int choice = Integer.parseInt(input);

            switch (choice) {

                // ===== ADD EXPENSE =====
                case 1:
                    System.out.print("Enter Amount: ");
                    double amount = Double.parseDouble(scanner.nextLine());

                    // Show expense categories from database
                    expenseDAO.showCategories();
                    System.out.print("Choose Category ID: ");
                    int categoryId = Integer.parseInt(scanner.nextLine());

                    // Show payment methods from database
                    expenseDAO.showPaymentMethods();
                    System.out.print("Choose Payment Method ID: ");
                    int methodId = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter Description: ");
                    String description = scanner.nextLine();

                    // Insert expense into database
                    boolean added = expenseDAO.addExpense(
                            userId,
                            amount,
                            categoryId,
                            methodId,
                            description,
                            LocalDate.now()
                    );

                    if (added) {
                        System.out.println("₹" + amount + " added for " + currentUserName + " ✔");
                    } else {
                        System.out.println("Failed to add expense!");
                    }
                    break;

                // ===== VIEW ALL EXPENSES =====
                case 2:
                    List<Expense> list = expenseDAO.getAllExpenses();

                    System.out.println("\n--- Expenses for " + currentUserName + " ---");

                    if (list.isEmpty()) {
                        System.out.println("No expenses found.");
                    } else {
                        // Display each expense
                        for (Expense e : list) {
                            System.out.println(
                                    e.getCategory() + " | ₹" +
                                    e.getAmount() + " | " +
                                    e.getDescription() + " | " +
                                    e.getDate()
                            );
                        }
                    }
                    break;
                // ===== EXIT APPLICATION =====
                case 3:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
