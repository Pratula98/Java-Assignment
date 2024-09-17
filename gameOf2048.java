/***
 * 2048 Game 
 * Owner : Pratul Agarwal
 * Date of creation : 17/09/2024
 */
package assignment_java_programming;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * A class representing the 2048 game. Implements Serializable to allow saving and loading game states.
 */
public class gameOf2048 implements Serializable {
    public static final int SIZE = 4;
    public static final char MOVE_LEFT = 'A';
    public static final char MOVE_RIGHT = 'D';
    public static final char MOVE_UP = 'W';
    public static final char MOVE_DOWN = 'S';

    private int[][] gameboard;  // Game grid
    private Random random;  // Random number generator for spawning new tiles
    private transient Scanner input;  // Scanner for user input, marked as transient since it's not serialized
    private String userName;  // Stores the player's name

    /**
     * Constructor for initializing the game with the player's name.
     *
     * @param userName The name of the user.
     */
    public gameOf2048(String userName) {
        this.userName = userName;
        gameboard = new int[SIZE][SIZE];  // Initialize gameboard
        random = new Random();  // Initialize random number generator
        input = new Scanner(System.in);  // Initialize scanner for user input
    }

    /**
     * Asks for the user’s name and returns it.
     *
     * @return The name entered by the user.
     */
    public static String askForUserName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name: ");
        return input.nextLine();
    }

    /**
     * Saves the current game state to a file "saved_game.ser".
     */
    public void saveGame() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("saved_game.ser"))) {
            out.writeObject(this);  // Write the current game state
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to save the game.");
        }
    }

    /**
     * Loads the saved game from the "saved_game.ser" file.
     *
     * @return Returns the saved game object or null if no saved game is found.
     */
    public static gameOf2048 loadGame() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("saved_game.ser"))) {
            return (gameOf2048) in.readObject();  // Return the deserialized game state
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved game found.");
            return null;
        }
    }

    /**
     * Displays the game board in a formatted way.
     */
    public void showBoard() {
        for (int i = 0; i < SIZE; i++) {
            System.out.print("-------");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print("|");
            for (int j = 0; j < SIZE; j++) {
                System.out.print("      |");
            }
            System.out.println();

            System.out.print("|");
            for (int j = 0; j < SIZE; j++) {
                if (gameboard[i][j] == 0) {
                    System.out.printf("  %-3s |", "");
                } else {
                    System.out.printf("  %-3s |", "" + gameboard[i][j]);
                }
            }
            System.out.println();

            System.out.print("|");
            for (int j = 0; j < SIZE; j++) {
                System.out.print("      |");
            }
            System.out.println();

            for (int j = 0; j < SIZE; j++) {
                System.out.print("-------");
            }
            System.out.println();
        }
    }

    /**
     * Adds a random digit (2 or 4) to an empty spot on the game board.
     *
     * @param digit The digit to add (typically 2 or 4).
     */
    public void addRandomDigit(int digit) {
        int i, j;
        do {
            i = random.nextInt(SIZE);
            j = random.nextInt(SIZE);
        } while (gameboard[i][j] != 0);  // Find a random empty spot

        gameboard[i][j] = digit;
    }

    /**
     * Searches for a specific value on the game board.
     *
     * @param x The value to search for (e.g., 2048).
     * @return True if the value is found, false otherwise.
     */
    public boolean searchOnBoard(int x) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (gameboard[i][j] == x) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the player has won the game by reaching 2048.
     *
     * @return True if 2048 is found, false otherwise.
     */
    public boolean gameWon() {
        return searchOnBoard(2048);
    }

    /**
     * Checks if the player can make any valid moves.
     *
     * @return True if a move is possible, false otherwise.
     */
    public boolean userCanMakeAMove() {
        for (int i = 0; i < SIZE - 1; i++) {
            for (int j = 0; j < SIZE - 1; j++) {
                if (gameboard[i][j] == gameboard[i][j + 1] || gameboard[i][j] == gameboard[i + 1][j]) {
                    return true;
                }
            }
        }
        for (int j = 0; j < SIZE - 1; j++) {
            if (gameboard[SIZE - 1][j] == gameboard[SIZE - 1][j + 1]) {
                return true;
            }
        }
        for (int i = 0; i < SIZE - 1; i++) {
            if (gameboard[i][SIZE - 1] == gameboard[i + 1][SIZE - 1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the game is over, either due to winning or having no valid moves left.
     *
     * @return True if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        return gameWon() || (!searchOnBoard(0) && !userCanMakeAMove());
    }

    /**
     * Gets the user's move choice (W, A, S, D or E for exit).
     *
     * @return The chosen move as a character.
     */
    public char getUserMove() {
        System.out.println("Choose a move: ");
        System.out.println("W/w: Up");
        System.out.println("S/s: Down");
        System.out.println("A/a: Left");
        System.out.println("D/d: Right");
        System.out.println("E/e: Exit Game");
        System.out.print("Enter move: ");

        String moveInput = input.nextLine();
        if (moveInput.equalsIgnoreCase("a") || moveInput.equalsIgnoreCase("w") || moveInput.equalsIgnoreCase("s")
                || moveInput.equalsIgnoreCase("d") || moveInput.equalsIgnoreCase("e")) {
            return moveInput.toUpperCase().charAt(0);
        }

        System.out.println("Invalid Input!\n");
        showBoard();

        return getUserMove();
    }

    /**
     * Processes the player's move and updates the game board accordingly.
     *
     * @param move The move to process ('A', 'D', 'W', 'S', or 'E' for exit).
     */
    public void processMove(char move) {
        switch (move) {
            case MOVE_LEFT:
                for (int i = 0; i < SIZE; i++) {
                    gameboard[i] = processLeftMove(gameboard[i]);
                }
                break;
            case MOVE_RIGHT:
                for (int i = 0; i < SIZE; i++) {
                    gameboard[i] = processRightMove(gameboard[i]);
                }
                break;
            case MOVE_UP:
                for (int j = 0; j < SIZE; j++) {
                    int[] column = new int[SIZE];
                    for (int i = 0; i < SIZE; i++) {
                        column[i] = gameboard[i][j];
                    }
                    column = processLeftMove(column);
                    for (int i = 0; i < SIZE; i++) {
                        gameboard[i][j] = column[i];
                    }
                }
                break;
            case MOVE_DOWN:
                for (int j = 0; j < SIZE; j++) {
                    int[] column = new int[SIZE];
                    for (int i = 0; i < SIZE; i++) {
                        column[i] = gameboard[i][j];
                    }
                    column = processRightMove(column);
                    for (int i = 0; i < SIZE; i++) {
                        gameboard[i][j] = column[i];
                    }
                }
                break;
            case 'E':
                showExitOptions();
                break;
        }
    }

    /**
     * Displays exit options (Save, New Game, or Exit).
     */
    public void showExitOptions() {
        System.out.println("1. Save Game");
        System.out.println("2. Start a New Game");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        int choice = input.nextInt();
        input.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                saveGame();
                break;
            case 2:
                startNewGame();
                break;
            case 3:
                System.exit(0);
                break;
        }
    }

    /**
     * Starts a new game by reinitializing the gameboard.
     */
    public void startNewGame() {
        gameboard = new int[SIZE][SIZE];  // Reinitialize gameboard
        System.out.println("Starting a new game...");
        playGame();  // Start a new game loop
    }

    /**
     * Processes leftward movement of tiles.
     *
     * @param line A row or column of tiles.
     * @return The updated line after processing.
     */
    public int[] processLeftMove(int[] line) {
        line = moveDigitsToLeft(line);
        line = mergeLeft(line);
        return moveDigitsToLeft(line);
    }

    /**
     * Processes rightward movement of tiles.
     *
     * @param line A row or column of tiles.
     * @return The updated line after processing.
     */
    public int[] processRightMove(int[] line) {
        line = moveDigitsToRight(line);
        line = mergeRight(line);
        return moveDigitsToRight(line);
    }

    /**
     * Merges tiles that are the same and to the left.
     *
     * @param line A row or column of tiles.
     * @return The updated line after merging.
     */
    public int[] mergeLeft(int[] line) {
        for (int i = 0; i < SIZE - 1; i++) {
            if (line[i] == line[i + 1]) {
                line[i] *= 2;
                line[i + 1] = 0;
            }
        }
        return line;
    }

    /**
     * Merges tiles that are the same and to the right.
     *
     * @param line A row or column of tiles.
     * @return The updated line after merging.
     */
    public int[] mergeRight(int[] line) {
        for (int i = SIZE - 1; i > 0; i--) {
            if (line[i] == line[i - 1]) {
                line[i] *= 2;
                line[i - 1] = 0;
            }
        }
        return line;
    }

    /**
     * Moves non-zero digits in a row or column to the left.
     *
     * @param line A row or column of tiles.
     * @return The updated line after moving.
     */
    public int[] moveDigitsToLeft(int[] line) {
        int[] temp = new int[SIZE];
        int index = 0;
        for (int i = 0; i < SIZE; i++) {
            if (line[i] != 0) {
                temp[index++] = line[i];
            }
        }
        return temp;
    }

    /**
     * Moves non-zero digits in a row or column to the right.
     *
     * @param line A row or column of tiles.
     * @return The updated line after moving.
     */
    public int[] moveDigitsToRight(int[] line) {
        int[] temp = new int[SIZE];
        int index = SIZE - 1;
        for (int i = SIZE - 1; i >= 0; i--) {
            if (line[i] != 0) {
                temp[index--] = line[i];
            }
        }
        return temp;
    }

    /**
     * Starts the game and handles the main game loop.
     */
    public void playGame() {
        System.out.println("Welcome " + userName + "! Let's play 2048!");
        showBoard();
        addRandomDigit(2);  // Add the first random digit to the board
        addRandomDigit(2);  // Add the second random digit to the board

        while (!isGameOver()) {
            showBoard();
            char move = getUserMove();
            processMove(move);
            if (move != 'E') {
                addRandomDigit(2);  // Add a new random digit after a move
            }
        }

        if (gameWon()) {
            System.out.println("Congratulations, " + userName + "! You've won the game!");
        } else {
            System.out.println("Game over, " + userName + ". Better luck next time!");
        }
    }

    /**
     * Main method to start the game. Provides options to load a saved game or start a new one.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("2048 Game");
        System.out.println("1. Start a New Game");
        System.out.println("2. Load a Saved Game");

        int choice = input.nextInt();
        input.nextLine();  // Consume newline

        if (choice == 1) {
            String userName = askForUserName();
            gameOf2048 newGame = new gameOf2048(userName);
            newGame.playGame();
        } else if (choice == 2) {
            gameOf2048 savedGame = loadGame();
            if (savedGame != null) {
                savedGame.input = new Scanner(System.in);  // Reinitialize transient scanner
                savedGame.playGame();
            } else {
                System.out.println("No saved game to load.");
            }
        }
    }
}
