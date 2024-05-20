package workshopweek2;

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
        transactions.add(transaction);
    }

    // 1. Implementar getTotalBalance utilizando streams y reduce
    public Optional<Double> getTotalBalance() {

        Map<String, Function<Transaction, Double>> transactionHandler = new HashMap<>();
        transactionHandler.put("deposit", Transaction::getAmount);
        transactionHandler.put("withdrawal", transaction -> -transaction.getAmount());

        return Optional.of(transactions.stream()
                .mapToDouble(transaction ->
                        Optional.ofNullable(transactionHandler.get(transaction.getType()))
                                .map(handler -> handler.apply(transaction))
                                .orElse(0.0))
                .reduce(0.0, Double::sum));
    }

    //2. Implementar getDeposits utilizando streams y filter
    public Optional<List<Transaction>> getDeposits() {
        return Optional.of(transactions
                .stream()
                .filter(transactions -> transactions.getType().equals("deposit")))
                .map(transactions -> transactions.toList());
    }

    //3. Implementar getWithdrawals utilizando streams y filter
    public Optional<List<Transaction>> getWithdrawals() {
        return Optional.of(transactions
                .stream()
                .filter(transactions ->transactions.getType().equals("withdrawal")))
                .map(transactions -> transactions.toList());
    }

    // 4. Implementar filterTransactions utilizando Function y streams
    public Optional<List<Transaction>> filterTransactions(Function<Transaction, Boolean> predicate) {
        return Optional.of(transactions
                .stream()
                .filter(predicate::apply)
                .collect(Collectors.toList()));
    }

    // 5. Implementar getTotalDeposits utilizando getDeposits y mapToDouble
    public Optional<Double> getTotalDeposits() {
        return getDeposits().map(deposits ->
                deposits.stream()
                        .mapToDouble(Transaction::getAmount)
                        .sum());
    }

    // 6. Implementar getLargestWithdrawal utilizando getWithdrawals y max
    public Optional<Transaction> getLargestWithdrawal() {
        return getWithdrawals()
                .flatMap(withdrawals ->
                        withdrawals.stream()
                                .max(Comparator.comparing(Transaction::getAmount)));

    }

    //7. Implementar getTransactionsOnDate utilizando streams y filter
    public Optional<List<Transaction>> getTransactionsOnDate(LocalDate date) {
        return Optional.of(transactions
                .stream()
                .filter(transaction -> transaction.getDate().equals(date))
                .toList());
    }

    // 8. Implementar getAverageTransactionAmount utilizando streams y mapToDouble
    public OptionalDouble getAverageTransactionAmount() {
        Map<String, Function<Transaction, Double>> transactionHandler = new HashMap<>();
        transactionHandler.put("deposit", Transaction::getAmount);
        transactionHandler.put("withdrawal", transaction -> -transaction.getAmount());

        return OptionalDouble.of(transactions
                .stream()
                .mapToDouble(transactions -> transactions.getAmount())
                .average()
                .getAsDouble());
    }

    //9. Implementar getTransactionsWithAmountGreaterThan utilizando streams y filter
    public Optional<List<Transaction>> getTransactionsWithAmountGreaterThan(double amount) {
        return Optional.of(transactions.stream()
                .filter(transaction -> transaction.getAmount() > amount)
                .toList());
    }

    // TODO 10: Implementar transfer utilizando addTransaction
    public static void transfer(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
        // TODO 10
    /**
        sourceAccount.getTotalBalance()
                .stream()
                .mapToDouble()


**/


    }

    // 11. Implementar getTotalWithdrawals utilizando getWithdrawals y mapToDouble
    public Optional<Double> getTotalWithdrawals() {
        Map<String, Function<Transaction, Double>> transactionHandler = new HashMap<>();
        transactionHandler.put("withdrawal", transaction -> -transaction.getAmount());

        return Optional.of(transactions.stream()
                .mapToDouble(transaction -> Optional.ofNullable(transactionHandler.get(transaction.getType()))
                        .map(handler -> handler.apply(transaction))
                        .orElse(0.0))
                .sum());
    }

    // 12. Implementar getTransactionsSummary utilizando streams, map y collect
    public Map<String, Double> getTransactionsSummary() {
        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getType, Collectors.summingDouble(Transaction::getAmount)));
    }

}
