package com.answer.array;

public class Q165_Compare_Version_Numbers {
    public static void main(String[] args) {
        System.out.println(Integer.parseInt("001"));
        System.out.println(Double.parseDouble("11.2"));
    }
    /**
     * Approach 1: Split + Parse, Two Pass
     */
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        for(int i = 0; i< v1.length || i < v2.length; i++){

            int ver1 = 0, ver2 = 0;
            if(i < v1.length){
                ver1 = Integer.parseInt(v1[i]);
            }
            if(i < v2.length){
                ver2 = Integer.parseInt(v2[i]);
            }

            if(ver1 > ver2){
                return 1;
            }else if(ver1 < ver2){
                return -1;
            }
        }

        return 0;
    }
    /**
     * Approach 2: Two Pointers, One Pass
     */
    public int compareVersion_1(String version1, String version2) {
        int i = 0, j = 0;
        int n = version1.length(), m = version2.length();

        while(i < n || j < m){
            int num1 = 0, num2 = 0;
            while(i < n && version1.charAt(i) != '.'){
                num1 = num1 * 10 + (version1.charAt(i) - '0');
                i++;
            }
            i++;
            while(j < m && version2.charAt(j) != '.'){
                num2 = num2 * 10 + (version2.charAt(j) - '0');
                j++;
            }
            j++;
            if(num1 != num2){
                return num1 > num2 ? 1 : -1;
            }
        }

        return 0;
    }
}
