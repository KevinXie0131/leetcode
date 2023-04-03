package com.answer.greedy;

public class Q738_Monotone_Increasing_Digits {
    public static void main(String[] args) {
        int n = 332;
        System.out.println(monotoneIncreasingDigits_1(n));
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
    public static int monotoneIncreasingDigits_1(int n) {
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
}
