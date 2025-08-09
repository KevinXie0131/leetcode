package com.answer.array;

import java.util.Arrays;

public class Q31_Next_Permutation {
    /**
     * 下一个排列
     * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
     *
     * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
     * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，
     * 那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
     *
     * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
     * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
     * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
     * 给你一个整数数组 nums ，找出 nums 的下一个排列。
     *
     * 必须 原地 修改，只允许使用额外常数空间。
     */
    public static void main(String[] args) {
        int[]nums = {2, 3, 5, 4, 1};
        nextPermutation_5(nums);
        System.out.println(Arrays.toString(nums));
    }
    /**
     * 从后向前遍历
     * For example 2 3 5 4 1
     *          -> 2 4 5 3 1
     *          -> 2 4 1 3 5
     */
    static public void nextPermutation_1(int[] nums) {
        for(int i = nums.length - 1; i >= 0 ; i-- ){
            for(int j = nums.length - 1; j > i ; j-- ){
                if(nums[j] > nums[i]){
                    int temp = nums[j];  // 交换
                    nums[j] = nums[i];
                    nums[i] = temp;
                    // [i + 1, nums.length) 内元素升序排序
                    Arrays.sort(nums, i + 1, nums.length );
                    return;
                }
            }
        }
        Arrays.sort(nums);  // 到这里了说明整个数组都是倒序了，反转一下便可
    }
    /**
     * 另一种形式
     * 1.从后向前找到第一个正序数 k；
     * 2.从后向前找到第一个比 k 大的数 t；
     * 3.交换 k 和 t，并将原 k 位置后面的数字逆序。
     */
    static public void nextPermutation_5(int[] nums) {
        int k = nums.length - 1;
        while(k > 0 && nums[k - 1] >= nums[k]){ // find the first number in 逆序
            k--;
        }
        if(k <= 0) {
            Arrays.sort(nums);
        } else {
            int t = nums.length - 1;
            while(nums[t] <= nums[k - 1]){ // find the first number larger than nums[k - 1]
                t--;
            }
            int temp = nums[k - 1];  // 交换
            nums[k - 1] = nums[t];
            nums[t] = temp;

            Arrays.sort(nums, k, nums.length);
        }
    }
    /**
     * 优化时间复杂度为O(N)，空间复杂度为O(1)
     * 必须 原地 修改，只允许使用额外常数空间。The replacement must be in place and use only constant extra memory.
     */
    public void nextPermutation_2(int[] nums) {
        int index = findIndex(nums);// 1.从后向前获取逆序区域的前一位
        if(index != 0){// 判断数组是否处于最小组合状态
            exchange(nums, index);// 2.交换逆序区域刚好大于它的最小数字
        }
        reverse1(nums, index);  // 3.把原来的逆序区转为顺序
    }

    public static int findIndex(int [] nums){
        for(int i = nums.length - 1; i > 0; i--){
            if(nums[i] > nums[i - 1]){
                return i;
            }
        }
        return 0;
    }

    public static void exchange(int [] nums, int index){
        int head = nums[index - 1];
        for(int i = nums.length - 1; i > 0; i--){
            if(head < nums[i]){
                nums[index - 1] = nums[i];
                nums[i] = head;
                break;
            }
        }
    }

    public static void reverse1(int [] nums, int index){
        for(int i = index, j = nums.length - 1; i < j; i++, j--){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
    /**
     * Approach 2: Single Pass Approach
     * 将后面的「大数」与前面的「小数」交换，就能得到一个更大的数, 还希望下一个数 增加的幅度尽可能的小，这样才满足“下一个排列与当前排列紧邻“的要求
     *      在 尽可能靠右的低位 进行交换，需要 从后向前 查找
     *      将一个 尽可能小的「大数」 与前面的「小数」交换。
     *      将「大数」换到前面后，需要将「大数」后面的所有数 重置为升序，升序排列就是最小的排列
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while(i >= 0 && nums[i] >= nums[i + 1]){ // 从后向前 查找第一个 相邻升序 的元素对 (i,j)，满足 A[i] < A[j]。此时 [j,end) 必然是降序
            i--;
        }

        if(i >= 0){
            int j = nums.length - 1;
            while(j >= 0 && nums[i] >= nums[j]){ // 在 [j,end) 从后向前 查找第一个满足 A[i] < A[k] 的 k。A[i]、A[k] 分别就是上文所说的「小数」、「大数」
                j--;
            }
            swap(nums, i, j); // 将 A[i] 与 A[k] 交换
        }
        reverse(nums, i + 1); // 可以断定这时 [j,end) 必然是降序，逆置 [j,end)，使其升序
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start;
        int right = nums.length - 1;

        while(left < right){
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
