package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q145_Binary_Tree_Postorder_Traversal {
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
     */
    public List<Integer> postorderTraversal_1(TreeNode node) {
        List<Integer> list =  new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                list.add(node.value); // 记录节点值
                stack.push(node);
                node = node.right; //右节点
            }

            TreeNode cur = stack.pop();
            node = cur.left; // 左节点
        }

        Collections.reverse(list); //  结果反转 中-右-左 变成 左-右-中
        return list;
    }
}
