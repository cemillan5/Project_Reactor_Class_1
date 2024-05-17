package banking;

import java.time.LocalDate;
import java.util.Comparator;

public class Main {
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


        var highestWithdrwal = bank.getWithdrawal()
                .stream()
                .max(Comparator.comparing(Transaction::getAmount))
                .isPresent();

        System.out.println("Highest Withdrawal " + highestWithdrwal);

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
