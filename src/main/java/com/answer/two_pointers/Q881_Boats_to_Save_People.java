package com.answer.two_pointers;

import java.util.Arrays;

public class Q881_Boats_to_Save_People {
    /**
     * given an array people where people[i] is the weight of the ith person, and an infinite number of boats
     * where each boat can carry a maximum weight of limit. Each boat carries at most two people at the same time,
     * provided the sum of the weight of those people is at most limit.
     *
     * Return the minimum number of boats to carry every given person.
     * 给定数组 people 。people[i]表示第 i 个人的体重 ，船的数量不限，每艘船可以承载的最大重量为 limit。
     * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
     * 返回 承载所有人所需的最小船数 。
     */
    public static void main(String[] args) {
        /**
         * 示例 3：
         *  输入：people = [3,5,3,4], limit = 5
         *  输出：4
         *  解释：4 艘船分别载 (3), (3), (4), (5)
         */
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
