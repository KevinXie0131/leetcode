package com.answer.dfs_bfs;

import com.template.Node;
import java.util.*;

public class Q133_Clone_Graph {
    /**
     * 先通过 DFS 对每个点进行拷贝，接下来遍历每个点，将邻居节点转换为拷贝节点。
     */
    Map<Node, Node> map1 = new HashMap<>();

    public Node cloneGraph_5(Node node) {
        if(node == null) return null;
        dfs1(node);
        for(Map.Entry<Node, Node> entry : map1.entrySet()){
            Node from = entry.getKey();
            Node to = entry.getValue();
            for(Node neighbor : from.neighbors){
                to.neighbors.add(map1.get(neighbor));
            }
        }
        return map1.get(node);
    }
    void dfs1(Node node) {
        map1.put(node, new Node(node.getValue()));
        for(Node neighbor : node.neighbors){
            if (!map1.containsKey(neighbor)) {
                dfs1(neighbor);
            }
       }
    }
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
        // If the node was already visited before. Return the clone from the visited dictionary.
        if(map.containsKey(node)){
            return map.get(node);
        }
        // 克隆节点，注意到为了深拷贝我们不会克隆它的邻居的列表
        // Create a clone for the given node. Note that we don't have cloned neighbors as of now, hence [].
        Node clone = new Node(node.getValue());
        // 哈希表存储, 建立源节点到克隆节点的映射
        // The key is original node and value being the clone node.
        map.put(node, clone);
        // 遍历该节点的邻居并更新克隆节点的邻居列表
        // Iterate through the neighbors to generate their clones and prepare a list of cloned neighbors to be added to the cloned node.
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
        // Hash map to save the visited node and it's respective clone as key and value respectively. This helps to avoid cycles.
        HashMap <Node, Node> visited = new HashMap <> ();
        Node clone = new Node(node.getValue());
        // 克隆第一个节点并存储到哈希表中
        // Clone the node and put it in the visited dictionary.
        visited.put(node, clone);

        Deque<Node> queue = new ArrayDeque<>();
        // 将题目给定的节点添加到队列
        // Put the first node in the queue
        queue.offer(node);
        // 广度优先搜索
        // Start BFS traversal
        while(!queue.isEmpty()){
            // 取出队列的头节点
            // Pop a node say "n" from the from the front of the queue.
            Node temp = queue.poll();
            // 遍历该节点的邻居
            // Iterate through all the neighbors of the node "n"
            for(Node n : temp.neighbors){
                if(!visited.containsKey(n)){
                    // 如果没有被访问过，就克隆并存储在哈希表中
                    // Clone the neighbor and put in the visited, if not present already
                    visited.put(n, new Node(n.getValue()));
                    // 将邻居节点加入队列中
                    // Add the newly encountered node to the queue.
                    queue.offer(n);
                }
                // 更新当前节点的邻居列表
                // Add the clone of the neighbor to the neighbors of the clone node "n".
                visited.get(temp).neighbors.add(visited.get(n));
            }

        }
        return clone;
    }
}
