/*
 *Task 1: Valid Parentheses Combination Generator

 *Task 2: Digit Sum Loop(String)
               
 *Task 3: Consecutive Number Summer
            
*Task 4: Caesar Cipher with Shift Variability
           
*Task 5: Encoding Challenge with ASCII Conversion
           
*Owner-->Pratul Agarwal
*DATE-->09/09/2024
 */

package assignment_java_programming;
import java.util.Scanner;


public class week2assignment1 {
    public static void main(String[] args) {
        Scanner Inputfromuser = new Scanner(System.in);
        String input;

        do {
            System.out.println(Constants.INDEX);
            System.out.println(Constants.TASKNUMBER);
            input = Inputfromuser.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println(Constants.EXIT);
            } else {
                int taskNumber;
                try {
                    taskNumber = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println(Errors.INPUTERROR);
                    continue;
                }
                switch (taskNumber) {
                    case 1:
                        validParenthesesCombinationGenerator(Inputfromuser);
                        break;
                    case 2:
                        digitSumLoop(Inputfromuser);
                        break;
                    case 3:
                        //consecutiveNumberSummer();
                        break;
                    case 4:
                        //caesarCipherWithShiftVariability(Inputfromuser);
                        break;
                    case 5:
                     //   encodingChallenge(Inputfromuser);
                        break;
                    default:
                        System.out.println(Errors.INVALIDTASKNUMBER);
                        break;
                }
            }
        } while (!input.equalsIgnoreCase("exit"));

        Inputfromuser.close();
    }

    private static void validParenthesesCombinationGenerator(Scanner Inputstring) {
        System.out.println(Constants.TASK1EXECUTION);
        System.out.println(Constants.TASK1INPUT);
        String input = Inputstring.nextLine();
        generateCombinations("", input);
    }

    private static void generateCombinations(String current, String input) {
        if (current.length() == input.length()) {
            System.out.print(current + " ");
            return;
        }
        for (int i = 0; i < input.length(); i++) {
            generateCombinations(current + input.charAt(i), input);
        }
    }

    private static void digitSumLoop(Scanner InputDigit) {
        System.out.println(Constants.TASK2EXECUTION);
        System.out.print(Constants.TASK2INPUT);

        int number = InputDigit.nextInt();
        InputDigit.nextLine();
        if (number < 0) {
            System.out.println(Constants.PLEASEINPUT);
        } else {
            int result = digitSum(number);
            System.out.println(" Output: " + result);
        }
    }

    private static int digitSum(int number) {
        while (number >= 10) {
            number = sumOfDigits(number);
        }
        return number;
    }

    private static int sumOfDigits(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}    