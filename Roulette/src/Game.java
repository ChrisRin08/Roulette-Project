import java.util.*;

public class Game {
    private Player player;
    private RouletteWheel wheel;
    private Scanner scanner;

    public Game(Player player) {
        this.player = player;
        this.wheel = new RouletteWheel();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to Roulette, " + player.getName() + "!");

        while (player.getBalance() > 0) {
            System.out.println("\nYour balance: $" + player.getBalance());

            List<Bet> bets = new ArrayList<>();

            while (true) {
                System.out.println("\nEnter bet type: red / black / number / spin / quit");
                String type = scanner.next().trim().toLowerCase();

                if (type.equals("quit")) {
                    System.out.println("Game over! Final balance: $" + player.getBalance());
                    return;
                }

                if (type.equals("spin")) break;

                int number = -1;
                if (type.equals("number")) {
                    number = readValidNumber(); // ✅ helper method
                }

                double amount = readValidAmount(); // ✅ helper method

                player.updateBalance(-amount); // lock in bet
                bets.add(new Bet(type, number, amount));
                System.out.println("✅ Bet placed: " + type + 
                                   (type.equals("number") ? " " + number : "") + 
                                   " for $" + amount);
            }

            playRound(bets);
        }

        System.out.println("You’re out of money! Game over.");
    }

    // --- Helper Methods for Input Validation ---
    private int readValidNumber() {
        int number = -1;
        while (true) {
            System.out.print("Enter a number (0–36): ");
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                if (number >= 0 && number <= 36) break;
            } else {
                scanner.next(); // discard invalid input
            }
            System.out.println("Invalid Input! Please enter an integer between 0 and 36.");
        }
        return number;
    }

    private double readValidAmount() {
        double amount = -1;
        while (true) {
            System.out.print("Enter bet amount: ");
            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                if (amount >= 0 && amount <= player.getBalance()) break;
                else if (amount <= 0) System.out.println("Amount must be greater than 0.");
                else System.out.println("Not enough balance!");
            } else {
                scanner.next(); // discard invalid input
                System.out.println("Invalid input! Enter a number for bet amount.");
            }
        }
        return amount;
    }

    // --- Play Round ---
    private void playRound(List<Bet> bets) {
        int resultNum = wheel.spinNumber();
        String resultColor = wheel.getColor(resultNum);

        System.out.println("\n🎡 The wheel landed on " + resultNum + " (" + resultColor + ")\n");

        for (Bet bet : bets) {
            double winnings = bet.calculateWinnings(resultNum, resultColor);

            if (winnings > 0) {
                System.out.println("🎯 You won $" + (winnings - bet.getAmount()) + " on " + bet.description());
                player.updateBalance(winnings);
            } else {
                System.out.println("❌ Lost $" + bet.getAmount() + " on " + bet.description());
            }
        }

        System.out.println("\n💵 New balance: $" + player.getBalance());
    }
}
