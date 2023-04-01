import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String word = getWord();
        char[] wordArray = word.toCharArray();
        char[] guessedArray = new char[wordArray.length];
        int remainingLives = 7;
        ArrayList<Character> wrongGuesses = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (remainingLives > 0 && !isWordGuessed(wordArray, guessedArray)) {
            System.out.print("Word: ");
            for (int i = 0; i < wordArray.length; i++) {
                if (guessedArray[i] == 0) {
                    System.out.print("_ ");
                } else {
                    System.out.print(guessedArray[i] + " ");
                }
            }
            System.out.println();
            System.out.println("목숨: " + remainingLives);
            System.out.print("오답: ");
            for (char c : wrongGuesses) {
                System.out.print(c + " ");
            }
            System.out.println();
            System.out.print("입력: ");
            char guess = scanner.nextLine().charAt(0);
            if (word.contains(String.valueOf(guess))) {
                for (int i = 0; i < wordArray.length; i++) {
                    if (wordArray[i] == guess) {
                        guessedArray[i] = guess;
                    }
                }
            } else {
                remainingLives--;
                wrongGuesses.add(guess);
            }
        }
        if (remainingLives == 0) {
            System.out.println("실패: " + word);
        } else {
            System.out.println("성공: " + word);
        }
    }

    private static boolean isWordGuessed(char[] wordArray, char[] guessedArray) {
        for (int i = 0; i < wordArray.length; i++) {
            if (guessedArray[i] == 0) {
                return false;
            }
        }
        return true;
    }

    private static String getWord() {
        String word = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("word.txt"));
            ArrayList<String> words = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
            Random random = new Random();
            word = words.get(random.nextInt(words.size()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return word;
    }
}