package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q144_Binary_Tree_Preorder_Traversal {
    /**
     * 二叉树的前序遍历
     * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历
     * Given the root of a binary tree, return the preorder traversal of its nodes' values.
     */
    /**
     * 二叉树的前序遍历 (迭代法)
     * 中序遍历顺序: 左-中-右 入栈顺序： 中-右-左
     * 前序遍历是中左右，每次先处理的是中间节点，那么先将根节点放入栈中，然后将右孩子加入栈，再加入左孩子。
     * 为什么要先加入 右孩子，再加入左孩子呢？ 因为这样出栈的时候才是中左右的顺序。
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> returnList = new ArrayList<Integer>();
        if(root == null) {
            return returnList;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while(!stack.isEmpty()){
            TreeNode node = stack.pop(); // 中
            returnList.add(node.value);

            if(node.right != null){  // 右（空节点不入栈）
                stack.push(node.right);
            }
            if(node.left != null){   // 左（空节点不入栈）
                stack.push(node.left);
            }
        }
        return returnList;
    }
    /**
     * 遍历 (迭代法) 模板
     * 参考Q94 Binary Tree Inorder Traversal
     * 不断向左孩子移动，每遇到一个节点就把它加入结果，同时把当前节点入栈，直到空节点，把栈顶节点出栈并移动到出栈节点的右孩子，重复这个过程。
     */
    public List<Integer> preorderTraversal_1(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                list.add(root.value); //一开始就记录节点值
                stack.push(root);
                root = root.left; //找到中间节点 和 左节点
            }

            TreeNode cur = stack.pop();
            root = cur.right; //最后再找 右节点
        }
        return list;
    }
    /**
     * 统一迭代法 前序遍历
     *
     * 使用栈的话，无法同时解决访问节点（遍历节点）和处理节点（将元素放进结果集）不一致的情况。
     * 那我们就将访问的节点放入栈中，把要处理的节点也放入栈中但是要做标记。
     * 如何标记呢，就是要处理的节点放入栈之后，紧接着放入一个空指针作为标记。 这种方法也可以叫做标记法。
     *
     *  (注意此时我们和中序遍历相比仅仅改变了两行代码的顺序)
     */
    public List<Integer> preorderTraversal_2(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> st = new Stack<>();
        if (root != null) {
            st.push(root);
        }
        while (!st.empty()) {
            TreeNode node = st.peek();
            if (node != null) {
                st.pop(); // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                if (node.right != null){
                    st.push(node.right);  // 添加右节点（空节点不入栈）
                }
                if (node.left != null){
                    st.push(node.left);    // 添加左节点（空节点不入栈）
                }
                st.push(node);                          // 添加中节点
                st.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。

            } else { // 只有遇到空节点的时候，才将下一个节点放进结果集
                st.pop();           // 将空节点弹出
                node = st.peek();    // 重新取出栈中元素
                st.pop();
                result.add(node.value); // 加入到结果集
            }
        }
        return result;
    }
}
