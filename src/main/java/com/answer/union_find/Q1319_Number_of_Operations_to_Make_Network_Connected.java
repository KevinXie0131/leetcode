package com.answer.union_find;

public class Q1319_Number_of_Operations_to_Make_Network_Connected {
    /**
     * There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi]
     * represents a connection between computers ai and bi. Any computer can reach any other computer directly or indirectly through the network.
     * You are given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them
     * between any pair of disconnected computers to make them directly connected.
     * Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.
     * 连通网络的操作次数
     * 用以太网线缆将 n 台计算机连接成一个网络，计算机的编号从 0 到 n-1。线缆用 connections 表示，其中 connections[i] = [a, b] 连接了计算机 a 和 b。
     * 网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。
     * 给你这个计算机网络的初始布线 connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。
     * 请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回 -1 。
     *
     * 输入：n = 4, connections = [[0,1],[0,2],[1,2]]
     * 输出：1
     * 解释：拔下计算机 1 和 2 之间的线缆，并将它插到计算机 1 和 3 上。
     */
    public int makeConnected(int n, int[][] connections) {
        return 0;
    }
}
