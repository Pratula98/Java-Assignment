/***
 *A Java program to print the nth number in the Fibonacci series without using loops.
Owner = Pratul Agarwal
Date of Creation = 11/09/2024
 */
package assignment_java_programming;

import java.util.Scanner;

public class fibonacciSeries {
   
    public static long fibonacci(long n) {
        
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        // Recursive calculation
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter the value of n to find the nth Fibonacci number:");
            int n = scanner.nextInt();

            if (n < 0) {
                throw new IllegalArgumentException("n must be a non-negative integer.");
            }
            // Calculate and print the nth Fibonacci number
            long nthFibonacci = fibonacci(n);
            System.out.println("The " + n + "th Fibonacci number is: " + nthFibonacci);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
