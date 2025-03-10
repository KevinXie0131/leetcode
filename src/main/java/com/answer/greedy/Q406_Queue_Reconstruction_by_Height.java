package com.answer.greedy;

import java.util.*;

public class Q406_Queue_Reconstruction_by_Height {
    public static void main(String[] args) {
        int[][] people = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        int[][] res = reconstructQueue(people);
    }
    /**
     * Approach 1: Greedy
     *
     * + Sort people:
     *   - In the descending order by height.
     *   - Among the guys of the same height, in the ascending order by k-values.
     * + Take guys one by one, and place them in the output array at the indexes equal to their k-values.
     * + Return output array.
     *
     * 本题有两个维度，h和k，看到这种题目一定要想如何确定一个维度，然后再按照另一个维度重新排列。如果两个维度一起考虑一定会顾此失彼。
     * 那么按照身高h来排序呢，身高一定是从大到小排（身高相同的话则k小的站前面），让高个子在前面。
     * 此时我们可以确定一个维度了，就是身高，前面的节点一定都比本节点高！
     * 按照身高排序之后，优先按身高高的people的k来插入，后序插入节点也不会影响前面已经插入的节点，最终按照k的规则完成了队列。
     *
     * 局部最优：优先按身高高的people的k来插入。插入操作过后的people满足队列属性
     * 全局最优：最后都做完插入操作，整个队列满足题目队列属性
     */
    public static int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> { // 身高从大到小排（身高相同k小的站前面）
            if(a[0] == b[0]){
                return a[1] - b[1];// a - b 是升序排列，故在a[0] == b[0]的狀況下，會根據k值升序排列
            }
            return b[0] - a[0]; //b - a 是降序排列，在a[0] != b[0]，的狀況會根據h值降序排列
        });
        List<int[]> list = new LinkedList<>(); // 用LinkedList比ArrayList效率会高
        for(int[] p : people){
            list.add(p[1], p);  //Linkedlist.add(index, value)，會將value插入到指定index裡。
        }

        //  return list.toArray(new int[people.length][]);
        return list.toArray(new int[0][]);
    }
}
