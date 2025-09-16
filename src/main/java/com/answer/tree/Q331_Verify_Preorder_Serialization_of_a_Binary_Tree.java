package com.answer.tree;

import java.util.*;

public class Q331_Verify_Preorder_Serialization_of_a_Binary_Tree {
    /**
     * 验证二叉树的前序序列化
     * 序列化二叉树的一种方法是使用 前序遍历 。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
     * 例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
     * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
     * 保证 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
     * 你可以认为输入格式总是有效的
     *  例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
     * 注意：不允许重建树。
     * One way to serialize a binary tree is to use preorder traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as '#'.
     * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where '#' represents a null node.
     * Given a string of comma-separated values preorder, return true if it is a correct preorder traversal serialization of a binary tree.
     * It is guaranteed that each comma-separated value in the string must be either an integer or a character '#' representing null pointer.
     * You may assume that the input format is always valid.
     *  For example, it could never contain two consecutive commas, such as "1,,3".
     * Note: You are not allowed to reconstruct the tree.
     * 示例 1:
     *  输入: preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#"
     *  输出: true
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] tests = {
                "9,3,4,#,#,1,#,#,2,#,6,#,#", // true
                "1,#",                      // false
                "9,#,#,1",                  // false
                "#",                        // true
                "7,2,#,2,#,#,#",            // true
                "9,#,92,#,#"                // true
        };
        boolean[] expected = {
                true,
                false,
                false,
                true,
                true,
                true
        };

        for (int i = 0; i < tests.length; i++) {
            boolean result = Q331_Verify_Preorder_Serialization_of_a_Binary_Tree.isValidSerialization0(tests[i]);
            System.out.println("Test " + (i + 1) + ": " + tests[i]);
            System.out.println("Expected: " + expected[i] + ", Got: " + result);
            System.out.println(result == expected[i] ? "PASS\n" : "FAIL\n");
        }
    }
    /**
     * 用一个 slots 变量记录可用的槽位数。
     * 遇到非 # 节点（数字），消耗一个槽，增加两个槽（因为加了左右子树）。
     * 遇到 # 叶子节点，只消耗一个槽。
     * 最后所有槽位消耗完（slots == 0）才是合法的序列化。
     */
    public boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        // 用槽位计数法
        int slot = 1;  // 初始有一个根节点的槽位
        for(String node : nodes){
            slot--;   // 每读一个节点，占用一个槽
            if(slot < 0){ // 槽位用完了还没结束，直接返回 false
                return false;
            }
            if(!node.equals("#")){  // 非空节点会再生成两个槽位
                slot += 2;
            }
        }
      /*  if(slot > 0) return false;
        return true;*/
        return slot == 0; // 最终所有槽位正好用完
    }
    /**
     * 在一棵二叉树中：
     *  每个空节点（ "#" ）会提供 0 个出度和 1 个入度。
     *  每个非空节点会提供 2 个出度和 1 个入度（根节点的入度是 0）。
     * 所有节点的入度之和等于出度之和。可以根据这个特点判断输入序列是否为有效的！
     *
     * 每加入一个节点 都要先减去一个入度   若该节点是非空节点 还要再加上两个出度
     * 遍历完之前，出度是大于等于入度的    1个出度认为提供一个挂载点  1个入度认为消耗一个挂载点
     */
    static public boolean isValidSerialization0(String preorder) {
        if (preorder == "#") { // 特例
            return true;
        }
        int indegree = 0, outdegree = 0; // 初始 入度出度
        String[] nodes = preorder.split(","); // 转成数组
        // 这直接判断第一个位置的两种特殊情况，循环里面不需要判断了
        if (nodes.length == 1 && nodes[0].equals("#")) {
            return true;
        }
        if (nodes.length > 1 && nodes[0].equals("#")) {
            return false;
        }
        for (int i = 0; i < nodes.length; i++) { // 遍历数组
            if (i == 0) { // 根节点
                outdegree += 2; // 根节点  出度+2
            }
            else if (nodes[i].equals("#")) { // null节点，入度+1
                indegree += 1;
            } else {               // 非空节点 入度+1 出度+2
                indegree += 1;
                outdegree += 2;
            }
            if (i != nodes.length - 1 && indegree >= outdegree) {
                return false;//一直保持indegree<outdegree，直到最后才indegree==outdegree，做不到就false
            }
        }
        return indegree == outdegree; // 最后肯定入度==出度
    }
    /**
     * 使用栈来维护槽位的变化。
     * 当遇到空节点时，仅将栈顶元素减1个；当遇到非空节点时，将栈顶元素减1个后，再向栈中压入2个。
     * 无论何时，如果栈顶元素变为0个，就立刻将栈顶弹出。
     */
    public boolean isValidSerialization1(String preorder) {
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(0);
        int i = 0;
        while (i < preorder.length()) {
            if (stack.isEmpty()) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#'){
                stack.pop();
                i++;
            } else {
                while (i < preorder.length() && preorder.charAt(i) != ',') { // 读一个数字
                    i++;
                }
                stack.pop();
                stack.push(0);
                stack.push(0);
            }
        }
        return stack.isEmpty();
    }
    /**
     * 把有效的叶子节点使用 "#" 代替。 比如把 4## 替换成 # 。此时，叶子节点会变成空节点！
     */
    static public boolean isValidSerialization2(String preorder) {
        String[] nodes = preorder.split(",");
        List<String> stack = new ArrayList<>();

        for(String node : nodes){
            stack.add(node);

            while(stack.size() >= 3 && stack.get(stack.size() - 1).equals("#") && stack.get(stack.size() - 2).equals("#")
                    && !stack.get(stack.size() - 3).equals("#")){
                stack.remove(stack.size() - 1);
                stack.remove(stack.size() - 1);
                stack.remove(stack.size() - 1);
                stack.add("#");
            }
        }
        return stack.size() == 1 && stack.get(0).equals("#");
    }
    /**
     * another form
     */
    public boolean isValidSerialization4(String preorder) {
        LinkedList<String> stack = new LinkedList<>();
        for (String s : preorder.split(",")) {
            stack.push(s);

            while (stack.size() >= 3 && stack.get(0).equals("#") && stack.get(1).equals("#") && !stack.get(2).equals("#")) {
                stack.pop();
                stack.pop();
                stack.pop();
                stack.push("#");
            }
        }
        return stack.size() == 1 && stack.pop().equals("#");
    }
}
