/***
 * Given a string containing characters followed by digits, expand each
character by repeating it according to the digit that follows.

Owner : PratulAgarwal
Date of creation : 02/09/2024
 */
package assignment_java_programming;

import java.util.Scanner;

public class ExpandString {
    public static void main(String[] args) {
    	constant constant = new constant();
        Scanner scanner = new Scanner(System.in);
        String tryagain = "y";
    

        while (tryagain.equalsIgnoreCase("y")) {
        	 System.out.print(constant.ENTER_STRING);
            String inputString = scanner.nextLine();

        
            if (!isValidInput(inputString)) {
                System.out.println("Wrong input. Please enter the string in the form of a2b34c5 and digits do not exceed 100.");
            } else {
                String expandedString = "";

                for (int i = 0; i < inputString.length(); i++) {
                    char ch = inputString.charAt(i);
                    StringBuilder digit = new StringBuilder();

                   
                    while (i + 1 < inputString.length() && Character.isDigit(inputString.charAt(i + 1))) {
                    	digit.append(inputString.charAt(i + 1));
                        i++;
                    }

                    // Convert  digits to an integer
                    int count = Integer.parseInt(digit.toString());
                    for (int j = 0; j < count; j++) {
                    	expandedString += ch;
                    }
                }

                System.out.println("Expanded string: " + expandedString);
            }

         
            System.out.print(constant.TRY_AGAIN);
            tryagain = scanner.nextLine();
        }

        System.out.println("Program terminated.");
        scanner.close();
    }
    // Method to validate input
    public static boolean isValidInput(String s) {
        if (s.length() == 0) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            if (!Character.isLowerCase(s.charAt(i))) {
                return false;
            }
            i++;
            if (i < s.length() && !Character.isDigit(s.charAt(i))) {
                return false;
            }

            // Collect the digits to check if the number exceeds 99
            StringBuilder digit = new StringBuilder();
            while (i < s.length() && Character.isDigit(s.charAt(i))) {
            	digit.append(s.charAt(i));
                i++;
            }
            i--;
           
            if (Integer.parseInt(digit.toString()) > 100) {
                return false;
            }
        }

        return true;
    }
}