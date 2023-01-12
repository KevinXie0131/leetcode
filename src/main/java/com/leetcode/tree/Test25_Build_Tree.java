package com.leetcode.tree;

import com.template.TreeNode;
import java.util.*;

public class Test25_Build_Tree {

    public static void main(String[] args) {
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};

    //    TreeNode root = buildTree(inorder, postorder);
        TreeNode root = buildTree1(inorder, postorder);

        System.out.println(root);
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder) {

        List<Integer> postorderList = new ArrayList<>();
        List<Integer> inorderList = new ArrayList<>();
        int length = inorder.length;
        for (int i = 0; i < length; i++) {
            postorderList.add(postorder[i]);
            inorderList.add(inorder[i]);
        }
        return buildTree(inorderList, postorderList);
    }

    public static TreeNode buildTree(List<Integer> inorderList, List<Integer> postorderList) {
        if (inorderList.size() == 0) {
            return null;
        }
        int rootVal = postorderList.remove(postorderList.size() - 1);
        TreeNode root = new TreeNode(rootVal);
        int index = inorderList.indexOf(rootVal);

        root.right = buildTree(inorderList.subList(index + 1, inorderList.size()), postorderList);
        root.left = buildTree(inorderList.subList(0, index), postorderList);

        return root;
    }

    static Map<Integer, Integer> map = new HashMap<>();

    public static TreeNode buildTree1(int[] inorder, int[] postorder) {

        int n = inorder.length;
        for(int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return subBuildTree(inorder, postorder);
    }

    public static TreeNode subBuildTree(int[] inorder, int[] postorder)     {
        if(inorder.length == 0){
            return null;
        }
        int inorderRoot = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (postorder[postorder.length-1] == inorder[i]) {
                inorderRoot = i;
                break;
            }
        }
        TreeNode root = new TreeNode(postorder[postorder.length-1]);

        root.right = subBuildTree(Arrays.copyOfRange(inorder, inorderRoot + 1, inorder.length),
                Arrays.copyOfRange(postorder, inorderRoot, postorder.length -1));

        root.left = subBuildTree(Arrays.copyOfRange(inorder, 0, inorderRoot),
                Arrays.copyOfRange(postorder, 0, inorderRoot));

        return root;

    }
}
