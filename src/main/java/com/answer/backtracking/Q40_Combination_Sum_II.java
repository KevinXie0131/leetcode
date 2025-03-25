package com.answer.backtracking;

import java.util.*;

public class Q40_Combination_Sum_II {
    /**
     * 本题与 Q39. 组合总和 的区别在于需要对结果去重，同时每个元素只能选取一次。
     * 可以先将原数组排序，在搜索时通过判断重复元素的个数，那么对元素 c[i] 最多可以选个 k[i] 次，从而转换成上题的解法。
     */
    public static void main(String[] args) {
        int[]  candidates = {10,1,2,7,6,1,5}; int target = 8;
        combinationSum2_1(candidates, target);
        System.out.println(res);

    }
    /**
     * 时间复杂度: O(n * 2^n)
     * 空间复杂度: O(n)
     * 这道题目和39.组合总和 如下区别：
     *    本题candidates 中的每个数字在每个组合中只能使用一次。
     *    本题数组candidates的元素是有重复的，而39.组合总和  是无重复元素的数组candidates
     * 最后本题和39.组合总和  要求一样，解集不能包含重复的组合。
     *
     * 本题的难点在于区别2中：集合（数组candidates）有重复元素，但还不能有重复的组合。(我把所有组合求出来，再用set或者map去重，这么做很容易超时！)
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target)    {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new ArrayDeque<>();
        int[] used = new int[candidates.length]; // 加⼀个bool型数组used，⽤来记录同⼀树枝上的元素是否使⽤过
                                                 // 这个集合去重的重任就是used来完成的
        // ⾸先把给candidates排序，让其相同的元素都挨在⼀起
        Arrays.sort(candidates);
        backtracking(candidates, used, target, 0, result, path);
        return result;
    }
    // 使用标记数组
    public void backtracking(int[] candidates, int[] used, int target, int startIndex, List<List<Integer>> result, Deque<Integer> path ){
        if (0 == target) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        if(target < 0){
            return;
        }
  /*      for(int i = startIndex; i < candidates.length && target >= candidates[i] ; i++){*/ //剪枝操作
        for(int i = startIndex; i < candidates.length; i++){
            if(target < candidates[i]){ // 剪枝操作
                break;
            }
            /**
             * 去重
             * 要去重的是“同⼀树层上的使⽤过”，如果判断同⼀树层上元素（相同的元素）是否使⽤过了呢
             *
             * 如果candidates[i] == candidates[i - 1] 并且 used[i - 1] == false，就说明：前
             * ⼀个树枝，使⽤了candidates[i - 1]，也就是说同⼀树层使⽤过candidates[i - 1]
             *
             * 在candidates[i] == candidates[i - 1]相同的情况下：
             *      used[i - 1] == true，说明同⼀树⽀candidates[i - 1]使⽤过
             *      used[i - 1] == false，说明同⼀树层candidates[i - 1]使⽤过
             * 要对同⼀树层使⽤过的元素进⾏跳过
             */
            if(i > 0 && candidates[i-1] == candidates[i] && used[i-1] == 0){
                continue;
            }
            path.addLast(candidates[i]);
            target -= candidates[i];
            used[i] = 1;
            /**
             * 和39.组合总和的区别1：这⾥是i+1，每个数字在每个组合中只能使⽤⼀次
             */
            backtracking(candidates, used, target, i + 1, result, path);
            path.removeLast();
            target += candidates[i];
            used[i] = 0;
        }
        return;
    }
    /**
     * 不使用标记数组
     */
    static List<List<Integer>> res = new ArrayList<>();
    static LinkedList<Integer> path = new LinkedList<>();
    static int sum = 0;

    static public List<List<Integer>> combinationSum2_1( int[] candidates, int target ) {
        //为了将重复的数字都放到一起，所以先进行排序
        Arrays.sort( candidates );
        backTracking1( candidates, target, 0 );
        return res;
    }

    static private void backTracking1( int[] candidates, int target, int start ) {
        if ( sum == target ) {
            res.add( new ArrayList<>( path ) );
            return;
        }
        for ( int i = start; i < candidates.length && sum + candidates[i] <= target; i++ ) {
            //正确剔除重复解的办法
            //跳过同一树层使用过的元素
            if ( i > start && candidates[i] == candidates[i - 1] ) { // 与used数组相似，当前数值已经在过去的遍历中被考虑进去了，所以这次可以跳过
                continue;
            }

            sum += candidates[i];
            path.add( candidates[i] );
            // i+1 代表当前组内元素只选取一次
            backTracking1( candidates, target, i + 1 );

            int temp = path.getLast();
            sum -= temp;
            path.removeLast();
        }
    }
}
