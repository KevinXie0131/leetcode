package com.answer.prefix_sum;

public class Q724_Find_Pivot_Index {
    public static void main(String[] args) {
        int[] nums = {1,7,3,6,5,6};
     //   int[] nums = {2,1,-1};
        System.out.println(pivotIndex_1(nums));
    }
    /**
     * Approach #1: Prefix Sum
     */
    public static int pivotIndex_1(int[] nums) {
        int sum = 0;
        int leftsum = 0;
        for (int x: nums) {
            sum += x;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (leftsum == sum - leftsum - nums[i]) {
                return i;
            }
            leftsum += nums[i];
        }
        return -1;
    }
    /**
     * 遍历一遍求出总和sum
     * 遍历第二遍求中心索引左半和leftSum
     * 同时根据sum和leftSum 计算中心索引右半和rightSum
     * 判断leftSum和rightSum是否相同
     */
    public int pivotIndex1(int[] nums) {
        int sum = 0;
        for(int n : nums){
            sum += n; // 总和
        }
        int left = 0;  // 中心索引左半和
        int right = 0;  // 中心索引右半和

        for(int i = 0; i < nums.length; i++){
            left += nums[i];
            right = sum -left + nums[i];  // leftSum 里面已经有 nums[i]，多减了一次，所以加上
            if(left == right){
                return i;
            }
        }
        return -1; // 不存在
    }
}
