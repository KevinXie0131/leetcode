package com.answer.string;

public class Q344_Reverse_String {

    /**
     * Approach 1: Recursion, In-Place, O(N) Space
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
     * Approach 2: Two Pointers, Iteration, O(1) Space
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
    }
}
