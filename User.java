import java.io.*;
import java.util.*;

class User implements Serializable {
    private String name;
    private double bankAccount;
    private double wallet;
    private List<String> transactionHistory;

    public User(String name, double bankAccount, double wallet) {
        this.name = name;
        this.bankAccount = bankAccount;
        this.wallet = wallet;
        this.transactionHistory = new ArrayList<>();
    }

    public void depositMoney(double amount) {
        if (wallet - amount < 0) {
            System.out.println("Deposito annullato: fondi insufficienti nel portafoglio.");
            return;
        }
        wallet -= amount;
        bankAccount += amount;
        transactionHistory.add("Deposito di " + amount + " euro.");
    }

    public void withdrawMoney(double amount) {
        if (bankAccount - amount < 0) {
            System.out.println("Prelievo annullato: fondi insufficienti in banca.");
            return;
        }
        wallet += amount;
        bankAccount -= amount;
        transactionHistory.add("Prelievo di " + amount + " euro.");
    }

    public void showTransactionHistory() {
        System.out.println("Storico transazioni di " + name + ":");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public static User createUser(Scanner scanner) {
        System.out.print("Nome: ");
        String name = scanner.next();
        System.out.print("Deposito iniziale: ");
        double bank = scanner.nextDouble();
        System.out.print("Saldo portafoglio: ");
        double wallet = scanner.nextDouble();
        return new User(name, bank, wallet);
    }

    public static void depositToUser(Scanner scanner, List<User> users) {
        System.out.print("Inserisci numero utente: ");
        int userIndex = scanner.nextInt() - 1;
        System.out.print("Somma da depositare: ");
        double deposit = scanner.nextDouble();
        users.get(userIndex).depositMoney(deposit);
    }

    public static void withdrawFromUser(Scanner scanner, List<User> users) {
        System.out.print("Inserisci numero utente: ");
        int userIndex = scanner.nextInt() - 1;
        System.out.print("Somma da prelevare: ");
        double withdraw = scanner.nextDouble();
        users.get(userIndex).withdrawMoney(withdraw);
    }

    public String getName() {
        return name;
    }

    public double getWallet() {
        return wallet;
    }

    public double getBankAccount() {
        return bankAccount;
    }

    public static void showUserState(Scanner scanner, List<User> users) {
        System.out.print("Inserisci numero utente: ");
        int userIndex = scanner.nextInt() - 1;

        if (userIndex < 0 || userIndex >= users.size()) {
            System.out.println("Errore: utente non trovato.");
            return;
        }

        User user = users.get(userIndex);
        user.showTransactionHistory();
        System.out.println("Stato attuale del conto:");
        System.out.println("Saldo in banca: " + user.getBankAccount() + " euro");
        System.out.println("Saldo nel portafoglio: " + user.getWallet() + " euro");
    }
    public static Investment createInvestment() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digita la somma di denaro che inizia l'investimento: ");
        int amount = scanner.nextInt();
        System.out.print("Digita il tempo in mesi, necessari all'investimento: ");
        int duration = scanner.nextInt();
        System.out.print("Digita un numero da 1 a 10 che determina il livello di rischio che l'investimento prende: ");
        int risk = scanner.nextInt();
        return new Investment(amount, duration, risk);
    }
    public void getInvestment(Investment investment) {
        if (this.bankAccount >= investment.getAmount()) {
            this.bankAccount -= investment.getAmount();
            double returnOnInvestment = calculateInvestmentReturn(investment);
            this.bankAccount += returnOnInvestment;
            System.out.println("Investimento di " + investment.getAmount() + "€ applicato con successo.");
            System.out.println("Ritorno sull'investimento dopo " + investment.getDuration() + " mesi: " + returnOnInvestment + "€");
        } else {
            System.out.println("Fondi insufficienti per l'investimento.");
        }
    }

    private double calculateInvestmentReturn(Investment investment) {
        double riskFactor = 1 + (investment.getRisk() * 0.01);
        double durationFactor = investment.getDuration() * 0.02;
        return investment.getAmount() * (riskFactor + durationFactor);
    }



}
