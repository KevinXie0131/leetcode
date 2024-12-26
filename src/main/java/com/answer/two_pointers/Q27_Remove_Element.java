package com.answer.two_pointers;

public class Q27_Remove_Element {

    public static void main(String[] args) {
       int[]  nums = {0,1,2,2,3,0,4,2};
       int val = 2;

       int result =  removeElement_0(nums, val);
       System.out.println(result);

    }
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
    //相向双指针法
    public static int removeElement_0(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        while(right >= 0 && nums[right] == val) right--; //将right移到从右数第一个值不为val的位置
        while(left <= right) {
            if(nums[left] == val) { //left位置的元素需要移除
                //将right位置的元素移到left（覆盖），right位置移除
                nums[left] = nums[right];
                right--;
            }
            left++;
            while(right >= 0 && nums[right] == val) right--;
        }
        return left;
    }

    /**
     * 相向双指针法（版本二）
     */
    public static int removeElement_1(int[] nums, int val) {

        int left = 0;
        int right = nums.length - 1;

        while(left <= right){
            if(nums[left] == val){
                nums[left] = nums[right];
                right--;
            } else {
                // 这里兼容了right指针指向的值与val相等的情况
                left++;
            }
        }

        return left;
    }
}
