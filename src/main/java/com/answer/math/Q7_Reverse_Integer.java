package com.answer.math;

public class Q7_Reverse_Integer {
    /**
     * Given a signed 32-bit integer x, return x with its digits reversed.
     * If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
     * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1] ，就返回 0。
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     */
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE); // 2147483647
        System.out.println(Integer.MIN_VALUE); // -2147483648

        System.out.println(reverse6(-123));
        System.out.println(Integer.toBinaryString(-2147483648));
        System.out.println(Integer.parseUnsignedInt("10000000000000000000000000000000", 2));

        int a = -2147483648;
        System.out.println(a);
        System.out.println(Integer.toBinaryString(a));
        for(int i = 0; i < 32; i++){
            a = a >> 1;
            System.out.println(Integer.toBinaryString(a));
            System.out.println(a);
        }
    }
    /**
     * 构建反转整数的一位数字，同时需要预先检查向原整数附加另一位数字是否会导致溢出。
     * 使用如下数学方法：
     *      // 弹出 x 的末尾数字 digit
     *      digit = x % 10
     *      x /= 10
     *      // 将数字 digit 推入 rev 末尾
     *      rev = rev * 10 + digit
     */
    public static int reverse(int x) {
        int result = 0;
        // 循环的判断条件应该是while(x!=0)，无论正数还是负数，按照上面不断的/10这样的操作，最后都会变成0，所以判断终止条件就是!=0
        while(x != 0){
            // 到 最大数的1/10 时，就要开始判断溢出
            if (result < Integer.MIN_VALUE / 10 || result > Integer.MAX_VALUE / 10) { // y = y * 10 + x % 10 < MAX_VALUE
                return 0;
            }
            // 判断溢出
           // if (Math.abs(result) > Integer.MAX_VALUE / 10) return 0;

            int digit = x % 10;
            x = x /10;

            result = result * 10 + digit;
        }

        return result;
    }
    /**
     * 另一种形式
     */
    public int reverse1(int x) {
        int result = 0;
        while(x != 0){
            if (x > 0 && result > (Integer.MAX_VALUE - x % 10) / 10) {
                return 0;
            }
            if (x < 0 && result < (Integer.MIN_VALUE - x % 10) / 10 ) {
                return 0;
            }

            result = result * 10 +  x % 10;
            x = x /10;
        }
        return result;
    }
    /**
     * another form
     * 溢出条件有两个，一个是大于整数最大值MAX_VALUE，另一个是小于整数最小值 MIN_VALUE，设当前计算结果为 ans，下一位为 pop。
     *    从 ans * 10 + pop > MAX_VALUE 这个溢出条件来看
     *       当出现 ans > MAX_VALUE / 10 且 还有pop需要添加 时，则一定溢出
     *       当出现 ans == MAX_VALUE / 10 且 pop > 7 时，则一定溢出，7 是 2^31 - 1 的个位数
     *    从 ans * 10 + pop < MIN_VALUE 这个溢出条件来看
     *       当出现 ans < MIN_VALUE / 10 且 还有pop需要添加 时，则一定溢出
     *       当出现 ans == MIN_VALUE / 10 且 pop < -8 时，则一定溢出，8 是-2^31 的个位数
     */
    public int reverse3(int x) {
        int ans = 0;
        while (x != 0) {
            int pop = x % 10;
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            ans = ans * 10 + pop;
            x /= 10;
        }
        return ans;
    }
    /**
     * 解法二：转换为 Long 再操作
     * 转换为 long， 这可以轻松处理溢出的情况。
     */
    public int reverse4(int x) {
        long x_ll = (long) x;
        long ans = 0;

        while (x_ll != 0) {
            long digit = x_ll % 10; // 个位
            x_ll /= 10;
            ans = ans * 10 + digit;
        }
        // 溢出则返回 0
        if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) return 0;
        return (int) ans;
    }
    /**
     * 解法一：改成字符串间接实现
     */
    static public int reverse6(int x) {
        // 改成字符数组
        char[] chars = Integer.toString(x).toCharArray();
        // 有负号则 l = 1，无负号则 l = 0
        int l = (chars[0] == '-') ? 1 : 0;
        int r = chars.length - 1;

        // 从两侧向中间逼近，交换字符
        while (l < r) {
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
            ++l; --r;
        }
        String reversedStr = new String(chars);
        // 先转换为 long long，用来检测是否溢出
        long reversedLong = Long.parseLong(reversedStr);
        if (reversedLong > Integer.MAX_VALUE || reversedLong < Integer.MIN_VALUE) {
            return 0;
        }
        // 再转换为 int
        return (int) reversedLong;
    }
}
