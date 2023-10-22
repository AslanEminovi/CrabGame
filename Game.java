import java.util.Random;

public class Game {
    private int currentPlayer;
    private int player1Total;
    private int player2Total;
    private boolean isGameOver;
    private int firstPlayerTotal;

    public Game() {
        currentPlayer = 1;
        player1Total = 0;
        player2Total = 0;
        isGameOver = false;
        firstPlayerTotal = 0;
    }

    public void startGame() {
        while (!isGameOver) {
            playTurn();
            switchPlayer();
        }
    }

    public void playTurn() {
        Die die = new Die();
        int roll1 = die.roll();
        int roll2 = die.roll();
        int total = roll1 + roll2;

        if (currentPlayer == 1 && firstPlayerTotal == 0) {
            firstPlayerTotal = total; // Set the total for the first player
        }

        if (currentPlayer == 2 && total != firstPlayerTotal) {
            System.out.println("Player " + currentPlayer + " loses in this round because the total is not the same as the first player's total.");
            isGameOver = true;
            return;
        }

        if (isWinningCombination(total)) {
            System.out.println("Player " + currentPlayer + " wins with " + total + " in this turn!");
            isGameOver = true;
        } else if (isLosingCombination(total)) {
            System.out.println("Player " + currentPlayer + " loses with " + total + " in this turn.");
            isGameOver = true;
        } else if (total == 7) {
            System.out.println("Player " + currentPlayer + " loses with a total of 7 in this turn.");
            isGameOver = true;
        } else {
            System.out.println("Player " + currentPlayer + " rolls " + roll1 + " and " + roll2 + " for a total of " + total);
            if (currentPlayer == 1) {
                player1Total = total;
            } else {
                player2Total = total;
            }
        }
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }

    public boolean isWinningCombination(int total) {
        return (total == 12 || total == 10 || total == 6);
    }

    public boolean isLosingCombination(int total) {
        return (total == 2 || total == 4 || total == 8);
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}

class Die {
    private int sides;

    public Die() {
        sides = 6;
    }

    public int roll() {
        Random random = new Random();
        return random.nextInt(sides) + 1;
    }
}
