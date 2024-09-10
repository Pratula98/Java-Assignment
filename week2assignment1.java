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
import java.util.Arrays;

public class week2assignment1 {
	 static Scanner input = new Scanner(System.in);
	 static String originalString;
	 static String finalResult;
	 static int combinationCount;
    @SuppressWarnings("resource")
    
	 public static void main(String[] args) {
        Scanner Inputfromuser = new Scanner(System.in);
        String input;
        do {
            System.out.println(Constants.INDEX);
            System.out.println(Constants.TASKNUMBER);
            input = Inputfromuser.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println(Constants.EXIT);
            }
            else {
                int taskNumber;
                try {
                    taskNumber = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println(Errors.INPUTERROR);
                    continue;
                }
                
                switch (taskNumber) {
                    case 1:
                         System.out.println("enter string :");
                         String inputString = Inputfromuser.nextLine();
                         if (inputString == null || inputString.isEmpty()) {
                             System.out.println("Error: String can't be empty");
                             return;
                         }
                         char[] characterInputs = new char[inputString.length()];
                         boolean[] used = new boolean[characterInputs.length];
                         for (int i = 0; i < inputString.length(); i++) {
                             characterInputs[i] = inputString.charAt(i);
                         }
                         char[] result = new char[characterInputs.length];
                         finalResult = "";
                         combinationCount=0;
                         finalResult += "[";
                         System.out.println("Total Combinations :- ");
                         for (int length = 1; length <= characterInputs.length; length++) {
                             generateStrings(characterInputs, result, 0, used, length);
                         }
                         finalResult += "]";
                         System.out.println(finalResult);
                         System.out.println("Total combination is "+combinationCount);
                         System.out.println();
                         break;
                         
                    case 2:
                        digitSumLoop(Inputfromuser);
                        break;
                    
                    case 3:
                        try {
                            
                        	System.out.println(Constants.TASK3EXECUTION);
                            Scanner Inputnumber = new Scanner(System.in);
                            System.out.print(Constants.TASK2INPUT);
                            int number = Inputnumber.nextInt();
                            System.out.println("All possible consecutive sums for " + number + ":");
                            findConsecutiveSums(number);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid number for Consecutive Number Summer.");
                        }
                        break;
                        
                    case 4:
                        caesarCipherWithShiftVariability(Inputfromuser);
                        break;
                        
                    case 5:
                    	 System.out.println("Executing Case 5 :");
                         encodeToASCII();
                         break;
                         
                    default:
                        System.out.println(Errors.INVALIDTASKNUMBER);
                        break;
                }
            }
        } while (!input.equalsIgnoreCase("exit"));
        Inputfromuser.close();
    }

   
/***
 *  //Sum of digits function is used to print the sum of all the digits until we left with one digit number
	//This is recursive function which uses number as parameter
 * @param InputDigit
 */
    
    private static void digitSumLoop(Scanner InputDigit){
        System.out.println(Constants.TASK2EXECUTION);
        System.out.print(Constants.TASK2INPUT);
        int number = InputDigit.nextInt();
        InputDigit.nextLine();
        if (number < 0) {
            System.out.println(Constants.PLEASEINPUT);
        } 
        else {
            int result = digit(number);
            System.out.println(" Output: " + result);
        }
    }

    private static int digit(int number){
        while (number >= 10) {
            number = digitSum(number);
        }
        return number;
    }

    private static int digitSum(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

 
    
    //Print Numbers function is used to print consecutive numbers with addition sign
    
    public static void findConsecutiveSums(int number) {
        for (int start = 1; start < number; start++) {
            int sum = 0;
            for (int i = start; i < number; i++) {
                sum += i;
                if (sum == number) {
                    printConsecutiveNumbers(start, i);
                    break;
                } 
                else if (sum > number) {
                    break;
                }
            }
        }
        System.out.print("No possible combinations");
        }
    //Print Numbers function is used to print consecutive numbers with addition sign
   public static void printConsecutiveNumbers(int start, int end) {
        for (int i = start; i <= end; i++) {
            if (i != start) {
                System.out.print(" + ");
            }
            System.out.print(i);
        }
        System.out.println();
    }
   
 
    private static void caesarCipherWithShiftVariability(Scanner Inputfromuser) {
        System.out.println(Constants.TASK4EXECUTION);
        System.out.print(Constants.INPUTSTRING);
        String input = Inputfromuser.nextLine();

        System.out.print(Constants.SHIFTINPUT);
        String[] shiftInput = Inputfromuser.nextLine().split(",");
        int[] shiftPattern = new int[shiftInput.length];
        for (int i = 0; i < shiftInput.length; i++) {
            shiftPattern[i] = 0;
            for (char c : shiftInput[i].toCharArray()) {
                shiftPattern[i] = shiftPattern[i] * 10 + (c - '0');
            }
        }
        String encryptedText = encryptWithCaesarCipher(input, shiftPattern);
        System.out.println("Input: " + input);
        System.out.println("Shift Pattern: " + Arrays.toString(shiftPattern));
        System.out.println("Output: " + encryptedText);
    }
    private static String encryptWithCaesarCipher(String input, int[] shiftPattern) {
        String encrypted = "";
        int shiftIndex = 0;
        for (char currentChar : input.toCharArray()) {
            if (Character.isLetter(currentChar)) {
                int shift = shiftPattern[shiftIndex % shiftPattern.length];
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                currentChar = (char) ((currentChar - base + shift) % 26 + base);
                shiftIndex++;
            }
            encrypted += currentChar;
        }

        return encrypted;
    }

  
    private static boolean isValidInput(String inputArray) {
        for (char c : inputArray.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidSeries(String series) {
        for (char c : series.toCharArray()) {
            if (!Character.isDigit(c) || Character.getNumericValue(c) < 1) {
                return false;
            }
        }
        return true;
    }

    private static void encodeToASCII() {
        input.nextLine();
        String inputArray;
        String series;
        while (true) {
            System.out.println("Enter array of digits : ");
            inputArray = input.nextLine();
            System.out.println("Enter series : ");
            series = input.nextLine();

            if (inputArray.isEmpty() || series.isEmpty() || !isValidInput(inputArray) || !isValidSeries(series)) {
                System.out.println("Invalid input. Please enter only single-digit integers.");
            }else{
                break;
            }
        }
        

        int[] digits = new int[inputArray.length()];
        for (int i = 0; i < inputArray.length(); i++) {
            digits[i] = Character.getNumericValue(inputArray.charAt(i));
        }

        Arrays.sort(digits);

        String encodedString ="" ;

        for (char c : series.toCharArray()) {
            int index = Character.getNumericValue(c) - 1;
            if (index >= 0 && index < digits.length) {
                encodedString += String.valueOf((int) (digits[digits.length - 1 - index] + 48));
            }
        }
        System.out.println("output is : " + encodedString);
    }

/*This Function is used to generate all sub-strings 
	//Parameters include character array which stores every element of given String
	//Resultant array which stores all possible substrings
	//Index is used for keeping track of resultant array
	//Used array is used to remove duplicates
	//Length is used to traverse through to the character input array
	 * 
	 */
  public static void generateStrings(char[] characterInputs, char[] result, int index, boolean[] used, int length) {
      if (index == length) {
          finalResult += '"';
          for (int i = 0; i < length; i++) {
              finalResult = finalResult + result[i];
          }
          combinationCount++;
          finalResult+='"'+",";
          return;
      }
      for (int i = 0; i < characterInputs.length; i++) {
          if (!used[i]) {
              used[i] = true;
              result[index] = characterInputs[i];
              generateStrings(characterInputs, result, index + 1, used, length);
              used[i] = false;
          }
      }
  }   
}
   
  
