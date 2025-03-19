package com.answer.array;

import java.util.Arrays;

public class Q31_Next_Permutation {
    public static void main(String[] args) {
        int[]nums = {2, 3, 5, 4, 1};
        nextPermutation_5(nums);
        System.out.println(Arrays.toString(nums));
    }
    /**
     * 从后向前遍历
     * For example 2 3 5 4 1
     *          -> 2 4 5 3 1
     *          -> 2 4 1 3 5
     */
    static public void nextPermutation_1(int[] nums) {
        for(int i = nums.length - 1; i >= 0 ; i-- ){
            for(int j = nums.length - 1; j > i ; j-- ){
                if(nums[j] > nums[i]){
                    int temp = nums[j];  // 交换
                    nums[j] = nums[i];
                    nums[i] = temp;
                    // [i + 1, nums.length) 内元素升序排序
                    Arrays.sort(nums, i + 1, nums.length );
                    return;
                }
            }
        }
        Arrays.sort(nums);  // 到这里了说明整个数组都是倒序了，反转一下便可
    }
    /**
     * 另一种形式
     */
    static public void nextPermutation_5(int[] nums) {
        int k = nums.length - 1;
        while(k > 0 && nums[k - 1] >= nums[k]){ // find the first number in 逆序
            k--;
        }
        if(k <= 0) {
            Arrays.sort(nums);
        } else {
            int t = nums.length - 1;
            while(nums[t] <= nums[k - 1]){ // find the first number larger than nums[k - 1]
                t--;
            }
            int temp = nums[k - 1];  // 交换
            nums[k - 1] = nums[t];
            nums[t] = temp;

            Arrays.sort(nums, k, nums.length);
        }
    }
    /**
     * 优化时间复杂度为O(N)，空间复杂度为O(1)
     */
    public void nextPermutation_2(int[] nums) {
        // 1.从后向前获取逆序区域的前一位
        int index = findIndex(nums);
        // 判断数组是否处于最小组合状态
        if(index != 0){
            // 2.交换逆序区域刚好大于它的最小数字
            exchange(nums,index);
        }
        // 3.把原来的逆序区转为顺序
        reverse1(nums,index);
    }

    public static int findIndex(int [] nums){
        for(int i = nums.length-1;i>0;i--){
            if(nums[i]>nums[i-1]){
                return i;
            }
        }
        return 0;
    }
    public static void exchange(int [] nums, int index){
        int head = nums[index-1];
        for(int i = nums.length-1;i>0;i--){
            if(head < nums[i]){
                nums[index-1] = nums[i];
                nums[i] = head;
                break;
            }
        }
    }
    public static void reverse1(int [] nums, int index){
        for(int i = index,j = nums.length-1;i<j;i++,j--){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
    /**
     * Approach 2: Single Pass Approach
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while(i >= 0 && nums[i] >= nums[i + 1]){
            i--;
        }

        if(i >= 0){
            int j = nums.length - 1;
            while(j >= 0 && nums[i] >= nums[j]){
                j--;
            }

            swap(nums, i, j);
        }

        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start;
        int right = nums.length - 1;

        while(left < right){
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
