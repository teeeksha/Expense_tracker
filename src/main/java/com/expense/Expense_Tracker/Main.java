package com.expense.Expense_Tracker;

import com.expense.Expense_Tracker.DAO.ExpenseDAO;
import com.expense.Expense_Tracker.models.Expense;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ExpenseDAO expenseDAO = new ExpenseDAO();

        // ===== Runtime User Name =====
        System.out.print("Enter your name: ");
        String currentUserName = scanner.nextLine();

        int userId = 1; // fixed user id

        while (true) {

            System.out.println("\n=================================");
            System.out.println(" 💰 EXPENSE TRACKER FOR " + currentUserName);
            System.out.println("=================================");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();

            if (!input.matches("\\d")) {
                System.out.println("Invalid input!");
                continue;
            }

            int choice = Integer.parseInt(input);

            switch (choice) {

                // ================= ADD EXPENSE =================
                case 1:
                    System.out.print("Enter Amount: ");
                    double amount = Double.parseDouble(scanner.nextLine());

                    expenseDAO.showCategories();
                    System.out.print("Choose Category ID: ");
                    int categoryId = Integer.parseInt(scanner.nextLine());

                    expenseDAO.showPaymentMethods();
                    System.out.print("Choose Payment Method ID: ");
                    int methodId = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter Description: ");
                    String description = scanner.nextLine();

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

                // ================= VIEW EXPENSES =================
                case 2:
                    List<Expense> list = expenseDAO.getAllExpenses();

                    System.out.println("\n--- Expenses for " + currentUserName + " ---");

                    if (list.isEmpty()) {
                        System.out.println("No expenses found.");
                    } else {
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

                // ================= EXIT =================
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
