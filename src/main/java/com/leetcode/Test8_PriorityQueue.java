package com.leetcode;

import java.awt.*;
import java.util.Collections;
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

        PriorityQueue<Cordinate> priorityQueue5 = new PriorityQueue<>(Collections.reverseOrder());
        priorityQueue5.add(new Cordinate(1,1,111));
        priorityQueue5.add(new Cordinate(2,2,222));
        System.out.println(priorityQueue5.poll().toString());

        PriorityQueue<Cordinate> priorityQueue6 = new PriorityQueue<>(new MyComparator());
        priorityQueue6.add(new Cordinate(1,1,111));
        priorityQueue6.add(new Cordinate(2,2,222));
        System.out.println(priorityQueue6.poll().toString());

        int[] nums = {4, 5, 6, -1, 0, 8, -2,-6, 9, -10};
        PriorityQueue<Integer> queue10 = new PriorityQueue<>((a,b) -> -(nums[a] - nums[b]));
        for(int i = 0 ; i < nums.length; i++){
            queue10.add(i);
        }
        while(!queue10.isEmpty()){
            int n = queue10.poll();
            System.out.print(n + ":(" + nums[n] + ") -> ");
        }
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

class Cordinate implements Comparable {
    int i;
    int j;
    int val;

    public Cordinate (int i, int j, int val) {
        this.i = i;
        this.j = j;
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    @Override
    public String toString () {
        return "Cordinate: " + i + ", " + j + ", " + val;
    }

    @Override
    public int compareTo(Object o1) {
        return this.val - ((Cordinate)o1).val;
    }
}

class MyComparator implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {
        return ((Cordinate)o2).val - ((Cordinate)o1).val;
    }
}