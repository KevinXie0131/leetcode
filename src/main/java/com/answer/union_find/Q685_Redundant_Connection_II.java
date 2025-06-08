package com.answer.union_find;

import java.util.*;

public class Q685_Redundant_Connection_II { // Hard 困难
    /**
     * 冗余连接 II
     * In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all
     * other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.
     * 在本问题中，有根树指满足以下条件的 有向 图。该树只有一个根节点，所有其他节点都是该根节点的后继。该树除了根节点之外的每一个节点都有且只有一个父节点，而根节点没有父节点。
     *
     * 输入一个有向图，该图由一个有着 n 个节点（节点值不重复，从 1 到 n）的树及一条附加的有向边构成。附加的边包含在 1 到 n 中的两个不同顶点间，这条附加的边不属于树中已存在的边。
     * 结果图是一个以边组成的二维数组 edges 。 每个元素是一对 [ui, vi]，用以表示 有向(directed) 图中连接顶点 ui 和顶点 vi 的边，其中 ui 是 vi 的一个父节点。
     * 返回一条能删除的边，使得剩下的图是有 n 个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。
     */
    public static void main(String[] args) {
        int[][] inputEdges = {{1,2},{2,3},{3,4},{4,1},{1,5}}; // 输出：[4,1]
        System.out.println(Arrays.toString(findRedundantDirectedConnection(inputEdges)));
    }
    /**
     * 本题的本质是 ：有一个有向图，是由一颗有向树 + 一条有向边组成的 （所以此时这个图就不能称之为有向树），
     * 现在让我们找到那条边 把这条边删了，让这个图恢复为有向树。
     */
    public static int[] findRedundantDirectedConnection(int[][] inputEdges) {
        /*
         * 冗余连接II。主要问题是存在入度为2或者成环，也可能两个问题同时存在。
         * 1.判断入度为2的边
         * 2.判断是否成环（并查集）
         */
        int n = inputEdges.length;
        List<Edge> edges = new ArrayList<>();
        Node[] nodeMap = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodeMap[i] = new Node();
        }
        Integer doubleIn = null;
        for (int i = 0; i < n; i++) {
            int s = inputEdges[i][0];
            int t = inputEdges[i][1];
            //记录入度
            nodeMap[t].in++;
            if (nodeMap[t].in >= 2) {
                doubleIn = t;
            }
            Edge edge = new Edge(s, t);
            edges.add(edge);
        }
        Edge result = null;
        //存在入度为2的节点，既要消除入度为2的问题同时解除可能存在的环
        if (doubleIn != null) {
            List<Edge> doubleInEdges = new ArrayList<>(); // 记录节点入度
            for (Edge edge : edges) {
                if (edge.t == doubleIn) {
                    doubleInEdges.add(edge);
                }
                if (doubleInEdges.size() == 2) {
                    break; // 记录入度为2的边（如果有的话就两条边）
                            // 找入度为2的节点所对应的边，注意要倒序，因为优先删除最后出现的一条边
                }
            }
            // 情况一、情况二
            Edge edge = doubleInEdges.get(1);  // 放在vec里的边已经按照倒叙放的，所以这里就优先删vec[0]这条边
            if (isTreeWithExclude(edges, edge, nodeMap)) {
                result = edge;
            } else {
                result = doubleInEdges.get(0);
            }
        } else {
            //不存在入度为2的节点,则只需要解除环即可
            result = getRemoveEdge(edges, nodeMap);  // 处理情况三
                                                    // 明确没有入度为2的情况，那么一定有有向环，找到构成环的边返回就可以了
        }
        return new int[]{result.s, result.t};
    }
    // 判断删一个边之后是不是有向树
    // 将所有边的两端节点分别加入并查集，遇到要 要删除的边则跳过，如果遇到即将加入并查集的边的两端节点 本来就在并查集了，说明构成了环。
    // 如果顺利将所有边的两端节点（除了要删除的边）加入了并查集，则说明 删除该条边 还是一个有向树
    public static boolean isTreeWithExclude(List<Edge> edges, Edge exculdEdge, Node[] nodeMap) { // 删一条边之后判断是不是树
        // 初始化并查集
        DisJoint disjoint = new DisJoint(nodeMap.length + 1);

        for (Edge edge : edges) {
            if (edge == exculdEdge) {
                continue;
            }
            //成环则不是树
            if (disjoint.isSame(edge.s, edge.t)) { // 构成有向环了，一定不是树
                return false;
            }
            disjoint.join(edge.s, edge.t);
        }
        return true;
    }
    // 确定图中一定有了有向环，那么要找到需要删除的那条边： 将所有边的两端节点分别加入并查集，
    // 如果遇到即将加入并查集的边的两端节点 本来就在并查集了，说明构成了环。
    public static Edge getRemoveEdge(List<Edge> edges, Node[] nodeMap) { // 在有向图里找到删除的那条边，使其变成树
        int length = nodeMap.length;
        // 初始化并查集
        DisJoint disjoint = new DisJoint(length);

        for (Edge edge : edges) {  // 遍历所有的边
            if (disjoint.isSame(edge.s, edge.t)){
                return edge;  // 构成有向环了，就是要删除的边
            }
            disjoint.join(edge.s, edge.t);
        }
        return null;
    }

    static class Edge {
        int s;
        int t;

        public Edge(int s, int t) {
            this.s = s;
            this.t = t;
        }
    }

    static class Node {
        int id;
        int in;
        int out;
    }
}
