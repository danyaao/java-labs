package dev.danyaao;


import static dev.danyaao.core.PerformanceComparator.testArrayList;
import static dev.danyaao.core.PerformanceComparator.testLinkedList;

public class Main {
    public static void main(String[] args) {
        int repetitions = 1000; // Number of repetitions for each method

        System.out.println("Testing ArrayList:");
        testArrayList(repetitions);

        System.out.println("\nTesting LinkedList:");
        testLinkedList(repetitions);
    }
}