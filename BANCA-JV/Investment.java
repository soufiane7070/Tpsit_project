import java.util.Scanner;

public class Investment  {
    private int amount;
    private int duration;
    private int risk;
    private int monthsPassed;

    public Investment(int amount, int duration, int risk) {
        this.amount = amount;
        this.duration = duration;
        this.risk = risk;
        this.monthsPassed = 0;
    }

    public static Investment create(Scanner scanner) {
        System.out.print("Importo investimento: ");
        int amount = scanner.nextInt();
        System.out.print("Durata (mesi): ");
        int duration = scanner.nextInt();
        System.out.print("Rischio (1-10): ");
        int risk = scanner.nextInt();
        return new Investment(amount, duration, risk);
    }

    public double calculateReturn() {
        double riskFactor = 1 + (risk * 0.01);
        double durationFactor = duration * 0.02;
        return amount * (riskFactor + durationFactor);
    }

    public void advance() {
        monthsPassed++;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Importo: " + amount + "€, durata: " + duration + " mesi, rischio: " + risk + ", mesi trascorsi: " + monthsPassed;
    }
}
