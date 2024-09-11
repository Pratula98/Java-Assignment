/***
 *A Java program where the user inputs a string in snake_case. If the string is not in
snake_case, first converts it to snake_case, and then to camelCase.
Owner = Pratul Agarwal
Date of Creation = 11/09/2024
 */
package assignment_java_programming;

import java.util.Scanner;

public class camelCaseConverter {

    // Recursive method to process characters of the input string
    private static String processCharacters(String input, int index, boolean capitalizeNext) {
        if (index >= input.length()) {           
            return "";
        }
        
        char currentChar = input.charAt(index);
        StringBuilder result = new StringBuilder();
        // Process the current character
        if (Character.isLetterOrDigit(currentChar)) {
            if (capitalizeNext) {
                // Capitalize the current character
                result.append(Character.toUpperCase(currentChar));
                capitalizeNext = false;
            } else {
                // Add the current character
                if (result.length() == 0) {
                    result.append(Character.toLowerCase(currentChar)); 
                } else {
                    result.append(currentChar);
                }
            }
        } 
        else {
            // Set flag to capitalize the next letter
            capitalizeNext = true;
        }

        // Recursively process the remaining characters
        result.append(processCharacters(input, index + 1, capitalizeNext));
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter a string:");
            String input = scanner.nextLine().trim();
                     
            String resultString = processCharacters(input, 0, false);                       
            System.out.println("The formatted string is:");
            System.out.println(resultString);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}