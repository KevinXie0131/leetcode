package com.answer.array;

import java.util.*;

public class Q1346_Check_If_N_and_Its_Double_Exist {
    /**
     * 检查是否存在两个整数 N 和 M，满足 N 是 M 的两倍（即，N = 2 * M）。
     * 更正式地，检查是否存在两个下标 i 和 j 满足：
     *    i != j
     *    0 <= i, j < arr.length
     *    arr[i] == 2 * arr[j]
     */
    public static void main(String[] args) {
        int[] arr = {10,2,5,3};
      //  int[] arr = {3,1,7,11};
       // int[] arr = {-2,0,10,-19,4,6,-8};
        System.out.println(checkIfExist_1(arr));
    }
    /**
     * 暴力法
     * 直接遍历所有的数字对，判断一个数字是否是另一个数字的两倍。注意要用乘法判断，除法判断会有除零的问题。
     */
    public boolean checkIfExist_0(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr.length; ++j) {
                if (i != j && arr[i] * 2 == arr[j]){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 排序 + 二分查找
     */
    static public boolean checkIfExist(int[] arr) {
       Arrays.sort(arr);

       for(int i = 0; i < arr.length; i++){
           int index = Arrays.binarySearch(arr, arr[i] * 2);
           if(index >= 0 && index <= arr.length - 1 && index != i){ // 确保 N 和 M 不是同一个元素
               return true;
           }
       }
       return false;
    }
    /**
     * 遍历每个数字时都往set中添加该数字的两倍
     * 当遍历到数字i时
     *  set.contains(i) 会检查之前是否遇到过 该数字二分之一 大小的数字
     *  set.contains(i * 4) 会检查之前是否遇到过 该数字两倍 大小的数字
     */
    static public boolean checkIfExist_1(int[] arr) {
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < arr.length; i++){
            if(set.contains(arr[i]) || set.contains(arr[i] * 4)){
                return true;
            }
            set.add(arr[i] * 2);
        }
        return false;
    }
}
