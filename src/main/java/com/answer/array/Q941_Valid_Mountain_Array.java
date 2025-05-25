package com.answer.array;

public class Q941_Valid_Mountain_Array {
    /**
     * 有效的山脉数组: 给定一个整数数组 arr，如果它是有效的山脉数组就返回 true，否则返回 false。
     * 让我们回顾一下，如果 arr 满足下述条件，那么它是一个山脉数组：
     *  arr.length >= 3
     *  在 0 < i < arr.length - 1 条件下，存在 i 使得：
     *      arr[0] < arr[1] < ... arr[i-1] < arr[i]
     *      arr[i] > arr[i+1] > ... > arr[arr.length - 1]
     * 示例 2：
     *  输入：arr = [3,5,5]
     *  输出：false
     * 示例 3：
     *  输入：arr = [0,3,2,1]
     *  输出：true
     */
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
        while(left < arr.length - 1 && arr[left] < arr[left + 1]){ // 从左边往右边找，一直找到山峰为止
            left++;
        }
        // 注意防止越界
        while(right > 0 && arr[right - 1] > arr[right]){ // 从右边往左边找，一直找到山峰为止
            right--;
        }
        // 如果left或者right都在起始位置，说明不是山峰
        if(left == right && left != 0 && right != arr.length - 1){
            return true;
        }
        return false;
        // return  left > 0 && right < arr.length - 1 && left == right; // 判断从左边和从右边找的山峰是不是同一个
    }
    /**
     * 线性扫描
     */
    public boolean validMountainArray_1(int[] arr) {
        int len = arr.length;
        int i = 0;
        // 递增扫描
        while (i + 1 < len && arr[i] < arr[i + 1]) {
            i++;
        }
        // 最高点不能是数组的第一个位置或最后一个位置
        if (i == 0 || i == len - 1) {
            return false; // 即整个数组都是单调递增的），那么就返回 false
        }
        // 递减扫描
        while (i + 1 < len && arr[i] > arr[i + 1]) {
            i++;
        }
        return i == len - 1;
    }
}
