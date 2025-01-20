package com.answer.backtracking;

import java.util.*;

public class Q90_Subsets_II {
    public static void main(String[] args) {
        int[] nums = {1,2,2};
        subsetsWithDup(nums);
    }
    /**
     * 这道题目和78.子集  区别就是集合里有重复元素了，而且求取的子集要去重。
     * 注意去重需要先对集合排序
     */
    static List<List<Integer>> result = new ArrayList<List<Integer>>();
    static Deque<Integer> path = new LinkedList<>();
    static int[] used;

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        if(nums.length == 0){
            result.add(new ArrayList());
            return result;
        }
        int[]  used = new int[nums.length]; // 定义set对同⼀节点下的本层去重
        used = new int[nums.length];
        backtracking(nums, 0, used);  // 没有排序
        return result;
    }

    static public void backtracking(int[] nums, int startIndex, int[] used){
        result.add(new ArrayList(path));
        if(startIndex == nums.length){
            return;
        }
        for(int i = startIndex; i < nums.length; i++){
            int j = i;
            boolean isFoundSame = false; // 没有排序 所以需要这样做
            while (j > 0) {
                if (nums[i] == nums[j - 1] && used[j-1] == 0) { //used[j-1] 没有使用过
                    isFoundSame = true;
                    break; // 因为在while里面，所以需要break
                }
                j--;
            }
            if(isFoundSame){
                continue;
            }
            path.add(nums[i]);
            used[i] = 1;
            backtracking(nums, i + 1, used);
            used[i] = 0;
            path.removeLast();
        }
    }
    /**
     * 扩展法（暴力求解）
     * 需要排序和去重
     */
    public List<List<Integer>> subsetsWithDup1(int[] nums) {
        HashSet<List<Integer>> set= new HashSet<>();
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>());

        Arrays.sort(nums); //排序. 如果不排序，如果输入为[2,1,2]，会产生[2,1]和[1,2]

        for (Integer n : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newSub = new ArrayList<Integer>(res.get(i));
                newSub.add(n);
                if(set.add(newSub)){ //HashSet去重
                    res.add(newSub);
                }
            }
        }
        return res;
/*      HashSet<List<Integer>> set= new HashSet<>(res); // 这样也可以去重
        return  new ArrayList<List<Integer>>(set);   */
    }
    /**
     * DFS 方法二：递归法实现子集枚举
     * 在递归时，若发现没有选择上一个数，且当前数字与上一个数相同，则可以跳过当前生成的子集。
     */
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        ArrayList<Integer> subset = new ArrayList<Integer>();
        Arrays.sort(nums); // 数组排序

        inOrder(false,nums, 0, subset, res);
        return res;
    }

    public void inOrder(boolean choosePre,int[] nums, int i, ArrayList<Integer> subset, List<List<Integer>> res) {
        subset = new ArrayList<>(subset);

        if (i == nums.length) {
            res.add(subset);
            return;
        }
        inOrder(false, nums, i + 1, subset, res); //没有选择
        if (!choosePre && i > 0 && nums[i] == nums[i - 1]) {
            return; // 若发现没有选择上一个数，且当前数字与上一个数相同，则可以跳过当前生成的子集
        }
        subset.add(nums[i]);
        inOrder(true, nums, i + 1, subset, res);  //选择
    }
}
