package com.answer.greedy;

public class Q45_Jump_Game_II {
    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(jump(nums));
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
}
