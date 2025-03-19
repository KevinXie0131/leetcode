package com.answer.two_pointers;

public class Q26_Remove_Duplicates_from_Sorted_Array {
    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int r = removeDuplicates(nums);
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
     * General solution 通用解法
     * 为了让解法更具有一般性，我们将原问题的「最多保留 1 位」修改为「最多保留 k 位」。
     * 对于此类问题，我们应该进行如下考虑：
     *     由于是保留 k 个相同数字，对于前 k 个数字，我们可以直接保留。
     *     对于后面的任意数字，能够保留的前提是：与当前写入的位置前面的第 k 个元素进行比较，不相同则保留。
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
}
