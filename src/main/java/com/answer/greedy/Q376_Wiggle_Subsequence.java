package com.answer.greedy;

public class Q376_Wiggle_Subsequence {

    public static void main(String[] args) {
        int[] nums = {1,7,4,9,2,5};
        System.out.println(wiggleMaxLength(nums));
    }
    /**
     * Greedy
     */
    public static int wiggleMaxLength(int[] nums) {
        int res = 1;
        int preDiff = 0;
        int curDiff = 0;

        for(int i = 1; i < nums.length; i++){
            curDiff = nums[i] - nums[i - 1];

            if((preDiff <= 0 && curDiff > 0)
                    || (preDiff >= 0 && curDiff < 0)){

                res++;
                preDiff = curDiff;
            }
        }
        return res;
    }
}
