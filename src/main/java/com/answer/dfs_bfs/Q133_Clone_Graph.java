package com.answer.dfs_bfs;

import com.template.Node;
import java.util.*;

public class Q133_Clone_Graph {
    /**
     * 克隆图
     * Given a reference of a node in a connected undirected graph. Return a deep copy (clone) of the graph.
     * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
     * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
     * class Node {
     *     public int val;
     *     public List<Node> neighbors;
     * }
     * Test case format:
     * For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.
     * An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
     * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
     * 测试用例格式：
     * 简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1（val = 1），第二个节点值为 2（val = 2），以此类推。该图在测试用例中使用邻接列表表示。
     * 邻接列表 是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。
     * 给定节点将始终是图中的第一个节点（值为 1）。你必须将 给定节点的拷贝 作为对克隆图的引用返回。
     * There are no repeated edges and no self-loops in the graph.
     * The Graph is connected and all nodes can be visited starting from the given node.
     * 图中没有重复的边，也没有自环。
     * 图是连通图，你可以从给定节点访问到所有节点。
     *
     * 输入：adjList = [[2,4],[1,3],[2,4],[1,3]]
     * 输出：[[2,4],[1,3],[2,4],[1,3]]
     * 解释：图中有 4 个节点。
     *      节点 1 的值是 1，它有两个邻居：节点 2 和 4 。
     *      节点 2 的值是 2，它有两个邻居：节点 1 和 3 。
     *      节点 3 的值是 3，它有两个邻居：节点 2 和 4 。
     *      节点 4 的值是 4，它有两个邻居：节点 1 和 3 。
     */
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
