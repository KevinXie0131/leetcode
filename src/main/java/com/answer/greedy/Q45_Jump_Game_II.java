package com.answer.greedy;

public class Q45_Jump_Game_II {
    public static void main(String[] args) {
     //   int[] nums = {2,3,1,1,4};
        int[] nums = {1,1,1,1,1};
        System.out.println(jump_1(nums));
    }

    /**
     * Greedy
     * A greedy algorithm is any algorithm that follows the problem-solving heuristic of making locally optimal choice at
     * each step, with the intent of reaching the global optimum at the end.
     *
     * A greedy algorithm does not necessarily lead to a globally optimal solution, but rather a reasonable approximation
     * in exchange of less computing time.
     *
     * 可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新
     *
     * 所以，当一次跳跃结束时，从下一个格子开始，到现在能跳到最远的距离，都是下一次跳跃的起跳点
     * 1. 对每一次跳跃用 for 循环来模拟
     * 2. 跳完一次之后，更新下一次起跳点的范围
     * 3. 在新的范围内跳，更新能跳到最远的距离
     */
    public static int jump(int[] nums) {
        if(nums.length == 1) return 0;

        int res = 0;
        int start = 0;
        int end = 1;

        while(end < nums.length){
            int cover = 0;
            for(int i = start; i < end; i++){
                // 能跳到最远的距离
                cover = Math.max(cover, nums[i] + i);
            }
            start = end; // 下一次起跳点范围开始的格子
            end = cover + 1;  // 下一次起跳点范围结束的格子
            res++; // 跳跃次数
        }

        return res;
    }
    /**
     * Brute force
     * Time complexity n2 for 1,1,1,1,1
     */
    public static int jump_1(int[] nums) {
        int position = nums.length - 1; //要找的位置
        int steps = 0;
        while (position != 0) { //是否到了第 0 个位置
            for (int i = 0; i < position; i++) {
                if (nums[i] >= position - i) {
                    position = i; //更新要找的位置
                    steps++;
                //    break;
                }
            }
        }
        return steps;
    }
}
