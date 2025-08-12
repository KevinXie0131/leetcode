package com.answer.string;

public class Q5_Longest_Palindromic_Substring {
    /**
     * Given a string s, return the longest palindromic substring in s.
     * A string is palindromic if it reads the same forward and backward.
     * 最长回文子串
     * 给你一个字符串 s，找到 s 中最长的 回文 子串。如果字符串向前和向后读都相同，则它满足 回文性。
     */
    public static void main(String[] args) {
        String s = "babad";
        String r = longestPalindrome(s);
        System.out.println(r);
    }
    /**
     * 暴力解法 - Time Limit Exceeded
     */
    static public String longestPalindrome(String s) {
        String ans = "";
        int max = 0;
        int len = s.length();
        for (int i = 0; i < len; i++)
            for (int j = i + 1; j <= len; j++) {
                String test = s.substring(i, j);
                if (isPalindromic(test) && test.length() > max) {
                    ans = s.substring(i, j);
                    max = Math.max(max, ans.length());
                }
            }
        return ans;
    }

    static public boolean isPalindromic(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }
    /**
     * 中心扩展算法，枚举回文子串的中心位置，每次循环需要分奇数和偶数两种情况。
     * 例如 abcda 和 abba
     */
   static  public String longestPalindrome_2(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        if (s.length() == 1) return s;
        String result = "";
        for (int i = 0; i <= s.length() - 2; i++) {
            int left = i - 1, right = i + 1; // 奇数长度的回文串
            while(left <= right && left >= 0 && right <= s.length() -1 && s.charAt(left) == s.charAt(right)){
                if(right - left + 1 > result.length()) {
                    result = s.substring(left, right + 1);
                }
                left--; right++;
            }

            left = i; right = i + 1;       // 偶数长度的回文串
            while(left <= right && left >= 0 && right <= s.length() - 1 && s.charAt(left) == s.charAt(right)){
                if(right - left + 1 > result.length()) {
                    result = s.substring(left, right + 1);
                }
                left--; right++;
            }
        }
        if(result.length() == 0) return s.substring(0, 1);
        return result;
    }
    /**
     * 方法二：中心扩展算法
     */
    public String longestPalindrome_1(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 如果传入重合的下标，进行中心扩散，此时得到的回文子串的长度是奇数；
            int len1 = expandAroundCenter(s, i, i); // 回文子串为奇数，如abcba
            // 如果传入相邻的下标，进行中心扩散，此时得到的回文子串的长度是偶数
            int len2 = expandAroundCenter(s, i, i + 1); // 回文子串为偶数，如abba
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    /**
     * another form
     */
    public String longestPalindrome_1a(String s) {
        int n = s.length();
        int length = 1; // 最长回文子串的长度
        int start = 0;  // 最长回文子串的起始位置
        for(int i = 0; i < n; i++){
            // 枚举每个i为中心扩展
            int oddLen = expandAroundCenter(s, i, i);      // 以i为中心扩展，得到奇数长度的回文串
            int evenLen = expandAroundCenter(s, i, i+1);   // 以[i, i+1]为中心扩展，得到偶数长度的回文串
            int localMax = Math.max(oddLen, evenLen);    // 以i为中心扩展的最大长度
            if(localMax > length){
                length = localMax;
                start = i - (localMax - 1) / 2;     // 根据中心点和长度计算起始点
            }
        }
        return s.substring(start, start + length); // 截取最长回文子串
    }

    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        // (right - 1) - (left + 1) + 1 = right - left - 1
        return right - left - 1; // 退出循环时，right和left一定是指向不是回文串的部分，即回文串为[left + 1, right - 1]
    }
    /**
     * 动态规划
     * 参考LeetCode 647. Palindromic Substrings。该题的思路改一下、加一点，就能通过Q5 Longest Palindromic Substring
     */
    public String longestPalindrome_4(String s) {
        // 题目要求要return 最长的回文连续子串，故需要记录当前最长的连续回文子串长度、最终起点、最终终点。
        int finalStart = 0;
        int finalEnd = 0;
        int finalLen = 0;

        char[] chars = s.toCharArray();
        int len = chars.length;

        boolean[][] dp = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (chars[i] == chars[j] && (j - i <= 1 || dp[i + 1][j - 1])) // 简洁版
                    dp[i][j] = true;
                // 和LeetCode 647，差别就在这个if statement。
                // 如果当前[i, j]范围内的substring是回文子串(dp[i][j]) 且(&&) 长度大于当前要记录的最终长度(j - i + 1 > finalLen)
                // 我们就更新 当前最长的连续回文子串长度、最终起点、最终终点
                if (dp[i][j] && j - i + 1 > finalLen) {
                    finalLen = j - i + 1;
                    finalStart = i;
                    finalEnd = j;
                }
            }
        }
        // String.substring这个method的用法是[起点, 终点)，包含起点，不包含终点（左闭右开区间），故终点 + 1。
        return s.substring(finalStart, finalEnd + 1);
    }
    /**
     * 对于一个子串而言，如果它是回文串，并且长度大于 2，那么将它首尾的两个字母去除之后，它仍然是个回文串。
     * 例如对于字符串 ababa，如果我们已经知道 bab 是回文串，那么 ababa 一定是回文串，这是因为它的首尾两个字母都是 a
     * 还需要考虑动态规划中的边界条件，即子串的长度为 1 或 2。对于长度为 1 的子串，它显然是个回文串；
     * 对于长度为 2 的子串，只要它的两个字母相同，它就是一个回文串
     */
    public String longestPalindrome4(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    // 如果去掉 s[i..j] 头尾两个字符子串 s[i + 1..j - 1] 的长度严格小于 2（不构成区间），
                    // 即 j−1−(i+1)+1<2 时，整理得 j−i<3，此时 s[i..j] 是否是回文只取决于 s[i] 与 s[j] 是否相等。
                    // 结论也比较直观：j−i<3 等价于 j−i+1<4，即当子串 s[i..j] 的长度等于 2 或者等于 3 的时候，
                    // s[i..j] 是否是回文由 s[i] 与 s[j] 是否相等决定。
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1]; // 只有 s[i+1:j−1] 是回文串，并且 s 的第 i 和 j 个字母相同时，s[i:j] 才会是回文串
                    }
                }
                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
    /**
     * 动态规划
     * 我们用一个 boolean dp[l][r] 表示字符串从 i 到 j 这段是否为回文。试想如果 dp[l][r]=true，
     * 我们要判断 dp[l-1][r+1] 是否为回文。只需要判断字符串在(l-1)和（r+1)两个位置是否为相同的字符，是不是减少了很多重复计算。
     * 初始状态，l=r 时，此时 dp[l][r]=true。
     * 状态转移方程，dp[l][r]=true 并且(l-1)和（r+1)两个位置为相同的字符，此时 dp[l-1][r+1]=true。
     */
    public String longestPalindrome6(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int strLen = s.length();
        int maxStart = 0;  //最长回文串的起点
        int maxEnd = 0;    //最长回文串的终点
        int maxLen = 1;  //最长回文串的长度

        boolean[][] dp = new boolean[strLen][strLen];
/*        for (int r = 0; r < strLen; r++) {  // works too
            for (int l = 0; l <= r; l++) {
        */
        for (int r = 1; r < strLen; r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;

                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }
    /**
     * 动态规划
     * 当 j = i 时，子串长度为 1，一定为回文串；
     * 当 j = i - 1 时，子串长度为 2，是否为回文串取决于两个字符 s[i] 和 s[j] 是否相等；
     * 当 j < i - 1 时，子串长度大于 2，是否为回文串首先端点两个字符要相等，其次s[j+1, i-1]也要为回文串，即 dp[j+1][i-1]。
     */
    public String longestPalindrome7(String s) {
        int n = s.length();
        int length = 1; // 最长回文子串的长度
        int start = 0;  // 最长回文子串的起始位置
        boolean[][] dp = new boolean[n][n];    // dp[j][i]表示子串s[j:i]是否为回文串
        for(int i = 0; i < n; i++){
            // 以i为终点，往回枚举起点j
            for(int j = i; j >= 0; j--){
                if(i == j){
                    dp[j][i] = true;    // 一个字符，一定为回文串
                }else if(i == j + 1){
                    dp[j][i] = (s.charAt(i) == s.charAt(j));  // 两个字符，取决于二者是否相等
                }else{
                    dp[j][i] = (s.charAt(i) == s.charAt(j)) && dp[j+1][i-1];  // 两个字符以上，首先端点两个字符要相等，其次[j+1, i-1]也要为回文串
                }
                // [j,i]为回文串且长度更大，更新
                if(dp[j][i] && (i - j + 1) > length){
                    length = i - j + 1;
                    start = j;
                }
            }
        }
        return s.substring(start, start + length); // 截取最长回文子串
    }
}
