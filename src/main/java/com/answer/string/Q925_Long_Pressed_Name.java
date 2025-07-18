package com.answer.string;

public class Q925_Long_Pressed_Name {
    /**
     * 长按键入: 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
     * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
     * 示例 1：
     *  输入：name = "alex", typed = "aaleex"
     *  输出：true
     *  解释：'alex' 中的 'a' 和 'e' 被长按。
     * 示例 2：
     *  输入：name = "saeed", typed = "ssaaedd"
     *  输出：false
     *  解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
     */
    public static void main(String[] args) {
        String name = "kikcxmvzi";
        String typed = "kiikcxxmmvvzz";
        isLongPressedName(name, typed);
        String name1 = "alex", typed1 = "aaleelx";//aaleexa
        System.out.println(isLongPressedName5(name1, typed1));
    }
    /**
     * 双指针
     * 字符串 typed 的每个字符，有且只有两种「用途」：
     *  作为 name 的一部分。此时会「匹配」name 中的一个字符
     *  作为长按键入的一部分。此时它应当与前一个字符相同。
     * 如果 typed 中存在一个字符，它两个条件均不满足，则应当直接返回 false；否则，当 typed 扫描完毕后，我们再检查 name 的每个字符是否都被「匹配」了。
     */
    public boolean isLongPressedName3(String name, String typed) {
        int i = 0, j = 0;
        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) { // 当 name[i]=typed[j] 时，说明两个字符串存在一对匹配的字符，此时将 i,j 都加 1。
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) { // 否则，如果 typed[j]=typed[j−1]，说明存在一次长按键入，此时只将 j 加 1。
                j++;
            } else {
                return false;
            }
        }
        return i == name.length(); // 最后，如果 i=name.length，说明 name 的每个字符都被「匹配」了。
    }
    /**
     * 1.核心while循环,如果字符一样则两个指针都加一,如果不一样,则长按键字符指针加1;
     * 2.最后判断p1是否等于name的长度即可;
     * not pass for name = "alex", typed = "aaleexa"
     */
    static public boolean isLongPressedName5(String name, String typed) {
        char[] c1 = name.toCharArray();
        char[] c2 = typed.toCharArray();

        int p1 = 0, p2 = 0;
        while (p1 < c1.length && p2 < c2.length) {
            if (c1[p1] == c2[p2]) {
                p1++;
                p2++;
            }
            else{
                if(p1 >= 1 && c2[p2] == c1[p1-1]) {
                    p2++;
                } else {
                    return false;
                }
            }
        }
        while(p2 < c2.length) {
            if (c2[p2] == c1[p1 - 1]) {
                p2++;
            } else {
                return false;
            }
        }
        return p1 == c1.length;
    }
    /**
     * 这道题目一看以为是哈希，仔细一看不行，要有顺序。所以模拟同时遍历两个数组，进行对比就可以了。
     * 对比的时候需要一下几点：
     *   name[i] 和 typed[j]相同，则i++，j++ （继续向后对比）
     *   name[i] 和 typed[j]不相同
     *     看是不是第一位就不相同了，也就是j如果等于0，那么直接返回false
     *     不是第一位不相同，就让j跨越重复项，移动到重复项之后的位置，再次比较name[i] 和typed[j]
     *       如果 name[i] 和 typed[j]相同，则i++，j++ （继续向后对比）
     *       不相同，返回false
     * 对比完之后有两种情况
     *   name没有匹配完，例如name:"pyplrzzzzdsfa" type:"ppyypllr"
     *   type没有匹配完，例如name:"alex" type:"alexxrrrrssda"
     * 时间复杂度：O(n) 空间复杂度：O(1)
     */
    static public boolean isLongPressedName(String name, String typed) {
        int i = 0; int j = 0;
        char[] nameArr = name.toCharArray();
        char[] typedArr = typed.toCharArray();

        while(i < nameArr.length && j < typedArr.length){
            if(nameArr[i] == typedArr[j]){  // 相同则同时向后匹配
                i++; j++;
            } else { // 不相同
                while(j < typedArr.length && j > 0 // j跨越重复项，向后移动，同时防止j越界
                        && typedArr[j] == typedArr[j - 1]){
                    j++;
                }
                if(i < nameArr.length && j < typedArr.length && nameArr[i] == typedArr[j]){// j跨越重复项之后再次和name[i]匹配
                    i++; j++; // 相同则同时向后匹配
                } else {
                    return false;
                }
            }
        }
        // 说明name没有匹配完，例如 name:"pyplrzzzzdsfa" type:"ppyypllr"
        if(i != nameArr.length ){
            return false;
        }
        // 说明type没有匹配完，例如 name:"alex" type:"alexxrrrrssda"
        if(j != typedArr.length ){
            while(j < typedArr.length && j > 0){
                if(typedArr[j] == typedArr[j- 1]){
                    j++;
                }else{
                    return false;
                }
            }
        }
        return true;
    }
    /**
     *  另一种形式
     */
    public boolean isLongPressedName1(String name, String typed) {
        int i = 0, j = 0;
        int m = name.length(), n = typed.length();
        while (i< m && j < n) {
            if (name.charAt(i) == typed.charAt(j)) {  // 相同则同时向后匹配
                i++; j++;
            }
            else {
                if (j == 0) {
                    return false; // 如果是第一位就不相同直接返回false
                }
                // 判断边界为n-1,若为n会越界,例如name:"kikcxmvzi" typed:"kiikcxxmmvvzzz"
                while (j < n-1 && typed.charAt(j) == typed.charAt(j-1)) {
                    j++;
                }
                if (name.charAt(i) == typed.charAt(j)) {  // j跨越重复项之后再次和name[i]匹配
                    i++; j++; // 相同则同时向后匹配
                } else {
                    return false;
                }
            }
        }
        // 说明name没有匹配完
        if (i < m) {
            return false;
        }
        // 说明type没有匹配完
        while (j < n) {
            if (typed.charAt(j) == typed.charAt(j-1)) {
                j++;
            } else {
                return false;
            }
        }
        return true;
    }
}
