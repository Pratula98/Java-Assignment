/***
 * A Java program that takes an integer in binary format and converts it to its decimal
representation.
Owner : Pratul Agarwal
Date of Creation : 12/09/2024
 */
package assignment_java_programming;

import java.util.Scanner;

public class binaryToDecimal {
    /**
     * Entry point of the program, which reads binary input from the user and converts it to decimal.
     * Handles exceptions and prints the decimal equivalent of the binary number. 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter a binary number (it can include a decimal point, e.g., 1101.101): ");
            String binaryInput = scanner.nextLine();

            if (!isValidBinary(binaryInput)) {
                throw new IllegalArgumentException("Invalid input. Please enter a valid binary number.");
            }

            String[] parts = binaryInput.split("\\.");  

            // Handling binary integer part (before decimal point)
            int integerPart = 0;
            if (parts.length > 0) {
                integerPart = convertBinaryToDecimal(parts[0], parts[0].length() - 1);  // Convert the part before decimal point using recursion
            }

            // Handling binary fractional part (after decimal point)
            double fractionalPart = 0.0;
            if (parts.length == 2) {
                fractionalPart = convertBinaryFractionToDecimal(parts[1], 1);  // Convert the part after decimal point using recursion
            }

            // Final result: sum of integer part and fractional part
            double decimalNumber = integerPart + fractionalPart;

            System.out.println("Decimal equivalent: " + decimalNumber);

        } catch (IllegalArgumentException e) {
            // Catch invalid input exception
            System.out.println(e.getMessage());
        } catch (Exception e) {
            // Catch any unforeseen exceptions
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            // Close the scanner resource
            scanner.close();
        }
    }

    /**
     * Validates if the input string is a valid binary number (with at most one decimal point).    
     * @param input The string to validate.
     * @return True if the input is a valid binary number, otherwise false.
     */
    public static boolean isValidBinary(String input) {
        return isValidBinaryHelper(input, 0, 0);
    }
    
    /**
     * Recursive helper method for binary validation.      
     * @param input The string to validate.
     * @param index The current index in the string.
     * @param decimalPointCount The count of decimal points encountered so far.
     * @return True if the string is valid binary, otherwise false.
     */
    private static boolean isValidBinaryHelper(String input, int index, int decimalPointCount) {
        if (index >= input.length()) {
            return decimalPointCount <= 1; // Valid if there is at most one decimal point
        }

        char c = input.charAt(index);

        if (c == '.') {
            decimalPointCount++;
            return decimalPointCount <= 1 && isValidBinaryHelper(input, index + 1, decimalPointCount);
        } else if (c != '0' && c != '1') {
            return false; // Invalid character
        }

        return isValidBinaryHelper(input, index + 1, decimalPointCount);
    }

    /**
     * Recursively converts the binary part before the decimal point to decimal.      
     * @param binary The binary string to convert.
     * @param index The current index in the string.
     * @return The decimal representation of the binary string.
     */
    public static int convertBinaryToDecimal(String binary, int index) {
        if (index < 0) {
            return 0;  // Base case: no more digits to process
        }
        
        int currentDigit = binary.charAt(index) - '0';  // Convert char '0' or '1' to integer
        int power = binary.length() - 1 - index;  // Calculate power of 2 for this position
        
        // Recursively process the rest of the digits
        return currentDigit * powerOfTwo(power) + convertBinaryToDecimal(binary, index - 1);
    }

    /**
     * Recursively converts the binary fractional part to decimal.   
     * @param binaryFraction The binary fractional string to convert.
     * @param position The current position in the string.
     * @return The decimal representation of the binary fractional part.
     */
    public static double convertBinaryFractionToDecimal(String binaryFraction, int position) {
        if (position > binaryFraction.length()) {
            return 0.0;  // Base case: no more digits to process
        }

        int currentDigit = binaryFraction.charAt(position - 1) - '0';  
        
        // Recursively process the rest of the digits
        return currentDigit * (1.0 / powerOfTwo(position)) + convertBinaryFractionToDecimal(binaryFraction, position + 1);
    }

    /**
     * Recursively calculates powers of two.     
     * @param power The exponent to which 2 is raised.
     * @return The result of 2 raised to the given power.
     */
    public static int powerOfTwo(int power) {
        if (power == 0) {
            return 1; 
        } else {
            return 2 * powerOfTwo(power - 1);  // Recursive case: multiply by 2
        }
    }
}