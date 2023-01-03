package com.leetcode;

import java.util.*;

public class Test1_ArrayDeque {

    public static void main(String[] args) {

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(10);

        queue.add(10);
        queue.add(4);
        queue.add(1);
        queue.add(5);
        queue.add(2);
        queue.add(8);
        queue.offer(20);

        System.out.println(queue.size());

        Iterator iter  = queue.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        System.out.println("queue.peek() " + queue.peek());
        System.out.println("queue.poll() " + queue.poll());
        System.out.println("queue.poll() " + queue.poll());
        System.out.println("queue.poll() " + queue.poll());
        System.out.println("queue.poll() " + queue.poll());
        System.out.println("queue.poll() " + queue.poll());
        System.out.println("queue.poll() " + queue.poll());
        System.out.println("queue.poll() " + queue.poll());

        Stack<Integer> stack = new Stack();

        Deque<String> stack1 = new ArrayDeque<>(4);
        stack1.push("first");
        stack1.push("second");
        stack1.push("third");
        System.out.println("stack1.pop() " + stack1.pop());
        System.out.println("stack1.pop() " + stack1.pop());
        System.out.println("stack1.pop() " + stack1.pop());
    //    stack1.push(null);   // ArrayDeque also does not allow you to insert null elements.

        Deque<String> queue1 = new ArrayDeque<>();
        queue1.offer("first");
        queue1.offer("second");
        queue1.offer("third");
        System.out.println("queue.poll() " + queue1.poll());
        System.out.println("queue.poll() " + queue1.poll());
        System.out.println("queue.poll() " + queue1.poll());

        int a = 1;
        int b = -a;
        System.out.println("b " + b);

        Deque<String> queue2 = new LinkedList<>();
        queue2.add("aaa");
        queue2.add("bbb");
        System.out.println("queue2: " + queue2);
        queue2.addFirst("ccc");
        queue2.addLast("ddd");
        System.out.println("queue2: " + queue2);
        queue2.removeFirst();
        System.out.println("queue2: " + queue2);
        queue2.removeLast();
        System.out.println("queue2: " + queue2);
        queue2.offer("eee");
        System.out.println("queue2: " + queue2);
        System.out.println("queue2: " +  queue2.poll());
        System.out.println("queue2: " + queue2);
        queue2.push("fff");
        System.out.println("queue2: " + queue2);
        System.out.println("queue2: " +  queue2.peekLast());
        System.out.println("queue2: " +  queue2.pop());
        System.out.println("queue2: " + queue2);
    }
}
