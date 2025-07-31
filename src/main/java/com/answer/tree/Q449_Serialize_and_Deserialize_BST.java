package com.answer.tree;

import com.template.TreeNode;

public class Q449_Serialize_and_Deserialize_BST {
    /**
     * 序列化和反序列化二叉搜索树
     * 序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
     * 设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
     * 编码的字符串应尽可能紧凑。
     * Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
     * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You need to ensure that a binary search tree can be serialized to a string, and this string can be deserialized to the original tree structure.
     * The encoded string should be as compact as possible.
     */
    public static void main(String[] args) {
        Q449_Serialize_and_Deserialize_BST codec = new Q449_Serialize_and_Deserialize_BST();
        // 用例1：普通BST
        //      2
        //     / \
        //    1   3
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);
        String data1 = codec.serialize(root1);
        TreeNode out1 = codec.deserialize(data1);
        printPreorder(out1); // 输出应为 2 1 3
        System.out.println();
        // 用例2：只有左子树
        //    3
        //   /
        //  2
        // /
        //1
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(1);
        String data2 = codec.serialize(root2);
        TreeNode out2 = codec.deserialize(data2);
        printPreorder(out2); // 输出应为 3 2 1
        System.out.println();
        // 用例3：只有右子树
        // 1
        //  \
        //   2
        //    \
        //     3
        TreeNode root3 = new TreeNode(1);
        root3.right = new TreeNode(2);
        root3.right.right = new TreeNode(3);
        String data3 = codec.serialize(root3);
        TreeNode out3 = codec.deserialize(data3);
        printPreorder(out3); // 输出应为 1 2 3
        System.out.println();
        // 用例4：只有一个节点
        TreeNode root4 = new TreeNode(42);
        String data4 = codec.serialize(root4);
        TreeNode out4 = codec.deserialize(data4);
        printPreorder(out4); // 输出应为 42
        System.out.println();
        // 用例5：空树
        TreeNode root5 = null;
        String data5 = codec.serialize(root5);
        TreeNode out5 = codec.deserialize(data5);
        printPreorder(out5); // 不输出内容
        System.out.println();
    }
    // 先序遍历打印
    public static void printPreorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.value + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }
    /**
     * BST的特性
     *  二叉搜索树：每个节点的值大于左子树所有节点的值，小于右子树所有节点的值。因此，先序遍历（根-左-右）可以唯一确定一棵BST
     *
     * 如果告诉你某BST的inOrder/preOrder/postOrder遍历结果，能不能还原唯一的BST?
     *  preOrder/postOrder可以，首先，你可以定位root（preOrder是第一个元素，postOrder是最后一个）；
     *  其次，BST，那么可以知道root->left全是小于它的，right全是大于它的。所以可以还原唯一的BST。
     *  但是inOrder遍历就不行了，因为in遍历形成的数组是按照从小到大的顺序排列的，你根本不知道root是哪一个元素。
     */
    // Encodes a tree to a single string. 序列化
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString().trim();
    }

    // 先序遍历
    // 用先序遍历将树转为字符串（只需要记录节点值，不需要null占位符）。
    // 节点值用空格或逗号分隔。
    private void preorder(TreeNode node, StringBuilder sb) {
        if (node == null) return;
        sb.append(node.value).append(" ");
        preorder(node.left, sb);
        preorder(node.right, sb);
    }

    // Decodes your encoded data to tree. 反序列化：递归重建BST
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        String[] vals = data.split(" "); // 读取字符串，转换为整数列表。
        int[] idx = {0}; // 指针，记录当前读取到的位置
        return build(vals, idx, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    // 根据BST性质和先序遍历构建树
    // 递归还原BST：每次分配一个值给节点，然后所有比它小的值递归建左子树，比它大的递归建右子树。
    // 用上下界限制（lower/upper），保证每个节点都在合法区间。
    private TreeNode build(String[] vals, int[] idx, int lower, int upper) {
        if (idx[0] == vals.length) {// 递归终止条件
            return null;
        }
        int val = Integer.parseInt(vals[idx[0]]);
        // 当前值必须在有效区间
        if (val < lower || val > upper) { // 当前值不在合法区间，不能放在这里
            return null;
        }
        TreeNode node = new TreeNode(val);
        idx[0]++;   // 构建当前节点
        node.left = build(vals, idx, lower, val - 1); // 构建左子树，区间为：[lower, val-1]
        node.right = build(vals, idx, val + 1, upper); // 构建右子树，区间为：[val+1, upper]
        return node;
    }
}
