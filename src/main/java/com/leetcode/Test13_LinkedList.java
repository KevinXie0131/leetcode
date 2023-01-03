package com.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class Test13_LinkedList {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(3);
        list.add(9);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(6);
        list.add(9);

        System.out.println(list.getFirst());
        System.out.println(list.getLast());

        System.out.println(list.offer(11));
        System.out.println(list.getLast());

        System.out.println(list.offerFirst(12));
        System.out.println(list.get(0));

        System.out.println(list.offerLast(13));
        System.out.println(list.getLast());

        System.out.println(list.peek());
        System.out.println(list);
        System.out.println(list.poll());
        System.out.println(list);
        System.out.println(list.pollLast());
        System.out.println(list);

        System.out.println(list.pop());
        list.push(16);
        System.out.println(list);

        System.out.println(list.remove());
        System.out.println(list.removeLast());
    //    System.out.println(list.removeFirstOccurrence(6));
        System.out.println(list.removeLastOccurrence(6));
        System.out.println(list);

        System.out.println(list.indexOf(7));
        System.out.println(list.lastIndexOf(9));

        LinkedList<String> list2 = new LinkedList<>();
        list2.add("a");
        list2.addFirst("b");
        list2.add("c");
        list2.addLast("d");
        System.out.println(list2);
        list2.remove(list2.size() - 1);
        System.out.println(list2);
        list2.removeFirst();
        System.out.println(list2);

        System.out.println("Implement Deque by LinkedList");
        Deque<String> deque = new LinkedList<>();
        deque.push("a");
        deque.push("b");
        System.out.println(deque);
        deque.pop();
        System.out.println(deque);
        deque.offer("c");
        System.out.println(deque);
        deque.poll();
        System.out.println(deque);
    }
}
