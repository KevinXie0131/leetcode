package com.answer.greedy;

import java.util.*;

public class Q455_Assign_Cookies {
    /**
     * Greedy algorithm
     */
    public int findContentChildren(int[] g, int[] s) {
        int res = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int m = g.length;
        int n = s.length;

        for(int i = 0, j = 0; i < m && j < n; j++){
            if(s[j] >= g[i]){
                res++;
                i++;
            }
        }

        return res;
    }
}
