package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Q129_Sum_Root_to_Leaf_Numbers {
    /**
     * 本题和113.路径总和II 是类似的思路: 首先思路很明确，就是要遍历整个树把更节点到叶子节点组成的数字相加。
     */
    StringBuilder sb = new StringBuilder();
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        dfs(root);
        return sum;
    }
    // 回溯: 本题其实采用前中后序都不无所谓， 因为也没有中间几点的处理逻辑。
    //      主要是当左节点不为空，path收集路径，并递归左孩子，右节点同理
    public void dfs(TreeNode root){
        if(root == null) return;

        sb.append(root.value); // 中

        if(root.left == null && root.right == null) sum +=  Integer.valueOf(sb.toString());  // 遇到了叶子节点

        if(root.left != null) { // 左 （空节点不遍历）
            dfs(root.left); // 递归
            sb.deleteCharAt(sb.length() -1 );  // 回溯
        }
        if(root.right != null) {  // 右 （空节点不遍历）
            dfs(root.right);  // 递归
            sb.deleteCharAt(sb.length() -1 );  // 回溯
        }
    }
    /**
     * 回溯 另一种形式
     */
    List<Integer> path = new ArrayList<>();
    int sum1 = 0;
    public int sumNumbers1(TreeNode root) {
        dfs1(root);
        return sum1;
    }

    public void dfs1(TreeNode root){
        if(root == null) return;

        path.add(root.value);

        if(root.left == null && root.right == null) {
            int total = 0;   // 当是叶子节点的时候，开始处理
            for(int i = 0; i < path.size(); i++){
                total = total * 10 + path.get(i); // sum * 10 表示进位
            }
            sum1 += total;
        }

        if(root.left != null) {
            dfs1(root.left);
            path.remove(path.size() -1 );// 注意有回溯
        }
        if(root.right != null) {
            dfs1(root.right);
            path.remove(path.size() -1 );// 注意有回溯
        }
    }
}
