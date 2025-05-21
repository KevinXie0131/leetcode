package com.answer.bit;

public class Q371_Sum_of_Two_Integers {
    public static void main(String[] args) {
        int a = 2, b = 3;
        System.out.println(getSum_1(a, b));
    }
    /**
     * Given two integers a and b, return the sum of the two integers without using the operators + and -.
     * 给你两个整数 a 和 b ，不使用 运算符 + 和 -，计算并返回两整数之和。
     */
    /**
     * 使用「位运算」，利用二进制的「逢二进一」和「int 二进制表示长度为 32」，我们可以从低位往高位进行处理，处理过程中使用变量 t 记录进位信息。
     */
    static public int getSum(int a, int b) {
        int res = 0;
        int carry = 0;
        for (int i = 0; i < 32; i++) {
            int u1 = (a >> i) & 1, u2 = (b >> i) & 1;
            if (u1 == 1 && u2 == 1) { // 两个当前位均为 1：此时当前位是什么取决于前一位的进位情况, 同时进位 carry = 1
                res |= (carry << i);
                carry = 1;
            } else if (u1 == 1 || u2 == 1) { // 两个当前位只有一位是 1：此时当前位是什么取决于前一位的进位情况
                // 前一进位若为 1，结合该位为 1，则有当前位为 0，进位不变 carry=1；
                // 前一进位若为 0，结合该位为 1，则有当前位为 1，进位不变 carry=0；
                res |= ((1 ^ carry) << i);
            } else {
                res |= (carry << i); // 两个当前位为 0：此时当前位是什么取决于前一位的进位情况, 同时进位 t=0。
                carry = 0;
            }
        }
        return res;
    }
    /**
     * 对于整数 a 和 b
     *  在不考虑进位的情况下，其无进位加法结果为 a⊕b。
     *  而所有需要进位的位为 a & b，进位后的进位结果为 (a & b) << 1
     *  可以将整数 a 和 b 的和，拆分为 a 和 b 的无进位加法结果与进位结果的和。因为每一次拆分都可以让需要进位的最低位至少左移一位
     */
    static public int getSum_1(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1; // 如果要得到 a + b 的最终值，我们还要找到进位的数，把这二者相加
            a = a ^ b; // 在位运算操作中，异或的一个重要特性是无进位加法
            b = carry;
        }
        return a;
    }
}
