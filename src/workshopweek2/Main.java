package workshopweek2;


import java.time.LocalDate;

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


        //Verifications

        //1 Total balance
        System.out.println("Total balance ->"+bank.getTotalBalance());


        //2
        System.out.println("---- Deposits " + bank.getDeposits());

        //3 GetWinthdrawals

        System.out.println("---- Withdrawals " + bank.getWithdrawals());


        // 4 filter by type
        System.out.println(" ----> " + bank.filterTransactions(trans -> trans.getType().equals("deposit")));



        //5
        System.out.println("total deposits -> " + bank.getTotalDeposits());

        //&
        System.out.println("Largest withdrawal -> " + bank.getLargestWithdrawal());

        //7
        System.out.println("date "+bank.getTransactionsOnDate(LocalDate.of(2024, 5, 4)));

        //8 getaverage()
        System.out.println("average -> " + bank.getAverageTransactionAmount());


        //9
        System.out.println("greater amount "+ bank.getTransactionsWithAmountGreaterThan(100));


        //11 total get withdrawals

        System.out.println("total withdrawals -> " + bank.getTotalWithdrawals());


    }
}
