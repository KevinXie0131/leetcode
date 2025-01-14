package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q100_Same_Tree {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(2);
/*        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);*/
        System.out.println(isSameTree_4(root1, root2));
    }
    /**
     * 我们要比较的是两个树（这两个树是根节点的左右子树），所以在递归遍历的过程中，也是要同时遍历两棵树
     * 节点为空的情况有：
     *   tree1为空，tree2不为空，不对称，return false
     *   tree1不为空，tree2为空，不对称 return false
     *   tree1，tree2都为空，对称，返回true
     * 此时已经排除掉了节点为空的情况，那么剩下的就是tree1和tree2不为空的时候：
     *   tree1、tree2都不为空，比较节点数值，不相同就return false
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null && q != null) { // ⾸先排除空节点的情况
            return false;
        } else if (p != null && q == null) {
            return false;
        } else if (p.value != q.value) { // 排除了空节点，再排除数值不相同的情况
            return false;  // 注意这⾥我没有使⽤else
        }
        // 此时就是：左右节点都不为空，且数值相同的情况
        // 此时才做递归，做下⼀层的判断
        boolean left = isSameTree(p.left, q.left); // 左⼦树: 左、 右⼦树：左 （相对于求对称⼆叉树，只需改⼀下这⾥的顺序）
        boolean right = isSameTree(p.right, q.right); // 左⼦树: 右、 右⼦树：右
        return left && right; // 左⼦树: 中、 右⼦树: 中 （逻辑处理）
    }
    /**
     * 精简之后代码
     */
    public boolean isSameTree_1(TreeNode p, TreeNode q) {
        return compare(p, q);
    }
    boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right != null) {
            return false;
        } else if (left != null && right == null) {
            return false;
        } else if (left == null && right == null) {
            return true;
        } else if (left.value != right.value) {
            return false;
        } else {
            return compare(left.left, right.left) && compare(left.right, right.right);
        }
    }
    /**
     * 迭代法
     */
    static public boolean isSameTree_2(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(p);
        que.offer(q);

        while (!que.isEmpty()) {
            TreeNode leftNode = que.poll();
            TreeNode rightNode = que.poll();
            if (leftNode != null && rightNode != null && leftNode.value != rightNode.value) {
                return false;
            }
            if ((leftNode == null && rightNode == null)) {
                continue;
            }
            if ((leftNode == null && rightNode != null) || (leftNode != null && rightNode == null)) {
                return false;
            }
            // 相对于求对称⼆叉树，这⾥两个树都要保持⼀样的遍历顺序
            if((leftNode.left != null && rightNode.left == null) || leftNode.left == null && rightNode.left != null){
                return false;
            }
            if(leftNode.left != null) que.offer(leftNode.left);
            if(rightNode.left != null) que.offer(rightNode.left);
            if((leftNode.right != null && rightNode.right == null) || leftNode.right == null && rightNode.right != null){
                return false;
            }
            if(leftNode.right != null) que.offer(leftNode.right);
            if(rightNode.right != null)  que.offer(rightNode.right);
        }
        return true;
    }
    /**
     * 迭代法
     * 参考Q101_Symmetric_Tree
     */
    public boolean isSameTree_3(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(p);  //  添加根节点p
        queue.offer(q);  //  添加根节点q
        while (!queue.isEmpty()) {

            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            if (left == null && right == null) { // 若p的节点与q的节点都为空
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.value != right.value) {
                return false;
            }
            // 以上两个判断条件合并
    /*             if (left == null || right == null || left.val != right.val) {
                    return false;
                }*/
            queue.offerFirst(left.left); // 添加p节点的左子树节点
            queue.offerFirst(right.left); // 添加q节点的左子树节点
            queue.offerLast(left.right); // 添加p节点的右子树节点
            queue.offerLast(right.right); // 添加q节点的右子树节点
        }
        return true;
    }
    /**
     * 迭代法
     */
    static public boolean isSameTree_4(TreeNode p, TreeNode q) {
        Deque<TreeNode> queue = new LinkedList<>(); // LinkedList可以接受null, ArrayDeque不可以
        queue.offer(p);
        queue.offer(q);
        while(!queue.isEmpty()){
            TreeNode node1= queue.poll();
            TreeNode node2= queue.poll();
            if(node1 == null && node2 == null) continue;
            else if(node1 != null && node2 == null ) return false;
            else if(node2 != null && node1 == null ) return false;
            else if(node2.value != node1.value ) return false;

            queue.offer(node1.left);
            queue.offer(node2.left);
            queue.offer(node1.right);
            queue.offer(node2.right);
        }

        return true;
    }
}
