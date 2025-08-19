package com.answer.hashmap;

import java.util.*;

public class Q268_Missing_Number {
    /**
     * 丢失的数字
     * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
     * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
     * 示例 1：
     * 输入：nums = [3,0,1]
     * 输出：2
     * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
     * 示例 2：
     * 输入：nums = [0,1]
     * 输出：2
     * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
     * 示例 3：
     * 输入：nums = [9,6,4,2,3,5,7,0,1]
     * 输出：8
     * 解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
     */
    public static void main(String[] args) {
        int[] nums = {3,0,1};
        missingNumber_5(nums);
    }
    /**
     * 数组哈希
     * 利用 nums 的数值范围为 [0,n]，且只有一个值缺失，我们可以直接开一个大小为 n+1 的数组充当哈希表，进行计数，没被统计到的数值即是答案。
     */
    public int missingNumber0(int[] nums) {
        int n = nums.length;
        boolean[] hash = new boolean[n + 1];
        for (int i = 0; i <= n - 1; i++) {
            hash[nums[i]] = true;
        }
        for (int i = 0; i <= n; i++) {
            if (!hash[i]) {
                return i;
            }
        }
        return n;
    }
    /**
     * 原地哈希
     * 可以将 nums 本身作为哈希表进行使用，将 nums[i] 放到其应该出现的位置(下标)nums[i]上(nums[i]<n)
     */
    static public int missingNumber5(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i && nums[i] <= n - 1) { // 将数组的每个元素放在对应下标的位置，比如元素3放在下标为3的位置, 如果元素等于n(数组长度)，则跳过它。
                swap(nums, nums[i], i);
                i--;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i != nums[i]) { // 再遍历一遍数组，如果当前下标i放的元素不是i，说明答案就是i；遍历完后还没找到答案的话，那么n就是答案。
                return i;
            }
        }
        return n;
    }

    static void swap(int[] nums, int i, int j) {
        int c = nums[i];
        nums[i] = nums[j];
        nums[j] = c;
    }
    /**
     * 同上
     * refer to Q287_Find_the_Duplicate_Number
     */
   static public int missingNumber_5(int[] nums) {
        int n = nums.length;
        int j = 0;
        while(j < nums.length) {
            if(nums[j] == j) {
                j++;
                continue;
            }
            if(nums[j] <= n - 1) {
                int tmp = nums[j];
                nums[j] = nums[tmp];
                nums[tmp] = tmp;
            } else {
                j++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i != nums[i]) {
                return i;
            }
        }
        return n;
    }
    /**
     * HashSet 哈希集合
     * 使用哈希集合，可以将时间复杂度降低到 O(n)。
     */
    public int missingNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int len = nums.length;
        for(int i = 0; i <= len; i++){
            set.add(i);
        }
        for(int n : nums){
            set.remove(n);
        }
        return set.iterator().next();
    }
    /**
     * 哈希集合 HashSet 同上
     */
    public int missingNumber_1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int len = nums.length;

        for(int n : nums){
            set.add(n);
        }
        for(int i = 0; i <= len; i++){
            if(!set.contains(i)){
                return i;
            }
        }
        return -1;
    }
    /**
     * Sorting 排序法
     */
    public int missingNumber_2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return n; // 输出最后一个值
    }
    /**
     * Approach #3 Bit Manipulation: XOR 位运算
     * 数组 nums 中有 n 个数，在这 n 个数的后面添加从 0 到 n 的每个整数，则添加了 n+1 个整数，共有 2n+1 个整数。
     * 由于上述 2n+1 个整数中，丢失的数字出现了一次，其余的数字都出现了两次，因此对上述 2n+1 个整数进行按位异或运算，结果即为丢失的数字。
     */
    public int missingNumber_3(int[] nums) {
        int x = 0;
        for (int n : nums) {
            x ^= n; // 按位异或运算
        }
        for (int i = 0; i <= nums.length; i++) {
            x ^= i;
        }
        return x;
    }
    /**
     * another form 异或
     * 这个数组添加从0~n的n+1个元素，就变成了数组中只有一个数出现了一次，其他数字都出现了2次，让我们求这个只出现一次的数字
     */
    public int missingNumber6(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans ^= nums[i] ^ i;
        }
        return ans ^ n;
    }
    /**
     * 二分法查找
     */
    public int missingNumber7(int[] nums) {
        Arrays.sort(nums);
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == mid) {
                // 如果nums[mid] == mid也就是说当前元素的下标等于他自己，
                // 比如数组[0,1,2,3,4,5]每个元素的下标都等于他自己，说明[start,mid]
                // 没有缺少任何数字，那么缺少的肯定是在[mid+1,end]
                start = mid + 1;
            } else {
                //注意这里写法和上面代码不一样
                end = mid - 1;
            }
        }
        return start;
    }
    /**
     * 同上 二分法
     */
    public int missingNumber7a(int[] nums) {
        Arrays.sort(nums);
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            // {0, 1, 2, 3, 4, 5, 8, 9} mid = 4
            // {0, 1, 2, 3, 4, 5, 8, 9, 10} mid = 5
            // {0, 2, 3, 4, 5, 6, 7, 8} mid = 4
            if (nums[mid] == mid) { // missing number is in [mid + 1, end]
                start = mid + 1;
            }
            else if (nums[mid] > mid) { // missing number is in [start, mid - 1]
                end = mid - 1;
            }
        //    else if (nums[mid] < mid) {  // no need to consider
        //        end = mid - 1;
       //     }
        }
        return start;
    }
    /**
     * Approach #4 Gauss' Formula 数学
     */
    public int missingNumber_4(int[] nums) {
        int n = nums.length;
        int total = n * (n + 1) / 2; // 求和公式
        int arrSum = 0;
        for (int i = 0; i < n; i++) {
            arrSum += nums[i];
        }
        return total - arrSum;
    }
}
