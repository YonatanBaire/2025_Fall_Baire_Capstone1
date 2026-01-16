package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Ledger {

    public static void showLedgerMenu(List<Transactions> transactions) {
        Scanner scanner = new Scanner(System.in);
        boolean inLedger = true;
//
        while (inLedger) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println(Colors.RED + Colors.BOLD + "               LEDGER MENU" + Colors.RESET);
            System.out.println("=".repeat(50));
            System.out.println("""
                    A) All - Display all entries
                    D) Deposits - Display only deposits
                    P) Payments - Display only payments
                    R) Reports - Run reports
                    H) Home - Return to main menu
                    """);
            System.out.println("=".repeat(50));
            System.out.print("Please select an option: ");

            String input = scanner.nextLine().toUpperCase();

            switch (input) {
                case "A":
                    showAll(transactions);
                    break;
                case "D":
                    showDeposits(transactions);
                    break;
                case "P":
                    showPayments(transactions);
                    break;
                case "R":
                    Records.showReportsMenu(transactions);  // Calls Reports class
                    break;
                case "H":
                    inLedger = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void showAll(List<Transactions> transactions) {
        System.out.println("\n=== All Transactions (Newest First) ===");
        System.out.println("----------------------------------------");

        List<Transactions> sorted = new ArrayList<>(transactions);
        Collections.reverse(sorted);

        for (Transactions t : sorted) {
            System.out.println(t);
        }
        System.out.println("----------------------------------------\n");
    }

    public static void showDeposits(List<Transactions> transactions) {
        System.out.println("\n=== Deposits Only (Newest First) ===");
        System.out.println("----------------------------------------");

        List<Transactions> sorted = new ArrayList<>(transactions);
        Collections.reverse(sorted);

        for (Transactions t : sorted) {
            if (t.getAmount() > 0) {
                System.out.println(t);
            }
        }
        System.out.println("----------------------------------------\n");
    }

    public static void showPayments(List<Transactions> transactions) {
        System.out.println("\n=== Payments Only (Newest First) ===");
        System.out.println("----------------------------------------");

        List<Transactions> sorted = new ArrayList<>(transactions);
        Collections.reverse(sorted);

        for (Transactions t : sorted) {
            if (t.getAmount() < 0) {
                System.out.println(t);
            }
        }
        System.out.println("----------------------------------------\n");
    }

    public class Colors {
        public static final String RESET = "\u001B[0m";
        public static final String BOLD = "\u001B[1m";
        public static final String CYAN = "\u001B[36m";
        public static final String RED = "\u001B[31m";
//        public static final String BG_PURPLE = "\u001B[45m";
    }
}

