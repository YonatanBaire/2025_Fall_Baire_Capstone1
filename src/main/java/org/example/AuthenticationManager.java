package org.example;

import java.util.HashMap;
import java.util.Scanner;

public class AuthenticationManager {
    private HashMap<String, String> users;
    private String currentUser;
    private Scanner scanner;

    public AuthenticationManager() {
        this.users = new HashMap<>();
        this.scanner = new Scanner(System.in);
        this.currentUser = null;
        users.put("admin", "admin123");
    }

    public boolean login() {
        System.out.println("\n=== LOGIN ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            currentUser = username;
            System.out.println("Login successful! Welcome, " + username + "!");
            return true;
        } else {
            System.out.println("Invalid username or password!");
            return false;
        }
    }

    public void registerNewUser() {
        System.out.println("\n=== REGISTER ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        if (users.containsKey(username)) {
            System.out.println("Username already exists!");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        if (password.length() < 4) {
            System.out.println("Password must be at least 4 characters!");
            return;
        }

        users.put(username, password);
        System.out.println("User registered successfully!");
    }

    public boolean showAuthMenu() {
        while (true) {
            System.out.println("\n=== ACCOUNTING LEDGER ===");
            System.out.println("1) Login");
            System.out.println("2) Register");
            System.out.println("3) Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    if (login()) {
                        return true;
                    }
                    break;
                case "2":
                    registerNewUser();
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    return false;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    public void logout() {
        if (currentUser != null) {
            System.out.println("Goodbye, " + currentUser + "!");
            currentUser = null;
        }
    }

    public String getCurrentUser() {
        return currentUser;
    }
}