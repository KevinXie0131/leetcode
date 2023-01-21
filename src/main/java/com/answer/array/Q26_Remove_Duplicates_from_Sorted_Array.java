package com.answer.array;

public class Q26_Remove_Duplicates_from_Sorted_Array {

    public int removeDuplicates(int[] nums) {
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
        return slow + 1;
    }
}
