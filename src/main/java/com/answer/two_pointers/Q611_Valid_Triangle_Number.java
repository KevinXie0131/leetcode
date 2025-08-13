package com.answer.two_pointers;

import java.util.Arrays;

public class Q611_Valid_Triangle_Number {
    /**
     * 有效三角形的个数
     * 给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组triplets个数。
     * 示例 1:
     * 输入: nums = [2,2,3,4]
     * 输出: 3
     * 解释:有效的组合是: 2,3,4 (使用第一个 2)
     *                  2,3,4 (使用第二个 2)
     *                  2,2,3
     */
    /**
     * 计算有多少个三元组可以作为三角形的三条边，即任意两边之和大于第三边。
     * 双指针 解题关键点
     *  三角形两边之和必须大于第三边
     *  数组先排序，利用排序性质简化判断
     *  使用双指针技巧降低复杂度至O(n^2)
     */
    public int triangleNumber(int[] nums) {
        int count = 0;
        Arrays.sort(nums); // 先排序
        // i 指向最长边，从第三个元素开始
        for(int i = nums.length - 1; i >= 2; i--){
            int left = 0, right = i - 1;
            while(left < right){
                if(nums[left] + nums[right] > nums[i]){
                    count += right - left;  // nums[left..right-1]都能和nums[right], nums[i]组成三角形
                    right--;
                } else {
                  left++;
                }
            }
        }
        return count;
    }
    /**
     * another form
     */
    public int triangleNumber0(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);  //首先对其进行排序，找到各个数之间的大小关系
        int ans = 0;
        for (int i = 2; i < n; i++) {
            int left = 0;
            int right = i - 1;
            while (left < right){
                // 如果左右两边之和大于最长边，那么从left到right-1的所有数都可以和nums[right]及nums[i]组成三角形
                if(nums[left] + nums[right] <= nums[i]){
                    left++;  // 尝试更大的左边值
                }else {
                    ans += right - left;
                    right--; // 尝试更小的右边值
                }
            }
        }
        return ans;
    }
    /**
     * 二分查找
     * 首先对数组排序。
     * 固定最短的两条边，二分查找最后一个小于两边之和的位置。可以求得固定两条边长之和满足条件的结果。枚举结束后，总和就是答案。
     */
    public int triangleNumber1(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n - 2; ++i) {
            for (int j = i + 1; j < n - 1; ++j) {
                int sum = nums[i] + nums[j];
                int left = j + 1, right = n - 1;
                while (left <= right) {
                    int mid = (left + right) >>> 1;
                    if (nums[mid] < sum) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                // 找出最大的满足 nums[right]<nums[i]+nums[j] 的下标 right，这样一来，在 [right+1,k] 范围内的下标都可以作为边 c 的下标，
                // 我们将该范围的长度 right−j 累加入答案。
                if (nums[right] < sum) {
                    res += right - j;
                }
            }
        }
        return res;
    }
    /**
     * another form
     */
    public int triangleNumber3(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n-2; ++i) {
            for (int j = i + 1; j < n-1; ++j) {
                // 若二分查找失败，我们可以令 found=j，此时对应的范围长度 found−j=0，我们也就保证了答案的正确性。
                int left = j + 1, right = n - 1, found = j;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (nums[mid] < nums[i] + nums[j]) {
                        found = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                ans += found - j;
                //  ans += (left - 1) - j; // works too
                //  ans += right - j; // works too
            }
        }
        return ans;
    }
    /**
     * 排序 + 暴力枚举
     */
    public int triangleNumber4(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 2; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    if (nums[j] + nums[k] > nums[i]) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
}
