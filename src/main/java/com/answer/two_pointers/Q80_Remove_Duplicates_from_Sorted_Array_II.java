package com.answer.two_pointers;

import java.util.*;

public class Q80_Remove_Duplicates_from_Sorted_Array_II {
    /**
     * Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that
     * each unique element appears at most twice. The relative order of the elements should be kept the same.
     *
     * Since it is impossible to change the length of the array in some languages, you must instead have
     * the result be placed in the first part of the array nums. More formally, if there are k elements after
     * removing the duplicates, then the first k elements of nums should hold the final result. It does not matter
     * what you leave beyond the first k elements.
     *
     * Return k after placing the final result in the first k slots of nums.
     * Do not allocate extra space for another array. You must do this by modifying the input array in-place
     * with O(1) extra memory.
     * 删除有序数组中的重复项 II
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成
     * nums is sorted in non-decreasing order. / nums 已按升序排列
     */
    public static void main(String[] args) {
        /**
         * 示例 1：
         *  输入：nums = [1,1,1,2,2,3]
         *  输出：5, nums = [1,1,2,2,3]
         *  解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3。 不需要考虑数组中超出新长度后面的元素。
         */
        int[] nums = {1,1,1,2,2,3};
        System.out.println(removeDuplicates7(nums));
    }
    /**
     * 快慢指针
     * 因为给定数组是有序的，所以相同元素必然连续。可以使用双指针解决本题，遍历数组检查每一个元素是否应该被保留，如果应该被保留，就将其移动到指定位置
     */
    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if(n < 2) return n;

        int slow = 2, fast = 2; // 直接将双指针的初始值设为 2 即可
        while(fast < n){
            if(nums[slow - 2] != nums[fast]){ // 因为本题要求相同元素最多出现两次而非一次，所以我们需要检查上上个应该被保留的元素 nums[slow−2] 是否和当前待检查元素 nums[fast] 相同
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
    /**
     * 辅助下标
     * refer to Q26 Remove Duplicates from Sorted Array
     * 本题与 Q26. 删除有序数组中的重复项 解法相似，只是循环中的赋值条件发生了改变。
     * 由于允许每个元素最多出现两次，因此在循环中需要判断：只要当前元素和结果数组的倒数第二个元素不同就可以加入。
     */
    public static int removeDuplicates_2(int[] nums) {
        if(nums.length < 2) return nums.length;

        int index = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[index - 2] != nums[i]) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
    /**
     * General solution
     */
    public static int removeDuplicates_1(int[] nums) {
        int left = 0;
        int k = 2;
        for(int right = 0; right < nums.length; right++){
            if(right < k || nums[right] != nums[left -k]){
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }
    /**
     * 用一个栈记录去重后的元素，如果当前元素等于栈顶下方那个数（倒数第二个数），那么不能入栈（否则会有三个一样的数），反之可以入栈。
     */
    public int removeDuplicates4(int[] nums) {
        int stackSize = 2; // 栈的大小，前两个元素默认保留
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[stackSize - 2]) { // 和栈顶下方的元素比较
                nums[stackSize] = nums[i]; // 入栈
                stackSize++;
            }
        }
        return stackSize;
    }
    /**
     * 暴力模拟
     * 时间复杂度: O(n^2)
     */
   static public int removeDuplicates7(int[] nums) {
        int n = nums.length;

        List<Integer> correct = new ArrayList<>(); // 存储符合要求的元素
       // 第一个 for循环枚举当前元素
        for (int i = 0; i < n; i++) {
            // 统计 nums[i] 出现次数
            int count = 1;
            // 第二个 for判断它已经出现了几次
            for (int j = i - 1; j >= 0; j--) { // 逆序检查前面是否有相同元素
                if (nums[j] == nums[i]) {
                    count++;
                } else {
                    break;
                }
            }
            // 只保留前两个相同的元素
            if (count <= 2) { // 如果不止两个，说明已经添加过两次了，无需再次添加。
                correct.add(nums[i]);
            }
        }
         // 将 correct 的内容填充回 nums
        for (int i = 0; i < correct.size(); i++) {
            nums[i] = correct.get(i); // 注意，这里并非是原地修改。先保留所有正确数字，再覆盖 nums
        }
        return correct.size();
    }
    /**
     * another form
     */
    public int removeDuplicates8(int[] nums) {
        int n = nums.length;

        int[] newNums = new int[n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            int count = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] == nums[i]) {
                    count++;
                } else {
                    break;
                }
            }
            if (count <= 2) {
                newNums[index++] = nums[i];
            }
        }
        for (int i = 0; i < index; i++) {
            nums[i] = newNums[i];
        }
        return index;
    }
}
