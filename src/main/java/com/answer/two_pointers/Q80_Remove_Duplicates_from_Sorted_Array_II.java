package com.answer.two_pointers;

public class Q80_Remove_Duplicates_from_Sorted_Array_II {
    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        System.out.println(removeDuplicates(nums));
    }
    /**
     * 快慢指针
     */
    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if(n < 2) return n;

        int slow = 2, fast = 2;
        while(fast < n){
            if(nums[slow - 2] != nums[fast]){
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
    /**
     * 辅助下标
     * refer to Q26 Remove Duplicates from Sorted Array
     * 本题与 Q26. 删除有序数组中的重复项 解法相似，只是循环中的赋值条件发生了改变。
     * 由于允许每个元素最多出现两次，因此在循环中需要判断：只要当前元素和结果数组的倒数第二个元素不同就可以加入。
     */
    public static int removeDuplicates_2(int[] nums) {
        if(nums.length < 2) return nums.length;

        int index = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[index - 2] != nums[i]) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
    /**
     * General solution
     */
    public static int removeDuplicates_1(int[] nums) {
        int index = 0;
        int k = 2;
        for (int i = 0; i < nums.length; i++) {
            if (index < k || nums[index - k] != nums[i]) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
}
