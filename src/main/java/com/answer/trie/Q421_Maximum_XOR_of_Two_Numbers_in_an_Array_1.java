package com.answer.trie;

import java.util.HashSet;
import java.util.Set;

public class Q421_Maximum_XOR_of_Two_Numbers_in_an_Array_1 {
    public static void main(String[] args) {
       int[] nums = {3,10,5,25,2,8};
       int res = findMaximumXOR(nums);
       System.out.println(res);
    }
    /**
     * 构建一个前缀树, 每个树有俩个节点,一个代表0,一个代表1, 即前缀树有一个长度为2的前缀树数组
     * Trie[0]代表为0的节点,Trie[1]代表为1的节点, 树的深度为31
     *
     * 贪心策略：
     *  总之每一位的数一定更希望遇到和它不同的那个数
     *
     * 如果有期望的数
     *  直接从最后结果里添加这位的十进制值
     * 如果没有期望的数
     *  没有就只能走另一个分支,走另一个分支说明俩数相等, 相等异或为0,这一位一定是0,不需要加，直接下一层
     *
     */
    public static int findMaximumXOR(int[] nums) {
        Trie trie = new Trie(); //构建前缀树
        int max = 0;  //最大的异或结果

/*        for (int i = 0; i < nums.length; i++) {
            //向树中添加数
            trie.add(nums[i]);
            //从树中查找与当前数的 异或和最大的数
            //如果只要一个数,则他只能异或自己,一定为0
            max=Math.max(max,trie.findMax(nums[i]));
        }*/
       for (int i = 0; i < nums.length; i++) { // works too, but slow
            trie.add(nums[i]); //向树中添加数
           }
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, trie.findMax(nums[i]));
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
     /*
    前缀树+贪心:
    nums[i] XOR nums[j]实质上是要使得nums[i]与nums[j]的每一位尽量不同,那么异或出来的结果就是1居多
    设想一下,只要从高位开始寻找,就可以利用贪心的思想来优先取不同的数位
    那么问题来了,用什么数据结构存储前面的nums[i]每一个二进制位信息比较合理?-->前缀树
    前缀树是一种特殊的数据结构,他适用于将组成元素种类有限的串(英文字符串、二进制串)等保存在一棵高度有限的树中
    其可以快速查找出当前字符串是否存在;以及查找出是否有公共前缀
    这里都是int数据类型,本质上就是32位的二进制串,并且都是非负数,因此符号位可以忽略
    可以存储在一颗高度为32(含root盲节点)的Tire树中,在遍历到nums[j]时,将nums[j]加入前缀树
    从最高位开始寻找nums[0,j]构成的Tire中尽量多的与nums[j]不同的数位,异或的结果就是[0,j]中异或的最大值
    当nums中所有元素都遍历完的时候,得到的就是最大的异或值
    时间复杂度O(n),空间复杂度O(n)
    */
}
