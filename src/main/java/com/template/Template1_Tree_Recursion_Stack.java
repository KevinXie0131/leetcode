package com.template;

import java.util.*;

public class Template1_Tree_Recursion_Stack {

    public static void main(String[] args) {
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        root.setLeft(node2);
        root.setRight(node5);
        node2.setLeft(node3);
        node2.setRight(node4);
        node5.setLeft(node6);
        node5.setRight(node7);

        /**
         * 1  2  3  4  5  6  7
         */
        preOrderRecursion(root);
        System.out.println();

        /**
         * 3  2  4  1  6  5  7
         */
        List<Integer> list = inorderTraversalStack(root);
        list.stream().forEach(e -> System.out.print(e + "  "));
        System.out.println();

        /**
         * 3  4  2  6  7  5  1
         */
        postOrderRecursion(root);
        System.out.println();

        postOrderRecursionAnother(root);
    }
    /**
     * 前序遍历（迭代法）:
     * 前序遍历是中左右，每次先处理的是中间节点，那么先将跟节点放⼊栈中，然后将右孩⼦加⼊栈，再加⼊左孩⼦
     *
     * 为什么要先加⼊ 右孩⼦，再加⼊左孩⼦呢？ 因为这样出栈的时候才是中左右的顺序
     */
    public static void preOrderRecursion (Node root) {
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            Node node = stack.pop(); // 中
            if (node != null) {
                System.out.print(node.value + "  ");
            } else {
                continue;
            }
            if(node.right != null) {  // right is before left
                stack.push(node.right); // 右（空节点不⼊栈）
            }
            if(node.left != null) {
                stack.push(node.left);  // // 左（空节点不⼊栈）
            }
        }
    }

    /**
     * 中序遍历（迭代法）:
     * 中序遍历是左中右，先访问的是⼆叉树顶部的节点，然后⼀层⼀层向下访问，直到到达树左⾯的最底部，再开始处理节点
     * （也就是在把节点的数值放进result数组中），这就造成了处理顺序和访问顺序是不⼀致的
     *
     * 使⽤迭代法写中序遍历，就需要借⽤指针的遍历来帮助访问节点，栈则⽤来处理节点上的元素
     */
    public static List<Integer> inorderTraversalStack (Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null){
            return result;
        }

        Deque<Node> stack = new ArrayDeque<>();
        Node cur = root;
        while (cur != null || !stack.isEmpty()){
            if (cur != null) { // 指针来访问节点，访问到最底层
                stack.push(cur); // 将访问的节点放进栈
                cur = cur.left;  // 左
            } else {
                cur = stack.pop(); // 从栈⾥弹出的数据，就是要处理的数据（放进result数组⾥的数据）
                result.add(cur.value);
                cur = cur.right;
            }
        }
        return result;
    }
    /**
     * 后序遍历（迭代法）:
     * 先序遍历是中左右，后续遍历是左右中，那么我们只需要调整⼀下先序遍历的代码顺序，就变成中右左的遍历顺序，
     * 然后在反转result数组，输出的结果顺序就是左右中了
     */
    public static void postOrderRecursion (Node root) {
        List<Integer> list = new ArrayList<>();

        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            Node node = stack.pop();
            if (node != null) {
                list.add(node.value);
            } else {
                continue;
            }
            // 相对于前序遍历，这更改⼀下⼊栈顺序 （空节点不⼊栈）
            if(node.left != null) {   // left is before leftright
                stack.push(node.left);
            }
            if(node.right != null) { // 空节点不⼊栈
                stack.push(node.right);
            }
        }

        Collections.reverse(list); // 将结果反转之后就是左右中的顺序了
        list.stream().forEach(e -> System.out.print(e + "  "));
    }

    /**
     * In the implemented strategy, we push nodes into stack following the order Top->Bottom and Left->Right.
     * Since DFS postorder transversal is Bottom->Top and Left->Right the output list should be reverted after the end of the loop.
     */
    public static void postOrderRecursionAnother (Node root) {
        LinkedList<Integer> list = new LinkedList<>();

        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            Node node = stack.pop();
            if (node != null) {
                list.addFirst(node.value);  // Add val from the start
            } else {
                continue;
            }
            if(node.left != null) {   // left is before right
                stack.push(node.left);
            }
            if(node.right != null) {
                stack.push(node.right);
            }
        }

       // Collections.reverse(list);
        list.stream().forEach(e -> System.out.print(e + "  "));
    }
}
