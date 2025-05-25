package com.answer.backtracking;

import java.util.Arrays;
import java.util.Collections;

public class Q473_Matchsticks_to_Square {
    /**
     * 火柴拼正方形: 你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。你要用 所有的火柴棍
     * 拼成一个正方形。你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
     * 如果你能使这个正方形，则返回 true ，否则返回 false 。
     */
    public static void main(String[] args) {
        int[] matchsticks = {1,1,2,2,2}; // 输出: true 解释: 能拼成一个边长为2的正方形，每边两根火柴。
        System.out.println(makesquare(matchsticks));
        int[] matchsticks1 = {3,3,3,3,4}; // 输出: false 解释: 不能用所有火柴拼成一个正方形。
        System.out.println(makesquare(matchsticks1));
    }
    /**
     * You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick.
     * You want to use all the matchsticks to make one square. You should not break any stick,
     * but you can link them up, and each matchstick must be used exactly one time.
     * Return true if you can make this square and false otherwise
     * 一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。你要用 所有的火柴棍 拼成一个正方形。
     * 你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
     * 如果你能使这个正方形，则返回 true ，否则返回 false 。
     */
    public static boolean makesquare(int[] matchsticks) {
        int totalLen = Arrays.stream(matchsticks).sum();
        int edgeLen = totalLen / 4;
        if(totalLen % 4 != 0){
            return false;
        }
        //先排序
    //    Arrays.sort(matchsticks);
        // 如果我们让 nums[] 内的元素递减排序，先让值大的元素选择桶，这样可以增加剪枝的命中率，从而降低回溯的概率
        Integer[] array = Arrays.stream( matchsticks ).boxed().toArray( Integer[]::new );
        Arrays.sort(array, Collections.reverseOrder());
        matchsticks =  Arrays.stream(array).mapToInt(Integer::intValue).toArray();

        return backtracking(matchsticks, 0, new int[4], edgeLen);
    }

    public static  boolean backtracking(int[] matchsticks, int index, int edge[], int edgeLen){
        if(index == matchsticks.length){
            if(edge[0] == edgeLen && edge[1] == edgeLen && edge[2] == edgeLen && edge[3] == edgeLen){
                return true;
            }else {
                return false; // 其实这个地方不需要判断，因为当 index == matchsticks.length 时，所有球已经按要求装入所有桶，所以肯定是一个满足要求的解
            }
        }

        for(int i = 0; i < 4; i++){
            if(edge[i] + matchsticks[index] > edgeLen){  // Time Limit Exceeded for [1,1,1,1,1,1,1,1,1,1,1,1,1,1,102]
                continue;
            }
            //如果把当前火柴放到edge[i]这个边上，他的长度大于edgeLen，我们直接跳过。或者
            //edge[i] == edge[i - 1]即上一个分支的值和当前分支的一样，上一个分支没有成功，
            //说明这个分支也不会成功，直接跳过即可。
            if (edge[i] + matchsticks[index] > edgeLen || (i > 0 && edge[i] == edge[i - 1])  ){
                continue;
            }

            edge[i] += matchsticks[index];
            boolean result = backtracking(matchsticks, index + 1, edge, edgeLen);
            if(result == true){
                return  result; // must return true here
            }
            edge[i] -= matchsticks[index];
        }
        return false;
    }
}
