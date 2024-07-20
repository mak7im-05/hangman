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
                return "    +---+\n" +
                        "    |   |\n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "    =========";
            }
        },
        ONE {
            @Override
            public String toString() {
                return "    +---+\n" +
                        "    |   |\n" +
                        "    |   O\n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "    =========";
            }
        },
        TWO {
            @Override
            public String toString() {
                return "    +---+\n" +
                        "    |   |\n" +
                        "    |   O\n" +
                        "    |   |\n" +
                        "    |\n" +
                        "    |\n" +
                        "    =========";
            }
        },
        THREE {
            @Override
            public String toString() {
                return "    +---+\n" +
                        "    |   |\n" +
                        "    |   O\n" +
                        "    |  /|\n" +
                        "    |\n" +
                        "    |\n" +
                        "    =========";
            }
        },
        FOUR {
            @Override
            public String toString() {
                return "    +---+\n" +
                        "    |   |\n" +
                        "    |   O\n" +
                        "    |  /|\\\n" +
                        "    |\n" +
                        "    |\n" +
                        "    =========";
            }
        },
        FIVE {
            @Override
            public String toString() {
                return "    +---+\n" +
                        "    |   |\n" +
                        "    |   O\n" +
                        "    |  /|\\\n" +
                        "    |  /\n" +
                        "    |\n" +
                        "    =========";
            }
        },
        SIX {
            @Override
            public String toString() {
                return  "    +---+\n" +
                        "    |   |\n" +
                        "    |   O\n" +
                        "    |  /|\\\n" +
                        "    |  / \\\n" +
                        "    |\n" +
                        "    =========";
            }
        }
    }

    public static final Scanner scanner = new Scanner(System.in);
    public static final Random rand = new Random();
    public static boolean win = false;
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

        win = false;
        mistakeCnt = 0;
        String hiddenWord = generateNewHiddenWord();
        HashSet<Character> gameSet = createGameSet(hiddenWord); // множество символов загаданново слова
        HashSet<Character> userSet = new HashSet<>(); // множество отгаданных символов юзером

        startGameLoop(hiddenWord, gameSet, userSet);

    }

    public static void startGameLoop(String hiddenWord, HashSet<Character> gameSet, HashSet<Character> userSet) {
        while (mistakeCnt != 6 && !win) {
            char guessLetter = inputGuessLetter(); // ввод пользователя
            checkGuessLetter(gameSet, userSet, guessLetter); // проверка буквы
            printStateGame(userSet, hiddenWord); // вывод состояния игры
        }
    }

    private static void printStateGame(HashSet<Character> userSet, String hiddenWord) {
        System.out.println("Слово:" + getCurrGuessedWord(userSet, hiddenWord));
        if(win) {
            System.out.println("Победа!!!");
            System.out.println("\nТы угадал загаданное слово:" + hiddenWord);
            return;
        } else if (mistakeCnt == 6) {
            System.out.println();
            System.out.println("Игра окончена!");
            System.out.println("Ты проиграл!!!");
            System.out.println("Загаданное слово:" + hiddenWord);
            System.out.println(Scaffold.valueOf(scaff[mistakeCnt]));
            return;
        }
        System.out.println(Scaffold.valueOf(scaff[mistakeCnt]));
        System.out.println("Ошибки:" + (6 - mistakeCnt));
    }

    private static char inputGuessLetter() {
        System.out.println("Введите букву:");
        char s = scanner.next().toUpperCase().charAt(0);
        while (!Character.isLetter(s)) {
            System.out.println("Это не буква, введите правильный символ!");
            s = scanner.next().toUpperCase().charAt(0);
        }
        return s;
    }

    public static void checkGuessLetter(HashSet<Character> gameSet, HashSet<Character> userSet, char guessLetter) {
        if (gameSet.contains(guessLetter) || userSet.contains(guessLetter)) {
            userSet.add(guessLetter);
        } else {
            userSet.add(guessLetter);
            mistakeCnt++;
        }
    }

    private static String getCurrGuessedWord(HashSet<Character> userSet, String hiddenWord) {
        win = true;
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < hiddenWord.length(); i++) {
            if (userSet.contains(hiddenWord.charAt(i))) {
                s.append(hiddenWord.charAt(i));
            } else {
                s.append("*");
                win = false;
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