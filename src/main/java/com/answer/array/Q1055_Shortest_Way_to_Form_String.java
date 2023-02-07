package com.answer.array;

public class Q1055_Shortest_Way_to_Form_String {
    public static void main(String[] args) {
        String source = "xyz", target = "xzyxz";
        System.out.println(shortestWay(source, target));
    }

    /**
     * Approach 3: Two Pointers + Greedy
     *
     * 子序列（subsequence）：并不要求连续，序列 [4, 6, 5] 是 [1, 2, 4, 3, 7, 6, 5] 的一个子序列。(子序列（subsequence）：并不要求在原序列中连续)
     * 子串（substring、subarray）：一定是连续的 (子串（substring、subarray）：在原序列中一定是连续的)
     *
     * 注意事项：按照 LeetCode 的习惯，子序列（subsequence）不必连续，子数组（subarray）或子字符串 （substring）必须连续
     */
    public static int shortestWay(String source, String target) {
        char[] str1 = source.toCharArray();
        char[] str2 = target.toCharArray();
        int j = 0 ;
        int count = 0 ;
        while( j < str2.length){
            int prev = j ;
            for(int i = 0 ; i < str1.length ; i++){
                if( j < str2.length && str1[i] == str2[j]) {
                    j++;
                }
            }
            if( prev == j ) { //如果j没有移动
                return -1;
            }
            count++;
        }

        return count;
    }
}
