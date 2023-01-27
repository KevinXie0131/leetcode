package com.answer.array;

public class Q67_Add_Binary {

    public String addBinary(String a, String b) {
        StringBuffer sb = new StringBuffer();
        int n = a.length();
        int m = b.length();
        int i = n - 1, j = m - 1;
        int carry = 0;
        while(i >= 0 || j >= 0){
            int x = i >= 0 ? a.charAt(i) - '0' : 0;
            int y = j >= 0 ? b.charAt(j) - '0' : 0;
            int sum = x + y + carry;
            sb.insert(0, sum % 2); // Change to 2 since is it binary
            carry = sum / 2;

            i--;
            j--;
        }
        if(carry > 0){
            sb.insert(0, carry);
        }
        return sb.toString();
    }
    /**
     * move carry into while
     */
    public String addBinary_1(String a, String b) {
        StringBuffer sb = new StringBuffer();
        int n = a.length();
        int m = b.length();
        int i = n - 1, j = m - 1;
        int carry = 0;
        while(i >= 0 || j >= 0 || carry > 0){
            int x = i >= 0 ? a.charAt(i) - '0' : 0;
            int y = j >= 0 ? b.charAt(j) - '0' : 0;
            int sum = x + y + carry;
            sb.insert(0, sum % 2);
            carry = sum / 2;

            i--;
            j--;
        }
        return sb.toString();
    }
}
