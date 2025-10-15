package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Records {
    public static void showReportsMenu(List<Transactions> transactions) {
        Scanner scanner = new Scanner(System.in);
        boolean inReports = true;

        while (inReports) {
            System.out.println("""
                    
                    === Reports Menu ===
                    1) Month To Date
                    2) Previous Month
                    3) Year To Date
                    4) Previous Year
                    5) Search by Vendor
                    0) Back to Ledger
                    """);

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    showMonthToDate(transactions);
                    break;
                case "2":
                    showPreviousMonth(transactions);
                    break;
                case "3":
                    showYearToDate(transactions);
                    break;
                case "4":
                    showPreviousYear(transactions);
                    break;
                case "5":
                    searchByVendor(transactions);
                    break;
                case "0":
                    inReports = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void showMonthToDate(List<Transactions> transactions) {
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1);

        System.out.println("\n=== Month To Date ===");
        System.out.println("----------------------------------------");

        List<Transactions> sorted = new ArrayList<>(transactions);
        Collections.reverse(sorted);

        for (Transactions t : sorted) {
            if (!t.getDate().isBefore(startOfMonth) && !t.getDate().isAfter(now)) {
                System.out.println(t);
            }
        }
        System.out.println("----------------------------------------\n");
    }

    public static void showPreviousMonth(List<Transactions> transactions) {
        LocalDate now = LocalDate.now();
        LocalDate startOfPrevMonth = now.minusMonths(1).withDayOfMonth(1);
        LocalDate endOfPrevMonth = now.withDayOfMonth(1).minusDays(1);

        System.out.println("\n=== Previous Month ===");
        System.out.println("----------------------------------------");

        List<Transactions> sorted = new ArrayList<>(transactions);
        Collections.reverse(sorted);

        for (Transactions t : sorted) {
            if (!t.getDate().isBefore(startOfPrevMonth) && !t.getDate().isAfter(endOfPrevMonth)) {
                System.out.println(t);
            }
        }
        System.out.println("----------------------------------------\n");
    }

    public static void showYearToDate(List<Transactions> transactions) {
        LocalDate now = LocalDate.now();
        LocalDate startOfYear = now.withDayOfYear(1);

        System.out.println("\n=== Year To Date ===");
        System.out.println("----------------------------------------");

        List<Transactions> sorted = new ArrayList<>(transactions);
        Collections.reverse(sorted);

        for (Transactions t : sorted) {
            if (!t.getDate().isBefore(startOfYear) && !t.getDate().isAfter(now)) {
                System.out.println(t);
            }
        }
        System.out.println("----------------------------------------\n");
    }

    public static void showPreviousYear(List<Transactions> transactions) {
        LocalDate now = LocalDate.now();
        int prevYear = now.getYear() - 1;
        LocalDate startOfPrevYear = LocalDate.of(prevYear, 1, 1);
        LocalDate endOfPrevYear = LocalDate.of(prevYear, 12, 31);

        System.out.println("\n=== Previous Year ===");
        System.out.println("----------------------------------------");

        List<Transactions> sorted = new ArrayList<>(transactions);
        Collections.reverse(sorted);

        for (Transactions t : sorted) {
            if (!t.getDate().isBefore(startOfPrevYear) && !t.getDate().isAfter(endOfPrevYear)) {
                System.out.println(t);
            }
        }
        System.out.println("----------------------------------------\n");
    }

    public static void searchByVendor(List<Transactions> transactions) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter vendor name: ");
        String vendorName = scanner.nextLine();

        System.out.println("\n=== Transactions for: " + vendorName + " ===");
        System.out.println("----------------------------------------");

        List<Transactions> sorted = new ArrayList<>(transactions);
        Collections.reverse(sorted);

        boolean found = false;
        for (Transactions t : sorted) {
            if (t.getVendor().equalsIgnoreCase(vendorName)) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No transactions found for vendor: " + vendorName);
        }
        System.out.println("----------------------------------------\n");
    }
}
