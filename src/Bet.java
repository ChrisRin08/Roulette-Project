public class Bet {
    private String type;   // "red", "black", "number"
    private int number;    // only used if type == "number"
    private double amount; // money bet

    public Bet(String type, int number, double amount) {
        this.type = type;
        this.number = number;
        this.amount = amount;
    }

    // getters
    public String getType() { return type; }
    public int getNumber() { return number; }
    public double getAmount() { return amount; }

    // --- New method: calculate winnings ---
    public double calculateWinnings(int resultNumber, String resultColor) {
        if (type.equalsIgnoreCase("red") && resultColor.equals("red")) return amount * 2;
        if (type.equalsIgnoreCase("black") && resultColor.equals("black")) return amount * 2;
        if (type.equalsIgnoreCase("number") && number == resultNumber) return amount * 36;
        return 0;
    }

    // Optional: a nice description of the bet
    public String description() {
        if (type.equalsIgnoreCase("number")) {
            return type + " " + number;
        }
        return type;
    }
}

