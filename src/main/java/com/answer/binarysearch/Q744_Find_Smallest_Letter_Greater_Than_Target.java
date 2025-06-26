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
     * refer to Q704_Binary_Search
     * 大于 问题转化为target + 1
     * 寻找左侧边界
     */
    public char nextGreatestLetter_0(char[] letters, char target) {
        int left = 0, right = letters.length - 1; // 闭区间 [left, right]
        target++; // 稍微转换一下target = target + 1,即可转变为寻找lower bound 的问题
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (letters[mid] < target) {
                left = mid + 1; // 范围缩小到 [mid + 1, right]
            } else if (letters[mid] >= target) {
                right = mid - 1; // 范围缩小到 [left, mid - 1]
            }
        }
        int index = left < letters.length? left : 0;
        return letters[index];
    }
    /**
     * 容易理解的二分法，加了注释
     */
    public char nextGreatestLetter_3(char[] letters, char target) {
        int n = letters.length;
        int l = 0;
        int r = n - 1;
        char ans = letters[0]; // 找不到默认返回[0]
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (letters[m] == target) {
                l = m + 1; // 相等 但是我要的刚好大一点，所以找右边吧
            }
            else if (letters[m] < target) {
                l = m + 1; // 相等都不行 更何况比目标还小呢？ 继续找右边吧
            }
            else {
                ans = letters[m]; // 找到了，我先保存起来 我想要左边界（第一次出现）的位置
                r = m - 1;// 所以更新右边界，让右边界往左移动，继续找左边
            }
        }
        return ans;
    }
    /**
     * 查找区间左闭右闭[]
     * 1. 取 left = 0, right = lettersSize - 1，相当于在区间 [left, right]中查找；
     * 2. 如果 letters[mid] > target，则在区间 [left, mid - 1] 中查找；
     * 3. 否则在区间 [mid + 1, right] 中查找；
     * 4. 当 letters[mid] == target 时，也在区间 [mid + 1, right] 中查找，因为题目要求查找比目标字母大的最小字母，所有得在查找到 letters[mid] == target 时，还需要在 mid 的右侧查找。
     */
    public char nextGreatestLetter_9(char[] letters, char target) {
        int left = 0, right = letters.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (letters[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left == letters.length ? letters[0] : letters[left];
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
        for (char c: letters){
            if (c > target) return c;
        }
        return letters[0];
    }
}
