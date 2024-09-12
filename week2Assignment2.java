
package assignment_java_programming;

import java.util.Scanner;
import java.util.Set;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.InputMismatchException;

public class week2Assignment2 {
    static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        Scanner inputFromUser = new Scanner(System.in);
        String userInput;

        do {
        	System.out.println(Constants.INDEX);
        	System.out.println(Constants.TASKNUMBER);
            userInput = inputFromUser.nextLine();
            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program.");
                break;
            }

            int taskNumber;
            try {
                taskNumber = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Input error: Please enter a valid task number.");
                continue;
            }

            switch (taskNumber) {
                case 1:
                    try {
                        System.out.println("Enter a string:");
                        String input = inputFromUser.nextLine().trim();

                        if (input.isEmpty()) {
                            throw new IllegalArgumentException("Input cannot be empty.");
                        }

                        String cleanedInput = cleanInput(input);

                        if (!cleanedInput.equals(input)) {
                            System.out.println("Invalid input: Special characters are not allowed.");
                            return;
                        }

                        Set<String> uniqueSubstrings = collectSubstrings(cleanedInput);

                        Set<String> palindromicSubstrings = new HashSet<>();
                        for (String substring : uniqueSubstrings) {
                            if (isPalindrome(substring)) {
                                palindromicSubstrings.add(substring);
                            }
                        }

                        String[] palindromesArray = palindromicSubstrings.toArray(new String[0]);

                        System.out.println("Palindromic substrings:");
                        for (String palindrome : palindromesArray) {
                            System.out.println(palindrome);
                        }

                        System.out.println("Total count of palindromic substrings: " + palindromesArray.length);

                    } catch (IllegalArgumentException e) {
                        System.err.println("An error occurred: " + e.getMessage());
                    } catch (InputMismatchException e) {
                        System.err.println("Invalid input: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.println("Enter the value of n to find the nth Fibonacci number:");
                        String input = inputFromUser.nextLine();
                        int integerInput = Integer.parseInt(input);

                        if (integerInput < 0) {
                            System.out.println("n must be a non-negative integer.");
                        } else {
                            BigInteger result = nthInteger(integerInput);
                            System.out.println(result);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input");
                    }
                    break;

                case 3:
                	  try {
                          System.out.println("Enter a string:");
                          String input = inputFromUser.nextLine().trim();

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
                    break;

                case 4:
                    try {
                        System.out.println("Enter a string:");
                        String input = inputFromUser.nextLine();

                        char[] resultArray = new char[input.length()];
                        int[] pos = {0}; // Array to hold the current position in resultArray

                        processCharacters(input, 0, resultArray, pos);

                        char[] finalResultArray = new char[pos[0]];
                        System.arraycopy(resultArray, 0, finalResultArray, 0, pos[0]);

                        System.out.println("Consonants in the array are:");
                        printConsonants(finalResultArray, 0, 0);

                    } catch (Exception e) {
                        System.err.println("An error occurred: " + e.getMessage());
                    }
                    break;

                case 5:
                    try {
                        System.out.print("Enter a binary number (it can include a decimal point, e.g., 1101.101): ");
                        String binaryInput = inputFromUser.nextLine();

                        if (!isValidBinary(binaryInput)) {
                            throw new IllegalArgumentException("Invalid input. Please enter a valid binary number.");
                        }

                        String[] parts = binaryInput.split("\\.");

                        int integerPart = 0;
                        if (parts.length > 0) {
                            integerPart = convertBinaryToDecimal(parts[0], parts[0].length() - 1);
                        }

                        double fractionalPart = 0.0;
                        if (parts.length == 2) {
                            fractionalPart = convertBinaryFractionToDecimal(parts[1], 1);
                        }

                        double decimalNumber = integerPart + fractionalPart;
                        System.out.println("Decimal equivalent: " + decimalNumber);

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Invalid task number. Please try again.");
                    break;
            }
        } while (!userInput.equalsIgnoreCase("exit"));

        inputFromUser.close();
    }
    
    /**
     * Collects all unique substrings from the input string.      
     * @param str The input string from which substrings are to be collected.
     * @return A Set of unique substrings.
     */
    private static Set<String> collectSubstrings(String str) {
        Set<String> substringsSet = new HashSet<>();
        collectSubstringsRecursive(str, 0, 1, substringsSet);
        return substringsSet;
    }
    
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
    
    /**
     * Checks if a given string is a palindrome.     
     * @param str The string to be checked.
     * @return True if the string is a palindrome, otherwise false.
     */
    private static boolean isPalindrome(String str) {
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

    /**
     * Method to calculate the nth Fibonacci number by invoking the helper method.     
     * @param input The index of the Fibonacci number to calculate.
     * @return The nth Fibonacci number as a BigInteger.
     */
    private static BigInteger nthInteger(int n) {
        return nthIntegerHelper(n, BigInteger.ZERO, BigInteger.ONE);
    }

    /**
     * Recursive helper method to calculate the nth Fibonacci number.    
     * @param n The index of the Fibonacci number to calculate.
     * @param a The current Fibonacci number (F(n-1)).
     * @param b The next Fibonacci number (F(n)).
     * @return The nth Fibonacci number as a BigInteger.
     */
    private static BigInteger nthIntegerHelper(int n, BigInteger a, BigInteger b) {
        if (n == 0) {
            return a;
        }
        if (n == 1) {
            return b;
        }
        return nthIntegerHelper(n - 1, b, a.add(b));
    }

    /**
     * Recursively prints consonants from the result array and counts them.      
     * @param resultArray The array of characters to process.
     * @param index The current index in the result array.
     * @param consonantCount The count of consonants found so far.
     */
    private static void printConsonants(char[] resultArray, int index, int count) {
        if (index >= resultArray.length) {
            System.out.println("Total consonants in the array: " + count);
            return;
        }

        char currentChar = resultArray[index];
        if (isConsonant(currentChar)) {
            System.out.print(currentChar + " ");
            printConsonants(resultArray, index + 1, count + 1);
        } else {
            printConsonants(resultArray, index + 1, count);
        }
    }

    /**
     * Checks if a character is a letter (a-z or A-Z).     
     * @param c The character to check.
     * @return True if the character is a letter, otherwise false.
     */
    private static boolean isLetter(char c) {
        return Character.isLetter(c);
    }

    /**
     * Checks if a character is a consonant (a letter that is not a vowel).   
     * @param c The character to check.
     * @return True if the character is a consonant, otherwise false.
     */
    private static boolean isConsonant(char c) {
        c = Character.toLowerCase(c);
        return "bcdfghjklmnpqrstvwxyz".indexOf(c) != -1;
    }

    /**
     * Converts a character to lowercase.      
     * @param c The character to convert.
     * @return The lowercase version of the character if it is uppercase, otherwise the character itself.
     */
    private static char toLowerCase(char c) {
        return Character.toLowerCase(c);
    }

    /**
     * Validates if the input string is a valid binary number (with at most one decimal point).    
     * @param input The string to validate.
     * @return True if the input is a valid binary number, otherwise false.
     */
    private static boolean isValidBinary(String binaryInput) {
        return binaryInput.matches("[01]+(\\.[01]+)?");
    }

    /**
     * Recursively converts the binary part before the decimal point to decimal.      
     * @param binary The binary string to convert.
     * @param index The current index in the string.
     * @return The decimal representation of the binary string.
     */
    private static int convertBinaryToDecimal(String binary, int power) {
        if (binary.isEmpty()) {
            return 0;
        }
        int lastDigit = Character.getNumericValue(binary.charAt(binary.length() - 1));
        return (int) (lastDigit * Math.pow(2, power)) + convertBinaryToDecimal(binary.substring(0, binary.length() - 1), power - 1);
    }

    /**
     * Recursively converts the binary fractional part to decimal.   
     * @param binaryFraction The binary fractional string to convert.
     * @param position The current position in the string.
     * @return The decimal representation of the binary fractional part.
     */
    private static double convertBinaryFractionToDecimal(String binaryFraction, int power) {
        if (binaryFraction.isEmpty()) {
            return 0.0;
        }
        int firstDigit = Character.getNumericValue(binaryFraction.charAt(0));
        return firstDigit / Math.pow(2, power) + convertBinaryFractionToDecimal(binaryFraction.substring(1), power + 1);
    }
    
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
}
