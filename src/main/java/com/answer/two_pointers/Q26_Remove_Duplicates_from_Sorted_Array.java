package com.answer.two_pointers;

import java.util.*;

public class Q26_Remove_Duplicates_from_Sorted_Array {
    /**
     * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that
     * each unique element appears only once. The relative order of the elements should be kept the same.
     * Then return the number of unique elements in nums.
     * Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:
     *  Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
     *  Return k.
     * 删除有序数组中的重复项
     * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
     * 考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：
     *  更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。nums 的其余元素与 nums 的大小不重要。
     *  返回 k
     * nums is sorted in non-decreasing order. / nums 已按 非严格递增 排列
     */
    public static void main(String[] args) {
        /**
         * 示例 2：
         *  输入：nums = [0,0,1,1,1,2,2,3,3,4]
         *  输出：5, nums = [0,1,2,3,4]
         *  解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
         */
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int r = removeDuplicates_1(nums);
        System.out.println(r);
    }
    /**
     * Approach 1: Two indexes approach
     * 双指针法（快慢指针法）- sorted in non-decreasing order,
     * 一个指针 i 进行数组遍历，另外一个指针 j 指向有效数组的最后一个位置。
     * 只有当 i 所指向的值和 j 不一致（不重复），才将 i 的值添加到 j 的下一位置。
     */
    static public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;

        int slow = 0, fast = 0;
        while(fast < n){
            if(nums[slow] != nums[fast]){
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
/*        for(int fast = 0; fast < nums.length; fast++){
            if(nums[fast] != nums[slow]){
                slow++;
                nums[slow] = nums[fast];
            }
        }*/
        return slow + 1;
    }
    /**
     * 辅助下标
     * 在遍历数组的同时，通过用一个辅助下标记录最后一个不重复元素的位置，遍历结束时，
     * 辅助下标的值就代表不重复数组的长度，直接返回即可。
     */
    public int removeDuplicates_0(int[] nums) {
        int index = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[index] != nums[i]){ // nums[i] 不是重复项
                index++;
                nums[index] = nums[i]; // 保留 nums[i]
            }
        }
        return index + 1;
    }
    /**
     * General solution 通用解法
     * 为了让解法更具有一般性，我们将原问题的「最多保留 1 位」修改为「最多保留 k 位」。
     * 对于此类问题，我们应该进行如下考虑：
     *     由于是保留 k 个相同数字，对于前 k 个数字，我们可以直接保留。
     *     对于后面的任意数字，能够保留的前提是：与当前写入的位置前面的第 k 个元素进行比较，不相同则保留。
     */
    /**
     * 令 k=1，假设有样例：[3,3,3,3,4,4,4,5,5,5]
     * 设定变量 index，指向待插入位置。index 初始值为 0，目标数组为 []
     * 首先我们先让第 1 位直接保留（性质 1）。index 变为 1，目标数组为 [3]
     * 继续往后遍历，能够保留的前提是与 idx 的前面 1 位元素不同（性质 2），因此我们会跳过剩余的 3，将第一个 4 追加进去。index 变为 2，目标数组为 [3,4]
     * 继续这个过程，跳过剩余的 4，将第一个 5 追加进去。index 变为 3，目标数组为 [3,4,5]
     * 当整个数组被扫描完，最终我们得到了目标数组 [3,4,5] 和 答案 idx 为 3。
     */
    static public int removeDuplicates_1(int[] nums) {
        int index = 0;
        int k = 1;
        for (int i = 0; i < nums.length; i++) {
            if (index < k || nums[index - k] != nums[i]) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
    /**
     * 直接往set里面丢
     */
    public int removeDuplicates6(int[] nums) {
        Set<Integer> set = new TreeSet();
        for(int i = 0; i < nums.length; i++){
            set.add(nums[i]);
        }
        int index = 0;
        for(Integer res : set){
            nums[index] = res;
            index++;
        }
        return index;
    }
}
