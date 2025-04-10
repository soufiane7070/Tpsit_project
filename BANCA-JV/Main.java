import java.io.*;
import java.util.*;

public class Main {
    private static final String FILE_NAME = "users.dat";
    private static int currentMonth = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<User> users = loadUsers();

        System.out.println("Benvenuto nella Banca Vito Volterra!");

        User currentUser = login(scanner, users);

        char option;
        do {
            System.out.println("\nMenu:");
            System.out.println("\n[a] Deposita");
            System.out.println("\n[b] Preleva");
            System.out.println("\n[c] Crea Investimento");
            System.out.println("\n[d] Stato Conto");
            System.out.println("\n[e] Storico Investimenti");
            System.out.println("\n[f] Storico Transazioni");
            System.out.println("\n[g] Avanza di un mese");
            System.out.println("\n[x] Esci");
            System.out.print("Scegli un'opzione: ");
            option = scanner.next().charAt(0);

            switch (option) {
                case 'a' -> currentUser.deposit(scanner);
                case 'b' -> currentUser.withdraw(scanner);
                case 'c' -> currentUser.createInvestment(scanner);
                case 'd' -> currentUser.showState();
                case 'e' -> currentUser.showInvestmentHistory();
                case 'f' -> currentUser.showTransactionHistory();
                case 'g' -> {
                    currentMonth++;
                    System.out.println(">> Mese avanzato! Mese attuale: " + currentMonth);
                    for (User u : users) u.advanceMonth();
                }
                case 'x' -> {
                    saveUsers(users);
                    System.out.println("Uscita dalla banca. Arrivederci!");
                }
                default -> System.out.println("Opzione non valida.");
            }
        } while (option != 'x');

        scanner.close();
    }

    private static User login(Scanner scanner, List<User> users) {
        System.out.print("Hai un account? [s/n]: ");
        char answer = scanner.next().charAt(0);

        if (answer == 's') {
            while (true) {
                System.out.print("Username: ");
                String username = scanner.next();
                System.out.print("Password: ");
                String password = scanner.next();
                for (User u : users) {
                    if (u.authenticate(username, password)) {
                        System.out.println("Accesso eseguito come " + username);
                        return u;
                    }
                }
                System.out.println("Credenziali non valide.");
            }
        } else {
            System.out.print("Scegli username: ");
            String username = scanner.next();
            System.out.print("Scegli password: ");
            String password = scanner.next();
            System.out.print("Deposito iniziale: ");
            double bank = scanner.nextDouble();
            System.out.print("Saldo portafoglio: ");
            double wallet = scanner.nextDouble();
            User newUser = new User(username, password, bank, wallet);
            users.add(newUser);
            return newUser;
        }
    }

    private static void saveUsers(List<User> users) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(users);
        } catch (IOException e) {
            System.err.println("Errore nel salvataggio dati: " + e.getMessage());
        }
    }

    private static List<User> loadUsers() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<User>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
