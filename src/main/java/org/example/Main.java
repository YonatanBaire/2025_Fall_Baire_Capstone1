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
            List<Transactions> transactions = TransactionManager.getTransaction();
            boolean running = true;
            Scanner scanner = new Scanner(System.in);

            while (running) {
                System.out.println("\n" + "=".repeat(50));
                System.out.println(Ledger.Colors.CYAN + Ledger.Colors.BOLD +"          ACCOUNT LEDGER APPLICATION" + Ledger.Colors.RESET);
                System.out.println("""
                    D)Add Deposit
                    P)Make Payment (Debit)
                    L)Ledger
                    T) Delete Transaction
                    X)Exit
                    """);
                System.out.println("=".repeat(50));
                System.out.print("Please select an option: ");
                String input = scanner.nextLine().toUpperCase();

                switch (input) {
                    case "T":
                        deleteTransaction(transactions);
                        break;
                    case "D":
                        addDeposit(transactions);
                        break;
                    case "P":
                        makePayment(transactions);
                        break;
                    case "L":
                        Ledger.showLedgerMenu(transactions);
                        break;
                    case "X":
                        System.out.println("Exiting...");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please choose D, P, L, T, or X.");
                        break;
                }
            }
        }

        // Move this method outside of main
        public static void deleteTransaction(List<Transactions> transactions) {
            Scanner scanner = new Scanner(System.in);

            // Show transactions with index
            System.out.println("\n=== Delete Transaction ===");
            for (int i = 0; i < transactions.size(); i++) {
                System.out.printf("%d) %s%n", i + 1, transactions.get(i));
            }

            try {
                System.out.print("Enter transaction number to delete: ");
                int index = Integer.parseInt(scanner.nextLine()) - 1;

                if (index >= 0 && index < transactions.size()) {
                    // Call TransactionManager method to delete
                    TransactionManager.deleteTransaction(transactions, index);
                } else {
                    System.out.println("Invalid transaction number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
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

        // Create the transaction
        Transactions newTransaction = new Transactions(localDate, localTime, description, vendor, amount);

        // Add to list and save to file
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

        // Create the transaction
        Transactions newTransaction = new Transactions(localDate, localTime, description, vendor, amount);

        // Add to list and save to file
        transactions.add(newTransaction);
        TransactionManager.saveTransaction(newTransaction);

        System.out.println("\nPayment added successfully!\n");

    }

}