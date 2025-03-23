package com.answer.tree;

import com.template.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {
    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode root = buildTree0(preorder, inorder);
        System.out.println(root);
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
        for( index = inorderStart; index < inorderEnd; index++ ){
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
        int rightPreEnd =preorderEnd;

        root.left = buildHelper(inorder, leftInorderStart, leftInorderEnd, preorder, leftPreStart, leftPreEnd);
        root.right = buildHelper(inorder, rightInorderStart, rightInorderEnd, preorder, rightPreStart, rightPreEnd);

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
     *
     */
    int pre_idx;
    int[] preorder;
    int[] inorder;
    HashMap<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

    public TreeNode helper(int in_left, int in_right) {
        // if there is no elements to construct subtrees
        if (in_left > in_right)
            return null;

        // pick up post_idx element as a root
        int root_val = preorder[pre_idx];
        TreeNode root = new TreeNode(root_val);

        // root splits inorder list
        // into left and right subtrees
        int index = idx_map.get(root_val);

        // recursion
        pre_idx++;
        // build left subtree
        root.left = helper(in_left, index - 1);
        // build right subtree
        root.right = helper(index + 1, in_right);

        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        // start from the last postorder element
        pre_idx = 0;

        // build a hashmap value -> its index
        int idx = 0;
        for (Integer val : inorder)
            idx_map.put(val, idx++);
        return helper(0, inorder.length - 1);
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
