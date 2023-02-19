package com.answer.dfs_bfs;

import com.template.Node;
import java.util.*;

public class Q133_Clone_Graph {

    /**
     * Approach 1: Depth First Search - Recursion
     */
    Map<Node, Node> map = new HashMap<>();

    public Node cloneGraph_0(Node node) {
        if(node == null) return null;
        return dfs(node);
    }
    public Node dfs(Node node){
        // 如果该节点已经被访问过了，则直接从哈希表中取出对应的克隆节点返回
        if(map.containsKey(node)){
            return map.get(node);
        }
        // 克隆节点，注意到为了深拷贝我们不会克隆它的邻居的列表
        Node clone = new Node(node.getValue());
        // 哈希表存储, 建立源节点到克隆节点的映射
        map.put(node, clone);
        // 遍历该节点的邻居并更新克隆节点的邻居列表
        for(Node n : node.neighbors){
            clone.neighbors.add(dfs(n));
        }
        return clone;
    }
    /**
     * Approach 1: Depth First Search - use stack
     */
    public Node cloneGraph_1(Node node) {
        if(node == null) return null;
        // 使用一个哈希表 visited 存储所有已被访问和克隆的节点。哈希表中的 key 是原始图中的节点，value 是克隆图中的对应节点。
        HashMap <Node, Node> visited = new HashMap <> ();
        Node clone = new Node(node.getValue());
        // 克隆第一个节点并存储到哈希表中
        visited.put(node, clone);

        Deque<Node> stack = new ArrayDeque<>();
        // 将题目给定的节点添加到队列
        stack.push(node);
        // 广度优先搜索
        while(!stack.isEmpty()){
            // 取出队列的头节点
            Node temp = stack.pop();
            // 遍历该节点的邻居
            for(Node n : temp.neighbors){
                if(!visited.containsKey(n)){
                    // 如果没有被访问过，就克隆并存储在哈希表中
                    visited.put(n, new Node(n.getValue()));
                    // 将邻居节点加入队列中
                    stack.push(n);
                }
                // 更新当前节点的邻居列表
                visited.get(temp).neighbors.add(visited.get(n));
            }

        }
        return clone;
    }
    /**
     * Approach 2: Breadth First Search
     */
    public Node cloneGraph(Node node) {
        if(node == null) return null;
       // 使用一个哈希表 visited 存储所有已被访问和克隆的节点。哈希表中的 key 是原始图中的节点，value 是克隆图中的对应节点。
        HashMap <Node, Node> visited = new HashMap <> ();
        Node clone = new Node(node.getValue());
        // 克隆第一个节点并存储到哈希表中
        visited.put(node, clone);

        Deque<Node> queue = new ArrayDeque<>();
        // 将题目给定的节点添加到队列
        queue.offer(node);
        // 广度优先搜索
        while(!queue.isEmpty()){
            // 取出队列的头节点
            Node temp = queue.poll();
            // 遍历该节点的邻居
            for(Node n : temp.neighbors){
                if(!visited.containsKey(n)){
                    // 如果没有被访问过，就克隆并存储在哈希表中
                    visited.put(n, new Node(n.getValue()));
                    // 将邻居节点加入队列中
                    queue.offer(n);
                }
                // 更新当前节点的邻居列表
                visited.get(temp).neighbors.add(visited.get(n));
            }

        }
        return clone;
    }
}
