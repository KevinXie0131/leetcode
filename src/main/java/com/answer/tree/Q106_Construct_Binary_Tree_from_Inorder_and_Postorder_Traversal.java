package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {
    /**
     * 从中序与后序遍历序列构造二叉树
     * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
     * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.
     */
    public static void main(String[] args) {
        int[] inorder = {9,15,7,20,3};
        int[] postorder = {9,3,15,20,7};
        TreeNode root = buildTree3(postorder, inorder);
        System.out.println(root);
        /**
         *      3
         *     / \
         *   9    20
         *       /  \
         *     15    7
         */
    }
    /**
     * 根据⼀棵树的中序遍历与后序遍历构造⼆叉树
     * 以后序数组的最后⼀个元素为切割点，先切中序数组，根据中序数组，反过来在切后序数组。⼀层⼀层切下去，每次后序数组最后⼀个元素就是节点元素
     *
     * 递归
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder.length == 0){ // 第⼀步
            return null;
        }
        int inorderRoot = -1; // 第三步：找切割点
        for (int i = 0; i < inorder.length; i++) {
            if (postorder[postorder.length - 1] == inorder[i]) {
                inorderRoot = i;
                break;
            }
        }
        TreeNode root = new TreeNode(postorder[postorder.length - 1]); // 第⼆步：后序遍历数组最后⼀个元素，就是当前的中间节点
        // 第四步：切割中序数组，得到 中序左数组和中序右数组
        root.right = buildTree(Arrays.copyOfRange(inorder, inorderRoot + 1, inorder.length),
                               Arrays.copyOfRange(postorder, inorderRoot, postorder.length -1));
        // 第五步：切割后序数组，得到 后序左数组和后序右数组
        root.left = buildTree(Arrays.copyOfRange(inorder, 0, inorderRoot),
                              Arrays.copyOfRange(postorder, 0, inorderRoot));
        // ⾸先后序数组的最后⼀个元素指定不能要了，这是切割点 也是 当前⼆叉树中间节点的元素，已经⽤了
        // 中序数组⼤⼩⼀定是和后序数组的⼤⼩相同的（这是必然）
        return root;
    }
    /**
     * 递归
     */
    public TreeNode buildTree0(int[] inorder, int[] postorder) {
        return buildHelper(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }
    // 左闭右开
    private TreeNode buildHelper(int[] inorder, int inorderStart, int inorderEnd, int[] postorder, int postorderStart, int postorderEnd){
        if(postorderStart == postorderEnd){
            return null;
        }
        int rootValue = postorder[postorderEnd - 1]; // 后序遍历数组最后一个元素，就是当前的中间节点
        TreeNode root = new TreeNode(rootValue);
        int index;
        for(index = inorderStart; index < inorderEnd - 1; index++){ // 找切割点
            if(inorder[index] == rootValue){
                break;
            }
        }
        int leftInorderStart = inorderStart; // 切割中序数组，得到 中序左数组和中序右数组
        int leftInorderEnd =  index;         // 左闭右开区间：[0, delimiterIndex)
        int rightInorderStart =  index + 1;  // [delimiterIndex + 1, end)
        int rightInorderEnd = inorderEnd;

        int leftPostStart = postorderStart; // 切割后序数组，得到 后序左数组和后序右数组
        int leftPostEnd = postorderStart + (leftInorderEnd - leftInorderStart); // 左闭右开，注意这里使用了左中序数组大小作为切割点：[0, leftInorder.size)
        int rightPostStart = postorderStart  + (leftInorderEnd - leftInorderStart); // [leftInorder.size(), end)
        int rightPostEnd = postorderEnd - 1; // postorder 舍弃末尾元素，因为这个元素就是中间节点，已经用过了

        root.left = buildHelper(inorder, leftInorderStart, leftInorderEnd, postorder, leftPostStart, leftPostEnd); // (中序左数组, 后序左数组)
        root.right = buildHelper(inorder, rightInorderStart, rightInorderEnd, postorder, rightPostStart, rightPostEnd); // (中序右数组, 后序右数组)
        return root;
    }
    /**
     * 同上 左闭右闭 + 哈希表
     */
    static private Map<Integer, Integer> indexMap;

    static public TreeNode buildTree3(int[] inorder, int[] postorder) {
        int n = postorder.length;
        indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(postorder, inorder, 0, n - 1, 0, n - 1);
    }
    // 左闭右闭
    static public TreeNode myBuildTree(int[] postorder, int[] inorder, int postorder_left, int postorder_right, int inorder_left, int inorder_right) {
        if (postorder_left > postorder_right || inorder_left > inorder_right) {
            return null;
        }
        int postorder_root = postorder_right;
        int inorder_root = indexMap.get(postorder[postorder_root]);
        TreeNode root = new TreeNode(postorder[postorder_root]);
        int size_left_subtree = inorder_root - inorder_left;
        root.left = myBuildTree(postorder, inorder, postorder_left, postorder_left + (size_left_subtree) - 1, inorder_left, inorder_root - 1);
        root.right = myBuildTree(postorder, inorder, postorder_left + (size_left_subtree), postorder_right - 1, inorder_root + 1, inorder_right);
        return root;
    }
    /**
     * 使用map方便根据数值查找位置 + 递归
     */
    Map<Integer, Integer> map;  // 方便根据数值查找位置

    public TreeNode buildTree_3(int[] inorder, int[] postorder) {
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) { // 用map保存中序序列的数值对应位置
            map.put(inorder[i], i);
        }
        return findNode(inorder,  0, inorder.length, postorder,0, postorder.length);  // 前闭后开
    }
    // 左闭右开
    public TreeNode findNode(int[] inorder, int inBegin, int inEnd, int[] postorder, int postBegin, int postEnd) {
        // 参数里的范围都是前闭后开
        if (inBegin >= inEnd || postBegin >= postEnd) {  // 不满足左闭右开，说明没有元素，返回空树
            return null;
        }
        int rootIndex = map.get(postorder[postEnd - 1]);  // 找到后序遍历的最后一个元素在中序遍历中的位置
        TreeNode root = new TreeNode(inorder[rootIndex]);  // 构造结点
        int lenOfLeft = rootIndex - inBegin;  // 保存中序左子树个数，用来确定后序数列的个数
        root.left = findNode(inorder, inBegin, rootIndex,
                             postorder, postBegin, postBegin + lenOfLeft);
        root.right = findNode(inorder, rootIndex + 1, inEnd,
                              postorder, postBegin + lenOfLeft, postEnd - 1);
        return root;
    }
    /**
     * 同上
     * 后序序列的最后一个节点就是根节点，依此可以对两序列进行划分，得到左右子树的后序序列和中序序列，进而递归构建左右子树。
     */
    int post_idx;
    int[] postorder;
    int[] inorder;
    HashMap<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;
        post_idx = postorder.length - 1;    // start from the last postorder element
        int idx = 0;
        for (Integer val : inorder) { // build a hashmap value -> its index
            idx_map.put(val, idx++);
        }
        return helper(0, inorder.length - 1);
    }
    // 左闭右闭
    public TreeNode helper(int in_left, int in_right) {
        if (in_left > in_right) {
            return null;// if there is no elements to construct subtrees
        }
        int root_val = postorder[post_idx];// pick up post_idx element as a root
        TreeNode root = new TreeNode(root_val);
        int index = idx_map.get(root_val);// root splits inorder list into left and right subtrees
        post_idx--;
        // recursion
        root.right = helper(index + 1, in_right);  // build right subtree
        root.left = helper(in_left, index - 1);// build left subtree
        return root;
    }
}
