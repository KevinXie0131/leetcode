package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q173_Binary_Search_Tree_Iterator_1 {
    /**
     * Approach 2: Controlled Recursion
     * 本质是中序遍历
     * 参考Q94 Binary Tree Inorder Traversal
     * 将中序遍历 (迭代法) 模板分解到各函数中
     */
    private TreeNode cur;
    private Deque<TreeNode> stack;
    // 通过迭代的方式对二叉树做中序遍历。此时，我们无需预先计算出中序遍历的全部结果，只需要实时维护当前栈的情况即可。
    public Q173_Binary_Search_Tree_Iterator_1(TreeNode root) {
        cur = root;
        stack = new ArrayDeque<>();
    }

    public int next() {
        while (cur != null) { // 中序遍历，因此需要把当前子树的最左侧节点依次入栈
            stack.push(cur);
            cur = cur.left;
        }
        int result;
        cur = stack.pop();  // 栈顶节点为当前要处理节点
        result = cur.value;  // 缓存节点值
        cur = cur.right; // 左中右，当前节点处理完后该处理其右子树的节点了
        return result;
    }

    public boolean hasNext() {
        return cur != null || !stack.isEmpty();  // 只有当节点和栈同时为空，整个树遍历结束
    }
    /**
     * another form
     * 思路: 因为要中序遍历, 所以把根节点先入栈然后入栈左孩子, 每当输出一个节点后把那个节点的右孩子以及右孩子的所有左边的孩子加入进栈
     */
    Deque<TreeNode> stack1;

    // 构造方法：一路到底，把根节点和它的所有左节点放到栈中；栈中只保留左节点。
    public void BSTIterator1(TreeNode root) {
        stack1 = new LinkedList<>();
        TreeNode cur = root;

        while(cur != null){ //初始化栈：把根节点和根节点的所有左孩子及左孩子的左孩子加入进栈
            stack1.push(cur);
            cur = cur.left;
        }
    }

    public int next1() {
        TreeNode cur = stack1.pop();
        int res = cur.value;

        if(cur.right != null){      //把取出节点的右孩子和右孩子的所有左孩子及左孩子的左孩子加入进栈
            stack.push(cur.right);
            cur = cur.right.left;

            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
        }
        return res;
    }
    /**
     * another from
     * 弹出栈顶的节点. 如果它有右子树，则对右子树一路到底，把它和它的所有左节点放到栈中。
     */
    public int next2() {
        TreeNode node = stack.pop();

        if(node.right != null) {
            TreeNode cur = node.right;
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
        }
        return node.value;
    }

    public boolean hasNext1() {
        return !stack1.isEmpty();
    }
}
