import java.util.ArrayList;
import java.util.List;

class Game {
    private final String secretWord; //загаданое слово
    private final List<Character> guessedLetters; //список загаданны букв
    private int mistakes;

    public Game(String secretWord) {
        this.secretWord = secretWord;
        this.guessedLetters = new ArrayList<>();
        this.mistakes = 0;
    }

    public boolean guessLetter(char letter) {
        if (guessedLetters.contains(letter)) {
            System.out.println("You already guessed this letter.");
            return false;
        }

        guessedLetters.add(letter);

        if (secretWord.indexOf(letter) == -1) {
            mistakes++;
            return false;
        }

        return true;
    }

    public void displayCurrentState() {
        for (char c : secretWord.toCharArray()) {
            if (guessedLetters.contains(c)) {
                System.out.print(c);
            } else {
                System.out.print('*');
            }
        }
    }

    public boolean hasWon() {
        for (int i = 0; i < secretWord.length(); i++) {
            if (!guessedLetters.contains(secretWord.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isGameOver() {
        if (mistakes >= Hangman.MAX_MISTAKES) {
            return true;
        }
        if (hasWon()) {
            return true;
        }
        return false;
    }

    public int getMistakes() {
        return mistakes;
    }
}
