package dev.danyaao.core;

import java.util.Iterator;

/**
 * Simple LinkedList
 *
 * @param <T> the type of elements in this list
 * @author danyao
 */
public class LinkedList<T> {

    private Node<T> head;
    private int size;

    private static class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T t) {
            data = t;
            next = null;
        }
    }

    /**
     * Default constructor
     */
    public LinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Constructor with initial parameter
     *
     * @param initialData - accepts any data type
     */
    public LinkedList(T initialData) {
        try {
            head = new Node<>(initialData);
            size = 1;
        } catch (NullPointerException e) {
            throw new NullPointerException("Null value");
        }
        ;
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T data = current.data;
            current = current.next;
            return data;
        }
    }

    /**
     * Method to add a value to the list
     *
     * @param t the element to add
     */
    public void add(T t) {
        if (t == null) {
            throw new NullPointerException("Null value");
        }

        if (head != null) {
            Node<T> h1 = head;

            while (h1.next != null) {
                h1 = h1.next;
            }

            h1.next = new Node<>(t);
        } else {
            head = new Node<>(t);
        }
        size += 1;

    }

    /**
     * Method to add value T at position index
     *
     * @param t     the element to add
     * @param index where add element
     */
    public void add(T t, int index) {
        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        } else if (t == null) {
            throw new NullPointerException("Null value");
        }

        Node<T> current = head;

        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        Node<T> next = current.next;
        Node<T> data = new Node<>(t);

        current.next = data;
        data.next = next;

        size += 1;
    }

    /**
     * Method to remove an element in the list
     *
     * @param index - the index of the element to remove
     */
    public void remove(int index) {

        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> current = head;

            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            current.next = current.next.next;
        }
        size--;
    }

    /**
     * Method to check if the list contains a specific element
     *
     * @param t - the element to search for
     **/
    public boolean contains(T t) {
        Node<T> h1 = head;

        do {
            if (h1.data == t) {
                return true;
            }
            h1 = h1.next;
        } while (h1 != null);
        return false;
    }

    /**
     * Method to print all elements of the list into a string
     */
    public void print() {

        if (head == null) {
            throw new NullPointerException("List is empty!");
        }

        Node<T> h1 = head;

        do {
            System.out.print(h1.data + " ");
            h1 = h1.next;
        } while (h1 != null);

        System.out.println();

    }

    /**
     * Method to show the current size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Method to check if the list is empty
     */
    public boolean isEmpty() {
        return head == null;
    }
}
