import java.util.Random;

public class RouletteWheel {

    private Random random = new Random();
    
    private final int[] reds = {1, 3, 5, 7, 9, 12, 14, 16, 18, 
              19, 21, 23, 25, 27, 30, 32, 34, 36};

    private final int[] blacks = {2, 4, 6, 8, 10, 11, 13, 15, 17,
                20, 22, 24, 26, 28, 29, 31, 33, 35};

    public int spinNumber() {
        return random.nextInt(37);
    }

    public String getColor(int number) {
        if (number == 0) {
            return "green";
        }

        for (int n : reds) {
            if (n == number) {
                return "red";
            }
        }

        for (int n : blacks) {
            if (n == number) {
                return "black";
            }
        }

        return "error";
    }
}
