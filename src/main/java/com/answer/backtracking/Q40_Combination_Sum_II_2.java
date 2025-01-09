package com.answer.backtracking;

import java.util.*;

public class Q40_Combination_Sum_II_2 {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    int sum = 0;

    public List<List<Integer>> combinationSum2( int[] candidates, int target ) {
        Arrays.sort( candidates ); // 还是要sort, 比如candidates = [10,1,2,7,6,1,5]的10会直接结束
        backTracking( candidates, target, 0 );
        return res;
    }

    private void backTracking( int[] candidates, int target, int start ) {
        if ( sum == target ) {
            res.add( new ArrayList<>( path ) );
            return;
        }
        HashSet<Integer> uset = new HashSet<>(); // 参考Q491使用HashSet本层去重
        for ( int i = start; i < candidates.length && sum + candidates[i] <= target; i++ ) {
            // start
            if(uset.contains(candidates[i])){
                continue;
            }
            uset.add(candidates[i]);
            // end
            sum += candidates[i];
            path.add( candidates[i] );
            backTracking( candidates, target, i + 1 );

            int temp = path.getLast();
            sum -= temp;
            path.removeLast();
        }
    }
}
