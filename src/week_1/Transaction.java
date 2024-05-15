package week_1;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Transaction {
    private double amount;
    private String type;
    private String date;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

class BankAccount {
    private List<Transaction> transactions;

    public BankAccount() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public double getTotalBalance() {

        Map<String, Function<Transaction, Double>> transactionHandler = new HashMap<>();
        transactionHandler.put("deposit", Transaction::getAmount);
        transactionHandler.put("withdrawal", transaction -> -transaction.getAmount());

        return transactions.stream()
                .mapToDouble(transaction -> Optional.ofNullable(transactionHandler.get(transaction.getType()))
                        .map(handler -> handler.apply(transaction))
                        .orElse(0.0))
                .sum();
    }

    public List<Transaction> getDeposits() {
        return transactions.stream()
                .filter(transaction -> transaction.getType().equals("deposit"))
                .collect(Collectors.toList());
    }

    public Optional<Double> getTransactionWithHighestAmount() {
        return transactions.stream()
                .map(Transaction::getAmount)
                .max(Double::compare);
    }
}

class Main {
    public static void main(String[] args) {

    }
}
