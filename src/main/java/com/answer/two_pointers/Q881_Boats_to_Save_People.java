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
     * Approach 1: Greedy (Two Pointer) 贪心: 为了尽可能地利用船的承载重量，选择与体重最重的人同乘一艘船是最优的。
     * Time: nlogn
     * Space: logn
     * In Java, Arrays.sort() is implemented using a variant of the Quick Sort algorithm which has a space complexity of O(logn)
     * 可以先对 people 排序，然后用两个指针分别指向体重最轻和体重最重的人，按照上述规则来移动指针，并统计答案。
     */
   static public int numRescueBoats(int[] people, int limit) {
        // Sort array at first
        Arrays.sort(people);

        int n = people.length;
        int left = 0;
        int right = n - 1;
        int count = 0;

        while(left <= right){
            if((people[left] + people[right]) <= limit){ // 每艘船最多可同时载两人, 但条件是这些人的重量之和最多为 limit。
                left++;
            }
            right--;
            count++;
        }

        return count;
    }
    /**
     * another from
     * 从贪心思想出发，我们让 people[left] 与还未上船中最大体重的 people[right] 一起乘船是最优解。然后令 left 右移， right 左移。
     * 「最重匹配最轻、次重匹配次轻」的做法能使双人船的数量最大化。
     */
    public int numRescueBoats2(int[] people, int limit) {
        Arrays.sort(people);

        int left = 0;
        int right = people.length - 1;
        int cnt = 0;
        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                cnt++;
                left++;
                right--;
            } else {
                cnt++;
                right--;
            }
        }
        return cnt;
    }
}
