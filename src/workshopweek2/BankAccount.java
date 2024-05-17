package workshopweek2;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BankAccount {
    private List<Transaction> transactions;

    public BankAccount() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
    // TODO 9: Implementar getTransactionsWithAmountGreaterThan utilizando streams y filter
    public Optional<List<Transaction>> getTransactionsWithAmountGreaterThan(double amount) {
        return Optional.of(transactions.stream()
                .filter(transaction -> transaction.getAmount() > amount)
                .toList());
    }

    // TODO 10: Implementar transfer utilizando addTransaction
    public static void transfer(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
        // TODO 10

    }

    // TODO 11: Implementar getTotalWithdrawals utilizando getWithdrawals y mapToDouble
    public Optional<Double> getTotalWithdrawals() {
        // TODO 11

        Map<String, Function<Transaction, Double>> transactionHandler = new HashMap<>();
        transactionHandler.put("withdrawal", transaction -> -transaction.getAmount());

        return Optional.of(transactions.stream()
                .mapToDouble(transaction -> Optional.ofNullable(transactionHandler.get(transaction.getType()))
                        .map(handler -> handler.apply(transaction))
                        .orElse(0.0))
                .sum());
    }

    // TODO 12: Implementar getTransactionsSummary utilizando streams, map y collect
    public Map<String, Double> getTransactionsSummary() {
        // TODO 12

    }

}
