/***
 *When the program is run, the user will first enter a string. After that, the program should
ask the user which operation they want to execute on the string. The available operations are
listed below: Append, CountWords, Replace, isPalindrome, Splice, Split,
MaxRepeatingCharacter, Sort, Shift, and Reverse. Each method should perform its
respective operation based on the user's input.
Owner : Pratul_Agarwal
Date of creation : 04/09/2024
 */
package assignment_java_programming;

import java.util.Scanner;

public class assignment_2 {

    public static void main(String[] args) {
    	constant constant = new constant();
        Scanner scanner = new Scanner(System.in);
        String tryagain = "y";
        
        while (tryagain.equalsIgnoreCase("y")) {
        System.out.println(constant.ENTER_STRING6);
        String input = scanner.nextLine();

        // Present operations to the user
        System.out.println("Which operation you want to execute on the string.");
        System.out.println("1. Append");
        System.out.println("3. Replace");
        System.out.println("4. isPalindrome");
        System.out.println("5. Splice");
        System.out.println("7. MaxRepeatingCharacter");
        System.out.println("8. Sort");
        System.out.println("9. Shift");
        System.out.println("10. Reverse");

        int choice = scanner.nextInt();
        scanner.nextLine();  

        switch (choice) {
            case 1:
                System.out.println("Enter string to append:");
                String appendStr = scanner.nextLine();
                System.out.println("Result: " +Append_String(input, appendStr));
                break;
            case 3:
                System.out.println("Enter target substring to replace:");
                String target = scanner.nextLine();
                System.out.println("Enter replacement substring:");
                String replacement = scanner.nextLine();
                System.out.println("Result: " + replace(input, target, replacement));
                break;                     
            case 7:
                System.out.println("Max repeating character: " + maxRepeatingCharacter(input));
                break;
            case 8:
                System.out.println("Sorted string: " + sort(input));
                break;
            case 9:
                System.out.println("Enter shift amount:");
                int shiftAmount = scanner.nextInt();
                scanner.nextLine();  // Consume newline character
                System.out.println("Result: " + shift(input, shiftAmount));
                break;
            case 10:
                System.out.println("Reversed string: " + reverse(input));
                break;
            default:
                System.out.println("Invalid choice.");
        }
        System.out.print(constant.TRY_AGAIN);
        tryagain = scanner.nextLine();
    }

    System.out.println("Program terminated.");
    scanner.close(); } 

    // Append a string
    public static String Append_String(String input, String appendStr) {
    	input+= appendStr;
        System.out.println("Given String: " + input);
        return input; // Return the updated string
    }

    // Replace a substring with another substring
    public static String replace(String str, String target, String replacement) {
        return str.replace(target, replacement);
    }

    
    // Find the maximum repeating character
    public static char maxRepeatingCharacter(String str) {
        int[] charCount = new int[256]; 
        for (int i = 0; i < str.length(); i++) {
            charCount[str.charAt(i)]++;
        }

        int maxCount = -1;
        char result = '\0';
        for (int i = 0; i < charCount.length; i++) {
            if (charCount[i] > maxCount) {
                maxCount = charCount[i];
                result = (char) i;
            }
        }
        return result;
    }

    // Sort the characters of the string
    public static String sort(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] > chars[j]) {
                    char temp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = temp;
                }
            }
        }
        return new String(chars);
    }

    // Shift characters in the string by a given amount
    public static String shift(String str, int shiftAmount) {
        int length = str.length();
        shiftAmount = (shiftAmount % length + length) % length; // Handle negative shift amounts
        return str.substring(length - shiftAmount) + str.substring(0, length - shiftAmount);
    }

    // Reverse the string
    public static String reverse(String str) {
        char[] chars = str.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }
}

