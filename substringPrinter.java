/***
 *A Java program that takes a string as input and calculates the number of unique
combinations where a palindrome is formed.
Owner = Pratul Agarwal
Date of Creation = 11/09/2024
 */
package assignment_java_programming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class substringPrinter {

    // HashSet to store and check for unique substrings
    static HashSet<String> substringsSet = new HashSet<>();
    // List to store palindromic substrings
    static ArrayList<String> palindromeList = new ArrayList<>();

    // Helper function to check if a string is a palindrome
    public static boolean isPalindrome(String str) {
        // Remove all minus signs and spaces from the string
        String cleanedStr = str.replace("-", "").replace(" ", "");
        return isPalindromeHelper(cleanedStr, 0, cleanedStr.length() - 1);
    }

    // Recursive function to check if a string is a palindrome
    private static boolean isPalindromeHelper(String str, int left, int right) {
        // Base case: if pointers cross or are equal
        if (left >= right) {
            return true;
        }
        if (str.charAt(left) != str.charAt(right)) {
            return false;
        }
        // Recursive call to check the next characters
        return isPalindromeHelper(str, left + 1, right - 1);
    }

    // Recursive function to collect all unique substrings
    public static void collectSubstrings(String str, int start, int end) {
        // Base case: when the starting index reaches the end of the string
        if (start == str.length()) {
            return;
        }

        // Collect the substring from 'start' to 'end' if it hasn't been collected yet
        if (end == str.length() + 1) {
            // Recursively call for the next starting index
            collectSubstrings(str, start + 1, start + 1);
        } else {
            String originalSubstring = str.substring(start, end);
           if (originalSubstring.isEmpty()) {
                // Ignore empty substrings
                collectSubstrings(str, start, end + 1);
                return;
            }

            // Remove minus signs and spaces for uniqueness check
            String cleanedSubstring = originalSubstring.replace("-", "").replace(" ", "");

            // Check if the cleaned substring hasn't been collected yet
            if (!substringsSet.contains(cleanedSubstring)) {
                substringsSet.add(cleanedSubstring);  

                // Check if the cleaned substring is a palindrome
                if (isPalindrome(originalSubstring)) {
                    // Only add specific palindromes based on your requirement
                    if (shouldIncludeInPalindromeList(originalSubstring)) {
                        palindromeList.add("\"" + originalSubstring + "\""); // Add original substring with minus signs
                    }
                }
            }
            // Recursively call for the next end index
            collectSubstrings(str, start, end + 1);
        }
    }

    // Function to determine whether a substring should be included in the palindrome list
    public static boolean shouldIncludeInPalindromeList(String str) {        
        String cleanedStr = str.replace("-", "").replace(" ", "");      
        if (cleanedStr.equals("1") || cleanedStr.equals("0") || cleanedStr.equals("101")) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter a string (with minus signs before each character if desired):");
            String input = scanner.nextLine();

            // Handle empty input
            if (input == null || input.trim().isEmpty()) {
                throw new IllegalArgumentException("Input cannot be null or empty.");
            }

            // Collecting all unique substrings recursively
            collectSubstrings(input, 0, 1);
           
            System.out.println(palindromeList);
            
            System.out.println("Total count of palindromes: " + palindromeList.size());

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}