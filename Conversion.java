/***
 * Number System Conversion
 * Owner : Pratul Agarwal
 * Created On: 25/09/2024
 */
package assignment_java;

import java.util.Scanner;

public class Conversion {
	// Maximum input lengths based on 32-bit integer limits
    private static final int MAX_BINARY_LENGTH = 31; // 31 bits for positive values
    private static final int MAX_OCTAL_LENGTH = 11; // Integer.MAX_VALUE in octal is 17777777777
    private static final int MAX_DECIMAL_LENGTH = 10; // Integer.MAX_VALUE is 2147483647
    private static final int MAX_HEXADECIMAL_LENGTH = 8; // Integer.MAX_VALUE in hexadecimal is 7FFFFFFF

    /**
     * Validates if the input string is a valid binary number.
     * 
     * @param input The string to validate.
     * @return true if the input is a valid binary number, false otherwise.
     */
    public static boolean isValidBinary(String input) {
    	if (input.length() > MAX_BINARY_LENGTH) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch != '0' && ch != '1') {
                return false;
            }
        }
        return true;
    }

    /**
     * Validates if the input string is a valid octal number.
     * 
     * @param input The string to validate.
     * @return true if the input is a valid octal number, false otherwise.
     */
    public static boolean isValidOctal(String input) {
    	if (input.length() > MAX_OCTAL_LENGTH) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch < '0' || ch > '7') {
                return false;
            }
        }
        return true;
    }

    /**
     * Validates if the input string is a valid decimal number.
     * 
     * @param input The string to validate.
     * @return true if the input is a valid decimal number, false otherwise.
     */
    public static boolean isValidDecimal(String input) {  	
    	 if (input.length() > MAX_DECIMAL_LENGTH) {
             return false;
         }
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch < '0' || ch > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * Validates if the input string is a valid hexadecimal number.
     * 
     * @param input The string to validate.
     * @return true if the input is a valid hexadecimal number, false otherwise.
     */
    public static boolean isValidHexadecimal(String input) {
    	if (input.length() > MAX_HEXADECIMAL_LENGTH) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (!((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'F') || (ch >= 'a' && ch <= 'f'))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Converts a binary string to its decimal integer equivalent.
     * 
     * @param binary The binary string to convert.
     * @return The decimal integer equivalent of the binary string.
     */
    public static int binaryToDecimal(String binary) {
        int decimal = 0;
        int base = 1;
        for (int i = binary.length() - 1; i >= 0; i--) {
            decimal += (binary.charAt(i) - '0') * base;
            base *= 2;
        }
        return decimal;
    }

    /**
     * Converts an octal string to its decimal integer equivalent.
     * 
     * @param octal The octal string to convert.
     * @return The decimal integer equivalent of the octal string.
     */
    public static int octalToDecimal(String octal) {
        int decimal = 0;
        int base = 1;
        for (int i = octal.length() - 1; i >= 0; i--) {
            decimal += (octal.charAt(i) - '0') * base;
            base *= 8;
        }
        return decimal;
    }

    /**
     * Converts a hexadecimal string to its decimal integer equivalent.
     * 
     * @param hex The hexadecimal string to convert.
     * @return The decimal integer equivalent of the hexadecimal string.
     */
    public static int hexToDecimal(String hex) {
        int decimal = 0;
        int base = 1;
        for (int i = hex.length() - 1; i >= 0; i--) {
            char ch = hex.charAt(i);
            if (ch >= '0' && ch <= '9') {
                decimal += (ch - '0') * base;
            } else if (ch >= 'A' && ch <= 'F') {
                decimal += (ch - 'A' + 10) * base;
            } else if (ch >= 'a' && ch <= 'f') {
                decimal += (ch - 'a' + 10) * base;
            }
            base *= 16;
        }
        return decimal;
    }

    /**
     * Converts a decimal integer to its binary string equivalent.
     * 
     * @param decimal The decimal integer to convert.
     * @return The binary string equivalent of the decimal integer.
     */
    public static String decimalToBinary(int decimal) {
        String binary = "";
        while (decimal > 0) {
            binary = (decimal % 2) + binary;
            decimal /= 2;
        }
        return binary.isEmpty() ? "0" : binary;
    }

    /**
     * Converts a decimal integer to its octal string equivalent.
     * 
     * @param decimal The decimal integer to convert.
     * @return The octal string equivalent of the decimal integer.
     */
    public static String decimalToOctal(int decimal) {
        String octal = "";
        while (decimal > 0) {
            octal = (decimal % 8) + octal;
            decimal /= 8;
        }
        return octal.isEmpty() ? "0" : octal;
    }

    /**
     * Converts a decimal integer to its hexadecimal string equivalent.
     * 
     * @param decimal The decimal integer to convert.
     * @return The hexadecimal string equivalent of the decimal integer.
     */
    public static String decimalToHexadecimal(int decimal) {
        String hexadecimal = "";
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (decimal > 0) {
            hexadecimal = hexDigits[decimal % 16] + hexadecimal;
            decimal /= 16;
        }
        return hexadecimal.isEmpty() ? "0" : hexadecimal;
    }

    /**
     * Gets input from the user and converts it to decimal based on the specified number type.
     * 
     * @param scanner The Scanner object to read user input.
     * @param numberType The type of number to convert (binary, octal, decimal, hexadecimal).
     * @return The decimal equivalent of the input number, or -1 for invalid input.
     */
    public static int getInputAndConvertToDecimal(Scanner scanner, String numberType) {
        System.out.print("Enter the number in " + numberType + " format: ");
        String input = scanner.nextLine();

        switch (numberType) {
            case Constants.BINARY:
                if (isValidBinary(input)) {
                    return binaryToDecimal(input);
                } else {
                    System.out.println("Invalid binary input!");
                    return -1;
                }
            case Constants.OCTAL:
                if (isValidOctal(input)) {
                    return octalToDecimal(input);
                } else {
                    System.out.println("Invalid octal input!");
                    return -1;
                }
            case Constants.DECIMAL:
                if (isValidDecimal(input)) {
                    return Integer.parseInt(input);
                } else {
                    System.out.println("Invalid decimal input!");
                    return -1;
                }
            case Constants.HEXADECIMAL:
                if (isValidHexadecimal(input)) {
                    return hexToDecimal(input);
                } else {
                    System.out.println("Invalid hexadecimal input!");
                    return -1;
                }
            default:
                System.out.println("Unknown number type!");
                return -1;
        }
    }

    /**
     * Performs the specified arithmetic operation based on user inputs.
     * 
     * @param scanner The Scanner object to read user input.
     * @param operationType The type of operation to perform (addition, subtraction, multiplication).
     * @param inputFormat The format of the input numbers (binary, octal, decimal, hexadecimal).
     * @return The result of the operation, or -1 for invalid input.
     */
    public static int performOperation(Scanner scanner, String operationType, String inputFormat) {
        int result = 0;
        boolean isFirstInput = true;

        System.out.println("Enter numbers to perform " + operationType + " in " + inputFormat + " format (enter 'q' to stop):");

        while (true) {
            int decimalValue = getInputAndConvertToDecimal(scanner, inputFormat);
            if (decimalValue == -1) {
                return -1; // Invalid input
            }

            if (isFirstInput) {
                result = decimalValue;
                isFirstInput = false;
            } else {
                switch (operationType) {
                    case Constants.ADDITION:
                        result += decimalValue;
                        break;
                    case Constants.SUBTRACTION:
                        result -= decimalValue;
                        break;
                    case Constants.MULTIPLICATION:
                        result *= decimalValue;
                        break;
                    default:
                        System.out.println("Invalid operation type!");
                        return -1;
                }
            }

            System.out.print("Enter 'q' to stop or continue entering numbers: ");
            String decision = scanner.nextLine();
            if (decision.equalsIgnoreCase("q")) {
                break;
            }
        }
        return result;
    }

    /**
     * The main method for the conversion operations.
     * It prompts the user for their choices and performs the specified operations.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueProgram = true;
        while (continueProgram) {
            try {
                executeConversion(scanner);
            } catch (Exception e) {
                System.out.println("INVALID INPUT. " + e.getMessage());
            }

            // Prompt the user if they want to continue or exit
            System.out.print("Do you want to perform another operation or conversion? (yes/no): ");
            String userChoice = scanner.nextLine();
            if (!userChoice.equalsIgnoreCase("yes")) {
                continueProgram = false;
                System.out.println("Exiting the program. Thank you!");
            }
        }

        scanner.close();
    }
    /**
     * Executes the conversion operations based on user input.
     *
     * @param scanner The Scanner object to read user input.
     */
    public static void executeConversion(Scanner scanner) {
        System.out.println("Select option:");
        System.out.println("1. Conversion");
        System.out.println("2. Operation (Addition, Subtraction, Multiplication)");
        int option = scanner.nextInt();
        scanner.nextLine();

        if (option == 1) {
        	 System.out.println("Conversion selected.");            
             System.out.println(Constants.INPUTTYPE);
             int inputType = scanner.nextInt();
             scanner.nextLine(); 

             String numberType = "";
             switch (inputType) {
                 case 1:
                     numberType = Constants.BINARY;
                     break;
                 case 2:
                     numberType = Constants.OCTAL;
                     break;
                 case 3:
                     numberType = Constants.DECIMAL;
                     break;
                 case 4:
                     numberType = Constants.HEXADECIMAL;
                     break;
                 default:
                     System.out.println("Invalid input type choice!");
                     break;
             }

             int decimalValue = getInputAndConvertToDecimal(scanner, numberType);
             if (decimalValue == -1) {
                 return;  
             }

             System.out.println(Constants.CONVERSIONTYPE );
            
             int outputType = scanner.nextInt();

             switch (outputType) {
                 case 1:
                     System.out.println(Constants.BINARY + decimalToBinary(decimalValue));
                     break;
                 case 2:
                     System.out.println(Constants.OCTAL + decimalToOctal(decimalValue));
                     break;
                 case 3:
                     System.out.println(Constants.DECIMAL + decimalValue);
                     break;
                 case 4:
                     System.out.println(Constants.HEXADECIMAL + decimalToHexadecimal(decimalValue));
                     break;
                 default:
                     System.out.println("Invalid conversion choice!");
             }
        } else if (option == 2) {
            System.out.println("Operation selected.");
            System.out.println(Constants.OPERATIONS);

            int operationChoice = scanner.nextInt();
            scanner.nextLine();

            String operationType = "";
            switch (operationChoice) {
                case 1:
                    operationType = Constants.ADDITION;
                    break;
                case 2:
                    operationType = Constants.SUBTRACTION;
                    break;
                case 3:
                    operationType = Constants.MULTIPLICATION;
                    break;
                default:
                    System.out.println("Invalid operation choice!");
                    return;
            }

            System.out.println(Constants.INPUTTYPE);
            int inputFormatChoice = scanner.nextInt();
            scanner.nextLine();

            String inputFormat = "";
            switch (inputFormatChoice) {
                case 1:
                    inputFormat = Constants.BINARY;
                    break;
                case 2:
                    inputFormat = Constants.OCTAL;
                    break;
                case 3:
                    inputFormat = Constants.DECIMAL;
                    break;
                case 4:
                    inputFormat = Constants.HEXADECIMAL;
                    break;
                default:
                    System.out.println("Invalid format choice!");
                    return;
            }

            int result = performOperation(scanner, operationType, inputFormat);
            if (result == -1) {
                return;  
            }

            System.out.println(Constants.OUTPUTTYPE);
            int outputFormatChoice = scanner.nextInt();

            switch (outputFormatChoice) {
                case 1:
                    System.out.println("Binary: " + decimalToBinary(result));
                    break;
                case 2:
                    System.out.println("Octal: " + decimalToOctal(result));
                    break;
                case 3:
                    System.out.println("Decimal: " + result);
                    break;
                case 4:
                    System.out.println("Hexadecimal: " + decimalToHexadecimal(result));
                    break;
                default:
                    System.out.println("Invalid output format choice!");
            }
        } else {
            System.out.println("Invalid option choice!");
        }
    }
}