package com.answer.graph;

public class Q802_Find_Eventual_Safe_States {
    /**
     * There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].
     * A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).
     * Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
     * 找到最终的安全状态
     * 有一个有 n 个节点的有向图，节点按 0 到 n - 1 编号。图由一个 索引从 0 开始 的 2D 整数数组 graph表示， graph[i]是与节点 i 相邻的节点的整数数组，这意味着从节点 i 到 graph[i]中的每个节点都有一条边。
     * 如果一个节点没有连出的有向边，则该节点是 终端节点 。如果从该节点开始的所有可能路径都通向 终端节点 ，则该节点为 终端节点（或另一个安全节点）。
     * 返回一个由图中所有 安全节点 组成的数组作为答案。答案数组中的元素应当按 升序 排列。
     *
     * 示例 1：
     *  输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
     *  输出：[2,4,5,6]
     *  解释：节点 5 和节点 6 是终端节点，因为它们都没有出边。
     *       从节点 2、4、5 和 6 开始的所有路径都指向节点 5 或 6 。
     *       Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
     *       Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.
     * 示例 2：
     *  输入：graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
     *  输出：[4]
     *  解释: 只有节点 4 是终端节点，从节点 4 开始的所有路径都通向节点 4 。
     *        Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.
     *
     * graph[i] is sorted in a strictly increasing order. graph[i] 按严格递增顺序排列。
     * The graph may contain self-loops. 图中可能包含自环。
     */
}
