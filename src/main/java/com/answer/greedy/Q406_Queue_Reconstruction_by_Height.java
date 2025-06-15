package com.answer.greedy;

import java.util.*;

public class Q406_Queue_Reconstruction_by_Height {
    /**
     * 根据身高重建队列
     * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
     * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
     * 示例 1：
     * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
     * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
     * 解释：
     * 编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
     * 编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
     * 编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
     * 编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
     * 编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
     * 编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
     * 因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
     */
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
        // 将每个人按照身高从大到小进行排序，处理身高相同的人 按照 K个数升序排序
/*        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] p1, int[] p2) {
                if (p1[0] != p2[0]) {
                    return p2[0] - p1[0];
                } else {
                    return p1[1] - p2[1];
                }
            }
        });*/

        List<int[]> list = new ArrayList<>(); // 用ArrayList比LinkedList效率会高
        for(int[] p : people){
            list.add(p[1], p);  //Linkedlist.add(index, value)，會將value插入到指定index裡。
        }

        //  return list.toArray(new int[people.length][]);
        return list.toArray(new int[0][]);
    }
    /**
     * 解题思路：先排序再插入
     * 1.排序规则：按照先H高度降序，K个数升序排序
     * 2.遍历排序后的数组，根据K插入到K的位置上
     *
     * 核心思想：高个子先站好位，矮个子插入到K位置上，前面肯定有K个高个子，矮个子再插到前面也满足K的要求
     */
    // [7,0], [7,1], [6,1], [5,0], [5,2], [4,4]
    // 再一个一个插入。
    // [7,0]
    // [7,0], [7,1]
    // [7,0], [6,1], [7,1]
    // [5,0], [7,0], [6,1], [7,1]
    // [5,0], [7,0], [5,2], [6,1], [7,1]
    // [5,0], [7,0], [5,2], [6,1], [4,4], [7,1]

}
