package com.answer.array;

public class Q27_Remove_Element {

    public int removeElement(int[] nums, int val) {

        int slowIndex = 0;

        for(int fastIndex = 0; fastIndex < nums.length; fastIndex++){
            if(nums[fastIndex] != val){
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
            }
        }

        return slowIndex;
    }

    /**
     *
     */
    public int removeElement_1(int[] nums, int val) {

        int left = 0;
        int right = nums.length - 1;

        while(left <= right){
            if(nums[left] == val){
                nums[left] = nums[right];
                right--;
            } else {
                left++;
            }
        }

        return left;
    }
}
