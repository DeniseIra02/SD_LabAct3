//Binary to Decimal

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class BinarytoDecimal {
    private long[] mainStack;
    private int maxSize;
    private int top;

    // Constructor
    public BinarytoDecimal(int size) {
        maxSize = size;
        mainStack = new long[maxSize];
        top = -1;
    }

    //push method
    public void push(long userInput) {
        if (top >= maxSize - 1) {
            System.out.println("The stack reaches the limit: " + (top + 1) + "/" + maxSize);
        } else {
            mainStack[++top] = userInput;
        }
    }

    //pop method
    public long pop() {
        if (top < 0) {
            System.out.println("Empty Stack.");
            return 0;
        } else {
            long popVal = mainStack[top--];
            return popVal;
        }
    }

    //peek method
    public long peek() {
        if (top < 0){
            System.out.println("Empty Stack.");
            return 0;
        } else {
            long peekVal = mainStack[top];
            return peekVal;
        }
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int limSize = 0;
        boolean valid = false;

        // validate user input integer 
        while (!valid) {
            System.out.print("Enter a number for stack limit: "); //limit stack

            try {
                int num = userInput.nextInt(); // user input must be an integer
                userInput.nextLine();
                limSize = num;
                valid = true; // valif if the input is an integer
            } catch (InputMismatchException e){
                System.out.println("Enter a valid integer!"); // if the user input is not an integer
                userInput.nextLine();
            }
        }

        BinarytoDecimal stack = new BinarytoDecimal(limSize);
        int base = 0; // counter variable

        // Validate INPUTS to be a valid binary
        while (base < limSize) {
            System.out.print("Entry ("+ (base + 1) + "/" + limSize + "): ");
            try {
                long binaryInputs = userInput.nextLong();
                userInput.nextLine();
                
                // Check if it contains ones and zeros only
                if(isBinary(binaryInputs)){
                    base++;
                    stack.push(binaryInputs);
                } else {
                    System.out.println("Please enter a valid binary");
                    continue;
                }

            } catch (Exception e) {
                System.out.println("Please enter a valid binary");
                userInput.nextLine();
            }
        }

        userInput.close();

        Stack<Integer> tempoStack = new Stack<>();
        for (int k = 0; k < limSize; k++) {
            //for loop - exaluating stack
            long value = stack.pop();
            int decimalConvert = binaryToDecimal(value);
            
            //temporary stack
            tempoStack.push(decimalConvert);
        }

        // OUTPUT
        System.out.println("\n OUTPUT: \n");

        int order = 0;
        while (!tempoStack.isEmpty()){
            int decimalResult = tempoStack.pop();
            System.out.println("Decimal ("+ ++order + "/" + limSize + ") = " + decimalResult);
        }
    }

    // binary to decimal coverter
    public static int binaryToDecimal(long binaryNumber) {
        int decimal = 0, i = 0;
        long remainder;

        while (binaryNumber != 0) {
            remainder = binaryNumber % 10;
            binaryNumber /= 10;
            decimal += remainder * Math.pow(2, i);
            ++i;
        }
        
        return decimal;
    }

    // check or verify if the number is binary
    public static boolean isBinary(long number) {
        long copyInput = number;

        while (copyInput != 0) {
            if (copyInput % 10 > 1) {
                return false;
            }
            copyInput = copyInput / 10;
        }
        return true;
    }
}
