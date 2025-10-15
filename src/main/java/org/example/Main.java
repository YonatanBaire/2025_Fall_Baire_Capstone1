package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Transactions> transaction = TransactionManager.getTransaction();
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.println("""
                    D)Add Deposit
                    P)Make Payment (Debit)
                    L)Ledger
                    X)Exit
                    """);
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
                default:
                    System.out.println("Invalid option. Please choose D, P, L, or X.");
                    break;
            }
        }


    }

//    public static void showAllTransactions(List<Transactions> transactions) {
//        System.out.println("\nAll Transactions:");
//        System.out.println("----------------------------------------");
//        for (Transactions transaction : transactions) {
//            System.out.println(transaction);
//        }
//        System.out.println("----------------------------------------\n");
//    }

    public static void addDeposit(List<Transactions> transactions) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Add Deposit ===");
        LocalDate localDate = java.time.LocalDate.now();
        LocalTime localTime = java.time.LocalTime.now().withSecond(0).withNano(0);

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
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


