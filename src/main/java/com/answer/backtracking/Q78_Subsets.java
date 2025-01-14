package com.answer.backtracking;

import java.util.*;

public class Q78_Subsets {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(subsets(nums));
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

    static public void backtracking(int[] nums, int startIndex) {
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
}
