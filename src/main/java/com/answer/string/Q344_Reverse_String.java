package com.answer.string;

public class Q344_Reverse_String {
    /**
     * Write a function that reverses a string. The input string is given as an array of characters s.
     * You must do this by modifying the input array in-place with O(1) extra memory.
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * 示例 1：
     *  输入：s = ["h","e","l","l","o"]
     *  输出：["o","l","l","e","h"]
     */
    public static void main(String[] args) {
        reverseString1(new char[]{'h','e','l','l','o'});
    }
    /**
     * 借助 StringBuilder
     */
    static public void reverseString1(char[] s) {
        char[] res = new StringBuilder(new String(s)).reverse().toString().toCharArray();
        for(int i = 0; i < res.length; i++){
            s[i] = res[i];
        }
    }
    /**
     * Approach 1: Recursion, In-Place, O(N) Space 递归
     */
    public void reverseString(char[] s) {
        int right = s.length - 1;
        int left = 0;
        recursion(s, left, right);
    }

    public void recursion(char[] s, int left, int right){
        // Both can work
/*        if(left > right){
            return;
        }*/
        if(left >= right) {
            return;
        }
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;

        recursion(s, left+1, right-1);
    }
    /**
     * Approach 2: Two Pointers, Iteration, O(1) Space 双指针
     * 第二种方法用temp来交换数值更多人容易理解些
     */
    public void reverseString_1(char[] s) {
        int right = s.length - 1;
        int left = 0;
        while(left < right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
    /**
     * 单指针写法
     * 由于 left+right=n−1 恒成立，所以只需要用一个变量 i 表示 left，n−1−i 就是 right。循环直到 i=n/2 时停止
     */
    public void reverseString3(char[] s) {
        int n = s.length;
        for (int i = 0; i < n / 2; i++) {
            char tmp = s[i];
            s[i] = s[n - 1 - i];
            s[n - 1 - i] = tmp;
        }
    }
    /**
     * 三次异或可以交换两个数
     * 异或，英文为exclusive OR，缩写成xor，用符号⊕表示
     * 异或满足交换律和结合律
     * x ^ x == 0
     * x ^ 0 == x
     * 交换律: x ^ y = y ^ x
     * 结合律：a⊕b⊕c=（a⊕b）⊕c=a⊕（b⊕c）
     */
    public void reverseString_2(char[] s) {
        int l = 0;
        int r = s.length - 1;
        while (l < r) {
            s[l] ^= s[r];  //构造 a ^ b 的结果，并放在 a 中
            s[r] ^= s[l];  //将 a ^ b 这一结果再 ^ b ，存入b中，此时 b = a, a = a ^ b
            s[l] ^= s[r];  //a ^ b 的结果再 ^ a ，存入 a 中，此时 b = a, a = b 完成交换
            l++;
            r--;
        }
        // a ^ b
        // b ^ a ^ b = a
        // a ^ b ^ a = b
    }
}
