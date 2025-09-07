package com.answer.backtracking;

import java.util.*;

public class BacktrackingTemplate2 {
    /**
     * 子集: 数组中的元素互不相同. 解集不能包含重复的子集
     *      子集的集合是无序的，子集{1,2}和子集{2,1}是一样的。既然是无序，取过的元素不会重复取，for就要从startIndex开始，而不是从0开始。
     *      求取⼦集问题，不需要任何剪枝！因为⼦集就是要遍历整棵树。
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new LinkedList<>();
        backtracking(nums,0, result, path);
        return result;
    }

    public void backtracking(int[] nums, int startIndex, List<List<Integer>> result, Deque<Integer> path) {
        // 收集⼦集，要放在终⽌添加的上⾯，否则会漏掉⾃⼰ (遍历这个树的时候，把所有节点都记录下来，就是要求的子集集合)
        result.add(new ArrayList(path));

        for(int i = startIndex; i < nums.length; i++){
            path.add(nums[i]);                                // 子集收集元素
            backtracking(nums, i + 1, result, path); // 注意从i + 1开始，元素不重复取
            path.removeLast();                                // 回溯
        }
    }
    /**
     * 子集: "abc" -> [, c, b, bc, a, ac, ab, abc]
     * refer to template
     */
    public List<String> subset(String s){
        List<String> res = new ArrayList<>();
        buildSubset(res, s, new StringBuilder(), 0);
        return res;
    }

    private void buildSubset(List<String> res, String s, StringBuilder sb, int index){
        if(index == s.length()){
            res.add(sb.toString());
            return;
        }
        // 不选
        buildSubset(res, s, sb, index + 1);
        // 选
        sb.append(s.charAt(index));
        buildSubset(res, s, sb, index + 1);
        sb.deleteCharAt(sb.length() - 1);
    //  buildSubset(res, s, new StringBuilder(sb.toString() + s.charAt(index)), index + 1); // works too
    }
    /**
     * 子集 II: 可能包含重复元素. 解集不能包含重复的子集
     *          集合里有重复元素了，而且求取的子集要去重
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new LinkedList<>();
        boolean[] used = new boolean[nums.length]; // 对同⼀节点下的本层去重
        Arrays.sort(nums); // 去重需要排序
        backtracking(nums, 0, used, result, path);
        return result;
    }
    /**
     * 子集 II: 使用used数组来去重
     */
    public void backtracking(int[] nums, int startIndex, boolean[] used,  List<List<Integer>> result,  Deque<Integer> path){
        result.add(new ArrayList(path));

        for(int i = startIndex; i < nums.length; i++){
            if(i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false){ // 对同一树层的元素去重
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            backtracking(nums, i + 1, used, result, path);
            used[i] = false;
            path.removeLast();
        }
    }
    /**
     * 子集 II: 没有使用used数组作标记, 跳过当前树层使用过的、相同的元素
     */
    public void backtracking1(int[] nums, int startIndex, List<List<Integer>> result,  Deque<Integer> path  ){
        result.add(new ArrayList(path));

        for(int i = startIndex; i < nums.length; i++){
            if (i > startIndex && nums[i] == nums[i - 1]) { // 注意这里使用i > startIndex
                continue;
            }
            path.add(nums[i]);
            backtracking1(nums, i + 1, result, path);
            path.removeLast();
        }
    }
    /**
     * 子集 II: 使用HashSet本层去重
     */
    public void backtracking2(int[] nums, int startIndex, List<List<Integer>> result,  Deque<Integer> path  ){
        result.add(new ArrayList(path));

        HashSet<Integer> uset = new HashSet<>();  // 使用HashSet本层去重
        for(int i = startIndex; i < nums.length; i++){
            if(uset.contains(nums[i])){  // 如果发现出现过就pass
                continue;
            }
            uset.add(nums[i]); // set更新元素

            path.add(nums[i]);
            backtracking2(nums, i + 1, result, path);
            path.removeLast();
        }
    }
    /**
     * 子集 II: refer to template
     */
    public List<List<Integer>> subsetsWithDup_5(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        Arrays.sort(nums); // 排序
        buildSubset(res, nums, path, 0, false);
        return res;
    }

    private void buildSubset(List<List<Integer>>  res, int[] nums,  Deque<Integer> path , int index, boolean choosePre){
        if(index == nums.length){
            res.add(new ArrayList<>(path));
            return;
        }
        // 不选
        buildSubset(res, nums, path, index + 1, false);
        // 选
        if (!choosePre && index > 0 && nums[index] == nums[index - 1]) {
            return; // 若发现没有选择上一个数，且当前数字与上一个数相同，则可以跳过当前生成的子集
        }
        path.addLast(nums[index]);
        buildSubset(res, nums, path, index + 1, true);
        path.removeLast();
    }
}
