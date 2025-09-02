package com.answer.backtracking;

import java.util.*;

public class BacktrackingTemplate {
    /**
     * "abc" -> [, c, b, bc, a, ac, ab, abc]
     */
    public List<String> subset(String s){
        List<String> res = new ArrayList<>();
        buildSubset(res, s, new StringBuilder(), 0);
        return res;
    }

    private void buildSubset(List<String> res, String s, StringBuilder sb, int index){
        if(index == s.length()){
            res.add(sb.toString());
            return;
        }
        // not select
        buildSubset(res, s, sb, index + 1);
        // select
        sb.append(s.charAt(index));
        buildSubset(res, s, sb, index + 1);
        sb.deleteCharAt(sb.length() - 1);
    //  buildSubset(res, s, new StringBuilder(sb.toString() + s.charAt(index)) , index + 1); // works too
    }
}
