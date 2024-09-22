/***
 * GAME OF 2048
 * Owner : Pratul Agarwal
 * Created On : 17/09/2024
 */
package assignment_java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter; // enables writing game data to a file
import java.util.Random;
import java.util.Scanner;

public class Game2048 {

    private static final int GRIDSIZE = 4;
    private int[][] gameBoard;// 2D array representing the game board
    private Random random; // creates an instance of Random to generate random numbers for tile placement.
    private Scanner userInput;
    private String playerName;
    private int score;

    public Game2048() {
    	gameBoard = new int[GRIDSIZE][GRIDSIZE]; // initializes the 4x4 game board with zeros (empty)
        random = new Random();
        userInput = new Scanner(System.in);
    }

    /**
     * Main method that drives the game loop.
     * Asks the user for their name, whether they want to load a game,
     * and processes player input for movements and game actions.
     */
    public void play() {
        System.out.print("[[[GAME OF 2048]]]\n");
        System.out.print("Enter your name: ");
        playerName = userInput.nextLine();
        System.out.print("Load saved game? (Y/N): ");
        char choice = userInput.nextLine().toUpperCase().charAt(0);

        if (choice == 'Y') {
            loadGame();
        } else {
            startNewGame();
        }

        while (true) {
            clearConsole(); // Simulates console clearing
            printBoard();
            if (isGameOver()) {
                System.out.println("Game Over!");
                break;
            }

            System.out.println("Choose an option: ");
            System.out.println("U: Move Up");
            System.out.println("L: Move Left");
            System.out.println("D: Move Down");
            System.out.println("R: Move Right");
            System.out.println("E: Exit");
            System.out.print("Enter option: ");
            char option = userInput.nextLine().trim().toUpperCase().charAt(0);

            switch (option) {
                case 'U' -> moveUp();
                case 'L' -> moveLeft();
                case 'D' -> moveDown();
                case 'R' -> moveRight();
                case 'E' -> {
                    System.out.println("Exiting the game...");
                    exitMenu();
                    userInput.close();
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }

            if (!isBoardFull()) {
                addNewTile();
            }
        }
    }

    /**
     * Simulates clearing the console by printing multiple new lines.
     */
    private void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    /**
     * Starts a new game by clearing the board and adding two new tiles.
     */
    private void startNewGame() {
        clearBoard();       
        addNewTile();
        addNewTile();
    }

    /**
     * Clears the game board by setting all values to 0 and resets the score.
     */
    private void clearBoard() {
        for (int row = 0; row < GRIDSIZE; row++) {
            for (int col = 0; col < GRIDSIZE; col++) {
            	gameBoard[row][col] = 0;
            }
        }
        score = 0;
    }

    /**
     * Adds a new tile (either 2 or 4) at a random empty position on the board.
     */
    private void addNewTile() {
        int row, col;
        do {
            row = random.nextInt(GRIDSIZE);
            col = random.nextInt(GRIDSIZE);
        } while (gameBoard[row][col] != 0);
        gameBoard[row][col] = random.nextInt(10) == 0 ? 4 : 2;
    }

    /**
     * Moves all tiles to the left and merges them if needed.
     */
    private void moveLeft() {
        for (int row = 0; row < GRIDSIZE; row++) {
            mergeRow(gameBoard[row]);
        }
    }

    /**
     * Moves all tiles to the right and merges them if needed.
     * Reverses the row before and after merging to simulate right movement.
     */
    private void moveRight() {
        for (int row = 0; row < GRIDSIZE; row++) {
            reverse(gameBoard[row]);
            mergeRow(gameBoard[row]);
            reverse(gameBoard[row]);
        }
    }

    /**
     * Moves all tiles upwards and merges them if needed.
     */
    private void moveUp() {
        for (int col = 0; col < GRIDSIZE; col++) {
            int[] column = getColumn(col);
            mergeRow(column);
            setColumn(col, column);
        }
    }

    /**
     * Moves all tiles downwards and merges them if needed.
     * Reverses the column before and after merging to simulate downward movement.
     */
    private void moveDown() {
        for (int col = 0; col < GRIDSIZE; col++) {
            int[] column = getColumn(col);
            reverse(column);
            mergeRow(column);
            reverse(column);
            setColumn(col, column);
        }
    }

    /**
     * Merges the values of a row or column by moving all non-zero values together,
     * then combining adjacent values if they are the same.
     * @param row An array representing a row or column on the board.
     */
    private int[] mergeRow(int[] row) {
        int[] newRow = new int[GRIDSIZE];
        int index = 0;
        for (int i = 0; i < GRIDSIZE; i++) {
            if (row[i] != 0) {
                if (index > 0 && newRow[index - 1] == row[i]) {
                    newRow[index - 1] *= 2;
                    score += newRow[index - 1];
                } else {
                    newRow[index] = row[i];
                    index++;
                }
            }
        }
        return newRow;
    }

    /**
     * Reverses the contents of a row or column.
     * @param row An array representing a row or column on the board.
     */
    private void reverse(int[] row) {
        for (int i = 0; i < GRIDSIZE / 2; i++) {
            int temp = row[i];
            row[i] = row[GRIDSIZE - 1 - i];
            row[GRIDSIZE - 1 - i] = temp;
        }
    }

    /**
     * Extracts a column from the board.
     * @param index The column index to extract.
     * @return An array representing the column.
     */
    private int[] getColumn(int colIndex) {
        int[] column = new int[GRIDSIZE];
        for (int row = 0; row < GRIDSIZE; row++) {
            column[row] = gameBoard[row][colIndex];
        }
        return column;
    }

    /**
     * Updates the board with the values from the provided column array.
     * @param index The column index to update.
     * @param column The new column values.
     */
    private void setColumn(int colIndex, int[] column) {
        for (int row = 0; row < GRIDSIZE; row++) {
            gameBoard[row][colIndex] = column[row];
        }
    }

    /**
     * Checks if the board is full.
     * @return True if no empty spaces are left, false otherwise.
     */
    private boolean isBoardFull() {
        for (int row = 0; row < GRIDSIZE; row++) {
            for (int col = 0; col < GRIDSIZE; col++) {
                if (gameBoard[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if the game is over, i.e., the board is full and no moves are possible.
     * @return True if the game is over, false otherwise.
     */
    private boolean isGameOver() {
        if (!isBoardFull()) return false;
        for (int row = 0; row < GRIDSIZE; row++) {
            for (int col = 0; col < GRIDSIZE; col++) {
                if (row < GRIDSIZE - 1 && gameBoard[row][col] == gameBoard[row + 1][col]) return false;
                if (col < GRIDSIZE - 1 && gameBoard[row][col] == gameBoard[row][col + 1]) return false;
            }
        }
        return true;
    }

    /**
     * Prints the current state of the game board, along with the player name and score.
     */
    private void printBoard() {
        String horizontalBorder = "+----+----+----+----+";
        System.out.println("***GAME OF 2048***");
        System.out.println("[Player Name]: " + playerName);
        System.out.println("[Total Score]: " + score);

        for (int row = 0; row < GRIDSIZE; row++) {
            System.out.println(horizontalBorder);
            for (int col = 0; col < GRIDSIZE; col++) {
                if (gameBoard[row][col] == 0) {
                    System.out.print("|    ");
                } else {
                    System.out.printf("|%4d", gameBoard[row][col]);
                }
            }
            System.out.println("|");
        }
        System.out.println(horizontalBorder);
    }

    /**
     * Saves the current game state to a file named "<playerName>_game_save.txt".
     */
    private void saveGame() {
        try (PrintWriter writer = new PrintWriter(new File(playerName + "_game_save.txt"))) {
            writer.println(score);
            for (int[] row : gameBoard) {
                for (int tile : row) {
                    writer.print(tile + " ");
                }
                writer.println();
            }
            System.out.println("Game saved successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }

    /**
     * Loads a saved game state from a file named "<playerName>_game_save.txt".
     */
    private void loadGame() {
        try (Scanner fileScanner = new Scanner(new File(playerName + "_game_save.txt"))) {
            score = Integer.parseInt(fileScanner.nextLine());
            for (int row = 0; row < GRIDSIZE; row++) {
                for (int col = 0; col < GRIDSIZE; col++) {
                	gameBoard[row][col] = fileScanner.nextInt();
                }
            }
            System.out.println("Game loaded successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("No saved game found. Starting a new game.");
            startNewGame();
        }
    }
    private void startNewGameAfterExit() {
    	 clearBoard();
         play();
         printBoard();
         addNewTile();
         addNewTile();
    }
    /**
     * Displays the exit menu with options to start a new game, save the game, or exit.
     */
    private void exitMenu() {
        System.out.println("Choose an exit option:");
        System.out.println("1: Start a new game");
        System.out.println("2: Save and exit");
        System.out.println("3: Exit without saving");
        System.out.print("Enter your choice: ");
        int exitChoice = userInput.nextInt();
        userInput.nextLine(); // consume newline

        switch (exitChoice) {
            case 1 -> startNewGameAfterExit();
            case 2 -> saveGame();
            case 3 -> System.exit(0);
            default -> System.out.println("Invalid choice. Exiting without saving.");
        }
    }
    

    /**
     * Main method to start the game.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Game2048 game = new Game2048();
        game.play();
    }
}
