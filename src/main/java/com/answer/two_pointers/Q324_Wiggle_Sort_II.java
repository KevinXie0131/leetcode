package com.answer.two_pointers;

import java.util.Arrays;

public class Q324_Wiggle_Sort_II {
    /**
     * 摆动排序 II
     * 一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
     * 你可以假设所有输入数组都可以得到满足题目要求的结果。
     * 示例 1：
     * 输入：nums = [1,5,1,1,6,4]
     * 输出：[1,6,1,5,1,4]
     * 解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
     */
    public static void main(String[] args) {
       int[] nums = {1,3,2,2,3,1};
        wiggleSort(nums);
        System.out.println(Arrays.toString(nums)); // [2, 3, 1, 3, 1, 2]
    }
    /**
     * 排序 + 双指针 交错填充
     * 1. 先排序。
     * 2. 将前半部分元素和后半部分元素分离，从尾部交错填入原数组的偶数和奇数索引位置。
     */
    static public void wiggleSort(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int i = (n + 1) / 2 - 1;
        int j = n - 1;
        for(int k = 0; k < n; k++){
            nums[k] = (k % 2 == 0) ? sorted[i--]: sorted[j--];
        }
    }
    /**
     * 桶排序
     * 可以把所有的元素分桶然后逐个取出。由于数据大小最大是5000，我们可以开5001个桶来存放元素。
     * 然后从大到小依次放回原数组。
     */
    public void wiggleSort1(int[] nums) {
        int[] bucket = new int[5001];  //5001个桶
        for (int num : nums) {
            bucket[num]++;
        }
        int j = 5000;
        //插空放 较大元素
        for (int i = 1; i < nums.length; i += 2) {
            while (bucket[j] == 0) { //找到不为0的桶
                j--;
            }
            nums[i] = j;
            bucket[j]--;
        }
        //插空放 较小元素
        for (int i = 0; i < nums.length; i += 2) {
            while (bucket[j] == 0) {
                j--;
            }
            nums[i] = j;
            bucket[j]--;
        }
    }
}
