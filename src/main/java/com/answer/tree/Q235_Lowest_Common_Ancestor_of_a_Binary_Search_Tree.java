package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree {
    /**
     * 二叉搜索树的最近公共祖先
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
     * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
     * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
     */
    public static void main(String[] args) {
        // Construct BST:
        //       6
        //      / \
        //     2   8
        //    / \ / \
        //   0  4 7  9
        //     / \
        //    3   5
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        TreeNode p = root.left; // 2
        TreeNode q = root.left.right; // 4
        TreeNode lca = lowestCommonAncestor5(root, p, q);
        System.out.println(lca.value); // 2
    }
    /**
     * ⼆叉搜索树的最近公共祖先 - 前序遍历
     * 给定⼀个⼆叉搜索树, 找到该树中两个指定节点的最近公共祖先
     *
     * 普通⼆叉树求最近公共祖先需要使⽤回溯，从底向上来查找，⼆叉搜索树就不⽤了，因为搜索树有序（相当于⾃带⽅向），那么只要从上向下遍历就可以了
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if ((root.left == p && root.right == q) || (root.left == q && root.right == p)) {
            return root;
        }
        //如果根节点和p,q的差相乘是正数，说明这两个差值要么都是正数要么都是负数，也就是说
        //他们肯定都位于根节点的同一侧，就继续往下找
        while ((root.value - p.value) * (root.value - q.value) > 0) {
            root = p.value < root.value ? root.left : root.right;
          //  root = q.value < root.value ? root.left : root.right; // works too
        }
        return root;   //如果相乘的结果是负数，说明p和q位于根节点的两侧，如果等于0，说明至少有一个就是根节点
    }
    /**
     * 在遍历⼆叉搜索树的时候就是寻找区间[p.val, q.val]（注意这⾥是左闭又闭）
     * 那么如果 cur.val ⼤于 p.val，同时 cur.val ⼤于q.val，那么就应该向左遍历（说明⽬标区间在左⼦树上）。
     * 如果 cur.val ⼩于 p.val，同时 cur.val ⼩于 q.val，那么就应该向右遍历（⽬标区间在右⼦树）。
     */
    public TreeNode lowestCommonAncestor_0(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null; // 可省略 不存在遇到空的情况

        if (p.value < root.value && q.value < root.value) {
            TreeNode left = lowestCommonAncestor_0(root.left, p, q);
            if(left != null) {
                return left;
            }
        }
        if (p.value > root.value && q.value > root.value) {
            TreeNode right = lowestCommonAncestor_0(root.right, p, q);
            if(right != null) {
                return right;
            }
        }
        return root;
    }
    /**
     * 另一种形式的递归
     * 当我们从上向下去递归遍历，第一次遇到 cur节点是数值在[q, p]区间中，那么cur就是 q和p的最近公共祖先。
     */
    public TreeNode lowestCommonAncestor_1(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode left = null;
        if (p.value < root.value && q.value < root.value) { // 搜索整个树
            left = lowestCommonAncestor_1(root.left, p, q);
        }
        TreeNode right = null;
        if (p.value > root.value && q.value > root.value) {
            right = lowestCommonAncestor_1(root.right, p, q);
        }
        if(left!= null){
            return left;
        } else if(right!= null) {
            return right;
        } else{
            return root;
        }
    }
    /**
     * 精简后代码
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null; // 可省略 不存在遇到空的情况

        if(root.value > p.value && root.value > q.value){ // 第二种情况，p和q都在左子树，应该把视角放在左边，去左子树找第三种情况
            return lowestCommonAncestor3(root.left, p,q);
        }else if(root.value < p.value && root.value < q.value){ // 第一种情况，p和q都在右子树，应该把视角放在右边，去右子树找第三种情况
            return lowestCommonAncestor3(root.right, p,q);
        }else{
            return root; // 第三种情况，p,q不在同一子树，只能是p，q分别在一左一右，或者，p，q其中一个是根节点，都返回root
        }
    }
    /**
     * 迭代法
     * 从根节点开始遍历；
     * 如果当前节点的值大于 p 和 q 的值，说明 p 和 q 应该在当前节点的左子树，因此将当前节点移动到它的左子节点；
     * 如果当前节点的值小于 p 和 q 的值，说明 p 和 q 应该在当前节点的右子树，因此将当前节点移动到它的右子节点；
     * 如果当前节点的值不满足上述两条要求，那么说明当前节点就是「分岔点」。此时，p 和 q 要么在当前节点的不同的子树中，要么其中一个就是当前节点。
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.value < root.value && q.value < root.value) {  // 如果p和q都比根小，说明LCA在左子树
                root = root.left;
            } else if (p.value > root.value && q.value > root.value) {  // 如果p和q都比根大，说明LCA在右子树
                root = root.right;
            } else {
                // 否则，当前root就是最近公共祖先
                return root; // 剩下的情况，就是cur节点在区间中，那么cur就是最近公共祖先了，直接返回cur
            }
        }
        return root;
    }
    /**
     * another form
     */
    public TreeNode lowestCommonAncestor1a(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p == root || q == root || (p.value < root.value && q.value > root.value) || (p.value > root.value && q.value < root.value)) {
                return root;
            }
            if (p.value < root.value) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return root;
    }
    /**
     * Q236题解  没有运用搜索树
     * refer to Q236_Lowest_Common_Ancestor_of_a_Binary_Tree
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor2(root.left, p,  q);
        TreeNode right = lowestCommonAncestor2(root.right, p,  q);

        if (left == null && right == null) {
            return null;
        } else if(left != null && right == null) {
            return left;
        } else if(right != null && left == null) {
            return right;
        } else if (left != null && right != null) {
            return root;
        } else {
            return null;
        }
    }
    /**
     * Q236题解 没有运用搜索树
     * refer to Q236_Lowest_Common_Ancestor_of_a_Binary_Tree
     */
    Map<Integer, TreeNode> parent = new HashMap<>();
    Set<Integer> visited = new HashSet<>();

    public TreeNode lowestCommonAncestor4(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while(p != null){
            visited.add(p.value);
            p = parent.get(p.value);
        }

        while(q != null){
            if(visited.contains(q.value)){
                return q;
            }
            q = parent.get(q.value);
        }
        return null;
    }

    public void dfs(TreeNode root){
        if(root == null){
            return;
        }
        if(root.left != null){
            parent.put(root.left.value, root);
            dfs(root.left);
        }
        if(root.right != null){
            parent.put(root.right.value, root);
            dfs(root.right);
        }
    }
    /**
     * 两次遍历
     * p 和 q 的最近公共祖先就是从根节点到它们路径上的「分岔点」，也就是最后一个相同的节点。
     */
   static public TreeNode lowestCommonAncestor5(TreeNode root, TreeNode p, TreeNode q) {
       ArrayList<TreeNode> pathP = getPath(root, p);
       ArrayList<TreeNode> pathQ = getPath(root, q);
       TreeNode lca = null;
       int i = 0;
       while(i <= pathP.size() - 1 && i <= pathQ.size() - 1){
           if(pathP.get(i) == pathQ.get(i)){
               lca = pathP.get(i); // 找出最大的编号 i
           }
           i++;
       }
       return lca;
    }

    static public ArrayList<TreeNode> getPath(TreeNode root, TreeNode node) {
        ArrayList<TreeNode> path = new ArrayList<>();
        while(root != null){
            path.add(root);
            if(root.value > node.value){
                root = root.left;
            } else if(root.value < node.value) {
                root = root.right;
            } else {
                break;
            }
        }
        return path;
    }
}
