package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q1123_Lowest_Common_Ancestor_of_Deepest_Leaves {
    /**
     * 最深叶节点的最近公共祖先
     * 给你一个有根节点 root 的二叉树，返回它 最深的叶节点的最近公共祖先 。
     * 回想一下：
     *  叶节点 是二叉树中没有子节点的节点
     *  树的根节点的 深度 为 0，如果某一节点的深度为 d，那它的子节点的深度就是 d+1
     *  如果我们假定 A 是一组节点 S 的 最近公共祖先，S 中的每个节点都在以 A 为根节点的子树中，且 A 的深度达到此条件下可能的最大值。
     * Given the root of a binary tree, return the lowest common ancestor of its deepest leaves.
     * Recall that:
     *  The node of a binary tree is a leaf if and only if it has no children
     *  The depth of the root of the tree is 0. if the depth of a node is d, the depth of each of its children is d + 1.
     *  The lowest common ancestor of a set S of nodes, is the node A with the largest depth such that every node in S is in the subtree with root A.
     */
    public static void main(String[] args) {
        // root = [3,5,1,6,2,0,8,null,null,7,4]
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(0);
        TreeNode node7 = new TreeNode(8);
        TreeNode node8 = new TreeNode(7);
        TreeNode node9 = new TreeNode(4);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left= node6;
        node3.right = node7;
        node5.left = node8;
        node5.right = node9;
        TreeNode node = lcaDeepestLeaves(node1);
        System.out.println(node.value);

        TreeNode node1a = new TreeNode(0);
        TreeNode node2a = new TreeNode(1);
        TreeNode node3a = new TreeNode(3);
        TreeNode node4a = new TreeNode(2);
        node1a.left = node2a;
        node1a.right = node3a;
        node2a.right = node4a;
        TreeNode node11 = lcaDeepestLeaves1(node1a);
        System.out.println(node11.value);
    }
    /**
     * DFS
     * 如果左子树深度比右子树深，那么最深的叶子结点肯定在左边，最近公共祖先也肯定在左子树那边；
     * 如果右子树深度比左子树深，那么最深的叶子结点肯定在右边，最近公共祖先也肯定在右子树那边；
     * 如果左右两边深度一样，那么最下面那一层肯定都是叶子节点，所以最近公共祖先就是当前结点；
     *
     * Q104 Maximum Depth of Binary Tree
     * Q236 Lowest Common Ancestor of a Binary Tree
     * 核心思想
     *  最深节点一定出现在更深的那棵子树中
     *  当左右子树深度相同时，当前节点就是答案
     * 操作步骤
     *  计算左右子树深度
     *  比较深度：
     *      左深 → 答案在左子树
     *      右深 → 答案在右子树
     *      同深 → 当前节点就是答案
     */
    public static TreeNode lcaDeepestLeaves(TreeNode root) {
        if(root == null) {
            return null;
        }
        int left = maxDepth(root.left);  // 计算左右子树的深度
        int right = maxDepth(root.right);
        // 根据深度比较决定搜索方向
        if(left == right) { // 深度相等时，当前节点就是所求子树的根
            return root;
        } else if (left > right) { // 左子树更深，继续在左子树中搜索
            return lcaDeepestLeaves(root.left);
        } else {                   // 右子树更深，继续在右子树中搜索
            return lcaDeepestLeaves(root.right);
        }
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
    /**
     * 全局最大深度
     * 正确做法如下：
     *  从根节点开始递归，同时维护全局最大深度 maxDepth。
     *  在「递」的时候往下传 depth，用来表示当前节点的深度。
     *  在「归」的时候往上传当前子树最深的空节点的深度。这里为了方便，用空节点代替叶子，因为最深的空节点的上面一定是最深的叶子。
     *  设左子树最深空节点的深度为 leftMaxDepth，右子树最深空节点的深度为 rightMaxDepth。如果最深的空节点左右子树都有，即 leftMaxDepth=rightMaxDepth=maxDepth，那么更新答案为当前节点。注意这并不代表我们找到了答案，如果后面发现了更深的空节点，答案还会更新。另外注意，这个判断方式在只有一个最深叶子的情况下，也是正确的
     */
    static private TreeNode ans;
    static private int maxDepth = -1; // 全局最大深度

    static public TreeNode lcaDeepestLeaves1(TreeNode root) {
        dfs(root, 0);
        return ans;
    }
    // 用后序遍历搜索当前节点的深度，如果左右子树的深度一样，且均为当前最深的节点，则更新答案。由于是找最深的节点的祖先，因此ans只会更新不会被覆盖掉。
    static private int dfs(TreeNode node, int depth) {
        if (node == null) {
            maxDepth = Math.max(maxDepth, depth); // 更新全局最大深度
            return depth;
        }
        int leftMaxDepth = dfs(node.left, depth + 1); // 左子树最深空节点的深度
        int rightMaxDepth = dfs(node.right, depth + 1); // 右子树最深空节点的深度
        if (rightMaxDepth == maxDepth && leftMaxDepth == maxDepth) { // 最深的空节点左右子树都有
            ans = node; // 如果左右子树的深度相同，并且都等于最大深度，则说明当前节点是这棵子树的最近公共祖先。
        }
        return Math.max(leftMaxDepth, rightMaxDepth); // 当前子树最深空节点的深度
    }
    /**
     * 暴力BFS
     */
    public TreeNode lcaDeepestLeaves3(TreeNode root) {
        // 第一步：遍历树并记录每个节点的深度和父节点
        Map<TreeNode, Integer> depth = new HashMap<>();
        Map<TreeNode, TreeNode> parent = new HashMap<>();  // (节点i，它的父亲)
        parent.put(root, null);  // 根节点的父节点是 null
        Queue<Object[]> queue = new LinkedList<>();  // 层序遍历，记录[节点i，它的高度]
        List<TreeNode> deepestLeaves = new ArrayList<>();
        int maxDepth = -1;

        queue.offer(new Object[]{root, 0});

        while (!queue.isEmpty()) {
            Object[] item = queue.poll();
            TreeNode node = (TreeNode) item[0];
            int level = (Integer) item[1];
            depth.put(node, level);

            if (node.left == null && node.right == null) {  // 叶子节点
                if (level > maxDepth) {  // 更新最大深度
                    maxDepth = level;
                    deepestLeaves = new ArrayList<>();  // 清空并重新添加
                    deepestLeaves.add(node);
                } else if (level == maxDepth) {  // 否则，继续记录
                    deepestLeaves.add(node);
                }
            }
            if (node.left != null) {
                parent.put(node.left, node);  // 左孩子-父节点
                queue.offer(new Object[]{node.left, level + 1});
            }
            if (node.right != null) {
                parent.put(node.right, node);  // 右孩子-父节点
                queue.offer(new Object[]{node.right, level + 1});
            }
        }
        // 第二步：求所有最深叶子的LCA
        if (deepestLeaves.size() == 1) {
            return deepestLeaves.get(0);
        }
        // 找到lcaa与node的公共祖先
        TreeNode currentLCA = deepestLeaves.get(0);
        for (int i = 1; i < deepestLeaves.size(); i++) {  // 让每个节点的祖先一致
            currentLCA = getLCA(currentLCA, deepestLeaves.get(i), parent);
        }
        return currentLCA;
    }

    private TreeNode getLCA(TreeNode lcaa, TreeNode node, Map<TreeNode, TreeNode> parent) {
        // 得到节点lcaa的所有祖先
        Set<TreeNode> ancestors = new HashSet<>();
        ancestors.add(lcaa);  // 添加自己
        while (parent.containsKey(lcaa)) {  // 添加祖先
            lcaa = parent.get(lcaa);
            ancestors.add(lcaa);
        }
        // 查找节点node的祖先
        while (!ancestors.contains(node)) {
            node = parent.get(node);
        }
        return node;
    }
}
