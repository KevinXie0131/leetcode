package com.answer.bit;

public class Q190_Reverse_Bits {
    public static void main(String[] args) {
        String binaryString="00000010100101000001111010011100";
        int decimal = Integer.parseInt(binaryString,2);
        System.out.println(decimal);
        System.out.println(reverseBits_0(decimal));
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
            }
        }
        return ans;
    }
    /**
     * Approach 3: Mask and Shift
     * Divide & Conquer
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
