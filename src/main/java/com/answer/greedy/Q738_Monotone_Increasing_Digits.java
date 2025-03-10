package com.answer.greedy;

public class Q738_Monotone_Increasing_Digits {
    public static void main(String[] args) {
        int n = 332;
        System.out.println(monotoneIncreasingDigits_1(n));
    }
    /**
     * 贪心算法: 题目要求小于等于N的最大单调递增的整数
     * 一旦出现strNum[i - 1] > strNum[i]的情况（非单调递增），首先想让strNum[i - 1]--，然后strNum[i]给为9，
     * 从后向前遍历，就可以重复利用上次比较得出的结果了，从后向前遍历332的数值变化为：332 -> 329 -> 299
     */
    public int monotoneIncreasingDigits_4(int N) { // java版本1中创建了String数组，多次使用Integer.parseInt了方法，这导致不管是耗时还是空间占用都非常高，用时12ms，
        String[] strings = (N + "").split("");
        // start 用来标记赋值9从哪里开始
        int start = strings.length;    // 设置为这个默认值，为了防止第二个for循环在start没有被赋值的情况下执行
        for (int i = strings.length - 1; i > 0; i--) {
            if (Integer.parseInt(strings[i]) < Integer.parseInt(strings[i - 1])) {
                strings[i - 1] = (Integer.parseInt(strings[i - 1]) - 1) + "";
                start = i;
            }
        }
        for (int i = start; i < strings.length; i++) {
            strings[i] = "9";
        }
        return Integer.parseInt(String.join("",strings));
    }
    /**
     * Greedy
     */
    public static int monotoneIncreasingDigits(int n) {
        StringBuffer sb = new StringBuffer(n + "");
        int startIndex = sb.length();

        for(int i = sb.length() - 1; i > 0; i--){
            if(sb.charAt(i) < sb.charAt(i - 1)){
                int value = Character.getNumericValue(sb.charAt(i - 1)) - 1;
                sb.setCharAt(i - 1, (char)('0' + value));
                startIndex = i;
            }
        }

        for(int i = startIndex; i < sb.length(); i++){
            sb.setCharAt(i, '9');
        }
        return Integer.parseInt(sb.toString());
    }
    /**
     * Another form
     *
     * 局部最优：遇到strNum[i - 1] > strNum[i]的情况，让strNum[i - 1]--，然后strNum[i]给为9，可以保证这两位变成最大单调递增整数。
     * 全局最优：得到小于等于N的最大单调递增的整数。
     *
     * 从后向前遍历
     */
    public static int monotoneIncreasingDigits_1(int n) { // 提供一个版本在char数组上原地修改，用时1ms的版本
   //     char[] arr = new String(n + "").toCharArray();
        char[] arr = String.valueOf(n).toCharArray();

        int startIndex = arr.length;

        for(int i = arr.length - 1; i > 0; i--){
            if(arr[i] < arr[i - 1]){
                arr[i - 1]--;
                startIndex = i;
            }
        }

        for(int i = startIndex; i < arr.length; i++){
            arr[i] = '9';
        }
      //  return Integer.parseInt(new String(arr));
        return Integer.parseInt(String.valueOf(arr));
    }
    /**
     * 暴力解法 Time Limit Exceeded
     */
    public int monotoneIncreasingDigits_2(int n) {
        for (int i = n; i > 0; i--) { // 从大到小遍历
            if (checkNum(i)) return i;
        }
        return 0;
    }
    public boolean checkNum(int num) {
        int max = 10;
        while (num >= 0) {
            int t = num % 10;
            if (max >= t){
                max = t;
            } else {
                return false;
            }
            num = num / 10;
        }
        return true;
    }
}
