package com.answer.array;

import java.util.Arrays;

public class Q283_Move_Zeroes {

    public static void main(String[] args) {
    //    int[] nums = {0,1,0,3,12};
        int[] nums = {4,0,0,3,2,5,0,4};
        moveZeroes_2(nums);
        System.out.println(Arrays.toString(nums));
    }
    /**
     * 双指针法（快慢指针法）
     */
    public void moveZeroes0(int[] nums) {
        int slow = 0;
        for(int fast = 0; fast < nums.length; fast++){
            if(nums[fast] != 0){
                nums[slow] = nums[fast];
                slow++;
            }
        }
        for(; slow < nums.length; slow++){
            nums[slow] = 0;
        }
    }
    /**
     *
     */
    public static void moveZeroes(int[] nums) {
        int n = nums.length;
        int slow = 0, fast = 1;

        while(slow < n && fast < n){
            if(nums[slow] == 0){
                while(nums[fast] == 0){
                    fast++;
                    if(fast > n - 1){
                        return;
                    }
                }
                swap(nums, slow, fast);
                slow++;
            } else {
                slow++;
            }
        }
    }

    public static void swap(int[] nums, int left ,int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
    /**
     * Two pass
     */
    public static void moveZeroes_1(int[] nums) {
        if(nums==null) {
            return;
        }
        //第一次遍历的时候，j指针记录非0的个数，只要是非0的统统都赋给nums[j]
        int j = 0;
        for(int i=0;i<nums.length;++i) {
            if(nums[i]!=0) {
                nums[j++] = nums[i];
            }
        }
        //非0元素统计完了，剩下的都是0了
        //所以第二次遍历把末尾的元素都赋为0即可
        for(int i=j;i<nums.length;++i) {
            nums[i] = 0;
        }
    }
    /**
     * One pass 解题思路同下面
     */
    public static void moveZeroes_2(int[] nums) {
        int slow = 0;
        for(int fast = 0; fast< nums.length; fast++){
            if(nums[fast] != 0){
                int temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow] = temp;
                slow++;
            }
        }
    }
    /**
     * Official answer
     * 使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。
     * 右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
     * 注意到以下性质：
     * 左指针左边均为非零数；
     * 右指针左边直到左指针处均为零。
     * 因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。
     *
     */
    public static void moveZeroes_3(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }
}
