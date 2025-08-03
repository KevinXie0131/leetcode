package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {
    /**
     * 从前序与中序遍历序列构造二叉树
     * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
     */
    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode root = buildTree0a(preorder, inorder);
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
     * 根据⼀棵树的前序遍历与中序遍历构造⼆叉树
     *
     * 前序和中序可以唯⼀确定⼀颗⼆叉树。
     * 后序和中序可以唯⼀确定⼀颗⼆叉树。
     * 那么前序和后序可不可以唯⼀确定⼀颗⼆叉树呢？
     *    前序和后序不能唯⼀确定⼀颗⼆叉树！，因为没有中序遍历⽆法确定左右部分，也就是⽆法分割
     */
    public static TreeNode buildTree0(int[] preorder, int[] inorder) {
        return buildHelper(inorder, 0, inorder.length, preorder, 0, preorder.length); // 参数坚持左闭右开的原则
    }
    // 左闭右开
    private static TreeNode buildHelper(int[] inorder, int inorderStart, int inorderEnd, int[] preorder, int preorderStart, int preorderEnd){
        if (preorderStart == preorderEnd) {
            return null;  // preorder = [1,2] inorder = [2,1]
        }
        int rootValue = preorder[preorderStart];  // 注意用preorderBegin 不要用0
        TreeNode root = new TreeNode(rootValue);

        if (preorderEnd - preorderStart == 1){
            return root;
        }

        int index;
        for(index = inorderStart; index < inorderEnd; index++){
            if(inorder[index] == rootValue){
                break;
            }
        }
        // 切割中序数组
        // 中序左区间，左闭右开[leftInorderBegin, leftInorderEnd)
        int leftInorderStart = inorderStart;
        int leftInorderEnd =  index;
        // 中序右区间，左闭右开[rightInorderBegin, rightInorderEnd)
        int rightInorderStart =  index + 1;
        int rightInorderEnd = inorderEnd;
        // 切割前序数组
        // 前序左区间，左闭右开[leftPreorderBegin, leftPreorderEnd)
        int leftPreStart =  preorderStart + 1;
        int leftPreEnd = preorderStart + (index - inorderStart) + 1; // 终止位置是起始位置加上中序左区间的大小size
        // 前序右区间, 左闭右开[rightPreorderBegin, rightPreorderEnd)
        int rightPreStart = preorderStart  + (index - inorderStart) + 1;
        int rightPreEnd = preorderEnd;

        root.left = buildHelper(inorder, leftInorderStart, leftInorderEnd, preorder, leftPreStart, leftPreEnd);
        root.right = buildHelper(inorder, rightInorderStart, rightInorderEnd, preorder, rightPreStart, rightPreEnd);
        return root;
    }
    /**
     * 同上
     */
    public static TreeNode buildTree0a(int[] preorder, int[] inorder) {
        return buildHelper_0a(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
    }
    // 左闭右开闭
    private static TreeNode buildHelper_0a(int[] inorder, int inorderStart, int inorderEnd, int[] preorder, int preorderStart, int preorderEnd){
        if (preorderStart > preorderEnd) {
            return null;
        }
        int rootValue = preorder[preorderStart];
        TreeNode root = new TreeNode(rootValue);

        int index;
        for(index = inorderStart; index <= inorderEnd; index++){
            if(inorder[index] == rootValue){
                break;
            }
        }
        int leftInorderStart = inorderStart;
        int leftInorderEnd =  index - 1;
        int rightInorderStart =  index + 1;
        int rightInorderEnd = inorderEnd;
        int leftPreStart =  preorderStart + 1;
        int leftPreEnd = preorderStart + (index - inorderStart); // 终止位置是起始位置加上中序左区间的大小size
        int rightPreStart = preorderStart  + (index - inorderStart) + 1;
        int rightPreEnd = preorderEnd;

        root.left = buildHelper_0a(inorder, leftInorderStart, leftInorderEnd, preorder, leftPreStart, leftPreEnd);
        root.right = buildHelper_0a(inorder, rightInorderStart, rightInorderEnd, preorder, rightPreStart, rightPreEnd);
        return root;
    }
    /**
     *
     */
    private Map<Integer, Integer> indexMap;

    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        int n = preorder.length;
        indexMap = new HashMap<>();  // 构造哈希映射，帮助我们快速定位根节点
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }
        int preorder_root = preorder_left;// 前序遍历中的第一个节点就是根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);  // 在中序遍历中定位根节点
        TreeNode root = new TreeNode(preorder[preorder_root]);// 先把根节点建立出来
        int size_left_subtree = inorder_root - inorder_left;// 得到左子树中的节点数目
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }
    /**
     * 使用map方便根据数值查找位置 + 递归
     */
    Map<Integer, Integer> map;

    public TreeNode buildTree5(int[] preorder, int[] inorder) {
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) { // 用map保存中序序列的数值对应位置
            map.put(inorder[i], i);
        }
        return findNode(preorder, 0, preorder.length, inorder,  0, inorder.length);  // 前闭后开
    }

    public TreeNode findNode(int[] preorder, int preBegin, int preEnd, int[] inorder, int inBegin, int inEnd) {
        // 参数里的范围都是前闭后开
        if (preBegin >= preEnd || inBegin >= inEnd) {  // 不满足左闭右开，说明没有元素，返回空树
            return null;
        }
        int rootIndex = map.get(preorder[preBegin]);  // 找到前序遍历的第一个元素在中序遍历中的位置
        TreeNode root = new TreeNode(inorder[rootIndex]);  // 构造结点
        int lenOfLeft = rootIndex - inBegin;  // 保存中序左子树个数，用来确定前序数列的个数
        root.left = findNode(preorder, preBegin + 1, preBegin + lenOfLeft + 1,
                             inorder, inBegin, rootIndex);
        root.right = findNode(preorder, preBegin + lenOfLeft + 1, preEnd,
                              inorder, rootIndex + 1, inEnd);
        return root;
    }
    /**
     * 二叉树的根节点就是前序序列的第一个节点，这样就可以把中序序列拆分成两部分，根节点前面就是左子树，后面就是右子树，
     * 那么就知道了左右子树的节点数量，依此就可以对前序序列也进行拆分，这样左右子树的前序、中序序列都知道了，递归构建左右子树就可以了。
     */
    int pre_idx;
    int[] preorder;
    int[] inorder;
    HashMap<Integer, Integer> idx_map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        pre_idx = 0;  // start from the last postorder element
        int idx = 0; // build a hashmap value -> its index
        for (Integer val : inorder) {
            idx_map.put(val, idx++);
        }
        return helper(0, inorder.length - 1);
    }

    public TreeNode helper(int in_left, int in_right) {
        if (in_left > in_right) return null;// if there is no elements to construct subtrees

        int root_val = preorder[pre_idx];// pick up post_idx element as a root
        TreeNode root = new TreeNode(root_val);
        int index = idx_map.get(root_val); // root splits inorder list into left and right subtrees
        pre_idx++;
        // recursion
        root.left = helper(in_left, index - 1);   // build left subtree
        root.right = helper(index + 1, in_right); // build right subtree
        return root;
    }
    /**
     * 递归
     */
    public static TreeNode buildTree_1(int[] preorder, int[] inorder) {
        if(inorder.length == 0){ // 第⼀步
            return null;
        }
        int inorderRoot = -1; // 第三步：找切割点
        for (int i = 0; i < inorder.length; i++) {
            if (preorder[0] == inorder[i]) {
                inorderRoot = i;
                break;
            }
        }
        TreeNode root = new TreeNode(preorder[0]);

        root.right = buildTree_1(Arrays.copyOfRange(preorder, inorderRoot + 1, preorder.length),
                                 Arrays.copyOfRange(inorder, inorderRoot + 1, inorder.length));

        root.left = buildTree_1(Arrays.copyOfRange(preorder, 1, inorderRoot + 1),
                                Arrays.copyOfRange(inorder, 0, inorderRoot));

        return root;
    }
}
