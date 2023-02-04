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
    /**
     * Official answer
     */
    public boolean canJump_1(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Approach 1: Backtracking
     * Time Limit Exceeded
     */
    public boolean canJump_2(int[] nums) {
        return backtracking(0, nums);
    }
    public boolean backtracking(int position, int[] nums) {
        if (position == nums.length - 1) {
            return true;
        }
        int furthestJump = Math.min(position + nums[position], nums.length - 1);

        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (backtracking(nextPosition, nums)) {
                return true;
            }
        }
        return false;
    }


}
