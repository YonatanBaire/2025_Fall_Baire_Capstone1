package org.example;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

//        AuthenticationManager auth = new AuthenticationManager();
//        if (!auth.showAuthMenu()) {
//            return;
//        }


        List<Transactions> transaction = TransactionManager.getTransaction();
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println(Ledger.Colors.CYAN + Ledger.Colors.BOLD + "          ACCOUNT LEDGER APPLICATION" + Ledger.Colors.RESET);
            System.out.println("""
                    D)Add Deposit
                    P)Make Payment (Debit)
                    L)Ledger
                    S)Search Transactions
                    X)Exit
                    """);
            System.out.println("=".repeat(50));
            System.out.print("Please select an option: ");
            String input = scanner.nextLine().toUpperCase();

            switch (input) {
                case ("D"):
                    addDeposit(transaction);
                    break;
                case ("P"):
                    makePayment(transaction);
                    break;

                case ("L"):
                    Ledger.showLedgerMenu(transaction);
                    break;

                case ("X"):
                    System.out.println("Exiting...");
                    running = false;
                    break;
                    case "S":
    searchTransactions(transaction);
    break;
                default:
                    System.out.println("Invalid option. Please choose D, P, L, or X.");
                    break;
            }
        }


    }

    public static void addDeposit(List<Transactions> transactions) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Add Deposit ===");
        LocalDate localDate = java.time.LocalDate.now();
        LocalTime localTime = java.time.LocalTime.now().withSecond(0).withNano(0);

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount:$ ");
        double amount = Math.abs(Double.parseDouble(scanner.nextLine())); // Make positive

        Transactions newTransaction = new Transactions(localDate, localTime, description, vendor, amount);


        transactions.add(newTransaction);
        TransactionManager.saveTransaction(newTransaction);

        System.out.println("\nDeposit added successfully!\n");
    }

    public static void makePayment(List<Transactions> transactions) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Make Payment ===");
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now().withSecond(0).withNano(0);

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = -Math.abs(Double.parseDouble(scanner.nextLine())); // Make negative


        Transactions newTransaction = new Transactions(localDate, localTime, description, vendor, amount);


        transactions.add(newTransaction);
        TransactionManager.saveTransaction(newTransaction);

        System.out.println("\nPayment added successfully!\n");
    }
    public static void searchTransactions(List<Transactions> transactions) {
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== SEARCH TRANSACTIONS ===");
        System.out.println("""
                1) Search by Vendor
                2) Search by Description
                3) Search by Date Range
                4) Search by Amount Range
                5) Back to Main Menu
                """);
        System.out.println("=".repeat(50));
        System.out.print("Choose search option: ");

        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                searchByVendor(transactions, scanner);
                break;
            case "2":
                searchByDescription(transactions, scanner);
                break;
            case "3":
                searchByDateRange(transactions, scanner);
                break;
            case "4":
                searchByAmountRange(transactions, scanner);
                break;
            case "5":
                return;
            default:
                System.out.println("Invalid option!");
        }
    }
}

public static void searchByVendor(List<Transactions> transactions, Scanner scanner) {
    System.out.print("\nEnter vendor name (partial match): ");
    String vendor = scanner.nextLine().trim().toLowerCase();

    System.out.println("\n=== Search Results ===");
    boolean found = false;

    for (Transactions t : transactions) {
        if (t.getVendor().toLowerCase().contains(vendor)) {
            displayTransaction(t);
            found = true;
        }
    }

    if (!found) {
        System.out.println("No transactions found for vendor: " + vendor);
    }
}

public static void searchByDescription(List<Transactions> transactions, Scanner scanner) {
    System.out.print("\nEnter description (partial match): ");
    String description = scanner.nextLine().trim().toLowerCase();

    System.out.println("\n=== Search Results ===");
    boolean found = false;

    for (Transactions t : transactions) {
        if (t.getDescription().toLowerCase().contains(description)) {
            displayTransaction(t);
            found = true;
        }
    }

    if (!found) {
        System.out.println("No transactions found with description: " + description);
    }
}

public static void searchByDateRange(List<Transactions> transactions, Scanner scanner) {
    try {
        System.out.print("\nEnter start date (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine().trim());

        System.out.print("Enter end date (YYYY-MM-DD): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine().trim());

        System.out.println("\n=== Search Results ===");
        boolean found = false;

        for (Transactions t : transactions) {
            if (!t.getDate().isBefore(startDate) && !t.getDate().isAfter(endDate)) {
                displayTransaction(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No transactions found in date range.");
        }
    } catch (Exception e) {
        System.out.println("Invalid date format! Please use YYYY-MM-DD");
    }
}

public static void searchByAmountRange(List<Transactions> transactions, Scanner scanner) {
    try {
        System.out.print("\nEnter minimum amount: $");
        double minAmount = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Enter maximum amount: $");
        double maxAmount = Double.parseDouble(scanner.nextLine().trim());

        System.out.println("\n=== Search Results ===");
        boolean found = false;

        for (Transactions t : transactions) {
            double absAmount = Math.abs(t.getAmount());
            if (absAmount >= minAmount && absAmount <= maxAmount) {
                displayTransaction(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No transactions found in amount range.");
        }
    } catch (NumberFormatException e) {
        System.out.println("Invalid amount format!");
    }
}

public static void displayTransaction(Transactions t) {
    System.out.printf("Date: %s | Time: %s | %s | %s | Amount: $%.2f%n",
        t.getDate(),
        t.getTime(),
        t.getDescription(),
        t.getVendor(),
        t.getAmount());
}

}