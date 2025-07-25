package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q145_Binary_Tree_Postorder_Traversal {
    /**
     * 二叉树的后序遍历
     * 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
     * Given the root of a binary tree, return the postorder traversal of its nodes' values.
     */
    /**
     * 后序遍历
     * 遍历 (迭代法) 模板
     *
     * 后序遍历，先序遍历是中左右，后序遍历是左右中，那么我们只需要调整一下先序遍历的代码顺序，就变成中右左的遍历顺序，
     * 然后在反转result数组，输出的结果顺序就是左右中了
     *
     * 后序遍历顺序 左-右-中 入栈顺序：中-左-右 出栈顺序：中-右-左， 最后翻转结果
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> returnList = new ArrayList<Integer>();

        if(root == null) {
            return returnList;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while(!stack.isEmpty()){
            TreeNode node = stack.pop(); // 中
            returnList.add(node.value);
            // 所以后序遍历只需要前序遍历的代码稍作修改就可以了
            // 相对于前序遍历，这更改一下入栈顺序 （空节点不入栈）
            if(node.left != null){   // 左（空节点不入栈）
                stack.push(node.left);
            }
            if(node.right != null){  // 右（空节点不入栈）
                stack.push(node.right);
            }

        }
        Collections.reverse(returnList);  // 将结果反转之后就是左右中的顺序了
        return returnList;
    }
    /**
     * 遍历 (迭代法) 模板
     * 参考Q144 Binary Tree Preorder Traversal
     * 按照 “中右左” 的顺序进行前序遍历，最后再将结果翻转即可。
     */
    public List<Integer> postorderTraversal_1(TreeNode node) {
        List<Integer> list =  new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                list.add(node.value); // 记录节点值
                stack.push(node);
                node = node.right; //右节点（比前序遍历不同的地方）
            }

            TreeNode cur = stack.pop();
            node = cur.left; // 左节点（比前序遍历不同的地方）
        }

        Collections.reverse(list); //  结果反转 中-右-左 变成 左-右-中
        return list;
    }
    /**
     * 头插法
     * 参考Q144 Binary Tree Preorder Traversal
     */
    public List<Integer> postorderTraversal_1a(TreeNode node) {
        List<Integer> list =  new LinkedList<>();
        Deque <TreeNode> stack = new ArrayDeque<>();

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                list.add(0, node.value); // 头插法
                stack.push(node);
                node = node.right;//右节点（比前序遍历不同的地方）
            }

            TreeNode cur = stack.pop();
            node = cur.left; // 左节点（比前序遍历不同的地方）
        }
        return list;
    }
    /**
     * 统一迭代法 后序遍历
     *  (注意此时我们和中序遍历相比仅仅改变了两行代码的顺序)
     */
    public List<Integer> postorderTraversal_2(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> st = new Stack<>();
        if (root != null) st.push(root);
        while (!st.empty()) {
            TreeNode node = st.peek();
            if (node != null) {
                st.pop(); // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                st.push(node);                          // 添加中节点
                st.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。
                if (node.right!=null) st.push(node.right);  // 添加右节点（空节点不入栈）
                if (node.left!=null) st.push(node.left);    // 添加左节点（空节点不入栈）

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
