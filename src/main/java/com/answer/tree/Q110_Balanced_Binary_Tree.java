package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q110_Balanced_Binary_Tree {
    /**
     * ⼀棵⾼度平衡⼆叉树定义为：⼀个⼆叉树每个节点的左右两个⼦树的⾼度差的绝对值不超过1
     *
     * ⼆叉树节点的深度：指从根节点到该节点的最长简单路径边的条数。
     * ⼆叉树节点的⾼度：指从该节点到叶⼦节点的最长简单路径边的条数
     * 但leetcode中强调的深度和⾼度很明显是按照节点来计算的
     *
     * 关于根节点的深度究竟是1 还是 0，不同的地⽅有不⼀样的标准，leetcode的题⽬中都是以节
     * 点为⼀度，即根节点深度是1。但维基百科上定义⽤边为⼀度，即根节点的深度是0，我们暂时以leetcode为准
     *
     * 求深度可以从上到下去查 所以需要前序遍历（中左右），⽽⾼度只能从下到上去查，所以只能后序遍历（左右中）
     * 求的是⼆叉树的最⼤深度，也⽤的是后序遍历? 那是因为代码的逻辑其实是求的根节点的⾼度，⽽根节点的⾼度就是这颗树的最⼤深度，所以才可以使⽤后序遍历
     */
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1 ? true: false; // -1 表示已经不是平衡⼆叉树了，否则返回值是以该节点为根节点树的⾼度
    }
    /**
     * 如果当前传⼊节点为根节点的⼆叉树已经不是⼆叉平衡树了，还返回⾼度的话就没有意义了。
     * 所以如果已经不是⼆叉平衡树了，可以返回-1 来标记已经不符合平衡树的规则了
     */
    int getHeight(TreeNode node) {
        if(node == null) { // 明确终止条件
            return 0;      // 递归的过程中依然是遇到空节点了为终止，返回0，表示当前节点为根节点的树高度为0
        }
        int left = getHeight(node.left); // 左
        if (left == -1) { // -1 表示已经不是平衡二叉树了，否则返回值是以该节点为根节点树的高度
            return -1;    // 如果当前传入节点为根节点的二叉树已经不是二叉平衡树了，还返回高度的话就没有意义了。
        }
        int right = getHeight(node.right); // 右
        if (right == -1) {
            return -1;
        }
        // 明确单层递归的逻辑
        // 分别求出其左右子树的高度，然后如果差值小于等于1，则返回当前二叉树的高度，否则返回-1，表示已经不是二叉平衡树了。
        if (Math.abs(left - right) > 1) { // 中
            return -1;
        } else {
            return Math.max(left, right) + 1; // 以当前节点为根节点的最⼤⾼度
        }
    }
    /**
     * 代码精简 递归法
     */
    public boolean isBalanced1(TreeNode root) {
        return getHeight1(root) != -1 ? true: false;
    }

    int getHeight1(TreeNode node) {
        if(node == null) {
            return 0;
        }
        int left = getHeight(node.left);
        int right = getHeight(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        } else {
            return Math.max(left, right) + 1;
        }
    }
    /**
     * 迭代法，效率较低，计算高度时会重复遍历
     * 时间复杂度：O(n^2)
     */
    public boolean isBalanced_1(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root!= null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode inNode = stack.peek();
            // 右结点为null或已经遍历过
            if (inNode.right == null || inNode.right == pre) {
                // 比较左右子树的高度差，输出
                if (Math.abs(getHeight_1(inNode.left) - getHeight_1(inNode.right)) > 1) {
                    return false;
                }
                stack.pop();
                pre = inNode;
                root = null;// 当前结点下，没有要遍历的结点了
            } else {
                root = inNode.right;// 右结点还没遍历，遍历右结点
            }
        }
        return true;
    }
    /**
     * 层序遍历，求结点的高度
     */
    public int getHeight_1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        int depth = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();
                if (poll.left != null) {
                    deque.offer(poll.left);
                }
                if (poll.right != null) {
                    deque.offer(poll.right);
                }
            }
        }
        return depth;
    }
    /**
     * 优化迭代法，针对暴力迭代法的getHeight方法做优化，利用TreeNode.val来保存当前结点的高度，这样就不会有重复遍历
     * 获取高度算法时间复杂度可以降到O(1)，总的时间复杂度降为O(n)。
     * 时间复杂度：O(n)
     */
    public boolean isBalanced_2(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode inNode = stack.peek();
            // 右结点为null或已经遍历过
            if (inNode.right == null || inNode.right == pre) {
                // 输出
                if (Math.abs(getHeight_2(inNode.left) - getHeight_2(inNode.right)) > 1) {
                    return false;
                }
                stack.pop();
                pre = inNode;
                root = null;// 当前结点下，没有要遍历的结点了
            } else {
                root = inNode.right;// 右结点还没遍历，遍历右结点
            }
        }
        return true;
    }
    /**
     * 求结点的高度
     */
    public int getHeight_2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = root.left != null ? root.left.value : 0;
        int rightHeight = root.right != null ? root.right.value : 0;
        int height = Math.max(leftHeight, rightHeight) + 1;
        root.value = height;// 用TreeNode.val来保存当前结点的高度
        return height;
    }
}
