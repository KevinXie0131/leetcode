package com.answer.two_pointers;

import java.util.Arrays;

public class Q881_Boats_to_Save_People {
    public static void main(String[] args) {
        int[] people = {3, 5, 3, 4, 1, 2};
        int limit = 5;
        int res = numRescueBoats(people, limit);
        System.out.println(res);
    }
    /**
     * Approach 1: Greedy (Two Pointer)
     * Time: nlogn
     * Space: logn
     * In Java, Arrays.sort() is implemented using a variant of the Quick Sort algorithm which has a space complexity of O(logn)
     */
   static public int numRescueBoats(int[] people, int limit) {
        /**
         * Sort array at first
         */
        Arrays.sort(people);

        int n = people.length;
        int left = 0;
        int right = n - 1;
        int count = 0;

        while(left <= right){
            if((people[left] + people[right]) <= limit){
                left++;
            }
            right--;
            count++;
        }

        return count;
    }
}
