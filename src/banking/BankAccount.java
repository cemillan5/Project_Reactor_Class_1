package banking;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BankAccount {
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

    public double getTotalBalanceAverage(){
        Map<String, Function<Transaction, Double>> transactionHandler = new HashMap<>();
        transactionHandler.put("deposit", Transaction::getAmount);
        transactionHandler.put("withdrawal", transaction -> -transaction.getAmount());

        return transactions.stream()
                .mapToDouble(transaction ->
                        Optional.ofNullable(transactionHandler.get(transaction.getType()))
                                .map(handler -> handler.apply(transaction))
                                .orElse(0.0))
                .average()
                .orElse(0.0);
    }

    public List<LocalDate> getDate(){
        return transactions.stream()
                .map(Transaction::getDate)
                .collect(Collectors.toList());
    }

    public List<Transaction> getTransactionsOnDate(LocalDate date) {
        return transactions.stream()
                .filter(transaction -> transaction.getDate().equals(date))
                .collect(Collectors.toList());
    }

    public long getAmountOfTransactionsByDate(LocalDate date) {
        return transactions.stream()
                .filter(transaction -> transaction.getDate().equals(date))
                .count();
    }

    public long getNumberOfTransactions(LocalDate date){
        return transactions.stream()
                .filter(d -> d.getDate().isEqual(date))
                .count();
    }


    public List<Transaction> getDeposits() {
        return transactions.stream()
                .filter(transaction -> transaction.getType().equals("deposit"))
                .collect(Collectors.toList());
    }

    public List<Transaction> getWithdrawal(){
        return transactions.stream()
                .filter(transaction -> transaction.getType().equals("withdrawal"))
                .collect(Collectors.toList());
    }


    public List<Double> getWithdrawalNegative(){
        return transactions.stream()
                .filter(transaction -> transaction.getType().equals("withdrawal"))
                .map(transaction -> - transaction.getAmount())
                .collect(Collectors.toList());
    }

    public Optional<Double> getTransactionWithHighestAmount() {
        return transactions.stream()
                .map(Transaction::getAmount)
                .max(Double::compare);
    }
}
