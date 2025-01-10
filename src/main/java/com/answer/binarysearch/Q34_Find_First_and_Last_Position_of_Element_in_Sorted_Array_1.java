package com.answer.binarysearch;

public class Q34_Find_First_and_Last_Position_of_Element_in_Sorted_Array_1 {
    /**
     * 寻找target在数组里的左右边界，有如下三种情况：
     *   情况一：target 在数组范围的右边或者左边，例如数组{3, 4, 5}，target为2或者数组{3, 4, 5},target为6，
     *          此时应该返回{-1, -1}
     *   情况二：target 在数组范围中，且数组中不存在target，例如数组{3,6,7},target为5，此时应该返回{-1, -1}
     *   情况三：target 在数组范围中，且数组中存在target，例如数组{3,6,7},target为6，此时应该返回{1, 1}
     */
    int[] searchRange0(int[] nums, int target) {
        int leftBorder = getLeftBorder(nums, target);
        int rightBorder = getRightBorder(nums, target);
        // 情况一
        if (leftBorder == -2 || rightBorder == -2) return new int[]{-1, -1};
        // 情况三
        if (rightBorder - leftBorder > 1) return new int[]{leftBorder + 1, rightBorder - 1};
        // 情况二
        return new int[]{-1, -1};
    }
    // 二分查找，寻找target的右边界（不包括target）
    // 如果rightBorder为没有被赋值（即target在数组范围的左边，例如数组[3,3]，target为2），为了处理情况一
    int getRightBorder(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;  // 定义target在左闭右闭的区间里，[left, right]
        int rightBorder = -2; // 记录一下rightBorder没有被赋值的情况
        while (left <= right) { // 当left==right，区间[left, right]依然有效
            int middle = left + ((right - left) / 2);
            if (nums[middle] > target) {
                right = middle - 1;  // target 在左区间，所以[left, middle - 1]
            } else { // 寻找右边界，nums[middle] == target的时候更新left
                left = middle + 1;
                rightBorder = left;
            }
        }
        return rightBorder;
    }
    // 二分查找，寻找target的左边界leftBorder（不包括target）
    // 如果leftBorder没有被赋值（即target在数组范围的右边，例如数组[3,3],target为4），为了处理情况一
    int getLeftBorder(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 定义target在左闭右闭的区间里，[left, right]
        int leftBorder = -2; // 记录一下leftBorder没有被赋值的情况
        while (left <= right) {
            int middle = left + ((right - left) / 2);
            if (nums[middle] >= target) { // 寻找左边界，nums[middle] == target的时候更新right
                right = middle - 1;
                leftBorder = right;
            } else {
                left = middle + 1;
            }
        }
        return leftBorder;
    }
}
