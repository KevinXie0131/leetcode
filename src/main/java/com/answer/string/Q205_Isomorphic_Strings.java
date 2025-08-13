package com.answer.string;

import java.util.*;

public class Q205_Isomorphic_Strings {
    /**
     * Given two strings s and t, determine if they are isomorphic.
     * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
     * 同构字符串: 给定两个字符串 s 和 t ，判断它们是否是同构的。
     * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
     * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
     * 示例 1:
     *  输入：s = "egg", t = "add"
     *  输出：true
     * 示例 2：
     *  输入：s = "foo", t = "bar"
     *  输出：false
     */
    public static void main(String[] args) {
        String s = "foo";
        String t = "bar";
        System.out.println(isIsomorphic_4(s, t));
    }
    /**
     * 同构字符串
     * Approach 1: Character Mapping with Dictionary
     * 抽象理解题目给定条件，
     *   每个出现的字符都应当映射到另一个字符。代表字符集合 s , t 之间是「满射」。
     *   相同字符只能映射到同一个字符上，不同字符不能映射到同一个字符上。代表字符集合 s , t 之间是「单射」。
     *   当发现任意「一对多」的关系时返回 false 即可
     */
    static public boolean isIsomorphic(String s, String t) {
        int[] dict1 = new int[256]; // s and t consist of any valid ascii character.
        int[] dict2 = new int[256];
        Arrays.fill(dict1, -1);
        Arrays.fill(dict2, -1);
        for(int i = 0; i < s.length(); i++){
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if(dict1[c1] == -1 && dict2[c2] == -1){ // Case 1: No mapping exists in either of the dictionaries
                dict1[c1] = c2;
                dict2[c2] = c1;
            } else if(!(dict1[c1] == c2 && dict2[c2] == c1)){ // Case 2: Ether mapping doesn't exist in one of the dictionaries or Mapping exists and
            //  else if(dict1[c1] != c2 || dict2[c2] != c1){ // works too
                return false;// it doesn't match in either of the dictionaries or both
            }
        }
        return true;
    }
    /**
     * Use HashMap
     * 字符串没有说都是小写字母之类的，所以用数组不合适了，用map来做映射。
     * 使用两个map 保存 s[i] 到 t[j] 和 t[j] 到 s[i] 的映射关系，如果发现对应不上，立刻返回 false
     *
     * 一个哈希表只能完成一个方向的映射，因此我们需要使用两个哈希表分别存储两个字符串之间的映射
     */
    public boolean isIsomorphic_1(String s, String t) {
        Map<Character, Character> s2t = new HashMap<Character, Character>();
        Map<Character, Character> t2s = new HashMap<Character, Character>();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char x = s.charAt(i), y = t.charAt(i);
            // 对于已有映射 a -> s2t[a]，若和当前字符映射 a -> b 不匹配，说明有一对多的映射关系，则返回 false ；
            // 对于映射 b -> a 也同理
            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
                return false;   // 如果当前s字符和t字符不匹配，直接返回
            }
            // 更新s字符和t字符的映射关系，如果已经存储的映射不改变结果
            s2t.put(x, y);
            t2s.put(y, x);
/*            if(!s2t.containsKey(x)){ // map1保存s[i] 到 t[j]的映射
                s2t.put(x,y);
            }
            if(!t2s.containsKey(y)){ // map2保存t[j] 到 s[i]的映射
                t2s.put(y,x);
            }
            if(s2t.get(x) != y ||   t2s.get(y) != x ){   // 发现映射 对应不上，立刻返回false
                return false;
            }*/
        }
        return true;
    }
    /**
     * Use only one HashMap
     * 需要我们判断 s 和 t 每个位置上的字符是否都一一对应，即 s 的任意一个字符被 t 中唯一的字符对应，同时 t 的任意一个字符被 s 中唯一的字符对应。
     * 以示例 2 为例，t 中的字符 a 和 r 虽然有唯一的映射 o，但对于 s 中的字符 o 来说其存在两个映射 {a,r}，故不满足条件。
     */
    public boolean isIsomorphic_3(String s, String t) {
        return isIsomorphicHelper(s, t) && isIsomorphicHelper(t, s);
    }
    /**
     * 只需要验证 s - > t 和 t -> s 两个方向即可。如果验证一个方向，是不可以的。
     * 举个例子，s = ab, t = cc，如果单看 s -> t ，那么 a -> c, b -> c 是没有问题的。
     * 必须再验证 t -> s，此时，c -> a, c -> b，一个字母对应了多个字母，所以不是同构的。
     */
    private boolean isIsomorphicHelper(String s, String t) {
        int n = s.length();
        HashMap<Character, Character> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if(map.containsKey(c1) && map.get(c1) != c2){
                return false;
            }
            map.put(c1, c2);
        }

        return true;
    }
    /**
     * Approach 2: First occurrence transformation
     * transfor "add" and "egg" to "0 1 1 "
     */
    public static boolean isIsomorphic_4(String s, String t) {
        return transformString(s).equals(transformString(t));
    }

    private static String transformString(String s) {
        Map<Character, Integer> indexMapping = new HashMap<>();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);

            if (!indexMapping.containsKey(c1)) {
                indexMapping.put(c1, i);
            }

            builder.append(Integer.toString(indexMapping.get(c1)));
            builder.append(" ");
        }
        return builder.toString();
    }
    /**
     * another form
     * 可以用两个 map 分别记录两个字符串每个字母的映射。将所有字母初始都映射到 0。
     * 记录过程中，如果发现了当前映射不一致，就可以立即返回 false 了。
     */
    public boolean isIsomorphic8(String s, String t) {
        int len = s.length();
        int[] mapS = new int[128];
        int[] mapT = new int[128];

        for (int i = 0; i < len; i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            //当前的映射值是否相同
            if (mapS[c1] != mapT[c2]) {
                return false;
            }
            mapS[c1] = i + 1; // 也可以将当前字母直接映射为当前字母的下标加 1。因为 0 是我们的默认值，所以不能直接赋值为下标，而是「下标加 1」。
            mapT[c2] = i + 1;
        }
        return true;
    }
    /**
     * 字符串中，同一个位置的字符在本串中第一次出现的位置相同。
     * 用indexof查找字符串有O(n)复杂度，代码看着简单但是整体复杂度达O(n^2)
     */
    public boolean isIsomorphic7(String s, String t) {
        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if(s.indexOf(ch1[i]) != t.indexOf(ch2[i])){
                return false;
            }
        }
        return true;
    }
}
