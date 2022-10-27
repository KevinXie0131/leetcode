package com.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Test4_queue {
    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(3);
        queue.offer(5);
        queue.offer(1);
        queue.offer(8);
        queue.offer(2);

        while (!queue.isEmpty()) {
            System.out.println("queue poll " + queue.poll());
        }

        Stack<Integer> stack = new Stack<Integer>();
        stack.push(3);
        stack.push(5);
        stack.push(1);
        stack.push(8);
        stack.push(2);

        while (!stack.isEmpty()) {
            System.out.println("stack pop " + stack.pop());
        }

        Deque<Integer> queue1 = new LinkedList<Integer>();
        queue1.offer(3);
        queue1.offer(5);
        queue1.offer(1);
        queue1.offer(8);
        queue1.offer(2);

        while (!queue1.isEmpty()) {
            System.out.println("queue1 poll " + queue1.poll());
        }

        Deque<Integer> stack1 = new LinkedList<Integer>();
        stack1.push(3);
        stack1.push(5);
        stack1.push(1);
        stack1.push(8);
        stack1.push(2);

        while (!stack1.isEmpty()) {
            System.out.println("stack1 poll " + stack1.pop());
        }

    }
}
