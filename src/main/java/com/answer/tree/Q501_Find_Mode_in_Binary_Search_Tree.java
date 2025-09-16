package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q501_Find_Mode_in_Binary_Search_Tree {
    /**
     * 二叉搜索树中的众数
     * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
     * 如果树中有不止一个众数，可以按 任意顺序 返回。
     * 假定 BST 满足如下定义：
     *  结点左子树中所含节点的值 小于等于 当前节点的值
     *  结点右子树中所含节点的值 大于等于 当前节点的值
     *  左子树和右子树都是二叉搜索树
     * Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.
     * If the tree has more than one mode, return them in any order.
     * Assume a BST is defined as follows:
     *  The left subtree of a node contains only nodes with keys less than or equal to the node's key.
     *  The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
     *  Both the left and right subtrees must also be binary search trees.
     *
     */
    /**
     * ⼆叉搜索树中的众数
     * 给定⼀个有相同值的⼆叉搜索树（BST），找出 BST 中的所有众数（出现频率最⾼的元素）
     * 进阶：你可以不使⽤额外的空间吗？（假设由递归产⽣的隐式调⽤栈的开销不被计算在内）
     */
    /**
     * 递归法 (中序遍历-不使用额外空间，利用二叉搜索树特性)
     * 如果不是⼆叉搜索树，最直观的⽅法⼀定是把这个树都遍历了，⽤map统计频率，把频率排个序，最后取前⾯⾼频的元素的集合
     */
    int count;    // 统计频率
    int maxCount; // 最⼤频率
    ArrayList<Integer> list = new ArrayList<>();
    TreeNode pre; // // 记录前⼀个节点 ⼀个指针指向前⼀个节点，这样每次cur（当前节点）才能和pre（前⼀个节点）作⽐较

    public int[] findMode(TreeNode root) {
        recursion(root);
        return list.stream().mapToInt(i -> i).toArray();
    }

    public void recursion(TreeNode root){
        if (root == null) {
            return;
        }
        recursion(root.left); // 左
        // 中
        if(pre == null){ // 第⼀个节点
            count = 1; // 频率为1
        } else if(pre.value == root.value) { // 与前⼀个节点数值相同
            count++;
        } else if (pre.value != root.value){ // 与前⼀个节点数值不同
            count = 1;
        }
        pre = root; // 更新上⼀个节点

        // 要求最⼤频率的元素集合（注意是集合，不是⼀个元素，可以有多个众数）
        if(count == maxCount){ // 如果和最⼤值相同，放进result中
            list.add(root.value);
        }
        if (count > maxCount) { // 如果计数⼤于最⼤值
            maxCount = count; // 更新最⼤频率
            list.clear(); // 很关键的⼀步，不要忘记清空result，之前result⾥的元素都失效了
            list.add(root.value);
        }

        recursion(root.right); // 右
    }
    /**
     * 迭代法 中序遍历
     */
    public int[] findMode_1(TreeNode root) {
        TreeNode pre = null;
        int maxCount = 0; // 最⼤频率
        int count = 0; // 统计频率
        Deque<TreeNode> stack = new ArrayDeque<>();
        ArrayList<Integer> list = new ArrayList<>();

        while (!stack.isEmpty() || root != null) {
            while(root != null){
                stack.push(root);
                root= root.left; // 左
            }

            TreeNode cur = stack.pop(); // 中

            if(pre == null){ // 第⼀个节点
                count = 1; // 频率为1
            } else if(pre.value == cur.value) { // 与前⼀个节点数值相同
                count++;
            } else if (pre.value != cur.value){ // 与前⼀个节点数值不同
                count = 1;
            }
            pre = cur; // 更新上⼀个节点

            // 要求最⼤频率的元素集合（注意是集合，不是⼀个元素，可以有多个众数）
            if(count == maxCount){ // 如果和最⼤值相同，放进result中
                list.add(cur.value);
            }
            if (count > maxCount) { // 如果计数⼤于最⼤值
                maxCount = count; // 更新最⼤频率
                list.clear(); // 很关键的⼀步，不要忘记清空result，之前result⾥的元素都失效了
                list.add(cur.value);
            }

            root = cur.right; // 右

        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}
