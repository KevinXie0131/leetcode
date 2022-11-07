package com.leetcode;

import java.util.LinkedList;

public class Test13_LinkedList {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(3);
        list.add(5);
        list.add(6);
        list.add(7);
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
    }
}
