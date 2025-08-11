package com.answer.math;

public class MathTemplate {
    public static void main(String[] args) {
        System.out.println(multiply(3, 5));
        /**
         * 异或 XOR
         * a⊕0 = a
         * a⊕a = 0
         * a⊕b⊕a = (a⊕a)⊕b = 0⊕b = b
         */
        System.out.println(5 ^ 0); // 5
        System.out.println(5 ^ 5); // 0
        System.out.println(2 ^ 2 ^ 1); // 1
        System.out.println(2 ^ 1 ^ 2); // 1
        /**
         * n & (n−1)这个操作，，可以'消除最后一个1', 其运算结果恰为把 n 的二进制位中的最低位的 1 变为 0。
         * x = 1011 0000
         * x-1= 1010 1111
         * x & (x-1) = 1010 0000
         */
        int x = Integer.parseInt("10110000",2);
        x = x & (x - 1);
        System.out.println(Integer.toBinaryString(x));

        int count = 0;
        int z = 10;
        while(z > 0){
            z &= z - 1;
            count++;
        }
        System.out.println(count); // 计算其二进制表示中 1 的个数
        /**
         * 位运算 x & -x 取出 x 的二进制表示中最低位那个 1
         */
        int y = 6;
        System.out.println(Integer.toBinaryString(y)); // 110
        System.out.println(Integer.toBinaryString(-y)); // 11111111111111111111111111111010
        System.out.println(Integer.toBinaryString(y & -y)); // 10
    }
    /**
     * 快速幂 https://oi-wiki.org/math/binary-exponentiation/
     * 「快速乘法」模板，采用了倍增的思想
     * 进入循环，只要b大于0，就继续执行下去。
     * 在循环中，如果b的二进制表示的最低位是1，则将a加到re中。这是因为如果b的最低位是1，则a乘以2的对应幂次方，也就是a左移对应位数，应该被加到乘积中。
     * 将b右移一位，相当于将其二进制表示的最低位删除。
     * 将a加倍，相当于将其左移一位，表示a乘以2。
     * 循环结束后，返回re，它存储的就是a和b的乘积
     */
   static public int multiply(int a, int b) {
        int ans = 0;
        while (b > 0) {
            if ((b & 1) == 1) {
                ans += a;//判断b个位是否为0，为0就不乘
            }
            b >>= 1; //将b右移
            a += a; //也就是将a乘2
        }
        return ans;
    }
}
