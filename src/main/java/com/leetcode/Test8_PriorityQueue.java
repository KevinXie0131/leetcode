package com.leetcode;

import java.util.Comparator;
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

        priorityQueue1.offer(17);
        System.out.println(priorityQueue1.poll());

        PriorityQueue<String> priorityQueue2 = new PriorityQueue<String>((a, b) -> -b.compareTo(a));
        priorityQueue2.add("aa");
        priorityQueue2.add("bb");
        priorityQueue2.add("cc");

        priorityQueue2.offer("dd");
        System.out.println(priorityQueue2.poll());


        PriorityQueue<Integer> priorityQueue3 = new PriorityQueue<Integer>();
        priorityQueue3.add(18);
        priorityQueue3.add(12);
        priorityQueue3.add(13);
        priorityQueue3.add(9);
        priorityQueue3.add(15);
        System.out.println(priorityQueue3.peek());

        PriorityQueue<Cord> priorityQueue4 = new PriorityQueue<>(new Comparator<Cord>() {
            @Override
            public int compare(Cord o1, Cord o2) {
              //  return o1.val - o2.val;
                return o2.val - o1.val;
            }
        });
        priorityQueue4.add(new Cord(1,1,111));
        priorityQueue4.add(new Cord(2,2,222));
        priorityQueue4.add(new Cord(3,3,333));
        System.out.println(priorityQueue4.poll().toString());
        System.out.println(priorityQueue4.poll().toString());
        priorityQueue4.add(new Cord(4,4,444));
        System.out.println(priorityQueue4.poll().toString());
        System.out.println(priorityQueue4.poll().toString());
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

    @Override
    public String toString () {
        return "Cord: " + i + ", " + j + ", " + val;
    }
}