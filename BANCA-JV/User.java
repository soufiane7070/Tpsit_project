import java.util.*;

public class User  {
    private String username;
    private String password;
    private double bankAccount;
    private double wallet;
    private boolean inDebt;
    private List<String> transactionHistory;
    private List<Investment> investments;

    public User(String username, String password, double bankAccount, double wallet) {
        this.username = username;
        this.password = password;
        this.bankAccount = bankAccount;
        this.wallet = wallet;
        this.inDebt = false;
        this.transactionHistory = new ArrayList<>();
        this.investments = new ArrayList<>();
    }

    public boolean authenticate(String user, String pass) {
        return this.username.equals(user) && this.password.equals(pass);
    }

    public void deposit(Scanner scanner) {
        System.out.print("Quanto vuoi depositare? ");
        double amount = scanner.nextDouble();
        if (wallet < amount) {
            System.out.println("Fondi insufficienti nel portafoglio.");
            return;
        }
        wallet -= amount;
        bankAccount += amount;
        transactionHistory.add("Deposito: +" + amount + "€");
    }

    public void withdraw(Scanner scanner) {
        System.out.print("Quanto vuoi prelevare? ");
        double amount = scanner.nextDouble();
        if (bankAccount < amount || inDebt) {
            System.out.println("Prelievo non possibile. Saldo insufficiente o utente in debito.");
            return;
        }
        bankAccount -= amount;
        wallet += amount;
        transactionHistory.add("Prelievo: -" + amount + "€");
    }

    public double getBankAccount() {
        return bankAccount;
    }



    public List<Investment> getInvestments() {
        return investments;
    }






    public double getWallet() {
        return wallet;
    }

    public void createInvestment(Scanner scanner) {
        if (inDebt) {
            System.out.println("Impossibile investire: sei in debito.");
            return;
        }
        Investment inv = Investment.create(scanner);
        if (bankAccount < inv.getAmount()) {
            System.out.println("Fondi insufficienti per questo investimento.");
            return;
        }
        bankAccount -= inv.getAmount();
        double result = inv.calculateReturn();
        if (result < 0) inDebt = true;
        bankAccount += result;
        investments.add(inv);
        transactionHistory.add("Investimento: -" + inv.getAmount() + "€, ritorno: +" + result + "€");
    }

    public void advanceMonth() {
        wallet += 100;
        for (Investment inv : investments) inv.advance();
        if (bankAccount < 0) {
            inDebt = true;
            if (wallet > 0) {
                double used = Math.min(wallet, Math.abs(bankAccount));
                wallet -= used;
                bankAccount += used;
                transactionHistory.add("Rimborso automatico: -" + used + "€ dal portafoglio");
            }
        } else {
            inDebt = false;
        }
    }

    public void showState() {
        System.out.println("Saldo banca: " + bankAccount + "€");
        System.out.println("Portafoglio: " + wallet + "€");
        System.out.println("Stato: " + (inDebt ? "In debito" : "Attivo"));
    }

    public void showTransactionHistory() {
        System.out.println("Storico transazioni:");
        transactionHistory.forEach(System.out::println);
    }

    public void showInvestmentHistory() {
        System.out.println("Storico investimenti:");
        if (investments.isEmpty()) System.out.println("Nessun investimento.");
        for (Investment inv : investments) System.out.println(inv);
    }
}
