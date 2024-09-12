/***
 * A Java program that takes a string as input and finds the number of consonants in
the string.
Owner : Pratul Agarwal
Date of Creation : 12/09/2024
 */
package assignment_java_programming;

import java.util.Scanner;

public class countConsonants {
    /**
     * Recursively processes characters from the input string and stores them in the result array.     
     * @param input The input string to process.
     * @param index The current index in the input string.
     * @param resultArray The array to store processed characters.
     * @param pos Array holding the current position in the resultArray.
     */
    private static void processCharacters(String input, int index, char[] resultArray, int[] pos) {
    	
        if (index >= input.length()) {
            return;
        }
        
        char currentChar = input.charAt(index);
        
        if (isLetter(currentChar)) {
            resultArray[pos[0]++] = toLowerCase(currentChar);
        }
        processCharacters(input, index + 1, resultArray, pos);
    }
    /**
     * Checks if a character is a letter (a-z or A-Z).     
     * @param c The character to check.
     * @return True if the character is a letter, otherwise false.
     */
    private static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    /**
     * Converts a character to lowercase.      
     * @param c The character to convert.
     * @return The lowercase version of the character if it is uppercase, otherwise the character itself.
     */
    private static char toLowerCase(char c) {
        if (c >= 'A' && c <= 'Z') {
            return (char) (c + ('a' - 'A'));
        }
        return c;
    }

    /**
     * Checks if a character is a consonant (a letter that is not a vowel).   
     * @param c The character to check.
     * @return True if the character is a consonant, otherwise false.
     */
    private static boolean isConsonant(char c) {
        return (c >= 'a' && c <= 'z') && !(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }

    /**
     * Recursively prints consonants from the result array and counts them.      
     * @param resultArray The array of characters to process.
     * @param index The current index in the result array.
     * @param consonantCount The count of consonants found so far.
     */
    private static void printConsonants(char[] resultArray, int index, int consonantCount) {
        if (index >= resultArray.length) {
            System.out.println();
            System.out.println("Total number of consonants: " + consonantCount);
            return;
        }
        
        char c = resultArray[index];
        if (isConsonant(c)) {
            System.out.print(c + " ");
            consonantCount++;
        }
        
        printConsonants(resultArray, index + 1, consonantCount);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter a string:");
            String input = scanner.nextLine();
            
            char[] resultArray = new char[input.length()];
            int[] pos = {0}; // Array to hold the current position in resultArray
            
            // Process the input string
            processCharacters(input, 0, resultArray, pos);
            
            char[] finalResultArray = new char[pos[0]];
            System.arraycopy(resultArray, 0, finalResultArray, 0, pos[0]);

            // Print consonants and count them
            System.out.println("Consonants in the array are:");
            printConsonants(finalResultArray, 0, 0);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}