package dev.danyaao;

import java.util.Scanner;

import static dev.danyaao.core.Evaluator.evaluateExpression;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputValue = scanner.nextLine();
        double result = evaluateExpression(inputValue);
        System.out.println(result);

    }
}