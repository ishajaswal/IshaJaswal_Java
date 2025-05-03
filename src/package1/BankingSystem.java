package package1;
import java.util.ArrayList;
import java.util.HashMap;

class BankingSystem {
    
    static class Customer {
        String name;
        String id;

        Customer(String name, String id) {
            this.name = name;
            this.id = id;
        }
    }

    static class Account {
        String accountNumber;
        double balance;

        Account(String accountNumber, double balance) {
            this.accountNumber = accountNumber;
            this.balance = balance;
        }

     
        void depositMoney(double amount) {
            this.balance += amount;
        }

        
        void withdrawMoney(double amount) {
            if (amount <= balance) {
                this.balance -= amount;
            } else {
                System.out.println("Not enough funds for this transaction.");
            }
        }

      
        void showBalance() {
            System.out.println("Current balance: " + balance);
        }
    }

   
    static class Transaction {
        String accountNumber;
        String type;
        double amount;

        Transaction(String accountNumber, String type, double amount) {
            this.accountNumber = accountNumber;
            this.type = type;
            this.amount = amount;
        }

        void printTransactionDetails() {
            System.out.println(type + " of " + amount + " in Account: " + accountNumber);
        }
    }

    
    private static ArrayList<Customer> customersList = new ArrayList<>();
    private static HashMap<String, Account> accountMap = new HashMap<>();
    private static ArrayList<Transaction> transactionHistory = new ArrayList<>();

  
    public static void registerCustomer(String name, String id) {
        customersList.add(new Customer(name, id));
    }

   
    public static void createAccount(String customerId, String accountNumber, double initialDeposit) {
        Account account = new Account(accountNumber, initialDeposit);
        accountMap.put(accountNumber, account);
        System.out.println("Account created for customer: " + customerId);
    }

    
    public static void deposit(String accountNumber, double amount) {
        Account account = accountMap.get(accountNumber);
        if (account != null) {
            account.depositMoney(amount);
            Transaction transaction = new Transaction(accountNumber, "Deposit", amount);
            transactionHistory.add(transaction);
            transaction.printTransactionDetails();
        } else {
            System.out.println("Account not found!");
        }
    }

    
    public static void withdraw(String accountNumber, double amount) {
        Account account = accountMap.get(accountNumber);
        if (account != null) {
            account.withdrawMoney(amount);
            Transaction transaction = new Transaction(accountNumber, "Withdrawal", amount);
            transactionHistory.add(transaction);
            transaction.printTransactionDetails();
        } else {
            System.out.println("Account not found");
        }
    }

    
    public static void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactionHistory) {
            transaction.printTransactionDetails();
        }
    }

    
    public static void main(String[] args) {
        
        registerCustomer("Isha Jaswal", "1");
        registerCustomer("Ram", "2");

    
        createAccount("1", "11", 1000);
        createAccount("2", "22", 500);

    
        deposit("1", 200);
        withdraw("2", 50);
        deposit("1", 500);
        withdraw("1", 300);

       
        showTransactionHistory();

        
        System.out.println("Balance for Account 11 -");
        accountMap.get("11").showBalance();
        System.out.println("Balance for Account 22-");
        accountMap.get("22").showBalance();
    }
}

