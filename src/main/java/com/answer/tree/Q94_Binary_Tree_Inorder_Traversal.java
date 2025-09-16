package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q94_Binary_Tree_Inorder_Traversal {
    /**
     * 二叉树的中序遍历
     * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
     * Given the root of a binary tree, return the inorder traversal of its nodes' values.
     */
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.right = new TreeNode(7);
        List<Integer> list = inorderTraversal_1(root1);
        System.out.println(list);
    }
    /**
     * 中序遍历（迭代法）
     * 中序遍历是左中右，先访问的是二叉树顶部的节点，然后一层一层向下访问，直到到达树左面的最底部，再开始处理节点（也就是在把节点的数值放进result数组中），
     * 这就造成了处理顺序和访问顺序是不一致的。
     *
     * 那么在使用迭代法写中序遍历，就需要借用指针的遍历来帮助访问节点，栈则用来处理节点上的元素。
     * 中序遍历顺序: 左-中-右 入栈顺序： 左-右
     */
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        //define a pointer to track nodes
        TreeNode cur = root;

        while(!stack.isEmpty() || cur != null){
            // if it is not null, push to stack and go down the tree to left
            if(cur != null){ // 指针来访问节点，访问到最底层
                stack.push(cur);  // 将访问的节点放进栈
                cur = cur.left;  // 左
            }else{ // if no left child pop stack, process the node then let p point to the right
                TreeNode node = stack.pop();  // 从栈里弹出的数据，就是要处理的数据（放进result数组里的数据）
                result.add(node.value);  // 中
                cur = node.right;    // 右
            }
        }
        return result;
    }
    /**
     * 遍历 (迭代法) 模板
     * 记录节点值的位置不同
     * 不断向左孩子移动，同时把当前节点入栈，直到空节点，把栈顶节点出栈并加入结果，然后移动到出栈节点的右孩子，重复这个过程。
     */
    static public List<Integer> inorderTraversal_1(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left; // 左
            }

            TreeNode cur = stack.pop();
            list.add(cur.value); //这里记录节点值
            node = cur.right; // 右
        }
        return list;
    }
    /**
     *   如下代码也可以替代line 63-65
     *   node = stack.pop();
     *   list.add(node.val);
     *   node = node.right;
     */
    /**
     * 1->
     * 2->1->
     * 4->2->1->
     * 2->1->
     * 1->
     * 5->1->
     * 1->
     * 3->
     * 7->
     */
    static public void printAll(Deque<TreeNode> stack){
        Iterator it = stack.iterator();
        while(it.hasNext()) {
            TreeNode cur =  (TreeNode)it.next();
            System.out.print(cur.value + "->");
        }
        System.out.println();
    }
    /**
     * 统一迭代法 中序遍历
     */
    public List<Integer> inorderTraversal_2(TreeNode root) {
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
                st.push(node);                          // 添加中节点
                st.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。

                if (node.left != null){
                    st.push(node.left);    // 添加左节点（空节点不入栈）
                }
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
