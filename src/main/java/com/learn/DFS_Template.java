package com.learn;

public class DFS_Template {
    /**
     * DFS - Template I - Recursion
     *
     * An important difference: the traversal order:
     * Different from BFS, the nodes you visit earlier might not be the nodes which are closer to the root node. As a result,
     * the first path you found in DFS might not be the shortest path.
     */
    /*
     * Return true if there is a path from cur to target.
     */
    /**
    boolean DFS(Node cur, Node target, Set<Node> visited) {
        return true if cur is target;
        for (next : each neighbor of cur) {
            if (next is not in visited) {
                add next to visited;
                return true if DFS(next, target, visited) == true;
            }
        }
        return false;
    }
     */

    /**
     * DFS - Template II
     *
     * The advantage of the recursion solution is that it is easier to implement. However, there is a huge disadvantage:
     * if the depth of recursion is too high, you will suffer from stack overflow.
     */
    /*
     * Return true if there is a path from cur to target.
     */
    /**
    boolean DFS(int root, int target) {
        Set<Node> visited;
        Stack<Node> stack;
        add root to stack;
        while (stack is not empty) {
            Node cur = the top element in stack;
            remove the cur from the stack;
            return true if cur is target;
            for (Node next : the neighbors of cur) {
                if (next is not in visited) {
                    add next to visited;
                    add next to stack;
                }
            }
        }
        return false;
    }
     */
}
