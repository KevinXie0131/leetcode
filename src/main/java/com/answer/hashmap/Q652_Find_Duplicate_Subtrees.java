package com.answer.hashmap;

import com.template.TreeNode;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class Q652_Find_Duplicate_Subtrees {
    /**
     * 寻找重复的子树
     * 给你一棵二叉树的根节点 root ，返回所有 重复的子树 。
     * 对于同一类的重复子树，你只需要返回其中任意 一棵 的根结点即可。
     * 如果两棵树具有 相同的结构 和 相同的结点值 ，则认为二者是 重复 的。
     * Given the root of a binary tree, return all duplicate subtrees.
     * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
     * Two trees are duplicate if they have the same structure with the same node values.
     *
     * 示例 1：
     * 输入：root = [1,2,3,4,null,2,4,null,null,4]
     * 输出：[[2,4],[4]]
     *        1
     *      /   \
     *     2     3
     *    /     / \
     *   4     2   4
     *        /
     *       4
     */
    /**
     * 使用序列化进行唯一表示
     * 将每一棵子树都「序列化」成一个字符串，并且保证：
     *  相同的子树会被序列化成相同的子串；
     *  不同的子树会被序列化成不同的子串。
     * 那么我们只要使用一个哈希表存储所有子树的序列化结果，如果某一个结果出现了超过一次，我们就发现了一类重复子树。
     *
     * 两种常用的序列化方法：
     *  第一种方法是使用层序遍历的方法进行序列化，例如示例 1中的二叉树可以序列化为：1,2,3,4,null,2,4,null,null,4
     *  第二种方法是使用递归的方法进行序列化。我们可以将一棵以 x 为根节点值的子树序列化为：
     *
     * x(左子树的序列化结果)(右子树的序列化结果)
     * 左右子树分别递归地进行序列化。如果子树为空，那么序列化结果为空串。示例 1中的二叉树可以序列化为：1(2(4()())())(3(2(4()())())(4()()))
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)
     * 示例 1：4()()
     *        2(4()())()
     *        4()()
     *        2(4()())()
     *        4()()
     *        3(2(4()())())(4()())
     *        1(2(4()())())(3(2(4()())())(4()()))
     */
    Map<String, TreeNode> seen = new HashMap<String, TreeNode>();
    Set<TreeNode> repeat = new HashSet<TreeNode>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return new ArrayList<TreeNode>(repeat);
    }

    public String dfs(TreeNode node) {
        if (node == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(node.value);
        sb.append("(");
        sb.append(dfs(node.left));
        sb.append(")(");
        sb.append(dfs(node.right));
        sb.append(")");
        String serial = sb.toString();
        if (seen.containsKey(serial)) {
            repeat.add(seen.get(serial));
        } else {
            seen.put(serial, node);
        }
        return serial;
    }
    /**
     * 使用三元组进行唯一表示
     * 可以用一个三元组直接表示一棵子树，即 (x,l,r)，它们分别表示：
     *  根节点的值为 x；
     *  左子树的序号为 l；
     *  右子树的序号为 r。
     * 这里的「序号」指的是：每当我们发现一棵新的子树，就给这棵子树一个序号，用来表示其结构。那么两棵树是重复的，当且仅当它们对应的三元组完全相同。
     * 使用「序号」的好处在于同时减少了时间复杂度和空间复杂度。方法一的瓶颈在于生成的序列会变得非常长，而使用序号替换整个左子树和右子树的序列，可以使得每一个节点只使用常数大小的空间。
     *
     * 时间复杂度：O(n)，其中 n 是树中节点的数目。
     * 空间复杂度：O(n)，即为哈希表需要使用的空间。
     */
    Map<String, Pair<TreeNode, Integer>> seen1 = new HashMap<String, Pair<TreeNode, Integer>>();
    Set<TreeNode> repeat1 = new HashSet<TreeNode>();//用于存储重复子树的根节点
    int idx = 0; // 每个子树对应的唯一序号

    public List<TreeNode> findDuplicateSubtrees1(TreeNode root) { // 主函数 寻找重复的子树
        dfs1(root);
        return new ArrayList<TreeNode>(repeat1);
    }

    // 辅助函数 深度优先遍历所有节点,返回“节点作为根节点的子树”对应的唯一序号idx
    public int dfs1(TreeNode node) {
        if (node == null) {// 空节点对应序号0 递归结束
            return 0;
        }
        int[] tri = {node.value, dfs1(node.left), dfs1(node.right)};// DFS实现自底向上，先生成子树的idx
        String hash = Arrays.toString(tri); // 当前树节点+子树序号就是当前树的唯一标志，hash相同就代表树相同
        if (seen1.containsKey(hash)) {// 当前二叉树重复
            Pair<TreeNode, Integer> pair = seen1.get(hash);
            repeat1.add(pair.getKey());//使用Pair类就是为了方便加入重复树的根节点，而且只加入一次
            return pair.getValue();
        } else { // 当前二叉树第一次出现，结合未使用过的idx，加入哈希Map，
            Pair pair = new MutablePair<TreeNode, Integer>(node, ++idx); // seen.put(hash, new Pair<TreeNode, Integer>(node, ++idx));
            seen1.put(hash, pair);
            return idx;
        }
    }
    /**
     * DFS + 哈希表 + 序列化
     * 对于标识的设计只需使用 "_" 分割不同的节点值，同时对空节点进行保留（定义为空串 " "）即可。
     * 使用哈希表记录每个标识（子树）出现次数，当出现次数为 2（首次判定为重复出现）时，将该节点加入答案。
     *
     * 示例 1：4_
     *        2_4_
     *        4_
     *        2_4_
     *        4_
     *        3_2_4_   4_
     *        1_2_4_   3_2_4_   4_
     */
    Map<String, Integer> map = new HashMap<>();
    List<TreeNode> ans = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees2(TreeNode root) {
        dfs2(root);
        return ans;
    }

    String dfs2(TreeNode root) {
        if (root == null){
            return " ";
        }
        StringBuilder sb = new StringBuilder();  //序列化以当前节点为根节点的二叉树
        sb.append(root.value) // 前序遍历
                .append("_");
        sb.append(dfs2(root.left))
                .append(dfs2(root.right));
        String key = sb.toString();

        map.put(key, map.getOrDefault(key, 0) + 1);
        if (map.get(key) == 2){
            ans.add(root);
        }
        return key;
    }
    /**
     * 后序遍历+序列化+哈希表
     * 通过后序遍历树，自底向上地序列化其子树，并将序列化的子树存入哈希表。
     * 得到序列化子树后，判断子树是否已经出现过，如果出现过，则将该根节点存入结果集
     *
     * 示例 1： 4,#,#
     *         2,4,#,#,#
     *         4,#,#
     *         2,4,#,#,#
     *         4,#,#
     *         3,2,4,#,#,#,4,#,#
     *         1,2,4,#,#,#,3,2,4,#,#,#,4,#,#
     */
    Map<String,Integer> tree = new HashMap();
    List<TreeNode> answer = new LinkedList<>();

    public List<TreeNode> findDuplicateSubtrees3(TreeNode root) {
        traverse(root);
        return answer;
    }

    //返回序列化子树
    private String traverse(TreeNode root) {
        if(root == null) {
            return "#";
        }
        String leftTree = traverse(root.left); //得到序列化后的左子树
        String rightTree = traverse(root.right);//得到序列化后的右子树
        String treeSub = root.value + "," + leftTree + "," + rightTree; //后序遍历-自底向上地构建序列化子树-不断比较返回的子树是否已经存在
        int count = tree.getOrDefault(treeSub, 0);

        if(count == 1) {  //如果存在该子树
            answer.add(root);    //将该子树根节点存入结果集
        }
        tree.put(treeSub, count + 1);  //子树数量递增
        return treeSub;
    }
}
