package com.answer.array;

import java.util.*;

public class Q228_Summary_Ranges {
    public static void main(String[] args) {
        int[] nums = {0,1,2,4,5,7};
        List<String> res = summaryRanges(nums);
        System.out.println(res);
    }
    public static List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        int len = nums.length;

        for(int i = 0; i < len; i++){
            int l = i;
            while(i < len - 1 && nums[i + 1] == nums[i] + 1){
                i++;
            }
            int r = i;
            if(l != r){
                list.add(nums[l] + "->" + nums[r]);
            }else{
                list.add(nums[r] + "");
            }
        }
        return list;
    }
}
