package com.answer.string;

public class Q75_Sort_Colors {

    public void sortColors(int[] nums) {
        int n = nums.length;
        int index = 0;

        for(int i = 0; i < n; i++){
            if(nums[i] == 0){
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                index++;
            }
        }
        for(int i = index; i < n; i++){
            if(nums[i] == 1){
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                index++;
            }
        }
    }
}
