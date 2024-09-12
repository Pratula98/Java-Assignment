/***
 * A Java program where the user inputs a string in snake_case. If the string is not in
snake_case, first convert it to snake_case, and then to camelCase.
Owner : Pratul Agarwal
Date of Creation : 11/09/2024
 */
package assignment_java_programming;

import java.util.Scanner;

public class camelCaseConverter {
    /**
     * Converts the input string to snake case using recursion.
     * @param input The original string to be converted.
     * @param index The current index of the string being processed.
     * @param lastWasLetterOrDigit Flag indicating if the last processed character was a letter or digit.
     * @return The converted string in snake case.
     */
    private static String toSnakeCase(String input, int index, boolean lastWasLetterOrDigit) {
        if (index >= input.length()) {
            return "";
        }
        
        char currentChar = input.charAt(index);
        String result = "";

        if (Character.isLetterOrDigit(currentChar)) {
            if (lastWasLetterOrDigit) {
                result = Character.toLowerCase(currentChar) + toSnakeCase(input, index + 1, true);
            } else {
                if (index > 0) {
                    result = "_" + Character.toLowerCase(currentChar) + toSnakeCase(input, index + 1, true);
                } else {
                    result = Character.toLowerCase(currentChar) + toSnakeCase(input, index + 1, true);
                }
            }
        } else {
            result = toSnakeCase(input, index + 1, false);
        }

        return result;
    }

    /**
     * Converts the input string from snake case to camel case using recursion.
     * 
     * @param input The snake case string to be converted.
     * @param index The current index of the string being processed.
     * @param capitalizeNext Flag indicating if the next character should be capitalized.
     * @param isFirstLetter Flag to ensure the first letter is always lowercase.
     * @return The converted string in camel case.
     */
    private static String toCamelCase(String input, int index, boolean capitalizeNext, boolean isFirstLetter) {
        if (index >= input.length()) {
            return "";
        }
        
        char currentChar = input.charAt(index);
        String result = "";

        if (currentChar == '_') {
            result = toCamelCase(input, index + 1, true, isFirstLetter);
        } else if (Character.isLetterOrDigit(currentChar)) {
            if (capitalizeNext && !isFirstLetter) {
                result = Character.toUpperCase(currentChar) + toCamelCase(input, index + 1, false, false);
            } else {
                result = Character.toLowerCase(currentChar) + toCamelCase(input, index + 1, false, false);
            }
        } else {
            result = toCamelCase(input, index + 1, capitalizeNext, isFirstLetter);
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter a string:");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                throw new IllegalArgumentException("Input cannot be empty.");
            }
            
            // Convert to snake case
            String snakeCase = toSnakeCase(input, 0, false);
            System.out.println("Snake case:");
            System.out.println(snakeCase);

            // Convert to camel case from snake case, ensuring the first letter is lowercase
            String camelCase = toCamelCase(snakeCase, 0, false, true);
            System.out.println("Camel case:");
            System.out.println(camelCase);
        } 
        catch (IllegalArgumentException e) {
            System.err.println("Input error: " + e.getMessage());
        } 
        catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } 
        finally {
            scanner.close();
        }
    }
}
