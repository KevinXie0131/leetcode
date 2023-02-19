package com.answer.binarysearch;

public class Q744_Find_Smallest_Letter_Greater_Than_Target {
    public static void main(String[] args) {
/*        char[] letters = {'c','f','j'};
        char target = 'c';*/
/*        char[] letters = {'c','f','j'};
        char target = 'j';*/
        char[] letters = {'e','e','e','e','e','e','n','n','n','n'};
        char target = 'e';
        System.out.println(nextGreatestLetter_0a(letters, target));
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
    public static char nextGreatestLetter_0a(char[] letters, char target) {
        int length = letters.length;
        if (target >= letters[length - 1]) {
            return letters[0];
        }
        int low = 0, high = length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (letters[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return letters[low];
    }
    /**
     * 查找过程：
     *
     * - 取中间m
     * - m大于目标：答案可能是m或左侧，故先记下m，再在m左侧查找（right = m - 1）
     * - m小于等于目标：答案可能是m右侧，在m右侧查找（left = m + 1）
     */
    public char nextGreatestLetter_0b(char[] letters, char target) {
        int l=0, r=letters.length-1;
        int ret = 0;
        while(l <= r){
            int m = l + (r-l)/2;
            if(letters[m] <= target){
                l = m + 1;
            }else{
                ret = m;
                r = m - 1;
            }
        }
        return letters[ret];
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
