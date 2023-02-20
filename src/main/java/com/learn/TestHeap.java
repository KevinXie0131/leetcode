package com.learn;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Arrays;

public class TestHeap {
    /**
     * A priority queue is an abstract data type, while a Heap is a data structure.
     * Therefore, a Heap is not a Priority Queue, but a way to implement a Priority Queue.
     *
     * Definition of Heap:
     * 1. Is a complete binary tree;
     * 2. The value of each node must be no greater than (or no less than) the value of its child nodes.
     *
     * Classification of Heap:
     * Max Heap: the top element (root node) has the largest value in the Heap.
     * Min Heap: the top element (root node) has the smallest value in the Heap.
     *
     * Application of Heap:
     * 1. Heap Sort
     * 2. The Top-K problem
     * 3. The K-th element
     */
    public static void main(String[] args) {
        // In Java, a Heap is represented by a Priority Queue

        // Construct an empty Min Heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Construct an empty Max Heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Construct a Heap with initial elements.
        // This process is named "Heapify".
        // The Heap is a Min Heap
        PriorityQueue<Integer> heapWithValues = new PriorityQueue<>(Arrays.asList(3, 1, 2));

        // Insert an element to the Min Heap
        minHeap.add(5);

        // Insert an element to the Max Heap
        maxHeap.add(5);

        // Get top element from the Min Heap
        // i.e. the smallest element
        minHeap.peek();
        // Get top element from the Max Heap
        // i.e. the largest element
        maxHeap.peek();

        // Delete top element from the Min Heap
        minHeap.poll();

        // Delete top element from the Max Heap
        minHeap.poll();

        // Length of the Min Heap
        minHeap.size();

        // Length of the Max Heap
        maxHeap.size();

        // Note, in Java, apart from checking if the length of the Heap is 0, we can also use isEmpty()
        // If there are no elements in the Heap, isEmpty() will return true;
        // If there are still elements in the Heap, isEmpty() will return false;
    }

    public void testMinHeap(){
        // Construct an instance of Min Heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add 3，1，2 respectively to the Min Heap
        minHeap.add(3);
        minHeap.add(1);
        minHeap.add(2);

        // Check all elements in the Min Heap, the result is [1, 3, 2]
        System.out.println("minHeap: " + minHeap.toString());

        // Get the top element of the Min Heap
        int peekNum = minHeap.peek();

        // The result is 1
        System.out.println("peek number: " + peekNum);

        // Delete the top element in the Min Heap
        int pollNum = minHeap.poll();

        // The reult is 1
        System.out.println("poll number: " + pollNum);

        // Check the top element after deleting 1, the result is 2
        System.out.println("peek number: " + minHeap.peek());

        // Check all elements in the Min Heap, the result is [2,3]
        System.out.println("minHeap: " + minHeap.toString());

        // Check the number of elements in the Min Heap
        // Which is also the length of the Min Heap
        int heapSize = minHeap.size();

        // The result is 2
        System.out.println("minHeap size: " + heapSize);

        // Check if the Min Heap is empty
        boolean isEmpty = minHeap.isEmpty();

        // The result is false
        System.out.println("isEmpty: " + isEmpty);
    }

    public void testMaxHeap(){
        // Construct an instance of Max Heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Add 1，3，2 respectively to the Max Heap
        maxHeap.add(1);
        maxHeap.add(3);
        maxHeap.add(2);

        // Check all elements in the Max Heap, the result is [3, 1, 2]
        System.out.println("maxHeap: " + maxHeap.toString());

        // Get the top element of the Max Heap
        int peekNum = maxHeap.peek();

        // The result is 3
        System.out.println("peek number: " + peekNum);

        // Delete the top element in the Max Heap
        int pollNum = maxHeap.poll();

        // The reult is 3
        System.out.println("poll number: " + pollNum);

        // Check the top element after deleting 3, the result is 2
        System.out.println("peek number: " + maxHeap.peek());

        // Check all elements in the Max Heap, the result is [2,1]
        System.out.println("maxHeap: " + maxHeap.toString());

        // Check the number of elements in the Max Heap
        // Which is also the length of the Max Heap
        int heapSize = maxHeap.size();

        // The result is 2
        System.out.println("maxHeap size: " + heapSize);

        // Check if the Max Heap is empty
        boolean isEmpty = maxHeap.isEmpty();

        // The result is false
        System.out.println("isEmpty: " + isEmpty);
    }
}
