package com.answer.trie;

import java.util.HashSet;
import java.util.Set;

public class Q421_Maximum_XOR_of_Two_Numbers_in_an_Array_1 {
    /**
     *
     */
    public static int findMaximumXOR(int[] nums) {
        Trie trie = new Trie(); //构建前缀树
        int max = 0;  //最大的异或结果

        for (int i = 0; i < nums.length; i++) {
            trie.add(nums[i]); //向树中添加数
            max = Math.max(max, trie.findMax(nums[i]));   //从树中查找与当前数的 异或和最大的数. 如果只要一个数,则他只能异或自己,一定为0
        }
        return max;
    }
    /**
     * 前缀树
     */
    static class Trie{
        Trie[] next;//前缀树俩个节点,一个0,一个1

        public Trie() {
            this.next = new Trie[2];
        }
        //向前缀树中添加元素
        public void add(int num){
            Trie root = this;
            //保留31位整数的状态到前缀树中
            for(int i = 30; i >= 0; i--){
                //取出num中第 i+1 位的状态
                int index = num >> i & 1;
                /**
                 * 这是个坑,特别标注!!!如果原来有,就不需要new新的,不然会覆盖原来的
                 */
                if(root.next[index] == null){
                    root.next[index] = new Trie();
                }
                root = root.next[index];
            }
        }
        //查找前缀树中与当前数异或最大的元素
        public int findMax(int num){
            Trie root = this;
            int res = 0;
            for(int i = 30; i >= 0; i--){
                int index = num >> i & 1;//取出num中第 i+1 位的状态
                index ^= 1;//如果这位为1,那么他希望异或0,如果为0,希望异或1,所以把期望坐标取反

                if(root.next[index] != null){    //如果有期望的值,肯定走期望
                    res |= (1 << i); //结果加上这个值
                }else{
                    index ^= 1;  //说明没有期望的值,只能走另一条路. 自然不需要加,当前位的值
                }
                root = root.next[index];
            }
            return res;
        }
    }
}
