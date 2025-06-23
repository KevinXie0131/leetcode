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
     * Since every element in the sorted array appears exactly twice except for the single element,
     * we know that if we take any element at an even index (0-indexed), the next element should be the same.
     * Similarly, if we take any element at an odd index, the previous element should be the same.
     * Therefore, we can use binary search to compare the middle element with its adjacent elements to determine
     * which side of the array the single element is on.
     *
     * Time complexity: O(logn) Space complexity: O(1)
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
    /**
     * we can observe that for each pair: first element takes even position and second element takes odd position
     * this pattern will be missed when single element is appeared in the array.
     */
    static public int singleNonDuplicate1(int[] nums) {
        int left = 0, right = nums.length-1;
        while(left < right){
            int mid = (left + right)/2;
            // if mid is even, then it's duplicate should be in next index.
            //	or if mid is odd, then it's duplicate  should be in previous index.
            if( (mid % 2 == 0 && nums[mid] == nums[mid +1])
                    || (mid %2 == 1 && nums[mid] == nums[mid - 1]) ) {
                left = mid + 1; // if any of the conditions is satisfied,  then pattern is not missed, so check in next half of the array
            } else {
                right = mid; // if condition is not satisfied, then the pattern is missed. so, single number must be before mid.
            }
        }
        return nums[left];
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
            if (nums[mid] == nums[mid ^ 1]) { //不需要判断 mid 的奇偶性，mid 和 mid⊕1 即为每次需要比较元素的两个下标。
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }
}
