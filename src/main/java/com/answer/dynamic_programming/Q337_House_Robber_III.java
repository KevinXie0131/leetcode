package com.answer.dynamic_programming;

import com.template.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Q337_House_Robber_III {
    /**
     * Approach 1: Recursion
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(log n)，算上递推系统栈的空间
     */
    // 1.递归去偷，超时 (以上代码超时了，这个递归的过程中其实是有重复计算)
    // 计算了root的四个孙子（左右孩子的孩子）为头结点的子树的情况，又计算了root的左右孩子为头结点的子树的情况，计算左右孩子的时候其实又把孙子计算了一遍
    public int rob(TreeNode root) {
        if (root == null)
            return 0;
        // 偷父节点
        int money = root.value;
        if (root.left != null) {
            money += rob(root.left.left) + rob(root.left.right); // 跳过root->left，相当于不考虑左孩子了
        }
        if (root.right != null) {
            money += rob(root.right.left) + rob(root.right.right); // 跳过root->right，相当于不考虑右孩子了
        }
        // 不偷父节点
        int lowerMoney = rob(root.left) + rob(root.right); // 考虑root的左右孩子
        return Math.max(money, rob(root.left) + rob(root.right));
    }
    /**
     * Approach 2: Recursion with Memoization 记忆化递推
     * 使用一个map把计算过的结果保存一下，这样如果计算过孙子了，那么计算孩子的时候可以复用孙子节点的结果
     * 时间复杂度：O(n)
     * 空间复杂度：O(log n)，算上递推系统栈的空间
     */
    // 2.递归去偷，记录状态
    // 执行用时：3 ms , 在所有 Java 提交中击败了 56.24% 的用户
    public int rob1(TreeNode root) {
        Map<TreeNode, Integer> memo = new HashMap<>(); // 记录计算过的结果
        return robAction(root, memo);
    }
    int robAction(TreeNode root, Map<TreeNode, Integer> memo) {
        if (root == null)
            return 0;
        if (memo.containsKey(root)) // 如果map里已经有记录则直接返回
            return memo.get(root);
        // 偷父节点
        int money = root.value;
        if (root.left != null) {
            money += robAction(root.left.left, memo) + robAction(root.left.right, memo);// 跳过root->left
        }
        if (root.right != null) {
            money += robAction(root.right.left, memo) + robAction(root.right.right, memo); // 跳过root->right
        }
        // 不偷父节点
        int lowerMoney = robAction(root.left, memo) + robAction(root.right, memo);  // 考虑root的左右孩子
        int res = Math.max(money, lowerMoney);
        memo.put(root, res); // map记录一下结果
        return res;
    }
    /**
     * Approach 3: Dynamic Programming 动态规划
     * 动态规划其实就是使用状态转移容器来记录状态的变化，这里可以使用一个长度为2的数组，记录当前节点偷与不偷所得到的的最大金钱。
     */
    // 3.状态标记递归
    // 执行用时：0 ms , 在所有 Java 提交中击败了 100% 的用户
    // 不偷：Max(左孩子不偷，左孩子偷) + Max(又孩子不偷，右孩子偷)
    // root[0] = Math.max(rob(root.left)[0], rob(root.left)[1]) +
    // Math.max(rob(root.right)[0], rob(root.right)[1])
    // 偷：左孩子不偷+ 右孩子不偷 + 当前节点偷
    // root[1] = rob(root.left)[0] + rob(root.right)[0] + root.val;
    /**
     * 所有房屋的排列类似于一棵二叉树, 两个直接相连的房子不能偷，求能够偷到的最高金额。
     * 本题一定是要后序遍历（深度优先搜索），因为通过递归函数的返回值来做下一步计算
     *
     * 时间复杂度：O(n)，每个节点只遍历了一次
     * 空间复杂度：O(log n)，算上递推系统栈的空间
     */
    public int rob3(TreeNode root) {
        int[] res = robAction1(root);
        return Math.max(res[0], res[1]); // 在 不偷root 和 偷root 取最大值
    }
    // 本题dp数组就是一个长度为2的数组
    // 长度为2的数组怎么标记树中每个节点的状态呢？别忘了在递归的过程中，系统栈会保存每一层递归的参数。
    int[] robAction1(TreeNode root) { // dp[0]: 不偷 / dp[1]: 偷
        int res[] = new int[2]; // 下标为0记录不偷该节点所得到的的最大金钱，下标为1记录偷该节点所得到的的最大金钱。
        if (root == null) {
            return res; // 如果遇到空节点的话，很明显，无论偷还是不偷都是0，所以就返回
        }
        // 使用后序遍历。 因为要通过递归函数的返回值来做下一步计算
        int[] left = robAction1(root.left);   // 得到左节点偷与不偷的金钱
        int[] right = robAction1(root.right); // 得到右节点偷与不偷的金钱。

        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); // 如果不偷当前节点，那么左右孩子就可以偷，至于到底偷不偷一定是选一个最大的
        res[1] = root.value + left[0] + right[0]; // 如果是偷当前节点，那么左右孩子就不能偷
        return res;
    }
}
