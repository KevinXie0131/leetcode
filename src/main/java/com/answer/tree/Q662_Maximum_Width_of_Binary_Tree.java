package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q662_Maximum_Width_of_Binary_Tree {
    /**
     * 二叉树最大宽度
     * 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
     * 树的 最大宽度 是所有层中最大的 宽度 。
     * 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
     * 题目数据保证答案将会在  32 位 带符号整数范围内。
     * Given the root of a binary tree, return the maximum width of the given tree.
     * The maximum width of a tree is the maximum width among all levels.
     * The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.
     * It is guaranteed that the answer will in the range of a 32-bit signed integer.
     */
    public static void main(String[] args) {
        // [1,3,2,5,null,null,9,6,null,7]
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(9);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node2.right = node4;
        node3.left = node5;
        node4.left = node6;

        System.out.println(widthOfBinaryTree_0( root));
    }
    /**
     * doesn't work for [1,3,2,5,null,null,9,6,null,7] 提交无法通过
     *     1
     *   3   2
     *  5      9
     * 6     7
     * Output: 4
     * Expected: 7
     */
    public static int widthOfBinaryTree(TreeNode root) {
        int res = 0;
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();

            List<TreeNode> list = new LinkedList<>();
            while (size > 0) {
                TreeNode cur = queue.poll();
                list.add(cur);
                if(cur != null){
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }
                size--;
            }
            int i = 0, j = list.size() - 1;
            while(i <= j){
                if(list.get(i) == null){
                    i++;
                }
                if(list.get(j) == null){
                    j--;
                }
                if(list.get(i) != null && list.get(j) != null){
                    res = Math.max(j - i + 1, res);
                    break;
                }
            }
        }
        return res;
    }
    /**
     * Improved and works, but it has Time Limit Exceeded
     */
    public static int widthOfBinaryTree_0(TreeNode root) {
        int res = 0;
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size(), start = -1, end = -1;

            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                if(cur.value != Integer.MAX_VALUE && start == -1) start = i; // start只赋值一次
                if(cur.value != Integer.MAX_VALUE) end = i;

                queue.offer(cur.left == null ? new TreeNode(Integer.MAX_VALUE, null, null) : cur.left);
                queue.offer(cur.right == null ? new TreeNode(Integer.MAX_VALUE, null, null) : cur.right);
            }
            if(start == -1) {
                break;
            }
            res = Math.max(res, end - start + 1);
        }
        return res;
    }
    /**
     * Improved and works 广度优先搜索
     * 此题求二叉树所有层的最大宽度，比较直观的方法是求出每一层的宽度，然后求出最大值。求每一层的宽度时，
     * 因为两端点间的 null 节点也需要计入宽度，因此可以对节点进行编号。一个编号为 index 的左子节点的编号记为 2×index，
     * 右子节点的编号记为 2×index+1，计算每层宽度时，用每层节点的最大编号减去最小编号再加 1 即为宽度。
     *
     * 遍历节点时，可以用广度优先搜索来遍历每一层的节点，并求出最大值。
     *
     */
    public static int widthOfBinaryTree_0a(TreeNode root) {
        int res = 0;
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(new TreeNode(1, root.left, root.right));
        while (!queue.isEmpty()) {
            int size = queue.size(), start = -1, end = -1;

            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                end = cur.value;
                if(start == -1) {
                    start = cur.value;
                }
                if(cur.left != null) {
                    queue.offer(new TreeNode(cur.value * 2, cur.left.left, cur.left.right));//左节点index i*2
                }
                if(cur.right != null) {
                    queue.offer(new TreeNode(cur.value * 2 + 1, cur.right.left, cur.right.right));//右节点index i*2 + 1
                }
            }
            res = Math.max(res, end - start + 1);
        }
        return res;
    }
    /**
     * 使用两个queue
     */
    public static int widthOfBinaryTree_0c(TreeNode root) {
        int res = 0;
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        Deque<Integer> indexQueue = new LinkedList<>();
        queue.offer(root);
        indexQueue.offer(1);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int start = -1;
            int end = -1;

            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                Integer index = indexQueue.poll();
                end = index;
                if(start == -1) {
                    start = index;
                }
                if(cur.left != null) {
                    queue.offer(cur.left);//左节点index i*2
                    indexQueue.offer(index * 2);
                }
                if(cur.right != null) {
                    queue.offer(cur.right);//右节点index i*2 + 1
                    indexQueue.offer((index * 2) + 1) ;
                }
            }
            res = Math.max(res, end - start + 1);
        }
        return res;
    }
    /**
     * Improved and works
     */
    public static int widthOfBinaryTree_1(TreeNode root) {
        int res = 0;
        if (root == null) {
            return 0;
        }
        List<NodePair> list = new LinkedList<>();
        list.add(new NodePair(root, 1) );
        while (!list.isEmpty()) {
            List<NodePair> temp = new LinkedList<>();

            for(int i = 0; i < list.size(); i++){
                TreeNode cur = list.get(i).node;
                int value = list.get(i).val;
                if(cur.left != null){
                    temp.add(new NodePair(cur.left, value * 2 ) );
                }
                if(cur.right != null){
                    temp.add(new NodePair(cur.right, value * 2 + 1) );
                }
            }
            res = Math.max(res, list.get(list.size() - 1).val - list.get(0).val + 1);

            list = temp;
        }
        return res;
    }
    /**
     * Recursion 深度优先搜索
     *
     * 历时如果是先访问左子节点，再访问右子节点，每一层最先访问到的节点会是最左边的节点，即每一层编号的最小值，
     * 需要记录下来进行后续的比较。一次深度优先搜索中，需要当前节点到当前行最左边节点的宽度，以及对子节点进行深度优先搜索，求出最大宽度，并返回最大宽度。
     */
    static int max = 1;

    public static int widthOfBinaryTree_4(TreeNode root) {
        // 给每个节点一个编号（索引）
        // 按满二叉树来算，如果一个节点的索引为 x，那么它左右子节点的索引分别是 2x 和 2x+1
        int ans = 0;
        // 记录每个层级最小和最大的编号
        // 也可以只记录最小编号，边遍历边计算结果
        List<int[]> list = new ArrayList<>();
        dfs(list, root, 1, 0);

        for (int[] arr : list) {
            ans = Math.max(ans, arr[1] - arr[0] + 1); // 右节点index - 左节点index + 1
        }
        return ans;
    }

    private static void dfs(List<int[]> list, TreeNode node, int index, int level) {
        if (node == null) {
            return;
        }
        if (list.size() == level) {
            list.add(new int[] {index, index});
        } else {
            list.get(level)[1] = index;
        //    max = Math.max(max, list.get(level)[1] - list.get(level)[0] + 1); // works too
        }

        dfs(list, node.left, 2 * index, level + 1);//左节点index i*2
        dfs(list, node.right, 2 * index + 1, level + 1); //右节点index i*2 + 1
    }
    /**
     * 深度优先 + 哈希表
     */
    Map<Integer, Integer> map = new HashMap<>();
    int ans;

    public int widthOfBinaryTree6(TreeNode root) {
        dfs(root, 1, 0);
        return ans;
    }
    // root的编号=N
    // root.left的编号=2 * N
    // root.right的编号=2 * N + 1
    void dfs(TreeNode root, int index, int depth) {
        if (root == null) {
            return;
        }
        map.putIfAbsent(depth, index); // 每一层最先访问到的节点会是最左边的节点，即每一层编号的最小值
        ans = Math.max(ans, index - map.get(depth) + 1);

        dfs(root.left, index * 2, depth + 1);
        dfs(root.right, index * 2 + 1, depth + 1);
    }
}

class NodePair {
    TreeNode node;
    Integer val;

    public NodePair(TreeNode node, Integer val) {
        this.node = node;
        this.val = val;
    }
}
