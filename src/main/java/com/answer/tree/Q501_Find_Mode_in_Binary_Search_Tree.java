package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q501_Find_Mode_in_Binary_Search_Tree {
    /**
     * ⼆叉搜索树中的众数
     * 给定⼀个有相同值的⼆叉搜索树（BST），找出 BST 中的所有众数（出现频率最⾼的元素）
     * 进阶：你可以不使⽤额外的空间吗？（假设由递归产⽣的隐式调⽤栈的开销不被计算在内）
     */
    /**
     * 递归法
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
}
