/***
 * Java program to determine if a given integer is a prime number.
 * Owner : PratulAgarwal
   Date of creation : 02/09/2024
 */
package assignment_java_programming;

import java.util.Scanner;

public class Check_PrimeNumber {

    public static void main(String[] args) {
    	constant constant = new constant();
        Scanner scanner = new Scanner(System.in);
        boolean tryagain = true;

        while (tryagain) {
           
            System.out.print(constant.ENTER_STRING4);
            String input = scanner.nextLine();

            try {
                // Parse the input to an integer
                int number = Integer.parseInt(input);
                
               
                if (number < 1 || number > 1000000) {
                    System.out.println("Wrong input! The number must be between 1 and 10^6.");
                } else {
                    
                    if (isPrime(number)) {
                        System.out.println("The input is prime.");
                    } else {
                        System.out.println("The input is NOT prime.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }

          
            System.out.print(constant.TRY_AGAIN);
            String response = scanner.nextLine().trim().toLowerCase();
            
            // Check the response and decide whether to continue or terminate
            if (response.equals("n")) {
                tryagain = false; // Terminate the loop
            } else if (!response.equals("y")) {
                
                System.out.println("Invalid response! The program will now terminate.");
                tryagain = false; 
            }
        }

        scanner.close();
        System.out.println("Program terminated.");
    }

    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        if (number == 2) {
            return true; // 2 is the only even prime number
        }
        if (number % 2 == 0) {
            return false; // Exclude all even numbers greater than 2
        }
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}