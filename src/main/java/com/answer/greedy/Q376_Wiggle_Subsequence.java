package com.answer.greedy;

public class Q376_Wiggle_Subsequence {

    public static void main(String[] args) {
        int[] nums = {1,7,4,9,2,5};
        System.out.println(wiggleMaxLength(nums));
    }
    /**
     * Approach #5 Greedy Approach
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
    /**
     * Greedy 2
     */
    public int wiggleMaxLength_1(int[] nums) {
        int down = 1, up = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1])
                up = down + 1;
            else if (nums[i] < nums[i - 1])
                down = up + 1;
        }
        return nums.length == 0 ? 0 : Math.max(down, up);

    }
}
