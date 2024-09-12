/***
 *A Java program to print the nth number in the Fibonacci series without using loops.
Owner = Pratul Agarwal
Date of Creation = 11/09/2024
 */
package assignment_java_programming;

import java.math.BigInteger;
import java.util.Scanner;

public class fibonacciSeries {

    /**
     * Recursive helper method to calculate the nth Fibonacci number.    
     * @param n The index of the Fibonacci number to calculate.
     * @param a The current Fibonacci number (F(n-1)).
     * @param b The next Fibonacci number (F(n)).
     * @return The nth Fibonacci number as a BigInteger.
     */
    private static BigInteger nthIntegerHelper(int n, BigInteger a, BigInteger b) {
        if (n == 0) {
            return a;  // Base case for F(0)
        }
        if (n == 1) {
            return b;  // Base case for F(1)
        }
        // Recursive call to continue calculating the Fibonacci sequence
        return nthIntegerHelper(n - 1, b, a.add(b));
    }

    /**
     * Method to calculate the nth Fibonacci number by invoking the helper method.     
     * @param input The index of the Fibonacci number to calculate.
     * @return The nth Fibonacci number as a BigInteger.
     */
    private static BigInteger nthInteger(int input) {
        return nthIntegerHelper(input, BigInteger.ZERO, BigInteger.ONE);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the value of n to find the nth Fibonacci number:");
        String input = scanner.nextLine();
        int integerInput;

        try {
           
            integerInput = Integer.parseInt(input);
        }
        catch (NumberFormatException e) {
           
            System.out.println("Invalid input");
            return;
        }
        finally {
            scanner.close();
        }

        if (integerInput < 0) {
           
            System.out.println("n must be a non-negative integer.");
        } else {
            // Calculate the nth Fibonacci number
            BigInteger result = nthInteger(integerInput);
            System.out.println(result);
        }

        System.out.println();
    }
}
    
  
