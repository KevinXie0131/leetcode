package com.answer.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q1207_Unique_Number_of_Occurrences {
    /**
     * 可以用数组来做哈希: 此时可以定义一个2001大小的数组，例如int count[2001];，统计的时候，将arr[i]统一加1000，这样就可以统计arr[i]的出现频率了。
     * -1000 <= arr[i] <= 1000
     * 1 <= arr.length <= 1000
     */
    public boolean uniqueOccurrences(int[] arr) {
        int[] count = new int[2001];  // 统计数字出现的频率
        for(int i = 0 ; i < arr.length; i++){
            count[arr[i] + 1000]++; // 防止负数作为下标
        }

        boolean[] flag = new boolean[1001]; // 看相同频率是否重复出现
        for(int i = 0 ; i< count.length; i++){
            if(count[i] > 0){
                if(flag[count[i]] == false){
                    flag[count[i]] = true;
                }else {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * 使用数组
     * 题中提示中数组的大小和长度都有了限制，所以我们还可以使用数组。
     */
    public boolean uniqueOccurrences_3(int[] arr) {
        int[] count = new int[2001];
        for (int i = 0; i < arr.length; i++) {
            count[1000 + arr[i]]++;
        }
        Set<Integer> set = new HashSet<>();
        for (int value : count) {
            if (value == 0)
                continue;
            if (!set.add(value))//如果存储失败，说明有重复的
                return false;
        }
        return true;
    }
    /**
     * 哈希表
     * 首先使用哈希表记录每个数字的出现次数；随后再利用新的哈希表，统计不同的出现次数的数目。
     * 如果不同的出现次数的数目等于不同数字的数目，则返回 true，否则返回 false。
     */
    public boolean uniqueOccurrences_1(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : arr){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Set<Integer> set = new HashSet<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            set.add(entry.getValue());
        }

        return map.size() == set.size();
    }
    /**
     * 把出现次数的数组放到集合set中，如果有重复的就会被替换掉，那么set的大小肯定和出现次数的数组长度不一样。
     * 否则如果没有重复的，他们的长度肯定是一样的
     */
    public boolean uniqueOccurrences_2(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : arr){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Set<Integer> set = new HashSet<>(map.values());
        return map.size() == set.size();
    }

}
