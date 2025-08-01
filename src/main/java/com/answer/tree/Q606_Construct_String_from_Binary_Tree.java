package com.answer.tree;

import com.learn.TestTree;
import com.template.TreeNode;

public class Q606_Construct_String_from_Binary_Tree {
    /**
     * 根据二叉树创建字符串
     * 给你二叉树的根节点 root ，请你采用前序遍历的方式，将二叉树转化为一个由括号和整数组成的字符串，返回构造出的字符串。
     * 空节点使用一对空括号对 "()" 表示，转化后需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对
     *
     * The representation should be based on a preorder traversal of the binary tree and must adhere to the following guidelines:
     * Node Representation: Each node in the tree should be represented by its integer value.
     * Parentheses for Children: If a node has at least one child (either left or right), its children should be represented inside parentheses. Specifically:
     *  If a node has a left child, the value of the left child should be enclosed in parentheses immediately following the node's value.
     *  If a node has a right child, the value of the right child should also be enclosed in parentheses. The parentheses for the right child should follow those of the left child.
     * Omitting Empty Parentheses: Any empty parentheses pairs (i.e., ()) should be omitted from the final string representation of the tree, with one specific exception: when a node has a right child but no left child. In such cases, you must include an empty pair of parentheses to indicate the absence of the left child. This ensures that the one-to-one mapping between the string representation and the original binary tree structure is maintained.
     *
     * In summary, empty parentheses pairs should be omitted when a node has only a left child or no children. However, when a node has a right child but no left child, an empty pair of parentheses must precede the representation of the right child to reflect the tree's structure accurately.
     */
    // Helper to build tree from level order array (nulls allowed)
    public static TreeNode buildTree(Integer... vals) {
        if (vals.length == 0 || vals[0] == null) return null;
        TreeNode root = new TreeNode(vals[0]);
        java.util.Queue<TreeNode> q = new java.util.LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < vals.length) {
            TreeNode node = q.poll();
            if (i < vals.length && vals[i] != null) {
                node.left = new TreeNode(vals[i]);
                q.add(node.left);
            }
            i++;
            if (i < vals.length && vals[i] != null) {
                node.right = new TreeNode(vals[i]);
                q.add(node.right);
            }
            i++;
        }
        return root;
    }

    public static void test(String testName, TreeNode root, String expected) {
        Q606_Construct_String_from_Binary_Tree s = new Q606_Construct_String_from_Binary_Tree();
        String result = s.tree2str(root);
        System.out.println(testName + ": " + (result.equals(expected) ? "PASS" : "FAIL"));
        System.out.println("Expected: " + expected);
        System.out.println("Result:   " + result);
        System.out.println();
    }

    public static void main(String[] args) {
        // Example 1: 1(2(4))(3)
        TreeNode root1 = buildTree(1, 2, 3, 4);
        test("Example 1", root1, "1(2(4))(3)");
        // Example 2: 1(2()(4))(3)
        TreeNode root2 = buildTree(1, 2, 3, null, 4);
        test("Example 2", root2, "1(2()(4))(3)");
        // Single node
        TreeNode root3 = new TreeNode(1);
        test("Single node", root3, "1");
        // Empty tree
        TreeNode root4 = null;
        test("Empty tree", root4, "");
        // Only left: 1(2(3))
        TreeNode root5 = buildTree(1, 2, null, 3);
        test("Only left", root5, "1(2(3))");
        // Only right: 1()(2()(3))
        TreeNode root6 = buildTree(1, null, 2, null, 3);
        test("Only right", root6, "1()(2()(3))");
    }
    /**
     * 递归构建字符串。
     * 只有在有右子节点时才需要保留左子节点的空括号()
     */
    public String tree2str(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        build(root, sb);
        return sb.toString();
    }
    // 根据题意，就2种情况：
    //    左子树：存在，保留(left->val)；不存在，并且right存在，保留()
    //    右子树：存在，保留(right->val)
    // 直接加到递归代码里面即可。
    public void build(TreeNode root, StringBuilder sb){
        if(root == null) return;
        sb.append(root.value);
        if(root.left != null || root.right != null){ // 左子树存在 或 右子树存在（为了保留空的左括号对）
            sb.append("(");
            build(root.left, sb);
            sb.append(")");

            if(root.right != null){  // 只有右子树存在时，才需要加上右子树括号
                sb.append("(");
                build(root.right, sb);
                sb.append(")");
            }
        }
    }
 }
