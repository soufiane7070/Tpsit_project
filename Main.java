import java.io.*;
import java.util.*;

public class Main {
    private static final String FILE_NAME = "users.dat";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<User> users = loadUsers();

        System.out.println("Benvenuto nella Banca Vito Volterra!");
        if (users.isEmpty()) {
            users.add(User.createUser(scanner));
        }

        char option;
        do {
            System.out.println("\nMenu:\n[a] Crea Account\n[b] Deposita\n[c] Preleva\n[d] Stato Conto\n[e] Crea Investimento\n[x] Esci");
            System.out.print("Scegli un'opzione: ");
            option = scanner.next().charAt(0);

            switch (option) {
                case 'a':
                    users.add(User.createUser(scanner));
                    break;
                case 'b':
                    User.depositToUser(scanner, users);
                    break;
                case 'c':
                    User.withdrawFromUser(scanner, users);
                    break;
                case 'd':
                    User.showUserState(scanner, users);
                    break;
                case 'e':
                    createInvestmentForUser(scanner, users);
                    break;
                case 'x':
                    saveUsers(users);
                    System.out.println("Uscita dalla banca. Arrivederci!");
                    break;
                default:
                    System.out.println("Opzione non valida.");
            }
        } while (option != 'x');
        scanner.close();
    }

    public static void saveUsers(List<User> users) {
        if (users == null || users.isEmpty()) {
            System.out.println("Nessun dato da salvare.");
            return;
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(users);
        } catch (IOException e) {
            System.err.println("Errore nel salvataggio dati: " + e.getMessage());
        }
    }

    public static List<User> loadUsers() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("Il file " + FILE_NAME + " non esiste. Restituisco una lista vuota.");
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<User>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public static void createInvestmentForUser(Scanner scanner, List<User> users) {
        if (users.isEmpty()) {
            System.out.println("Non ci sono account disponibili.");
            return;
        }

        System.out.println("Scegli l'account per il quale creare un investimento:");
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ": " + users.get(i).getName());
        }

        int userChoice = scanner.nextInt();
        if (userChoice < 1 || userChoice > users.size()) {
            System.out.println("Scelta non valida.");
            return;
        }

        User selectedUser = users.get(userChoice - 1);
        Investment investment = Investment.createInvestment(scanner);
        selectedUser.getInvestment(investment);
        System.out.println("Investimento creato e applicato a " + selectedUser.getName() + ".");
    }
}
