package com.answer.bit;

public class Q190_Reverse_Bits {
    /**
     * Reverse bits of a given 32 bits unsigned integer.
     * 颠倒给定的 32 位无符号整数的二进制位。
     * 在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，
     * 因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
     * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在 示例 2 中，输入表示有符号整数 -3，
     * 输出表示有符号整数 -1073741825。
     */
    public static void main(String[] args) {
        String binaryString="00000010100101000001111010011100";
        int decimal = Integer.parseInt(binaryString,2);
        System.out.println(decimal);
        System.out.println(reverseBits_4(decimal));
    }
    /**
     * Approach 1: Bit by Bit - Time Limit Exceeded for 11111111111111111111111111111101
     */
    // you need treat n as an unsigned value
    public static int reverseBits(int n) {
        long ret = 0;
        int power = 31;

        while (n != 0) {
            ret += (n & 1) << power;
            n = n >> 1;
            power -= 1;
        }
        return (int)ret;
    }
    /**
     * 一个简单的做法是对输入的n做诸位检查。
     * 如果某一位是1的话，则将答案相应的对称位置修改为1
     */
    public static int reverseBits_0(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) { // 32 bits unsigned integer
            int t = (n >> i) & 1;
            if (t == 1) {
                ans |= (1 << (31 - i));
                //  ans += (1 << (31 - i)); // works too
            }
        }
        return ans;
    }
    /**
     * another form
     */
    public int reverseBits_5(int n) {
        int res = 0;   // 反转结果，初始为0表示所有位都为0
        // 循环处理32位，idx为位索引
        for(int idx = 0; idx < 32; idx++){
            int digit = n & 1;  // 获取当前最低位
            n >>= 1;            // 将最低位右移掉
            res |= (digit << (31 - idx)); // 将这个最低位反转到它实际位置上去
        }
        return res;
    }
    /**
     * 方法一：逐位颠倒
     * 将 n 视作一个长为 32 的二进制串，从低位往高位枚举 n 的每一位，将其倒序添加到翻转结果 rev 中。
     * 需要注意的是，在某些语言（如 Java）中，没有无符号整数类型，因此对 n 的右移操作应使用逻辑右移。
     * 时间复杂度：O(logn)。
     * 空间复杂度：O(1)。
     */
    static public int reverseBits_4(int n) {
        int rev = 0;
        for (int i = 0; i < 32 && n != 0; ++i) {
            rev |= (n & 1) << (31 - i);
            n >>>= 1;
        }
        return rev;
    }
    /**
     * Approach 3: Mask and Shift
     * Divide & Conquer 分而治之: 有另外一种不使用循环的做法，类似于归并排序。
     */
    private static final int M1 = 0x55555555; // 01010101010101010101010101010101
    private static final int M2 = 0x33333333; // 00110011001100110011001100110011
    private static final int M4 = 0x0f0f0f0f; // 00001111000011110000111100001111
    private static final int M8 = 0x00ff00ff; // 00000000111111110000000011111111
    /**
     * 取出所有奇数位和偶数位；
     * 将奇数位移到偶数位上，偶数位移到奇数位上。
     */
    public int reverseBits_1(int n) {
        n = n >>> 1 & M1 | (n & M1) << 1;
        n = n >>> 2 & M2 | (n & M2) << 2;
        n = n >>> 4 & M4 | (n & M4) << 4;
        n = n >>> 8 & M8 | (n & M8) << 8;
        return n >>> 16 | n << 16;
    }

    public int reverseBits_2(int n) {
        n = n >>> 16 | n << 16;
        n = n >>> 8 & M8 | (n & M8) << 8;
        n = n >>> 4 & M4 | (n & M4) << 4;
        n = n >>> 2 & M2 | (n & M2) << 2;
        n = n >>> 1 & M1 | (n & M1) << 1;
        return n;
    }
}
