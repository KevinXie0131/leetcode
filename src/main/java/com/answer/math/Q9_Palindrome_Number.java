package com.answer.math;

public class Q9_Palindrome_Number {

    public static void main(String[] args) {
        int x = 12321;
        System.out.println(isPalindrome_3(x));
    }
    /**
     * 回文数
     * 反转原数后 判断是否相同
     */
    public boolean isPalindrome(int x) {
        String reverse = new StringBuffer(x + "").reverse().toString();
        return (x + "").equals(reverse);
    }
    /**
     * 反转原数后 判断是否相同
     * 不会溢出
     */
    public boolean isPalindrome_1(int x) {
        if(x < 0){
            return false;
        }
        int cur = 0;
        int num = x;
        while(num != 0){
            cur = cur * 10 + num % 10;
            num = num / 10;
        }
        return cur == x;
    }
    /**
     * 双指针
     */
    public boolean isPalindrome_2(int x) {
        if(x < 0){
            return false;
        }
        String num = x + "";
        for(int i = 0, j = num.length() - 1; i < j; i++, j--){
            if(num.charAt(i) != num.charAt(j)){
                return false;
            }
        }
        return true;
    }
    /**
     *
     */
    public static boolean isPalindrome_3(int x) {
        //边界判断
        if (x < 0) return false;
        int div = 1;
        //
        while (x / div >= 10) {
            div *= 10;
        }
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    public boolean isPalindromez_4(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }
}
