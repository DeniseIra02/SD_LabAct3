//Balance Parentheses

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class Balance {
    private String[] stack;
    private int maxSize;
    private int top;

    // Constructor
    public Balance(int size) {
        maxSize = size;
        stack = new String[maxSize];
        top = -1;
    }

    // Add or push method
    public void push(String userInput) {
        if (top >= maxSize - 1) {
            System.out.println("The stack reaches the limit: " + (top + 1) + "/" + maxSize);
        } else {
            stack[++top] = userInput;
        }
    }

    //Remove or pop method
    public String pop() {
        if (top < 0) {// check if the stack is empty
            System.out.println("Empty Stack.");
            return "";
        } else {
            String popVal = stack[top--];
            return popVal;
        }
    }

    //Return top or peek method
    public String peek() {
        if (top < 0){ // check if the stack is empty
            System.out.println("Empty Stack.");
            return "";
        } else {
            String peekVal = stack[top];
            return peekVal;
        }
    }


    // Main
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
                System.out.println("Please enter a valid integer!"); // if the user input is not an integer
                userInput.nextLine();
            }
        }

        Balance stack = new Balance(limSize);

        //for loop - says when it limits
        for (int i = 0; i < limSize; i++) {
            System.out.print("(" + (i + 1)+ "/"+ limSize + "): ");
            String input = userInput.nextLine();
            
            stack.push(input);
        }

        userInput.close();
        
        // OUTPUT
        Stack<Boolean> reverseStack = new Stack<>();
        //for loop - evaluationg the stack
        for (int j = 0; j < limSize; j++) {
            String value = stack.pop();
            reverseStack.push(isBalance(value));
        }

        System.out.println("\n" + "OUTPUT: \n");
        while (!reverseStack.isEmpty()){ 
            Boolean balance = reverseStack.pop();
            if (balance) {
                System.out.println("BALANCED");
            } else {
                System.out.println("NOT BALANCED");
            }
        }
    }


    // evaluate 'parentheses' in string */

    public static boolean isBalance(String input) {
        Stack<Character> stackChar = new Stack<>();

        if (input != "") {
            for(int i = 0; i < input.length(); i++) {
                char holder = input.charAt(i);

                if (holder == '{' || holder == '[' || holder == '(') {
                    stackChar.push(holder); 

                } else if (holder == '}' || holder == ']' || holder == ')'){
                    if (stackChar.isEmpty()){
                        return false;
                    }

                    // Pop stack elements (ending brackets) 
                    char atop = stackChar.pop();

                    //these are not valid
                    if((holder == '(' && atop != ')') || (holder == '[' && atop != ']') || (holder == '{' && atop != '}')) {
                        return false;
                    }
                }
            }
            return stackChar.isEmpty(); // else, balance

        } else {
            return false;
        }
    }
}