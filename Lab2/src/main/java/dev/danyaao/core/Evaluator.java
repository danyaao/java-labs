package dev.danyaao.core;

import java.util.Stack;

/**
 * A class to evaluate mathematical expressions in string format.
 */
public class Evaluator {
    /**
     * Evaluates the given mathematical expression.
     *
     * @param expression The expression to be evaluated
     * @return The result of the evaluation
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static double evaluateExpression(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            // Ignore whitespace characters
            if (ch == ' ') {
                continue;
            }

            if (Character.isDigit(ch)) {
                // If the character is a digit, extract the entire number
                StringBuilder numBuilder = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    numBuilder.append(expression.charAt(i));
                    i++;
                }
                i--; // Move back one position to adjust for the loop increment
                double number = Double.parseDouble(numBuilder.toString());
                numbers.push(number);
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                // If it's a closing parenthesis, perform operations until an opening parenthesis is encountered
                while (!operators.isEmpty() && operators.peek() != '(') {
                    double result = performOperation(numbers, operators);
                    numbers.push(result);
                }
                // Pop the opening parenthesis
                if (!operators.isEmpty() && operators.peek() == '(') {
                    operators.pop();
                } else {
                    throw new IllegalArgumentException("Invalid expression");
                }
            } else if (isOperator(ch)) {
                // If it's an operator, perform operations based on precedence
                while (!operators.isEmpty() && precedence(ch) <= precedence(operators.peek())) {
                    double result = performOperation(numbers, operators);
                    numbers.push(result);
                }
                // Push the current operator onto the stack
                operators.push(ch);
            } else {
                // If the character is not a digit, operator, or parenthesis, it's invalid
                throw new IllegalArgumentException("Invalid character: " + ch);
            }
        }

        // Perform remaining operations
        while (!operators.isEmpty()) {
            double result = performOperation(numbers, operators);
            numbers.push(result);
        }

        // If there's more than one number left, the expression is invalid
        if (numbers.size() != 1) {
            throw new IllegalArgumentException("Invalid expression");
        }

        // Return the final result
        return numbers.pop();
    }

    /**
     * Performs the operation based on the given operator.
     *
     * @param numbers   Stack of numbers
     * @param operators Stack of operators
     * @return The result of the operation
     * @throws IllegalArgumentException if the operator is invalid
     */
    private static double performOperation(Stack<Double> numbers, Stack<Character> operators) {
        double b = numbers.pop();
        double a = numbers.pop();
        char operator = operators.pop();

        return switch (operator) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Invalid operator: " + operator);
        };
    }

    /**
     * Checks if the given character is an operator.
     *
     * @param ch The character to check
     * @return true if the character is an operator, false otherwise
     */
    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    /**
     * Determines the precedence of the given operator.
     *
     * @param ch The operator
     * @return The precedence level
     */
    private static int precedence(char ch) {
        if (ch == '+' || ch == '-') {
            return 1;
        } else if (ch == '*' || ch == '/') {
            return 2;
        } else {
            return 0; // Parentheses have the lowest precedence
        }
    }
}
