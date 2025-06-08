package com.answer.sorting;

import java.util.*;

public class Q912_Sort_an_Array {
    /**
     * Given an array of integers nums, sort the array in ascending order and return it.
     * You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest space complexity possible.
     * 排序数组
     * 一个整数数组 nums，请你将该数组升序排列。
     * 你必须在 不使用任何内置函数 的情况下解决问题，时间复杂度为 O(nlog(n))，并且空间复杂度尽可能小。
     * Example 1:
     *  Input: nums = [5,2,3,1]
     *  Output: [1,2,3,5]
     *  Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3), while the positions of other numbers are changed (for example, 1 and 5).
     * Example 2:
     *  Input: nums = [5,1,1,2,0,0]
     *  Output: [0,0,1,1,2,5]
     *  Explanation: Note that the values of nums are not necessairly unique.
     */
    public static void main(String[] args) {
        int[] nums = {5, 2, 3, 1, 9, 4, 8, 10, 7};
        Q912_Sort_an_Array solution = new Q912_Sort_an_Array();
        int[] res = solution.sortArray_0(nums);
        System.out.println(Arrays.toString(res));
    }

    /**
     * Bubble sort
     * 冒泡排序
     */
    public int[] sortArray_0(int[] nums) {
        int len = nums.length;
        for(int i = len - 1;  i >=0; i--) {
            // 先默认数组是有序的，只要发生一次交换，就必须进行下一轮比较，
            // 如果在内层循环中，都没有执行一次交换操作，说明此时数组已经是升序数组
            boolean sorted = true;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    sorted = false;
                }
            }
            if (sorted) {
                break;
            }
        }
        return nums;
    }
    /**
     * Selection sort
     * 选择排序：每一轮选择最小元素交换到未排定部分的开头
     * 由于「插入排序」在「几乎有序」的数组上表现良好，特别地，在「短数组」上的表现也很好。因为「短数组」的特点是：
     * 每个元素离它最终排定的位置都不会太远。为此，在小区间内执行排序任务的时候，可以转向使用「插入排序」
     */
    // 选择排序：每一轮选择最小元素交换到未排定部分的开头
    public  static int[] sortArray(int[] nums) {
        int len = nums.length;
        // 循环不变量：[0, i) 有序，且该区间里所有元素就是最终排定的样子
        for(int i = 0; i < len - 1; i++){
            // 选择区间 [i, len - 1] 里最小的元素的索引，交换到下标 i
            int min = i;
            for(int j = i + 1; j < len; j++){
                if(nums[j] < nums[min]){
                    min = j;
                }
            }
            swap(nums, i, min);
        }

        return nums;
    }

    /**
     * Insertion sort
     * 插入排序：稳定排序，在接近有序的情况下，表现优异
     */
    public static int[] sortArray_1(int[] nums) {
        int len = nums.length;
        // 循环不变量：将 nums[i] 插入到区间 [0, i) 使之成为有序数组
        for(int i = 1; i < len; i++){
            // 先暂存这个元素，然后之前元素逐个后移，留出空位
            int temp = nums[i];
            int j = i;
            while(j > 0 && nums[j - 1] > temp){
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = temp;
        }
        return nums;
    }
    /**
     * Merge sort
     * 归并排序
     * 「归并排序」比「快速排序」好的一点是，它借助了额外空间，可以实现「稳定排序」，Java 里对于「对象数组」的排序任务，就是使用归并排序-升级版 TimSort
     *
     * 归并排序采用经典的分治（divide-and-conquer）策略来对序列进行排序：
     * -「分」的阶段首先将序列一步步分解成小的子序列进行分段排序；
     * -「治」的阶段则将分段有序的子序列合并在一起，使得整个序列变得有序。
     */
    public static int[] sortArray_2(int[] nums) {
        int len = nums.length;
        int[] temp = new int[len];
        mergeSort(nums, 0, len - 1, temp);
        return nums;
    }
    private static void mergeSort(int[] nums, int left, int right, int[] temp) {
        if (right <= left) {
            return;
        }
        int mid = (left + right) >>> 1;
        mergeSort(nums, left, mid, temp);
        mergeSort(nums, mid + 1, right, temp);
        // 如果数组的这个子区间本身有序，无需合并
        if (nums[mid] <= nums[mid + 1]) {
            return;
        }
        System.out.println(Arrays.toString(nums) + " " + Arrays.toString(temp));
        mergeOfTwoSortedArray(nums, left, mid, right, temp);
        System.out.println(Arrays.toString(nums) + " " + Arrays.toString(temp));
    }
    private static void mergeOfTwoSortedArray(int[] nums, int left, int mid, int right, int[] temp) {
        System.arraycopy(nums, left, temp, left, right - left  + 1 );

        int i = left;
        int j = mid + 1;

        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                // 注意写成 < 就丢失了稳定性（相同元素原来靠前的排序以后依然靠前）
                nums[k] = temp[i];
                i++;
            } else {
                // temp[i] > temp[j]
                nums[k] = temp[j];
                j++;
            }
        }
    }
    /**
     * Quick sort
     * 快速排序
     * - 挑选基准值：从数列中挑出一个元素，称为“基准”（pivot）；
     * - 分割（partition）：重新排序数列，所有比基准值小的元素摆放在基准前面，所有比基准值大的元素摆在基准后面（与基准值相等的数可以到任何一边）。在这个分割结束之后，对基准值的排序就已经完成；
     * - 递归排序子序列：递归地将小于基准值元素的子序列和大于基准值元素的子序列排序。
     */
     public static int[] sortArray_5(int[] nums) {
        int len = nums.length;
        System.out.println(Arrays.toString(nums));
        quickSort(nums, 0, len - 1);
        return nums;
    }
    private static void quickSort(int[] nums, int left, int right) {
        // 小区间使用插入排序
        if (right <= left) {
            return;
        }

        int pIndex = partition(nums, left, right);
        System.out.println(pIndex);
        quickSort(nums, left, pIndex - 1);
        System.out.println(Arrays.toString(nums));
        quickSort(nums, pIndex + 1, right);
        System.out.println(Arrays.toString(nums));
    }

    private static int partition(int[] nums, int left, int right) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(right - left + 1) + left;
        swap(nums, left, randomIndex);

        // 基准值
        int pivot = nums[left];
        int lt = left;
        // 循环不变量：
        // all in [left + 1, lt] < pivot
        // all in [lt + 1, i) >= pivot
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                lt++;
                swap(nums, i, lt);
            }
        }
        swap(nums, left, lt);
        return lt;
    }
    /**
     * Heap sort
     * 堆排序
     */
    public static int[] sortArray_6(int[] nums) {
        int len = nums.length;
        // 将数组整理成堆
        heapify(nums);

        // 循环不变量：区间 [0, i] 堆有序
        for (int i = len - 1; i >= 1; ) {
            // 把堆顶元素（当前最大）交换到数组末尾
            swap(nums, 0, i);
            // 逐步减少堆有序的部分
            i--;
            // 下标 0 位置下沉操作，使得区间 [0, i] 堆有序
            siftDown(nums, 0, i);
        }
        return nums;
    }
    private static void heapify(int[] nums) { // 将数组整理成堆（堆有序）
        int len = nums.length;
        // 只需要从 i = (len - 1) / 2 这个位置开始逐层下移
        for (int i = (len - 1) / 2; i >= 0; i--) {
            siftDown(nums, i, len - 1);
        }
    }
    private static void siftDown(int[] nums, int k, int end) {
        while (2 * k + 1 <= end) {
            int j = 2 * k + 1;
            if (j + 1 <= end && nums[j + 1] > nums[j]) {
                j++;
            }
            if (nums[j] > nums[k]) {
                swap(nums, j, k);
            } else {
                break;
            }
            k = j;
        }
    }
    private static void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

}
