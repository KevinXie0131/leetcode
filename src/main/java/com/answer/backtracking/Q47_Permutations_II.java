package com.answer.backtracking;

import java.util.ArrayDeque;
import java.util.*;
import java.util.Deque;
import java.util.List;

public class Q47_Permutations_II {
    /**
     * 这道题⽬和回溯算法：排列问题！的区别在与给定⼀个可包含重复数字的序列，要返回所有不重复的全排列。
     * 这⾥又涉及到去重了
     * 是去重⼀定要对元素经⾏排序，这样我们才⽅便通过相邻的节点来判断是否重复使⽤了。
     *
     * ⼀般来说：组合问题和排列问题是在树形结构的叶⼦节点上收集结果，⽽⼦集问题就是取树上所有节点的结果
     * 时间复杂度:  O(n! * n) 最差情况所有元素都是唯一的。复杂度和全排列1都是 O(n! * n) 对于 n 个元素一共有 n! 中排列方案。而对于每一个答案，我们需要 O(n) 去复制最终放到 result 数组
     * 空间复杂度: O(n) 回溯树的深度取决于我们有多少个元素
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new ArrayDeque<>();
        int[] used = new int[nums.length];

        Arrays.sort(nums); // 排序

        backtracking(nums, used, result, path);
        return result;
    }

    public void backtracking(int[] nums, int[] used, List<List<Integer>> result, Deque<Integer> path) {
        if (path.size() == nums.length) { // 此时说明找到了⼀组
            result.add(new ArrayList<Integer>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 1) {
                continue;
            }
            // used[i - 1] == true，说明同⼀树⽀nums[i - 1]使⽤过
            // used[i - 1] == false，说明同⼀树层nums[i - 1]使⽤过
            // 如果同⼀树层nums[i - 1]使⽤过则直接跳过
            if(i> 0 && nums[i] == nums[i-1] && used[i-1] == 0) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = 1;
            backtracking(nums, used, result, path);
            path.removeLast();
            used[i] = 0;
        }
    }
    /**
     * 如果改成 used[i - 1] == 1， 也是正确的
     * 如果要对树层中前一位去重，就用used[i - 1] == 0，
     * 如果要对树枝前一位去重, 用used[i - 1] == 1。
     * 对于排列问题，树层上去重和树枝上去重，都是可以的，但是树层上去重效率更高！
     *
     * 树层上对前一位去重非常彻底，效率很高，树枝上对前一位去重虽然最后可以得到答案，但是做了很多无用搜索
     */
/*     if(i> 0 && nums[i] == nums[i-1] && used[i-1] == 1) { // 因为是排列，从0开始，所以可以变换
        continue;
    }*/

    /**
     * 另一种形式
     */
/*      for (int i = 0; i < nums.length; i++) {
        if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
            continue;
        }
        //如果同⼀树⽀nums[i]没使⽤过开始处理
        if (used[i] == false) {
            used[i] = true;//标记同⼀树⽀nums[i]使⽤过，防止同一树枝重复使用
            path.add(nums[i]);
            backTrack(nums, used);
            path.remove(path.size() - 1);//回溯，说明同⼀树层nums[i]使⽤过，防止下一树层重复
            used[i] = false;//回溯
        }*/
    }
}
