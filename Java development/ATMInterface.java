import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    String accountNumber;
    String pin;
    double balance;
    List<String> transactionHistory;

    User(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    boolean authenticate(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    void checkBalance() {
        System.out.println("Your current balance is: $" + balance);
    }

    void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawn: $" + amount);
            System.out.println("You have withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: $" + amount);
        System.out.println("You have deposited: $" + amount);
    }

    void viewTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

public class ATMInterface {
    static List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Example user
        User user1 = new User("123456", "1234", 1000.0);
        users.add(user1);

        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();

        User currentUser = null;
        for (User user : users) {
            if (user.accountNumber.equals(accountNumber) && user.authenticate(pin)) {
                currentUser = user;
                break;
            }
        }

        if (currentUser != null) {
            System.out.println("Login successful!");
            boolean exit = false;
            while (!exit) {
                System.out.println("1. Check Balance");
                System.out.println("2. Withdraw Cash");
                System.out.println("3. Deposit Cash");
                System.out.println("4. View Transaction History");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        currentUser.checkBalance();
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        currentUser.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        currentUser.deposit(depositAmount);
                        break;
                    case 4:
                        currentUser.viewTransactionHistory();
                        break;
                    case 5:
                        exit = true;
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid account number or PIN.");
        }
    }
}