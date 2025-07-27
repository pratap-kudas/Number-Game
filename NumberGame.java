package src;

import java.util.*;

public class NumberGame {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DARK_BG = "\u001B[40m";
    private static final String BRIGHT_CYAN = "\u001B[96m";
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[92m";
    private static final String RED = "\u001B[91m";
    private static final String YELLOW = "\u001B[93m";

    private static int highScore = Integer.MAX_VALUE;
    private static int roundsWon = 0;

    public static void main(String[] args) {
        printWelcome();
        boolean playAgain;
        do {
            playRound();
            playAgain = askPlayAgain();
        } while (playAgain);
        printLeaderboard();
        System.out.println(DARK_BG + BRIGHT_CYAN + "\nThanks for playing the Unique Number Game!" + RESET);
    }

    private static void printWelcome() {
        System.out.println(DARK_BG + BRIGHT_CYAN + "\n==============================");
        System.out.println("   UNIQUE NUMBER GAME (JAVA)");
        System.out.println("==============================" + RESET);
        System.out.println(YELLOW + "Guess the number! Try to beat the high score.\n" + RESET);
    }

    private static void playRound() {
        System.out.print("Enter the minimum number: ");
        int min = scanner.nextInt();
        System.out.print("Enter the maximum number: ");
        int max = scanner.nextInt();
        System.out.print("Enter max attempts: ");
        int maxAttempts = scanner.nextInt();
        int number = new Random().nextInt(max - min + 1) + min;
        int attempts = 0;
        boolean guessed = false;
        while (attempts < maxAttempts) {
            System.out.print("\nYour guess: ");
            int guess = scanner.nextInt();
            attempts++;
            if (guess == number) {
                System.out.println(GREEN + "Correct! You guessed the number in " + attempts + " attempts." + RESET);
                guessed = true;
                break;
            } else if (guess < number) {
                System.out.println(RED + "Too low!" + RESET);
            } else {
                System.out.println(RED + "Too high!" + RESET);
            }
            printAnimatedFeedback();
        }
        if (guessed) {
            roundsWon++;
            if (attempts < highScore) {
                highScore = attempts;
                System.out.println(BRIGHT_CYAN + "New High Score!" + RESET);
            }
            printMotivationalMessage();
        } else {
            System.out.println(RED + "Out of attempts! The number was: " + number + RESET);
        }
    }

    private static void printAnimatedFeedback() {
        try {
            System.out.print("."); Thread.sleep(200);
            System.out.print("."); Thread.sleep(200);
            System.out.print(".\n"); Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void printMotivationalMessage() {
        String[] messages = {
            "Great job! Keep it up!",
            "You're on fire!",
            "Impressive guessing!",
            "You have a sharp mind!"
        };
        System.out.println(YELLOW + messages[new Random().nextInt(messages.length)] + RESET);
    }

    private static boolean askPlayAgain() {
        System.out.print("Play another round? (y/n): ");
        String input = scanner.next();
        return input.equalsIgnoreCase("y");
    }

    private static void printLeaderboard() {
        System.out.println(DARK_BG + BRIGHT_CYAN + "\n===== LEADERBOARD =====" + RESET);
        System.out.println("Rounds Won: " + roundsWon);
        System.out.println("Best Score (fewest attempts): " + (highScore == Integer.MAX_VALUE ? "N/A" : highScore));
    }
} 