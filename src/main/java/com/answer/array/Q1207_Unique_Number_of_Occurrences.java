package com.answer.array;

public class Q1207_Unique_Number_of_Occurrences {
    /**
     * 可以用数组来做哈希: 此时可以定义一个2001大小的数组，例如int count[2001];，统计的时候，将arr[i]统一加1000，这样就可以统计arr[i]的出现频率了。
     *
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
}
