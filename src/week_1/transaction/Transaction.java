package week_1.transaction;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Transaction  {
    private double amount;
    private String type;
    private LocalDate date;

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

class Main {
    public static void main(String[] args) {

        var bank = new BankAccount();

        Transaction t1 = new Transaction();
        t1.setAmount(1000.0);
        t1.setType("deposit");
        t1.setDate(LocalDate.of(2024, 5, 1));
        bank.addTransaction(t1);

        Transaction t2 = new Transaction();
        t2.setAmount(200.0);
        t2.setType("withdrawal");
        t2.setDate(LocalDate.of(2024, 5, 2));
        bank.addTransaction(t2);

        Transaction t3 = new Transaction();
        t3.setAmount(500.0);
        t3.setType("deposit");
        t3.setDate(LocalDate.of(2024, 5, 3));
        bank.addTransaction(t3);

        Transaction t4 = new Transaction();
        t4.setAmount(300.0);
        t4.setType("withdrawal");
        t4.setDate(LocalDate.of(2024, 5, 4));
        bank.addTransaction(t4);


        Transaction t5 = new Transaction();
        t5.setAmount(450.0);
        t5.setType("deposit");
        t5.setDate(LocalDate.of(2024, 7, 15));
        bank.addTransaction(t5);

        Transaction t6 = new Transaction();
        t6.setAmount(250.0);
        t6.setType("withdrawal");
        t6.setDate(LocalDate.of(2024, 3, 3));
        bank.addTransaction(t6);

        System.out.println(t6);
        System.out.println("Deposit " + bank.getDeposits());

        Transaction t7 = new Transaction();
        t7.setAmount(300.0);
        t7.setType("deposit");
        t7.setDate(LocalDate.of(2024, 5, 4));
        bank.addTransaction(t7);


        // 1st Obtain the total amount of deposits realized

        var totalDeposits = bank.getDeposits().stream().reduce(0,(accumulator,deposit)-> (int) (accumulator + deposit.getAmount()), (a, b) -> a + b);

        double totalDeposits2 = bank.getDeposits()
                .stream()
                .map(Transaction::getAmount)
                .reduce(0.0, Double::sum);

        System.out.println("The total amount on deposits is: "+ totalDeposits);
        System.out.println("The total amount on deposits is: "+ totalDeposits2);

        //2. Encotrar la transaccion de retiro de mayor monto getTransactionWithHighestAmount()


        double highestWithdrawal2 = bank.getWithdrawal()
                .stream()
                .max(Comparator.comparing(Transaction::getAmount))
                .map(Transaction::getAmount)
                .orElse(0.0);

        System.out.println("Highest Withdrawal: " + highestWithdrawal2);


        var highestWithdrawl = bank.getWithdrawal()
                .stream()
                .max(Comparator.comparing(Transaction::getAmount))
                .get();

        System.out.println("Highest Withdrawal " + highestWithdrawl);

        //3. Contar el numero de transacciones realizadas en una fecha especifica

        var onThisDay = bank.getTransactionsOnDate(LocalDate.of(2024, 5, 2));
        System.out.println("on This day "+ onThisDay);

        System.out.println("date " + t3.getDate());

        var getTransactionsByDate = bank.getTransactionsOnDate(LocalDate.of(2024,5,4));
        System.out.println("Transaction by a specific date: "+ getTransactionsByDate);

        var amountOfTransactions = bank.getAmountOfTransactionsByDate(LocalDate.of(2024,5,4));
        System.out.println("Amount of Transactions by a date: "+ amountOfTransactions);


        //4. Obtener el promedio de los montos de todas las transacciones getTotalBalance()

        System.out.println("getTotalBalance "+bank.getTotalBalance());
        System.out.println("Average Amount on the transactions "+bank.getTotalBalanceAverage());


        //5. Filtrar transacciones con montos negativos (retiros)

        System.out.println("Get amount of transactions with negative values (withdrawal) -> "+bank.getWithdrawalNegative());

    }
}
