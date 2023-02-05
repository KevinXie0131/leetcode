package com.answer.greedy;

public class Q45_Jump_Game_II {
    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(jump_1(nums));
    }

    /**
     * Greedy
     */
    public static int jump(int[] nums) {
        if(nums.length == 1) return 0;

        int res = 0;
        int start = 0;
        int end = 1;

        while(end < nums.length){
            int cover = 0;
            for(int i = start; i < end; i++){
                cover = Math.max(cover, nums[i] + i);
            }
            start = end;
            end = cover + 1;
            res++;
        }

        return res;
    }
    /**
     * Brute force
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
