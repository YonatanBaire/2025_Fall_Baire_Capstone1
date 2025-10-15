package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    public static List<Transactions> getTransaction() {
        List<Transactions> transaction = new ArrayList<>();

        try {
            FileReader fr = new FileReader("src/main/resources/transactions.csv");
        BufferedReader reader = new BufferedReader(fr);
        String line;
            while ((line = reader.readLine()) != null) {
                String[] transactionData = line.split("\\|");
                Transactions newTransaction = new Transactions();
                newTransaction.setDate(LocalDate.parse(transactionData[0]));
                newTransaction.setTime(LocalTime.parse(transactionData[1]));
                newTransaction.setDescription(transactionData[2]);
                newTransaction.setVendor(transactionData[3]);
                newTransaction.setAmount(Double.parseDouble(transactionData[4]));

                transaction.add(newTransaction);
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find file");
        } catch (IOException ex) {
            System.out.println("File had a problem with it.");
        }
                return transaction;
    }
    public static void saveTransaction(Transactions transaction) {
        try {
            FileWriter fw = new FileWriter("src/main/resources/transactions.csv", true);
            BufferedWriter writer = new BufferedWriter(fw);

            String line = String.format("%s|%s|%s|%s|%.2f%n",
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount());

            writer.write(line);
            writer.close();

            System.out.println("Transaction saved successfully!");

        } catch (IOException ex) {
            System.out.println("Error saving transaction: " + ex.getMessage());
        }
    }
}


