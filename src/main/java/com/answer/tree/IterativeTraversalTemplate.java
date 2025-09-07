package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class IterativeTraversalTemplate {
    /**
     * 遍历二叉树详解
     * https://leetcode.cn/problems/binary-tree-preorder-traversal/solutions/87526/leetcodesuan-fa-xiu-lian-dong-hua-yan-shi-xbian-2/
     * 一篇文章带你吃透对称性递归(思路分析+解题模板+案例解读)
     * https://leetcode.cn/problems/invert-binary-tree/solutions/791028/yi-pian-wen-zhang-dai-ni-chi-tou-dui-che-21l9/
     * 写树算法的套路框架
     * https://leetcode.cn/problems/same-tree/solutions/6558/xie-shu-suan-fa-de-tao-lu-kuang-jia-by-wei-lai-bu-/
     * 「代码随想录」我要打十个！二叉树层序遍历登场
     * https://leetcode.cn/problems/average-of-levels-in-binary-tree/solutions/859789/dai-ma-sui-xiang-lu-wo-yao-da-shi-ge-er-cbxt1/
     * 二叉树关于行操作汇总级别整理「带给你不一样的灵感」
     * https://leetcode.cn/problems/find-largest-value-in-each-tree-row/solutions/1621053/by-lfool-b1ty/
     */
    /**
     * 二叉树的中序遍历 (迭代法) 模板
     */
    public List<Integer> inorderTraversal_1(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (node != null || !stack.isEmpty()) {
            while (node != null) { //不断往左子树方向走，每走一次就将当前节点保存到栈中, 这是模拟递归的调用
                stack.push(node);
                node = node.left; // 左
            }

            TreeNode cur = stack.pop(); //当前节点为空，说明左边走到头了，从栈中弹出节点并保存, 然后转向右边节点，继续上面整个过程
            list.add(cur.value); //这里记录节点值
            node = cur.right; // 右
        }
        return list;
    }
}
