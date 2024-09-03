/***
 * Java program that takes a string input and outputs the frequency of
each character in a compressed form.

Owner : PratulAgarwal
Date of creation : 02/09/2024
 */
package assignment_java_programming;

import java.util.Scanner;

public class string_compressor {
    public static void main(String[] args) {
    	constant constant = new constant();
        Scanner scanner = new Scanner(System.in);
        String tryagain = "y";

        while (tryagain.equalsIgnoreCase("y")) {
            System.out.print(constant.ENTER_STRING2);
            String inputString = scanner.nextLine();

            // Validate input
            if (!isValidInput(inputString)) {
                System.out.println("Wrong input. Please ensure the string only contains lowercase letters and is up to 1000 characters long.");
            } else {
                StringBuilder compressedString = new StringBuilder();

                
                for (int i = 0; i < inputString.length(); i++) {
                    char currentChar = inputString.charAt(i);
                    int count = 1;

                    // Count contiguous occurrences of the current character
                    for (int j = i + 1; j < inputString.length(); j++) {
                        if (inputString.charAt(j) == currentChar) {
                            count++;
                        } else {
                            break;
                        }
                    }

                    
                    if (count > 1) {
                        compressedString.append(currentChar).append(count);
                    } else {
                        compressedString.append(currentChar);
                    }

                    // Skip over counted characters
                    i += count - 1;
                }

                System.out.println("Compressed string: " + compressedString.toString());
            }

            
            System.out.print(constant.TRY_AGAIN);
            tryagain = scanner.nextLine();
        }

        System.out.println("Program terminated.");
        scanner.close();
    }

    // Method to validate input
    public static boolean isValidInput(String s) {
        if (s.length() == 0 || s.length() > 1000) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            if (!Character.isLowerCase(s.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}