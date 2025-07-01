package com.answer.binarysearch;

public class Q540_Single_Element_in_a_Sorted_Array {
    /**
     * 有序数组中的单一元素
     * 一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
     * You are given a sorted array consisting of only integers where every element appears exactly twice,
     * except for one element which appears exactly once.
     * 请你找出并返回只出现一次的那个数。
     * Return the single element that appears only once.
     * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
     * Your solution must run in O(log n) time and O(1) space.
     */
    public static void main(String[] args) {
       int[] nums = {1,1,2,3,3,4,4,8,8}; // 输出: 2
       int result = singleNonDuplicate1(nums);
       System.out.println(result);
    }
    /**
     * 假设只出现一次的元素位于下标 x，由于其余每个元素都出现两次，因此下标 x 的左边和右边都有偶数个元素，数组的长度是奇数。
     * 由于数组是有序的，因此数组中相同的元素一定相邻。
     * 对于下标 x 左边的下标 y，如果 nums[y]=nums[y+1]，则 y 一定是偶数；
     * 对于下标 x 右边的下标 z，如果 nums[z]=nums[z+1]，则 z 一定是奇数。
     * 由于下标 x 是相同元素的开始下标的奇偶性的分界，因此可以使用二分查找的方法寻找下标 x
     *
     * 方法一：全数组的二分查找
     * 如果 mid 是偶数，则比较 nums[mid] 和 nums[mid+1] 是否相等；
     * 如果 mid 是奇数，则比较 nums[mid−1] 和 nums[mid] 是否相等。
     * 利用按位异或的性质，可以得到 mid 和相邻的数之间的如下关系，其中 ⊕ 是按位异或运算符：
     *     当 mid 是偶数时，mid+1=mid⊕1；
     *     当 mid 是奇数时，mid−1=mid⊕1。
     */
    public int singleNonDuplicate4(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            // 如果 mid 是偶数，则比较 nums[mid] 和 nums[mid+1] 是否相等
            // 如果 mid 是奇数，则比较 nums[mid−1] 和 nums[mid] 是否相等
            if (nums[mid] == nums[mid ^ 1]) { //不需要判断 mid 的奇偶性，mid 和 mid⊕1 即为每次需要比较元素的两个下标。
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }
    /**
     * Solution - IV (XOR)
     * We know that a ⊕ a = 0,  two same numbers when xor-ed, cancels each other out. In our case, every element occurs twice except the one.
     * Thus, if we xor all the elements from nums, every element will cancel each other out except the required answer.
     * Time Complexity : O(N) Space Complexity : O(1)
     */
    public int singleNonDuplicate2(int[] nums) {
        int result = 0;
        for(int n : nums) {
            result ^= n;    // xor-ing all elements of array
        }
        return result;
    }
    /**
     * 最简单的方法是以「步长为 2」的方式进行从前往后的遍历，找到第一个不符合「与后一个数相等」条件的值即是答案。
     * 或是利用单个元素只有一个（其余成对出现），从头到尾异或一遍，最终结果为单一元素值
     */
    public int singleNonDuplicate5(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i += 2) {
            if (nums[i] != nums[i + 1]) return nums[i];
        }
        return nums[n - 1];
    }
    /**
     * we can observe that for each pair: first element takes even position and second element takes odd position
     * this pattern will be missed when single element is appeared in the array.
     * 题目有两个已知条件：
     *  数组是有序的。
     *  除了一个数出现一次外，其余每个数都出现两次。
     * 第二个条件意味着，数组的长度一定是奇数。
     * 第一个条件意味着，出现两次的数，必然相邻，不可能出现 1,2,1 这样的顺序。
     * 这也意味着，只出现一次的那个数，一定位于偶数下标上。
     */
    static public int singleNonDuplicate1(int[] nums) {
        int left = 0, right = nums.length-1;
        while(left <= right){
            int mid = (left + right)/2;
            // if mid is even, then it's duplicate should be in next index.
            //	or if mid is odd, then it's duplicate  should be in previous index.
            if( (mid < nums.length - 1 && mid % 2 == 0 && nums[mid] == nums[mid + 1])
                    || (mid % 2 == 1 && nums[mid] == nums[mid - 1]) ) {
                left = mid + 1; // if any of the conditions is satisfied,  then pattern is not missed, so check in next half of the array
            } else {
                right = mid - 1; // if condition is not satisfied, then the pattern is missed. so, single number must be before mid.
            }
        }
        return nums[left]; // return nums[right + 1 ]; // works too
    }
    /**
     * Since every element in the sorted array appears exactly twice except for the single element,
     * we know that if we take any element at an even index (0-indexed), the next element should be the same.
     * Similarly, if we take any element at an odd index, the previous element should be the same.
     * Therefore, we can use binary search to compare the middle element with its adjacent elements to determine
     * which side of the array the single element is on.
     *
     * Time complexity: O(logn) Space complexity: O(1)
     *
     * 偶数下标的二分查找
     * 如果 mid 是奇数则将 mid 减 1，确保 mid 是偶数，比较 nums[mid] 和 nums[mid+1] 是否相等，如果相等则 mid<x，调整左边界，
     * 否则 mid≥x，调整右边界。调整边界之后继续二分查找，直到确定下标 x 的值。
     *
     * & 是按位与运算符：
     *  当 mid 是偶数时，mid & 1=0；
     *  当 mid 是奇数时，mid & 1=1。
     */
    static public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1; // int mid = (left + right) >>> 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (mid % 2 == 1) { //  If the index of the middle element is odd, subtract 1 to make it even.
                mid--;
            }
            // If the middle element is not equal to its right neighbor, the single element must be on the left side
            // of the array, so update the right pointer to be the current middle index.
            if (nums[mid] != nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 2; // Otherwise, the single element must be on the right side of the array,
                                // so update the left pointer to be the middle index plus 2.
            }
        }
        return nums[left];
    }
}
