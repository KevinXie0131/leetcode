package com.answer.priorityqueue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Offer_40_Least_K_Numbers {

    public static void main(String[] args) {
        int[] arr = {3,2,1};
        int k = 2;

        int[] result = getLeastNumbers(arr, k);
        System.out.println(Arrays.toString(result));
    }

    public static int[] getLeastNumbers(int[] arr, int k) {
        int[] result = new int[k];
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>()      {
            public int compare(Integer num1, Integer num2) {
                return num2 - num1;
            }
        });

        for(int i = 0; i < k; i++){
            queue.offer(arr[i]);
        }
        for(int i = k; i < arr.length; i++){
            if(queue.peek() > arr[i]){
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        for(int i = 0; i < k; i++){
            result[i] = queue.poll();
        }

        return result;
    }
}
