package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q270_Closest_Binary_Search_Tree_Value {
    public static void main(String[] args) {
          //[2,1,3]
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        System.out.println(closestValue_4(node1, 3.714286));
    }

    static int res = Integer.MAX_VALUE;

    public static int closestValue(TreeNode root, double target) {
        double closest = Integer.MAX_VALUE;
        dfs(root, target, closest);
        return res;
    }

    public static void dfs(TreeNode root, double target, double closest){
        if(root == null) {
            return;
        }

        dfs(root.left, target, closest);

        double absVal = Math.abs(target - (double)root.value);
        if(absVal < closest){
            closest = absVal;
            res = root.value;
        }

        dfs(root.right, target, closest);
    }
    /**
     * Approach 2: Iterative Inorder, O(k) time
     */
    public int closestValue_1(TreeNode root, double target) {
        double closest = Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                double absVal = Math.abs(target - (double)cur.value);
                if(absVal < closest){
                    closest = absVal;
                    res = cur.value;
                }

                cur = cur.right;
            }
        }
        return res;
    }
    /**
     * Approach 3: Binary Search, O(H) time
     */
    public int closestValue_3(TreeNode root, double target) {
        int val, closest = root.value;
        while(root != null){
            closest = Math.abs(root.value - target) < Math.abs(closest - target) ? root.value : closest;

            root = target < root.value ? root.left : root.right;
        }
        return closest;
    }
    /**
     * Approach 1: Recursive Inorder + Linear search, O(N) time
     */
    public static void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) {
            return;
        }
        inorder(root.left, nums);
        nums.add(root.value);
        inorder(root.right, nums);
    }
    public static int closestValue_4(TreeNode root, double target) {
        List<Integer> nums = new ArrayList();
        inorder(root, nums);
  //     Collections.sort(nums,(o1, o2) -> Math.abs(o1 - target) < Math.abs(o2 - target) ? -1 : 1);

        Collections.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(o1 - target) < Math.abs(o2 - target) ? -1 : 1;
            }
        });

      //  return Collections.min(nums);
        return nums.get(0);
    }

}
