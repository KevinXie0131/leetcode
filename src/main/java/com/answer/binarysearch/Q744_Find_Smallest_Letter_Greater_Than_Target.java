package com.answer.binarysearch;

public class Q744_Find_Smallest_Letter_Greater_Than_Target {
    /**
     * 寻找比目标字母大的最小字母
     * 一个字符数组 letters，该数组按非递减顺序排序，以及一个字符 target。letters 里至少有两个不同的字符。
     * You are given an array of characters letters that is sorted in non-decreasing order, and a character target.
     * There are at least two different characters in letters.
     * 返回 letters 中大于 target 的最小的字符。如果不存在这样的字符，则返回 letters 的第一个字符。
     * Return the smallest character in letters that is lexicographically greater than target.
     * If such a character does not exist, return the first character in letters.
     *
     * 示例 1：
     *  输入: letters = ["c", "f", "j"]，target = "a"
     *  输出: "c"
     * 解释：letters 中字典上比 'a' 大的最小字符是 'c'。  The smallest character that is lexicographically greater than 'a' in letters is 'c'.
     * 示例 2:
     *  输入: letters = ["c","f","j"], target = "c"
     *  输出: "f"
     *  解释：letters 中字典顺序上大于 'c' 的最小字符是 'f'。The smallest character that is lexicographically greater than 'c' in letters is 'f'.
     * 示例 3:
     *  输入: letters = ["x","x","y","y"], target = "z"
     *  输出: "x"
     *  解释：letters 中没有一个字符在字典上大于 'z'，所以我们返回 letters[0]。There are no characters in letters that is lexicographically greater than 'z' so we return letters[0].
     * 提示：
     *  2 <= letters.length <= 104
     *  letters[i] 是一个小写字母
     *  letters 按非递减顺序排序
     *  letters 最少包含两个不同的字母
     *  target 是一个小写字母
     */
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
