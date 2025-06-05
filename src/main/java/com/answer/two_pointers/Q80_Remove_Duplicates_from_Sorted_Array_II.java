package com.answer.two_pointers;

public class Q80_Remove_Duplicates_from_Sorted_Array_II {
    /**
     * Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that
     * each unique element appears at most twice. The relative order of the elements should be kept the same.
     *
     * Since it is impossible to change the length of the array in some languages, you must instead have
     * the result be placed in the first part of the array nums. More formally, if there are k elements after
     * removing the duplicates, then the first k elements of nums should hold the final result. It does not matter
     * what you leave beyond the first k elements.
     *
     * Return k after placing the final result in the first k slots of nums.
     * Do not allocate extra space for another array. You must do this by modifying the input array in-place
     * with O(1) extra memory.
     * 删除有序数组中的重复项 II
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成
     * nums is sorted in non-decreasing order. / nums 已按升序排列
     */
    public static void main(String[] args) {
        /**
         * 示例 1：
         *  输入：nums = [1,1,1,2,2,3]
         *  输出：5, nums = [1,1,2,2,3]
         *  解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3。 不需要考虑数组中超出新长度后面的元素。
         */
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
