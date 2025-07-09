package com.answer.hashmap;

import java.util.*;
import java.util.stream.Collectors;

public class Q15_3Sum {
    /**
     * 三数之和
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
     * Notice that the solution set must not contain duplicate triplets.
     *
     * 示例 1：
     *  输入：nums = [-1,0,1,2,-1,-4]
     *  输出：[[-1,-1,2],[-1,0,1]]
     *  解释：nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
     *       nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
     *       nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
     *       不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
     *       注意，输出的顺序和三元组的顺序并不重要。
     */
    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> list = threeSum_1(nums);
        System.out.println(list);
    }
    /**
     * 双指针法，这道题目使用双指针法 要比哈希法高效一些 (其实这道题目使用哈希法并不十分合适，因为在去重的操作中有很多细节需要注意)
     * 时间复杂度: O(n^2)
     * 空间复杂度: O(1)
     * 由于需要求三个数字的和，遍历复杂度不会小于O(n^2)，因此可以先对数组排序，这样方便之后处理。
     * 整体思路为，通过遍历数组选取第一个数字，在遍历的同时用前后双指针寻找满足条件的数字即可
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int n = nums.length;
        if(n<3) {
            return result;
        }
        Arrays.sort(nums); // 首先将数组排序
        // 找出a + b + c = 0
        // a = nums[i], b = nums[left], c = nums[right]
        for(int i = 0; i < n; i++){
            if(nums[i] > 0)  { // 排序之后如果第一个元素已经大于零，那么无论如何组合都不可能凑成三元组，直接返回结果就可以了
                return result;
            }
            // 错误去重a方法，将会漏掉-1,-1,2 这种情况
            // 那我们就把 三元组中出现重复元素的情况直接pass掉了。 例如{-1, -1 ,2} 这组数据，当遍历到第一个-1 的时候，判断 下一个也是-1，
            // 那这组数据就pass了。
            /* if (nums[i] == nums[i + 1]) {
                  continue;
            }*/
            // 正确去重a方法 考虑-1,-1,2 这种情况.
            // 我们要做的是 不能有重复的三元组，但三元组内的元素是可以重复的！
            // 这么写就是当前使用 nums[i]，我们判断前一位是不是一样的元素，在看 {-1, -1 ,2} 这组数据，当遍历到 第一个 -1 的时候，
            // 只要前一位没有-1，那么 {-1, -1 ,2} 这组数据一样可以收录到 结果集里。
            if(i > 0 && i < n-1 && nums[i] == nums[i-1]  ){
                continue;
            }
            int left = i + 1;
            int right = n - 1;
            while(left < right){
                if(nums[i] + nums[left] + nums[right] == 0){
                    int[] res = new int[]{nums[i],nums[left],nums[right]};
                 //   result.add(Arrays.stream(res).boxed().collect(Collectors.toList()));
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 去重逻辑应该放在找到一个三元组之后，对b 和 c去重
                    while(left<right && nums[left]==nums[left+1]){
                        left++;  // 去重 left
                    }
                    while(left<right && nums[right]==nums[right-1]){
                        right--; // 去重 right
                    }
                    left++; // 找到答案时，双指针同时收缩
                    right--;
                }else if(nums[i] + nums[left] + nums[right] < 0){
                    left++; // 如果 nums[i] + nums[left] + nums[right] < 0 说明 此时 三数之和小了，left 就向右移动
                }else{
                    right--;// 如果nums[i] + nums[left] + nums[right] > 0 就说明 此时三数之和大了，right下标就应该向左移动
                }
            }
        }
        return result;
    }
    /**
     * 使用哈希集合
     */
    public static List<List<Integer>> threeSum_1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // 如果第一个元素大于零，不可能凑成三元组
            if (nums[i] > 0) {
                return result;
            }
            // 三元组元素a去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            HashSet<Integer> set = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                // 三元组元素b去重
                if (j > i + 2 && nums[j] == nums[j - 1] && nums[j - 1] == nums[j - 2]) {
                    continue;
                }

                int c = -nums[i] - nums[j];
                if (set.contains(c)) {
                    result.add(Arrays.asList(nums[i], nums[j], c));
                    set.remove(c); // 三元组元素c去重
                } else {
                    set.add(nums[j]);
                }
            }
        }
        return result;

    }
}
