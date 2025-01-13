package com.answer.array;

public class Q941_Valid_Mountain_Array {
    /**
     * 双指针
     * 因为left和right是数组下标，移动的过程中注意不要数组越界
     * 如果left或者right没有移动，说明是一个单调递增或者递减的数组，依然不是山峰
     */
    public boolean validMountainArray(int[] arr) {
        if (arr.length < 3) { // 此时，一定不是有效的山脉数组
            return false;
        }
        int left = 0;
        int right = arr.length - 1;
        // 注意防止越界
        while(left < arr.length - 1 && arr[left] < arr[left + 1]){
            left++;
        }
        // 注意防止越界
        while(right > 0 && arr[right - 1] > arr[right]){
            right--;
        }
        // 如果left或者right都在起始位置，说明不是山峰
        if(left == right && left != 0 && right != arr.length - 1){
            return true;
        }
        return false;
    }
}
