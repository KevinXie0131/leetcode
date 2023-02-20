package com.answer.binarysearch;

public class Q702_Search_in_a_Sorted_Array_of_Unknown_Size {
    /**
     * Approach 1: Binary Search
     * 1. Define search limits, i.e. left and right boundaries for the search.
     * 2. Perform binary search in the defined boundaries.
     *
     * Left shift: x << 1. The same as multiplying by 2
     * Right shift: x >> 1. The same as dividing by 2
     */
    public int search(ArrayReader reader, int target) {
        if (reader.get(0) == target) return 0;
        // search boundaries

        int left = 0, right = 1;
        while (reader.get(right) < target) {
            left = right; // Move the left boundary to the right
            right <<= 1; // Extend the right boundary: right = right * 2;
        }

        // binary search
        int pivot, num;
        while (left <= right) {
            pivot = left + ((right - left) >> 1);
            num = reader.get(pivot);

            if (num == target) return pivot;
            if (num > target) right = pivot - 1;
            else left = pivot + 1;
        }

        // there is no target element
        return -1;
    }
    /**
     * Binary Search
     */
    public int search_1(ArrayReader reader, int target) {
        int left = 0, right = 20000;
        while (left <= right) {
            int mid = (left + right) >> 1;
            int midVal = reader.get(mid);
            if (midVal > target) { // 越界或者非越界情况下，继续二分查找左半部分
                right = mid - 1;
            } else if (midVal == target) { // 相等返回
                return mid;
            } else { // 当前值大于目标值，继续二分查找右半部分
                left = mid + 1;
            }
        }
        return -1;
    }
}

class ArrayReader {
   public int get(int index) {
       return -1;
   }
}