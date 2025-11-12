public class Player {
    private String name;
    private  double balance;
    private static final double STARTING_BALANCE = 5000.0;

    public Player(String name) {
        this.name = name;
        balance = STARTING_BALANCE;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void updateBalance(double change) {
        balance += change;
    }
}
