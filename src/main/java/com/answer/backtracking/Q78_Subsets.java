package com.answer.backtracking;

import java.util.*;

public class Q78_Subsets {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(subsets_4(nums));
    }
    /**
     * 求子集问题和77.组合  和131.分割回文串 又不一样了。
     * 如果把 子集问题、组合问题、分割问题都抽象为一棵树的话，那么组合问题和分割问题都是收集树的叶子节点，而子集问题是找树的所有节点！
     * 其实子集也是一种组合问题，因为它的集合是无序的，子集{1,2} 和 子集{2,1}是一样的。
     * 那么既然是无序，取过的元素不会重复取，写回溯算法的时候，for就要从startIndex开始，而不是从0开始！
     *
     * 子集是收集树形结构中树的所有节点的结果, 而组合问题、分割问题是收集树形结构中叶子节点的结果
     * 求取⼦集问题，不需要任何剪枝！因为⼦集就是要遍历整棵树。
     */
    static List<List<Integer>> result = new ArrayList<List<Integer>>();
    static Deque<Integer> path = new LinkedList<>();
    // 时间复杂度: O(n * 2^n)
    // 空间复杂度: O(n)
    static public List<List<Integer>> subsets(int[] nums) {
        backtracking(nums,0);
        return result;
    }
    // Backtracking 回溯
    static public void backtracking(int[] nums, int startIndex) {
        // 防止引用传递
        result.add(new ArrayList(path)); // 收集⼦集，要放在终⽌添加的上⾯，否则会漏掉⾃⼰ (遍历这个树的时候，把所有节点都记录下来，就是要求的子集集合)
        /**
         * if(startIndex > nums.length - 1){
         */
        if(startIndex == nums.length ){ // 递归终止条件: startIndex已经大于数组的长度了，就终止了，因为没有元素可取了
            return;                     // 其实可以不需要加终止条件，因为startIndex >= nums.size()，本层for循环本来也结束了
        }

        for(int i = startIndex; i < nums.length; i++){
            path.add(nums[i]);                   // 子集收集元素
            backtracking(nums, i + 1); // 注意从i+1开始，元素不重复取
            path.removeLast();                   // 回溯
        }
    }
    /**
     * 思路二：循环枚举 / 扩展法
     * 逐个枚举，空集的幂集只有空集，每增加一个元素，让之前幂集中的每个集合，追加这个元素，就是新增的子集。
     */
    public static List<List<Integer>> subsets_2(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        for (Integer n : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newSub = new ArrayList<Integer>(res.get(i)); // 防止引用传递
                newSub.add(n);
                res.add(newSub);
                System.out.println(newSub);
            }
        }
        return res;
    }
    /**
     * 思路三：DFS (一条路走到底)
     * 集合中每个元素的选和不选，构成了一个满二叉状态树，比如，左子树是不选，右子树是选，从根节点、到叶子节点的所有路径，构成了所有子集。
     * 可以有前序、中序、后序的不同写法，结果的顺序不一样。本质上，其实是比较完整的中序遍历。
     */
    public static List<List<Integer>> subsets_3(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        ArrayList<Integer> subset = new ArrayList<Integer>();
        inOrder(nums, 0, subset, res);
        return res;

    }
    // 中序遍历
    public static  void inOrder(int[] nums, int i, ArrayList<Integer> subset, List<List<Integer>> res) {
        subset = new ArrayList<>(subset);
        System.out.println(subset);

        if (i == nums.length) { // 一直走到底
            System.out.println("-> " + subset);
            res.add(subset);
            return;
        }
        inOrder(nums, i + 1, subset, res);
        subset.add(nums[i]);
        inOrder(nums, i + 1, subset, res);
    }
    /**
     * 二进制迭代法实现子集枚举
     * 求子集，对于集合中的元素，要么出现在子集中，要么不出现在子集中。因此子集的个数正好是2^n个，
     * 而且对于这n个元素，出现用1表示，不出现用0表示，子集正好对应0~2^n-1的二进制。
     * 比如[1,2]，子集有四个，分别是[]（空集）,[1],[2],[1,2]。
     * 用二进制表
     * []（空集）   就是00
     * [1]         就是01
     * [2]         就是10
     * [1,2]       就是11
     * 这样就可以把所有子集求出来了。
     */
    public static List<List<Integer>> subsets_4(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int n = nums.length;

        for(int mask = 0; mask < (1 << n); mask++){
            ArrayList<Integer> subset = new ArrayList<Integer>();
            for(int i = 0; i < n; i++){
                if((mask & (1 << i)) > 0){
                    subset.add(nums[i]);
                }
            }
            res.add(subset);
        }

        return res;
    }
}
