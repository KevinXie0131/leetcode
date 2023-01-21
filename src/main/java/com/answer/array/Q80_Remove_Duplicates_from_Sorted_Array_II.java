package com.answer.array;

public class Q80_Remove_Duplicates_from_Sorted_Array_II {
    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        System.out.println(removeDuplicates(nums));
    }
    /**
     *
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
