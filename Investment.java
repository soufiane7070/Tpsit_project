
import java.util.Scanner;
class Investment {
    private int amount;
    private int duration;
    private int risk;

    public Investment(int amount, int duration, int risk) {
        this.amount = amount;
        this.duration = duration;
        this.risk = risk;
    }

    public int getAmount() { return amount; }
    public int getDuration() { return duration; }
    public int getRisk() { return risk; }

    public static Investment createInvestment(Scanner scanner) {
        System.out.print("Digita la somma di denaro che inizia l'investimento: ");
        int amount = scanner.nextInt();
        System.out.print("Digita il tempo in mesi, necessari all'investimento: ");
        int duration = scanner.nextInt();
        System.out.print("Digita un numero da 1-10 che determina il livello del rischio: ");
        int risk = scanner.nextInt();
        return new Investment(amount, duration, risk);
    }
}
