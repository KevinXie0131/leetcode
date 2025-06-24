package com.answer.dynamic_programming;

public class Q647_Palindromic_Substrings {
    /**
     * 回文子串
     * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
     * 回文字符串 是正着读和倒过来读一样的字符串。
     * 子字符串 是字符串中的由连续字符组成的一个序列。
     *
     * 示例 1：
     * 输入：s = "abc"
     * 输出：3
     * 解释：三个回文子串: "a", "b", "c"
     * 示例 2：
     * 输入：s = "aaa"
     * 输出：6
     * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
     */
    /**
     * 暴力解法
     * 两层for循环，遍历区间起始位置和终止位置，然后还需要一层遍历判断这个区间是不是回文。所以时间复杂度：O(n^3)
     */
    public boolean isPalindrome(String s, int left, int right) {
        while(left < right) {
            if(s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }
    public int countSubstrings_0(String s) {
        int ans = 0;
        int n = s.length();
        for(int i = 0; i < n; i++) {
            for(int j = i;j < n;j++) {
                if(isPalindrome(s, i, j)) ans++;
            }
        }
        return ans;
    }
    /**
     * Approach #2: Dynamic Programming 动态规划
     * 1. Optimal substructure: Remember that larger palindromes are made of smaller palindromes
     * 2. Overlapping sub-problems: While checking all substrings of a large string for palindromicity, we might need to check some smaller substrings for the same, repeatedly.
     * 统计并返回这个字符串中 回文子串 的数目
     * 回文字符串 是正着读和倒过来读一样的字符串。
     * 子字符串 是字符串中的由连续字符(contiguous sequence)组成的一个序列。
     */
    public int countSubstrings(String s) {
        // 布尔类型的dp[i][j]：表示区间范围[i,j] （注意是左闭右闭）的子串是否是回文子串，如果是dp[i][j]为true，否则为false。
        boolean[][] dp = new boolean[s.length()][s.length()]; // dp: [i, j]子串是否回文  初始化为false
        int result = 0;
        // 当s[i]与s[j]不相等，那没啥好说的了，dp[i][j]一定是false。
        // 当s[i]与s[j]相等时，这就复杂一些了，有如下三种情况
        //  情况一：下标i 与 j相同，同一个字符例如a，当然是回文子串
        //  情况二：下标i 与 j相差为1，例如aa，也是回文子串
        //  情况三：下标：i 与 j相差大于1的时候，例如cabac，此时s[i]与s[j]已经相同了，我们看i到j区间是不是回文子串就看aba是不是回文就可以了，
        //         那么aba的区间就是 i+1 与 j-1区间，这个区间是不是回文就看dp[i + 1][j - 1]是否为true。
        for (int i = s.length() - 1; i >= 0; i--) { // 注意遍历顺序。所以一定要从下到上，从左到右遍历，这样保证dp[i + 1][j - 1]都是经过计算的。
            for (int j = i; j < s.length(); j++) {
                //The base cases that we have identified already define states for single and double letter strings.
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) { // 情况⼀(i==j: a) 和 情况⼆(i相差j等于1: aa)
                        result++;
                        dp[i][j] = true;
                    } else if (dp[i + 1][j - 1]) { // 情况三(i相差j大于1: aaa 判断内测子串是否回文)
                        result++;
                        dp[i][j] = true;
                    }
                }
            }
        }
        // 注意因为dp[i][j]的定义，所以j一定是大于等于i的，那么在填充dp[i][j]的时候一定是只填充右上半部分
        return result; // result就是统计回文子串的数量。
    }
    /**
     * Approach #3: Expand Around Possible Centers
     * 双指针法 两边扩散: 首先确定回文串，就是找中心然后向两边扩散看是不是对称的就可以了。
     * Odd and even length palindromes
     *    Odd-length palindromes have a single character in the middle. e.g. "civic" with middle character 'v'.
     *    Even-length palindromes have two characters constitute the middle, both of which are same. e.g. "noon" with two middle characters 'o'.
     *
     * Every single character in the string is a center for possible odd-length palindromes
     * Every pair of consecutive characters in the string is a center for possible even-length palindromes.
     */
    public int countSubstrings_2(String s) {
        int result = 0;
        for (int i = 0; i <= s.length() - 1; i++) {
            result += extend(s, i, i, s.length()); // 以i为中⼼
            result += extend(s, i, i + 1, s.length()); // 以i和i+1为中⼼
        }
        return result;
    }
    // 在遍历中心点的时候，要注意中心点有两种情况。
    //      一个元素可以作为中心点，两个元素也可以作为中心点。
    // 所以我们在计算的时候，要注意一个元素为中心点和两个元素为中心点的情况。
    // 这两种情况可以放在一起计算，但分别计算思路更清晰，我倾向于分别计算
    int extend(String s, int i, int j, int n) {
        int res = 0;
        while (i >= 0 && j <= n - 1 && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
            res++;
        }
        return res;
    }
    /**
     * 另一种形式
     * If it is odd, expand left = (i-1) and right = (i+1)
     * If it is even, expand left = (i) and right = (i+1)
     */
    public int countSubstrings_4(String s) {
        int n = s.length();
        int ans = 0;
        for(int i = 0; i < n;i++) {
            int even = palindromeCount(s, i, i+1);
            int odd = palindromeCount(s, i-1, i+1);
            ans += even + odd + 1;
        }
        return ans;
    }
    public int palindromeCount(String s, int left, int right) {
        int count = 0;
        while(left >= 0 && right < s.length() && s.charAt(left--) == s.charAt(right++)) {
            count++;
        }
        return count;
    }
    /**
     * 中心扩散法
     */
    public int countSubstrings_7(String s) {
        int len, ans = 0;
        if (s == null || (len = s.length()) < 1) return 0;
        //总共有2 * len - 1个中心点
        for (int i = 0; i < 2 * len - 1; i++) {
            //通过遍历每个回文中心，向两边扩散，并判断是否回文字串
            //有两种情况，left == right，right = left + 1，这两种回文中心是不一样的
            int left = i / 2, right = left + i % 2;
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                //如果当前是一个回文串，则记录数量
                ans++;
                left--;
                right++;
            }
        }
        return ans;
    }
}
