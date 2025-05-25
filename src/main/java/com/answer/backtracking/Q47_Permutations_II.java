package com.answer.backtracking;

import java.util.ArrayDeque;
import java.util.*;
import java.util.Deque;
import java.util.List;

public class Q47_Permutations_II {
    /**
     * Given a collection of numbers, nums, that might contain duplicates,
     * return all possible unique permutations in any order.
     * 全排列 II: 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     * 示例 1：
     *  输入：nums = [1,1,2]
     *  输出：
     *      [[1,1,2],
     *      [1,2,1],
     *      [2,1,1]]
     */
    /**
     * 这道题⽬和回溯算法：排列问题 的区别在与给定⼀个可包含重复数字的序列，要返回所有不重复的全排列。
     * 这⾥又涉及到去重了
     * 是去重⼀定要对元素经⾏排序，这样我们才⽅便通过相邻的节点来判断是否重复使⽤了。
     *
     * ⼀般来说：组合问题和排列问题是在树形结构的叶⼦节点上收集结果，⽽⼦集问题就是取树上所有节点的结果
     * 时间复杂度:  O(n! * n) 最差情况所有元素都是唯一的。复杂度和全排列1都是 O(n! * n) 对于 n 个元素一共有 n! 中排列方案。而对于每一个答案，我们需要 O(n) 去复制最终放到 result 数组
     * 空间复杂度: O(n) 回溯树的深度取决于我们有多少个元素
     *
     * 本题与 Q46. 全排列 类似，要去除不重复排列，只需要保证重复元素的相对顺序不变，即按序使用重复元素。
     * 因此，在循环时增加判断前一个相等元素是否被使用，如果未被使用说明是乱序，跳过即可。
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new ArrayDeque<>();
        int[] used = new int[nums.length];

        Arrays.sort(nums); // 排序

        backtracking(nums, used, result, path);
        return result;
    }
    // 与Q46 Permutations相比，有重复元素，所以需要去重
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
            if(i> 0 && nums[i] == nums[i-1] && used[i-1] == 0) { //如果与上一个数值相同，但是上一个数值没有使用过，则跳过
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

    /**
     * 使用set去重的版本 (相对于used数组的版本效率都要低很多)
     *
     * 如果使用set去重，空间复杂度就变成了O(n^2)，因为每一层递归都有一个set集合，系统栈空间是n，每一个空间都有set集合
     * used数组可是全局变量，每层与每层之间公用一个used数组，所以空间复杂度是O(n + n)，最终空间复杂度还是O(n)
     */
    private List<List<Integer>> res1 = new ArrayList<>();
    private List<Integer> path1 = new ArrayList<>();
    private boolean[] used1 = null;

    public List<List<Integer>> permuteUnique1_0(int[] nums) {
        used1 = new boolean[nums.length];
        Arrays.sort(nums);
        backtracking1(nums);
        return res1;
    }

    public void backtracking1(int[] nums) {
        if (path1.size() == nums.length) {
            res1.add(new ArrayList<>(path1));
            return;
        }
        HashSet<Integer> hashSet = new HashSet<>();//层去重
        for (int i = 0; i < nums.length; i++) {
            if (hashSet.contains(nums[i])) // 控制某一节点下的同一层元素不能重复
                continue;
            if (used1[i] == true)//枝去重
                continue;
            hashSet.add(nums[i]);//记录元素
            used1[i] = true;
            path1.add(nums[i]);
            backtracking1(nums);
            path1.remove(path1.size() - 1);
            used1[i] = false;
        }
    }
}
