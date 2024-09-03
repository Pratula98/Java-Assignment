/***
 * A Java program that converts a given integer into its written English form.
 * Owner : PratulAgarwal
   Date of creation : 03/09/2024
 */
package assignment_java_programming;

import java.util.Scanner;

public class Number_Words {

    public static void main(String[] args) {
    	constant constant = new constant();
        Scanner scanner = new Scanner(System.in);
        boolean continueProgram = true;

        while (continueProgram) {
            System.out.print(constant.ENTER_STRING5);
            String input = scanner.nextLine();

            try {
                int number = Integer.parseInt(input);

                if (number < 1 || number > 1000) {
                    System.out.println("Invalid input! The number must be between 1 and 1000.");
                } else {
                    System.out.println(convertToWords(number));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }

            System.out.print(constant.TRY_AGAIN);
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("n")) {
                continueProgram = false;
            } else if (!response.equals("y")) {
                System.out.println("Invalid response! The program will now terminate.");
                continueProgram = false;
            }
        }

        scanner.close();
        System.out.println("Program terminated.");
    }

    private static String convertToWords(int number) {
        if (number == 1000) {
            return "one thousand";
        }

        String[] ones = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] teens = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tens = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

        String words = "";

        if (number >= 100) {
            int hundreds = number / 100;
            words += ones[hundreds] + " hundred";
            number %= 100;
            if (number > 0) {
                words += " and ";
            }
        }

        if (number >= 20) {
            int ten = number / 10;
            int one = number % 10;
            words += tens[ten];
            if (one > 0) {
                words += " " + ones[one];
            }
        } else if (number >= 10) {
            words += teens[number - 10];
        } else if (number > 0) {
            words += ones[number];
        }

        return words.trim();
    }
}