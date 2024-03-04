package dev.danyaao.core;

import java.util.Stack;


public class Evaluator {
    public static double evaluateExpression(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch == ' ') {
                continue;
            }

            if (Character.isDigit(ch)) {
                StringBuilder numBuilder = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    numBuilder.append(expression.charAt(i));
                    i++;
                }
                i--;
                double number = Double.parseDouble(numBuilder.toString());
                numbers.push(number);
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    double result = performOperation(numbers, operators);
                    numbers.push(result);
                }
                if (!operators.isEmpty() && operators.peek() == '(') {
                    operators.pop();
                } else {
                    throw new IllegalArgumentException("Invalid expression");
                }
            } else if (isOperator(ch)) {
                while (!operators.isEmpty() && precedence(ch) <= precedence(operators.peek())) {
                    double result = performOperation(numbers, operators);
                    numbers.push(result);
                }
                operators.push(ch);
            } else {
                throw new IllegalArgumentException("Invalid character: " + ch);
            }
        }

        while (!operators.isEmpty()) {
            double result = performOperation(numbers, operators);
            numbers.push(result);
        }

        if (numbers.size() != 1) {
            throw new IllegalArgumentException("Invalid expression");
        }

        return numbers.pop();
    }

    private static double performOperation(Stack<Double> numbers, Stack<Character> operators) {
        double b = numbers.pop();
        double a = numbers.pop();
        char operator = operators.pop();

        return switch (operator) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> a / b;
            default -> throw new IllegalArgumentException("Invalid operator: " + operator);
        };
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private static int precedence(char ch) {
        if (ch == '+' || ch == '-') {
            return 1;
        } else if (ch == '*' || ch == '/') {
            return 2;
        } else {
            return 0;

        }
    }
}
