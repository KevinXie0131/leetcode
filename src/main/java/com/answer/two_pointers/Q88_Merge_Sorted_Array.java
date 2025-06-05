package com.answer.two_pointers;

import java.util.Arrays;

public class Q88_Merge_Sorted_Array {
    /**
     * given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
     * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
     * The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
     * To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that
     * should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
     * 合并两个有序数组
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     * Follow up: Can you come up with an algorithm that runs in O(m + n) time?
     * 进阶：你可以设计实现一个时间复杂度为 O(m + n) 的算法解决此问题吗？
     * nums1.length == m + n
     * nums2.length == n
     */
    public static void main(String[] args) {
        /**
         * 示例 1：
         *  输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
         *  输出：[1,2,2,3,5,6]
         *  解释：需要合并 [1,2,3] 和 [2,5,6] 。合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
         */
        int[] nums1 = {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = {2,5,6};
        int n = 3;
        merge1(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }
    /**
     * Approach 3: Three Pointers (Start From the End) 逆向双指针
     * 观察可知，nums1的后半部分是空的，可以直接覆盖而不会影响结果。因此可以指针设置为从后向前遍历，
     * 每次取两者之中的较大者放进 nums1的最后面。
     * 时间复杂度：O(m+n)。
     */
    static public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1; // point to the tail to avoid override num1
        int cur;
        int tail = m + n - 1;
        while(p1 >= 0 || p2 >= 0){
            if(p1 == -1){
                cur = nums2[p2--];
            }
            else if(p2 == -1){
                cur = nums1[p1--];
            }
            else if(nums1[p1] > nums2[p2]){
                cur = nums1[p1--];
            }
            else{
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }
    /**
     * 另一种形式
     * 逆序双指针，从后向前遍历两个数组，选取大的元素从后向前赋值
     */
    static public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int i= m - 1, j = n - 1;
        int tail = m + n - 1;

        while(i >= 0 && j >= 0){
            if(nums1[i] >= nums2[j]) {
                nums1[tail--] = nums1[i--];
            } else {
                nums1[tail--] = nums2[j--];
            }
        }
        // nums2 还有要合并的元素
        while(j >= 0){ // 当 i<0 时遍历结束，此时 nums2 中数据未拷贝完全，将其直接拷贝到 nums1 的前面，最后得到结果数组
            nums1[tail--] = nums2[j--];
        }
    }
    /**
     * 直接合并后排序
     */
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i != n; ++i) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }
}
