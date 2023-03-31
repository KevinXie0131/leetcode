package com.answer.dynamic_programming;

public class Q718_Maximum_Length_of_Repeated_Subarray {
    /**
     * 2-D Dynamic Programming
     */
    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int result = 0;

        for(int i = 1; i <= nums1.length; i++){
            for(int j = 1; j <= nums2.length; j++){
                if(nums1[i - 1] == nums2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                if(dp[i][j] > result){
                    result = dp[i][j];
                }
            }
        }

        return result;
    }
    /**
     * 1-D Dynamic Programming
     */
    public int findLength_2(int[] nums1, int[] nums2) {
        int[] dp = new int[nums2.length + 1];
        int result = 0;

        for(int i = 1; i <= nums1.length; i++){
            for(int j = nums2.length; j >= 1; j--){
                if(nums1[i - 1] == nums2[j - 1]){
                    dp[j] = dp[j - 1] + 1;
                }else{
                    dp[j] = 0;
                }
                if(dp[j] > result){
                    result = dp[j];
                }
            }
        }
        return result;
    }
}
