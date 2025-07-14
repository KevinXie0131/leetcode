package com.answer.hashmap;

import java.util.*;

public class Q18_4Sum {
    /**
     * 四数之和
     * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
     *  0 <= a, b, c, d < n
     *  a、b、c 和 d 互不相同
     *  nums[a] + nums[b] + nums[c] + nums[d] == target
     * 你可以按 任意顺序 返回答案 。
     * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
     *  0 <= a, b, c, d < n
     *  a, b, c, and d are distinct.
     *  nums[a] + nums[b] + nums[c] + nums[d] == target
     * You may return the answer in any order.
     * 示例
     *  输入：nums = [1,0,-1,0,-2,2], target = 0
     *  输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
     */
    public static void main(String[] args) {
        int[] nums = {1,0,-1,0,-2,2};
        int target = 0;
        List<List<Integer>>  res = fourSum(nums, target);
        System.out.println(res);
    }
    /**
     * 排序 + 双指针
     * 推荐使用双指针法
     * 四数之和的双指针解法是两层for循环nums[k] + nums[i]为确定值，依然是循环内有left和right下标作为双指针，
     * 找出nums[k] + nums[i] + nums[left] + nums[right] == target的情况，
     * 三数之和的时间复杂度是O(n^2)，四数之和的时间复杂度是O(n^3) 。
     *
     * 对于15.三数之和 双指针法就是将原本暴力O(n^3)的解法，降为O(n^2)的解法，
     * 四数之和的双指针解法就是将原本暴力O(n^4)的解法，降为O(n^3)的解法。
     *
     * 本题思路与 Q15. 三数之和 相同，只需增加一层循环，枚举前两个数字的值，双指针确定后两个数字的值即可。
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>(); // 结果集
        int n = nums.length;
        if(n < 4){
            return result;
        }

        Arrays.sort(nums); // 排序数组

        for(int i = 0; i < n - 3; i++){
            if (nums[i] > target && nums[i] >= 0) { // 剪枝处理
                break;
            }
            if(i > 0 && nums[i] == nums[i - 1]){ // 对nums[i]去重
                continue;
            }

            // 如果剩余四元组的最小元素和 > target，退出即可
            if ((long)nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            // 如果固定此 i 时，四元组的最大元素和 < target，跳到下一个 i
            if ((long)nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3] < target) continue;

            for(int j = i + 1; j < n - 2;j++){
                if (nums[j] + nums[i] > target && nums[j] + nums[i] >= 0) { // 第二级剪枝
                    break;
                }
                if(j > i + 1 && nums[j] == nums[j - 1]){ // 对nums[j]去重
                    continue;
                }
                // 相加时用 Int 是会溢出的，在这些语言中我们在求 nums[i]+nums[j]+nums[l]+nums[r] 时要先转换为 long
                // 固定此 i 并限定最小 j 时，如果剩余四元组的最小元素和 > target，跳到下一个 i
                if ((long)nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                // 固定此 i j 时，如果四元组的最大元素和 < target，跳到下一个 j
                if ((long)nums[i] + nums[j] + nums[n - 1] + nums[n - 2] < target) continue;

                int left = j + 1;
                int right = n - 1;
                while(left < right){
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if(sum < target){
                        left++;
                    } else if(sum > target){
                        right--;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        while(left < right && nums[left] == nums[left + 1]){   // 对nums[left]和nums[right]去重
                            left++;
                        }
                        while(left < right && nums[right] == nums[right - 1]){
                            right--;
                        }
                        left++;
                        right--;
                    }
                }
            }
        }
        return result;
    }
}
