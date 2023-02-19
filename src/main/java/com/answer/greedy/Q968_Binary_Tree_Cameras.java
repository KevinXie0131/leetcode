package com.answer.greedy;

import com.template.TreeNode;

public class Q968_Binary_Tree_Cameras {

    /**
     * Approach 2: Greedy
     * If a node has children that are not covered by a camera, then we must place a camera here.
     * Additionally, if a node has no parent and it is not covered, we must place a camera here.
     */
    int result = 0;

    public int minCameraCover(TreeNode root) {
        if(dfs(root) == 0){
            result++;
        }
        return result;
    }

    public int dfs(TreeNode root){
        if(root == null) return 2; // 根据子节点的状态返回父节点的状态；

        int left = dfs(root.left);
        int right = dfs(root.right);

        if(right == 2 && left == 2){ // 3.子节点有一个状态是2，则父节点返回 1 （因为子节点有一个是有摄像头的所以 父节点是被监控了的）
            return 0;
        }
        if(right == 0 || left == 0){ // 1.子节点有一个状态为0，则父节点返回 2 父节点需要一个摄像头 res++（根据贪心思想 由上面的节点监控下面的节点比较省摄像头）；
            result++;
            return 1;
        }
        if(right == 1 || left == 1){ // 2.左右2个子节点状态都是1，则父节点返回0 （因为子节点都没有摄像头，所以父节点需要被他的父节点监控；所以他是未被监控0）；
            return 2;
        }
        return -1;
    }
}
