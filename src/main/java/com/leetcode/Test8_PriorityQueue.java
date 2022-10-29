package com.leetcode;

import java.util.PriorityQueue;

public class Test8_PriorityQueue {

    public static void main(String[] args) {
        PriorityQueue<Cord> priorityQueue = new PriorityQueue<Cord>((a,b) -> b.val - a.val);

        priorityQueue.add(new Cord(1,1,11));
        priorityQueue.add(new Cord(2,2,22));
        priorityQueue.add(new Cord(3,3,33));

        Cord cord = priorityQueue.poll();
        System.out.println(cord.val);

        PriorityQueue<Integer> priorityQueue1 = new PriorityQueue<Integer>((a, b) -> b - a);
        priorityQueue1.add(12);
        priorityQueue1.add(13);
        priorityQueue1.add(15);
        System.out.println(priorityQueue1.poll());

        PriorityQueue<String> priorityQueue2 = new PriorityQueue<String>((a, b) -> -b.compareTo(a));
        priorityQueue2.add("aa");
        priorityQueue2.add("bb");
        priorityQueue2.add("cc");
        System.out.println(priorityQueue2.poll());
    }
}

class Cord {
    int i;
    int j;
    int val;

    public Cord (int i, int j, int val) {
        this.i = i;
        this.j = j;
        this.val = val;
    }


}