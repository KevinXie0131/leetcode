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