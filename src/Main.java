import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.Random;
import java.util.HashSet;

class Main {
    public enum Scaffold {
        ZERO {
            @Override
            public String toString() {
                return "     " + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + "\n" +
                        "         " + (char) 124 + "\n" +
                        "         " + (char) 124 + "\n" +
                        "         " + (char) 124 + "\n" +
                        "         " + (char) 124 + "\n" +
                        (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 124;
            }
        },
        ONE {
            @Override
            public String toString() {
                return "     " + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + "\n" +
                        "    " + (char) 79 + "    " + (char) 124 + "\n" +
                        "         " + (char) 124 + "\n" +
                        "         " + (char) 124 + "\n" +
                        "         " + (char) 124 + "\n" +
                        (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 124;
            }
        },
        TWO {
            @Override
            public String toString() {
                return "     " + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + "\n" +
                        "    " + (char) 79 + "    " + (char) 124 + "\n" +
                        "    " + (char) 124 + "    " + (char) 124 + "\n" +
                        "    " + (char) 124 + "    " + (char) 124 + "\n" +
                        "         " + (char) 124 + "\n" +
                        (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 124;
            }
        },
        THREE {
            @Override
            public String toString() {
                return "     " + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + "\n" +
                        "    " + (char) 79 + "    " + (char) 124 + "\n" +
                        "  " + (char) 45 + (char) 45 + (char) 124 + "    " + (char) 124 + "\n" +
                        "    " + (char) 124 + "    " + (char) 124 + "\n" +
                        "         " + (char) 124 + "\n" +
                        (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 124;
            }
        },
        FOUR {
            @Override
            public String toString() {
                return "     " + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + "\n" +
                        "    " + (char) 79 + "    " + (char) 124 + "\n" +
                        "  " + (char) 45 + (char) 45 + (char) 124 + (char) 45 + (char) 45 + "  " + (char) 124 + "\n" +
                        "    " + (char) 124 + "    " + (char) 124 + "\n" +
                        "         " + (char) 124 + "\n" +
                        (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 124;
            }
        },
        FIVE {
            @Override
            public String toString() {
                return "     " + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + "\n" +
                        "    " + (char) 79 + "    " + (char) 124 + "\n" +
                        "  " + (char) 45 + (char) 45 + (char) 124 + (char) 45 + (char) 45 + "  " + (char) 124 + "\n" +
                        "    " + (char) 124 + "    " + (char) 124 + "\n" +
                        "   " + (char) 47 + "     " + (char) 124 + "\n" +
                        (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 124;
            }
        },
        SIX {
            @Override
            public String toString() {
                return "     " + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + "\n" +
                        "    " + (char) 79 + "    " + (char) 124 + "\n" +
                        "  " + (char) 45 + (char) 45 + (char) 124 + (char) 45 + (char) 45 + "  " + (char) 124 + "\n" +
                        "    " + (char) 124 + "    " + (char) 124 + "\n" +
                        "   " + (char) 47 + " " + (char) 92 + "   " + (char) 124 + "\n" +
                        (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 124;
            }
        }
    }

    public static final Scanner scanner = new Scanner(System.in);
    public static final Random rand = new Random();
    public static int mistakeCnt = 0;
    public static final String[] scaff = {"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX"};

    public static void main(String[] args) {
        while (true) {
            System.out.println();
            System.out.println("[Н]овая игра или [З]акончить игру?");
            char choice = scanner.next().toUpperCase().charAt(0);
            if (choice == 'Н') {
                startGameRound();
            } else if (choice == 'З') {
                System.out.println("Игра окончена!");
                break;
            } else {
                System.out.println("Введите правильную букву:");
            }
        }
    }

    public static void startGameRound() {
        System.out.println("Новый раунд!");

        mistakeCnt = 0;
        String hiddenWord = generateNewHiddenWord();
        HashSet<Character> gameSet = createGameSet(hiddenWord); // множество символов загаданново слова
        HashSet<Character> userSet = new HashSet<>(); // множество отгаданных символов юзером

        startGameLoop(hiddenWord, gameSet, userSet);

    }

    public static void startGameLoop(String hiddenWord, HashSet<Character> gameSet, HashSet<Character> userSet) {
        while (mistakeCnt != 6) {
            System.out.println();

            char guessLetter = inputGuessLetter();
            checkGuessLetter(gameSet, userSet, guessLetter);

            printStateScaffold(userSet, hiddenWord);
            if (checkStateGame(userSet, gameSet)) {
                System.out.println("\nТы угадал загаданное слово:" + hiddenWord);
                break;
            }
        }
        if (!checkStateGame(userSet, gameSet)) {
            System.out.println();
            System.out.println("Игра окончена!");
            System.out.println("Загаданное слово:" + hiddenWord);
        }
    }

    private static boolean checkStateGame(HashSet<Character> userSet, HashSet<Character> gameSet) {
        return gameSet.hashCode() == userSet.hashCode();
    }

    private static void printStateScaffold(HashSet<Character> userSet, String hiddenWord) {
        System.out.println(Scaffold.valueOf(scaff[mistakeCnt]));
        System.out.println("Слово:" + getCurrGuessedWord(userSet, hiddenWord));
        System.out.println("Ошибки:" + (6 - mistakeCnt));
    }

    private static char inputGuessLetter() {
        System.out.println("Введите букву:");
        return scanner.next().toUpperCase().charAt(0);
    }

    public static void checkGuessLetter(HashSet<Character> gameSet, HashSet<Character> userSet, char guessLetter) {
        if (gameSet.contains(guessLetter)) {
            userSet.add(guessLetter);
        } else {
            mistakeCnt++;
        }
    }

    private static String getCurrGuessedWord(HashSet<Character> userSet, String hiddenWord) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < hiddenWord.length(); i++) {
            if (userSet.contains(hiddenWord.charAt(i))) {
                s.append(hiddenWord.charAt(i));
            } else {
                s.append("*");
            }
        }
        return s.toString();
    }

    private static HashSet<Character> createGameSet(String hiddenWord) {
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < hiddenWord.length(); i++) {
            set.add(hiddenWord.charAt(i));
        }
        return set;
    }

    public static String generateNewHiddenWord() {
        Scanner scFile = openFile();
        int x = rand.nextInt(89);
        for (int i = 0; i < x; ++i) {
            scFile.nextLine();
        }
        return scFile.nextLine().toUpperCase();
    }

    public static Scanner openFile() {
        File file = new File("./src/dictionary.txt");
        Scanner scFile;
        try {
            scFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return scFile;
    }
}