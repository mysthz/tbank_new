import java.util.Random;
import java.util.Scanner;

public class Hangman {
    private static final String[] words = {"apple", "moscow", "orange", "mango"};
    public static final int MAX_MISTAKES = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //считывать ввод пользователя.
        Random random = new Random();

        // выбираем рандомное слово
        String secretWord = words[random.nextInt(words.length)];


        Game game = new Game(secretWord);

        System.out.println("Welcome to the Hangman game!");

        boolean isPlaying = true;
        while (isPlaying && !game.isGameOver()) {
            System.out.print("\nThe word: ");
            game.displayCurrentState(); //текущий стэйт загаданного слова+угаданные буквы

            System.out.print("\nGuess a letter or type 'quit' to give up: ");
            String input = scanner.nextLine().trim().toLowerCase(); // убираются ненужные пробелы, быквы делаются строчными

            if ("quit".equals(input)) {
                System.out.println("\nOh, no! You gave up.");
                isPlaying = false;
            } else if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Please enter a single letter."); // введено больше одного символа или чтото помимо буквы
            } else {
                char guess = input.charAt(0);
                if (game.guessLetter(guess)) {
                    System.out.println("Hit!");
                } else {
                    System.out.printf("Missed, mistake %d out of %d.\n", game.getMistakes(), MAX_MISTAKES);
                }
            }
        }

        if (!isPlaying) {
            return;
        }

        if (game.hasWon()) {
            System.out.println("\nYou won!");
        } else {
            System.out.println("\nYou lost!");
        }
    }
}
