/***
 * A Java program that takes a string as input and calculates the number of unique
combinations where a palindrome is formed.
Owner : Pratul Agarwal
Date of Creation : 11/09/2024
 */
package assignment_java_programming;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class countPalindrome {

    /**
     * Recursively collects all unique substrings from the input string.    
     * @param str The input string from which substrings are to be collected.
     * @param start The starting index for generating substrings.
     * @param end The ending index for generating substrings.
     * @param substringsSet A set to store unique substrings.
     */
    private static void collectSubstringsRecursive(String str, int start, int end, Set<String> substringsSet) {
        if (start >= str.length()) return;
        if (end > str.length()) {
            collectSubstringsRecursive(str, start + 1, start + 1, substringsSet);
            return;
        }
        substringsSet.add(str.substring(start, end));
        collectSubstringsRecursive(str, start, end + 1, substringsSet);
    }

    /*
     * Collects all unique substrings from the input string.      
     * @param str The input string from which substrings are to be collected.
     * @return A Set of unique substrings.
     */
    public static Set<String> collectSubstrings(String str) {
        Set<String> substringsSet = new HashSet<>();
        collectSubstringsRecursive(str, 0, 1, substringsSet);
        return substringsSet;
    }

    /**
     * Checks if a given string is a palindrome.     
     * @param str The string to be checked.
     * @return True if the string is a palindrome, otherwise false.
     */
    public static boolean isPalindrome(String str) {
        return isPalindromeHelper(str, 0, str.length() - 1);
    }

    /**
     * Recursively checks if a string is a palindrome.    
     * @param str The string to be checked.
     * @param left The left index.
     * @param right The right index.
     * @return True if the string is a palindrome, otherwise false.
     */
    private static boolean isPalindromeHelper(String str, int left, int right) {
        if (left >= right) return true;
        if (str.charAt(left) != str.charAt(right)) return false;
        return isPalindromeHelper(str, left + 1, right - 1);
    }

    /**
     * Removes spaces and special characters from the input string.   
     * @param str The input string to be cleaned.
     * @return The cleaned string with only alphanumeric characters.
     */
    public static String cleanInput(String str) {
        return str.replaceAll("[^a-zA-Z0-9]", "");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter a string:");
            String input = scanner.nextLine().trim();

            // Handle empty input
            if (input.isEmpty()) {
                throw new IllegalArgumentException("Input cannot be empty.");
            }

            // Clean input by removing spaces and special characters
            String cleanedInput = cleanInput(input);

            // Check if there were any special characters
            if (!cleanedInput.equals(input)) {
                System.out.println("Invalid input: Special characters are not allowed.");
                return;
            }

            // Collect all unique substrings
            Set<String> uniqueSubstrings = collectSubstrings(cleanedInput);

            // Filter palindromic substrings
            Set<String> palindromicSubstrings = new HashSet<>();
            for (String substring : uniqueSubstrings) {
                if (isPalindrome(substring)) {
                    palindromicSubstrings.add(substring);
                }
            }

            // Convert Set to Array for printing
            String[] palindromesArray = palindromicSubstrings.toArray(new String[0]);

            // Print palindromic substrings
            System.out.println("Palindromic substrings:");
            for (String palindrome : palindromesArray) {
                System.out.println(palindrome);
            }

            // Print total count of palindromic substrings
            System.out.println("Total count of palindromic substrings: " + palindromesArray.length);

        } catch (IllegalArgumentException e) {
            System.err.println("An error occurred: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
