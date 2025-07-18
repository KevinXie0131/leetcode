package com.answer.string;

import java.util.Arrays;

public class Q541_Reverse_String_II {
    /**
     * Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start of the string.
     * If there are fewer than k characters left, reverse all of them. If there are less than 2k but greater than
     * or equal to k characters, then reverse the first k characters and leave the other as original.
     * 反转字符串 II: 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
     *  如果剩余字符少于 k 个，则将剩余字符全部反转。
     *  如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     */
    public static void main(String[] args) {
        String s = "abcdefg";
        int k = 2;
        System.out.println(reverseStr2(s, k)); // 输出："bacdfeg"
    }
    /**
     * 解法1
     * 当需要固定规律一段一段去处理字符串的时候，要想想在在for循环的表达式上做做文章
     * 只要让 i += (2 * k)，i 每次移动 2 * k 就可以了，然后判断是否需要有反转的区间
     */
    public static String reverseStr(String s, int k) {
        char[] array = s.toCharArray();
        // 1. 每隔 2k 个字符的前 k 个字符进行反转
        for(int i = 0; i < array.length; i += 2 * k){
            int r = i + k - 1;
            reverse(array, i, Math.min(array.length - 1, r)); // 反转每个下标从 2k 的倍数开始的，长度为 k 的子串。若该子串长度不足 k，则反转整个子串。
        }
        return new String(array);
    }
    /**
     * works too
     */
    public static String reverseStr2(String s, int k) {
        char[] array = s.toCharArray();
        for(int i = 0; i < array.length; i += 2 * k){
            int r = i + k - 1;
            // 2. 剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符
            if (i + k <= array.length) {
                reverse(array, i, i + k -1);
                continue;
            }
            // 3. 剩余字符少于 k 个，则将剩余字符全部反转
            reverse(array, i, array.length - 1);
        }
        return new String(array);
    }
    // 定义翻转函数
    public static void reverse(char arr[], int left, int right){
        while(left < right){
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
    /**
     * 题目的意思其实概括为 每隔2k个反转前k个，尾数不够k个时候全部反转
     * 解法二还可以用temp来交换数值，会的人更多些
     */
    public String reverseStr_0(String s, int k) {
        char[] ch = s.toCharArray();
        for (int i = 0 ; i < ch.length; i += 2 * k) {
            int start = i;
            // 判断尾数够不够k个来取决end指针的位置
            int end = Math.min(i + k - 1, ch.length - 1); // 注意最后一个区间的右开端点要和 n 取最小值，防止下标越界。
            while(start < end){
                char temp = ch[start];
                ch[start] = ch[end];
                ch[end] = temp;
                start++;
                end--;
            }
            //用异或运算反转
/*            while(start < end){
                ch[start] ^= ch[end];
                ch[end] ^= ch[start];
                ch[start] ^= ch[end];
                start++;
                end--;
            }*/
        }
        return new String(ch);
    }
    /**
     * 解法3
     */
    public String reverseStr_1(String s, int k) {
        StringBuffer result = new StringBuffer();
        int start = 0;
        int len = s.length();

        while(start < len){
            // 找到k处和2k处
            StringBuffer temp = new StringBuffer();
            // 与length进行判断，如果大于length了，那就将其置为length
            int first = (start + k > len) ? len : start + k;
            int second = (start + 2 * k > len) ? len : start + 2 * k;

            //无论start所处位置，至少会反转一次
            temp.append(s.substring(start, first));
            result.append(temp.reverse());

            // 如果firstK到secondK之间有元素，这些元素直接放入res里即可。
            if (first < second) { //此时剩余长度一定大于k。
                result.append(s.substring(first, second));
            }
            start += 2 * k;
        }
        return result.toString();
    }
}
