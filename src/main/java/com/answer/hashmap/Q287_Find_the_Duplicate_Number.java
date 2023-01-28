package com.answer.hashmap;

import java.util.*;

public class Q287_Find_the_Duplicate_Number {
    public static void main(String[] args) {
        int[] nums = {1,3,4,2,2};
        int res = findDuplicate_index_sort(nums);
        System.out.println(res);
    }
    /**
     * Sorting
     * Time: NlogN + N
     * Space: logN
     */
    public int findDuplicate_1(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        return len;
    }
    /**
     * Use array as counter
     * Time: N
     * Space: N
     */
    public int findDuplicate_2(int[] nums) {
        int len = nums.length;
        int[] cnt = new int[len];
        for (int i = 0; i < len; i++) {
            cnt[nums[i]]++;
            if (cnt[nums[i]] > 1) {
                return nums[i];
            }
        }
        return len;
    }
    /**
     * Use HashSet
     * Time: N
     * Space: N
     */
    public int findDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (!set.add(n)) {
                return n;
            }
        }
        return -1;
    }
    /**
     * Marked and modifying the array
     * Time: N
     * Space: 1
     */
    public static int findDuplicate_mark(int[] nums) {
        int len = nums.length;
        for (int num : nums) {
            int idx = Math.abs(num);
            if (nums[idx] < 0) {
                return idx;
            }
            nums[idx] = -nums[idx];
        }
        return len;
    }
    /**
     * Index Sort and modifying the array
     * Time: N
     * Space: 1
     */
    public static int findDuplicate_index_sort(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; ) {
            int n = nums[i];
            if (n == i + 1) {
                i++;
            } else if (n == nums[n - 1]) {
                return n;
            } else {
                nums[i] = nums[n - 1];
                nums[n - 1] = n;
            }
        }
        return 0;
    }
    /**
     * Binary Search
     * Time: NlogN
     * Space: 1
     */
    public static int findDuplicate_bs(int[] nums) {
        int len = nums.length;
        int low = 0;
        int high = len - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int cnt = 0;
            for (int i = 0; i < len; i++) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }

            if (cnt <= mid) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
    /**
     * Slow Fast Pointers
     * Time: N
     * Space: 1
     */
    public static int findDuplicate_fastSlow(int[] nums) {
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
