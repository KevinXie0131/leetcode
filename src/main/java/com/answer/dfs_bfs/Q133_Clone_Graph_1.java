package com.answer.dfs_bfs;

import java.util.*;

public class Q133_Clone_Graph_1 {
    /**
     * 深度优先搜索
     * 为了防止多次遍历同一个节点，陷入死循环，我们需要用一种数据结构记录已经被克隆过的节点。
     * 使用一个哈希表存储所有已被访问和克隆的节点。哈希表中的 key 是原始图中的节点，value 是克隆图中的对应节点。
     * 从给定节点开始遍历图。如果某个节点已经被访问过，则返回其克隆图中的对应节点。
     * 如下图，我们给定无向边边 A - B，表示 A 能连接到 B，且 B 能连接到 A。如果不对访问过的节点做标记，则会陷入死循环中。
     */
    private HashMap <Node, Node> visited = new HashMap <> ();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }
        // 如果该节点已经被访问过了，则直接从哈希表中取出对应的克隆节点返回
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        // 克隆节点，注意到为了深拷贝我们不会克隆它的邻居的列表
        Node cloneNode = new Node(node.value, new ArrayList());
        // 哈希表存储
        visited.put(node, cloneNode);
        // 遍历该节点的邻居并更新克隆节点的邻居列表
        for (Node neighbor: node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        return cloneNode;
    }
    /**
     * 广度优先遍历
     * 都需要借助哈希表记录被克隆过的节点来避免陷入死循环。
     */
    public Node cloneGraph1(Node node) {
        if (node == null) return node;

        HashMap<Node, Node> visited = new HashMap();
        LinkedList<Node> queue = new LinkedList<Node> (); // 将题目给定的节点添加到队列
        queue.offer(node);
        visited.put(node, new Node(node.value, new ArrayList())); // 克隆第一个节点并存储到哈希表中
        // 广度优先搜索
        while (!queue.isEmpty()) {
            Node n = queue.poll();// 取出队列的头节点

            for (Node neighbor: n.neighbors) {  // 遍历该节点的邻居
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, new Node(neighbor.value, new ArrayList())); // 如果没有被访问过，就克隆并存储在哈希表中
                    queue.offer(neighbor);  // 将邻居节点加入队列中
                }
                visited.get(n).neighbors.add(visited.get(neighbor));  // 更新当前节点的邻居列表
            }
        }
        return visited.get(node);
    }

    /**
     * 题目里说明了元素个数不会超过100，而且元素的值等于其下标（值不会重复），所以DFS中的HashMap可以替换为数组。
     */
    Node[] nodes = new Node[100];

    public Node cloneGraph2(Node node) {
        if (node == null) return null;
        if (nodes[node.value - 1] != null) {
            return nodes[node.value - 1];
        }

        List<Node> neighbors = node.neighbors;

        ArrayList<Node> newNeighbors = new ArrayList<>();
        Node cloned = new Node(node.value, newNeighbors);
        nodes[cloned.value - 1] = cloned;

        for (Node n : neighbors) {
            Node cur = cloneGraph(n);
            newNeighbors.add(cur);
        }
        return cloned;
    }
    /**
     * 广度优先搜索
     */
    public Node cloneGraph4(Node node) {
        if(node == null)return null;    // 空节点不拷贝
        Node[] cloneNodes = new Node[101];  // 存储每一个拷贝过的节点
        cloneNodes[node.value] = new Node(node.value, new ArrayList<>());  // 克隆起点节点

        Queue<Node> queue = new LinkedList<>();     // 用于广度优先搜索的队列
        queue.offer(node);  // 起点节点入队
        Node cur;   // 获取当前处理的节点

        while(!queue.isEmpty()){
            cur = queue.poll();     // 从队列中获取一个待处理的节点
            Node cloneNode = cloneNodes[cur.value];   // 待处理的节点一定是克隆好了的，直接获取其克隆节点
            for(Node neighbor: cur.neighbors){
                // 处理当前节点的邻接节点
                if(cloneNodes[neighbor.value] == null){
                    cloneNodes[neighbor.value] = new Node(neighbor.value, new ArrayList<>());  // 如果邻接节点未拷贝，则拷贝
                    queue.offer(neighbor);      // 未拷贝的节点说明还没有处理，加入队列等待处理
                }
                cloneNode.neighbors.add(cloneNodes[neighbor.value]);  // 将邻接节点的拷贝节点加入当前节点的拷贝节点的邻接列表
            }
        }
        return cloneNodes[node.value];    // 返回起点节点的克隆节点
    }

    public class Node {
        public ArrayList<Node> neighbors;
        int value;

        public Node (int value, ArrayList<Node> neighbors){
            this.neighbors = neighbors;
            this.value = value;
        }
    }
}
