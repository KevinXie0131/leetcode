package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class IterativeTraversal {
    /**
     * 遍历二叉树详解
     * https://leetcode.cn/problems/binary-tree-preorder-traversal/solutions/87526/leetcodesuan-fa-xiu-lian-dong-hua-yan-shi-xbian-2/
     * 一篇文章带你吃透对称性递归(思路分析+解题模板+案例解读)
     * https://leetcode.cn/problems/invert-binary-tree/solutions/791028/yi-pian-wen-zhang-dai-ni-chi-tou-dui-che-21l9/
     * 写树算法的套路框架
     * https://leetcode.cn/problems/same-tree/solutions/6558/xie-shu-suan-fa-de-tao-lu-kuang-jia-by-wei-lai-bu-/
     */
    /**
     * 二叉树的中序遍历 (迭代法) 模板
     */
    public List<Integer> inorderTraversal_1(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (node != null || !stack.isEmpty()) {
            while (node != null) { //不断往左子树方向走，每走一次就将当前节点保存到栈中, 这是模拟递归的调用
                stack.push(node);
                node = node.left; // 左
            }

            TreeNode cur = stack.pop(); //当前节点为空，说明左边走到头了，从栈中弹出节点并保存, 然后转向右边节点，继续上面整个过程
            list.add(cur.value); //这里记录节点值
            node = cur.right; // 右
        }
        return list;
    }
    /**
     * 二叉树的前序遍历 (迭代法) 模板1
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
     * 二叉树的后序遍历 (迭代法) 模板1
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
     * 二叉树的前序遍历 (迭代法) 模板2
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> returnList = new ArrayList<Integer>();

        if(root == null) return returnList;

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
     * 二叉树的后序遍历 (迭代法) 模板2
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> returnList = new ArrayList<Integer>();

        if(root == null) return returnList;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while(!stack.isEmpty()){
            TreeNode node = stack.pop(); // 中
            returnList.add(node.value);
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
     * 二叉树的后序遍历 (迭代法) 模板3: 头插法
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
}
