package dev.danyaao.core;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A class to compare the performance of ArrayList and LinkedList for basic operations.
 */
public class PerformanceComparator {
    /**
     * Method to test the performance of ArrayList.
     *
     * @param repetitions the number of repetitions for each method
     */
    public static void testArrayList(int repetitions) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        long startTime, endTime;

        // Test the add method
        startTime = System.nanoTime();
        for (int i = 0; i < repetitions; i++) {
            arrayList.add(i);
        }
        endTime = System.nanoTime();
        printResult("add", repetitions, endTime - startTime);

        // Test the remove method
        startTime = System.nanoTime();
        for (int i = 0; i < repetitions; i++) {
            arrayList.removeLast(); // Remove the last element
        }
        endTime = System.nanoTime();
        printResult("remove", repetitions, endTime - startTime);

        // Test the get method
        startTime = System.nanoTime();
        for (int i = 0; i < repetitions; i++) {
            if (arrayList.isEmpty()) {
                continue;
            }
            int temp = arrayList.get(i % arrayList.size()); // Get elements by index
        }
        endTime = System.nanoTime();
        printResult("get", repetitions, endTime - startTime);
    }


    /**
     * Method to test the performance of LinkedList.
     *
     * @param repetitions the number of repetitions for each method
     */
    public static void testLinkedList(int repetitions) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        long startTime, endTime;

        // Test the add method
        startTime = System.nanoTime();
        for (int i = 0; i < repetitions; i++) {
            linkedList.add(i);
        }
        endTime = System.nanoTime();
        printResult("add", repetitions, endTime - startTime);

        // Test the remove method
        startTime = System.nanoTime();
        for (int i = 0; i < repetitions; i++) {
            linkedList.removeLast(); // Remove the last element
        }
        endTime = System.nanoTime();
        printResult("remove", repetitions, endTime - startTime);

        // Test the get method
        startTime = System.nanoTime();
        for (int i = 0; i < repetitions; i++) {
            if (linkedList.isEmpty()) {
                continue;
            }
            int temp = linkedList.get(i % linkedList.size()); // Get elements by index
        }
        endTime = System.nanoTime();
        printResult("get", repetitions, endTime - startTime);
    }

    /**
     * Method to print the test results.
     *
     * @param method      the method being tested
     * @param repetitions the number of repetitions for the method
     * @param time        the time taken to perform the test
     */
    private static void printResult(String method, int repetitions, long time) {
        System.out.println(STR."Method: \{method}, Repetitions: \{repetitions}, Time: \{time} nanoseconds");
    }
}
