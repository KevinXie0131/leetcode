package com.answer.greedy;

public class Q55_Jump_Game {
    public static void main(String[] args) {
        int[] nums = {3,2,1,0,4};
        boolean res = canJump(nums);
        System.out.println(res);
    }

    /**
     * Greedy algorithm
     */
    public static boolean canJump(int[] nums) {
        if(nums.length == 1) return true;

        int cover = 0;

        for(int i = 0; i < nums.length; i++){
            if(cover < i){
                return false;
            }
            cover = Math.max(cover, nums[i] + i);
        }

        return true;
    }
}
