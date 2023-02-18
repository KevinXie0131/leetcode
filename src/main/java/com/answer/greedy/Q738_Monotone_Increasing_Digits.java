package com.answer.greedy;

public class Q738_Monotone_Increasing_Digits {
    public static void main(String[] args) {
        int n = 332;
        System.out.println(monotoneIncreasingDigits(n));
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
}
