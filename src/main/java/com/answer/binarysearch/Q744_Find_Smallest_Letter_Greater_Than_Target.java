package com.answer.binarysearch;

public class Q744_Find_Smallest_Letter_Greater_Than_Target {
    public static void main(String[] args) {
/*        char[] letters = {'c','f','j'};
        char target = 'c';*/
        char[] letters = {'c','f','j'};
        char target = 'j';
        System.out.println(nextGreatestLetter(letters, target));
    }

    /**
     * Approach #3: Binary Search
     */
    public static char nextGreatestLetter(char[] letters, char target) {
        if(target < letters[0] || target >= letters[letters.length - 1]){
            return letters[0];
        }

        int left = 0;
        int right = letters.length - 1;

        while(left <= right){
            int mid = (left + right) >>> 1;
            if(letters[mid] == target){
                if(letters[mid + 1] != target){
                    return letters[mid + 1];
                }
                while(mid <= letters.length - 1 && letters[mid] == target){
                    mid++;
                }
                return letters[mid];
            }else if(letters[mid] > target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return letters[left];
    }
    /**
     * Official answer
     */
    public char nextGreatestLetter_0(char[] letters, char target) {
        int lo = 0, hi = letters.length;
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (letters[mi] <= target) {
                lo = mi + 1;
            }
            else {
                hi = mi;
            }
        }
        return letters[lo % letters.length];
    }
    /**
     * Approach #1: Record Letters Seen
     */
    public char nextGreatestLetter_1(char[] letters, char target) {
        boolean[] found = new boolean[26];
        for(char c : letters){
            found[c - 'a'] = true;
        }

        while(true){
            target++;
            if(target > 'z') return letters[0];

            if(found[target - 'a']){
                return target;
            }
        }
    }
    /**
     * Approach #2: Linear Scan
     */
    public char nextGreatestLetter_2(char[] letters, char target) {
        for (char c: letters)
            if (c > target) return c;
        return letters[0];
    }
}
