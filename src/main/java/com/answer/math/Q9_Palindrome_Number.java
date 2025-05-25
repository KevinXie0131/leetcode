package com.answer.math;

public class Q9_Palindrome_Number {
    /**
     * Given an integer x, return true if x is a palindrome, and false otherwise.
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * 例如，121 是回文，而 123 不是。
     * Follow up: Could you solve it without converting the integer to a string?
     * 进阶：你能不将整数转为字符串来解决这个问题吗？
     */
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
     * 本题可以借鉴 Q7. 整数反转 的做法，将数字反转后判断是否与原数字相等
     */
    public boolean isPalindrome_1(int x) {
        if(x < 0){
            return false;
        }
        int cur = 0;
        int num = x;
        while(num != 0){
            cur = cur * 10 + num % 10; // 如果是正数，则将其倒序数值计算出来，然后比较和原数值是否相等
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
     * 解法二：进阶解法---数学解法
     * 通过取整和取余操作获取整数中对应的数字进行比较。
     * 举个例子：1221 这个数字。
     *  通过计算 1221 / 1000， 得首位1
     *  通过计算 1221 % 10， 可得末位 1
     *  进行比较
     *  再将 22 取出来继续比较
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
            if (left != right){
                return false;
            }
            x = (x % div) / 10; // 需要去掉首位和末位, 可以采用 x % div / 10 的方式, 12321%10000==2321 可以将最高位去掉，然后 2321/10==232 可以将最低为去掉。
            div /= 100; // 最后不要忘记将 div/100。
        }
        return true;
    }
    /**
     * 将数字本身反转，然后将反转后的数字与原始数字进行比较，如果它们是相同的，那么这个数字就是回文。
     * 但是，如果反转后的数字大于 int.MAX，我们将遇到整数溢出问题。
     * 为了避免数字反转可能导致的溢出问题，为什么不考虑只反转 int 数字的一半？毕竟，如果该数字是回文，其后半部分反转后应该与原始数字的前半部分相同。
     * 例如，输入 1221，我们可以将数字 “1221” 的后半部分从 “21” 反转为 “12”，并将其与前半部分 “12” 进行比较，因为二者相同，我们得知数字 1221 是回文。
     *
     * 时间复杂度：O(logn)，对于每次迭代，我们会将输入除以 10，因此时间复杂度为 O(logn)。
     * 空间复杂度：O(1)。我们只需要常数空间存放若干变量。
     */
    public boolean isPalindromez_4(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) { // 所有负数都不可能是回文
            return false; // 除了 0 以外，所有个位是 0 的数字不可能是回文，因为最高位不等于 0
        }

        int revertedNumber = 0;
        while (x > revertedNumber) { // 当原始数字小于或等于反转后的数字时，就意味着我们已经处理了一半位数的数字了。
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }
    /**
     * 动态规划
     * refer to Q125_Valid_Palindrome
     */
    public boolean isPalindromez_5(int x) {
        String num = x + "";
        char[] array = num.toString().toCharArray();
        int len = array.length;
        boolean[][] dp = new boolean[len][len];
        int maxLen = 0;
        for(int j = 0; j < len; j++){
            for(int i = 0; i <= j; i++){
                if(array[i] == array[j] && ((j - i <= 2) || dp[i + 1][j- 1] == true)){
                   dp[i][j] = true;
                   maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen == len;
    }
}
