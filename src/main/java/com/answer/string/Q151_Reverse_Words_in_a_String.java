package com.answer.string;

import java.util.*;

public class Q151_Reverse_Words_in_a_String {

    public static void main(String[] args) {
     //   String s = "the sky is blue";
        String s = "a good   example";
        System.out.println(reverseWords_1(s));
    }
    // 用Java内置方法实现
    public String reverseWords(String s) {
        s = s.trim();

        String[] str = s.split("\\s+");
        List<String> list = Arrays.asList(str);

        Collections.reverse(list);
        String result = String.join(" ", list);
        return result;
    }
    /**
     * 解法1: 不使用Java内置方法实现. 先整体反转再局部反转
     * 1.去除首尾以及中间多余空格
     * 2.反转整个字符串
     * 3.反转各个单词
     */
    public static String reverseWords_1(String s) {
        StringBuffer sb = trimString(s);  // 1.去除首尾以及中间多余空格
        reverseString(sb, 0, sb.length()-1);  // 2.反转整个字符串
        reverseEachWord(sb);// 3.反转各个单词
        return sb.toString();
    }

    private static StringBuffer trimString(String s){
        int left = 0;
        int right = s.length() - 1;
        while(s.charAt(left) == ' '){
            left++;
        }
        while(s.charAt(right) == ' '){
            right--;
        }
       /* StringBuffer sb = new StringBuffer();
        while (left <= right) {
            char c = s.charAt(left);
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            left++;
        }
        return sb;*/
        StringBuffer sb = new StringBuffer();
        while(left <= right){
            char c = s.charAt(left);
            if(!(c == ' ' && sb.charAt(sb.length() - 1) == ' ')){
                sb.append(c);
            }
            left++;
        }
        return sb;
    }
    // 反转字符串指定区间[start, end]的字符
    private static void reverseString(StringBuffer sb, int start, int end){
        while(start < end){
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }

    private static void reverseEachWord(StringBuffer sb) {
        int left = 0;
        int right = 1;
        int n = sb.length();
        while(left < n){
            while(right < n && sb.charAt(right) != ' '){
                right++;
            }
            reverseString(sb, left, right - 1);
            left = right + 1;
            right++;
        }
    }
    /**
     * 解法二：创建新字符数组填充。时间复杂度O(n)
     */
    public String reverseWords_2(String s) {
        //源字符数组
        char[] initialArr = s.toCharArray();
        //新字符数组
        char[] newArr = new char[initialArr.length+1];//下面循环添加"单词 "，最终末尾的空格不会返回
        int newArrPos = 0;
        //i来进行整体对源字符数组从后往前遍历
        int i = initialArr.length-1;
        while(i>=0){
            while(i>=0 && initialArr[i] == ' '){i--;}  //跳过空格
            //此时i位置是边界或!=空格，先记录当前索引，之后的while用来确定单词的首字母的位置
            int right = i;
            while(i>=0 && initialArr[i] != ' '){i--;}
            //指定区间单词取出(由于i为首字母的前一位，所以这里+1,)，取出的每组末尾都带有一个空格
            for (int j = i+1; j <= right; j++) {
                newArr[newArrPos++] = initialArr[j];
                if(j == right){
                    newArr[newArrPos++] = ' ';//空格
                }
            }
        }
        //若是原始字符串没有单词，直接返回空字符串；若是有单词，返回0-末尾空格索引前范围的字符数组(转成String返回)
        if(newArrPos == 0){
            return "";
        }else{
            return new String(newArr,0,newArrPos-1);
        }
    }
    /**
     * 解法三：双反转+移位，String 的 toCharArray() 方法底层会 new 一个和原字符串相同大小的 char 数组，空间复杂度：O(n)
     * 思路：
     *	①反转字符串  "the sky is blue " => " eulb si yks eht"
     *	②遍历 " eulb si yks eht"，每次先对某个单词进行反转再移位
     *	   这里以第一个单词进行为演示：" eulb si yks eht" ==反转=> " blue si yks eht" ==移位=> "blue si yks eht"
     */
    public String reverseWords_3(String s) {
        //步骤1：字符串整体反转（此时其中的单词也都反转了）
        char[] initialArr = s.toCharArray();
        reverse(initialArr, 0, s.length() - 1);
        int k = 0;
        for (int i = 0; i < initialArr.length; i++) {
            if (initialArr[i] == ' ') {
                continue;
            }
            int tempCur = i;
            while (i < initialArr.length && initialArr[i] != ' ') {
                i++;
            }
            for (int j = tempCur; j < i; j++) {
                if (j == tempCur) { //步骤二：二次反转
                    reverse(initialArr, tempCur, i - 1);//对指定范围字符串进行反转，不反转从后往前遍历一个个填充有问题
                }
                //步骤三：移动操作
                initialArr[k++] = initialArr[j];
                if (j == i - 1) { //遍历结束
                    //避免越界情况，例如=> "asdasd df f"，不加判断最后就会数组越界
                    if (k < initialArr.length) {
                        initialArr[k++] = ' ';
                    }
                }
            }
        }
        if (k == 0) {
            return "";
        } else {
            //参数三：以防出现如"asdasd df f"=>"f df asdasd"正好凑满不需要省略空格情况
            return new String(initialArr, 0, (k == initialArr.length) && (initialArr[k - 1] != ' ') ? k : k - 1);
        }
    }

    public void reverse(char[] chars, int begin, int end) {
        for (int i = begin, j = end; i < j; i++, j--) {
            chars[i] ^= chars[j];
            chars[j] ^= chars[i];
            chars[i] ^= chars[j];
        }
    }
    /**
     * 解法四：时间复杂度 O(n)
     * 参考卡哥 c++ 代码的三步骤：先移除多余空格，再将整个字符串反转，最后把单词逐个反转
     * 有别于解法一 ：没有用 StringBuilder  实现，而是对 String 的 char[] 数组操作来实现以上三个步骤
     */
    //用 char[] 来实现 String 的 removeExtraSpaces，reverse 操作
    public String reverseWords_4(String s) {
        char[] chars = s.toCharArray();
        //1.去除首尾以及中间多余空格
        chars = removeExtraSpaces(chars);
        //2.整个字符串反转
        reverse_4(chars, 0, chars.length - 1);
        //3.单词反转
        reverseEachWord(chars);
        return new String(chars);
    }

    //1.用 快慢指针 去除首尾以及中间多余空格，可参考数组元素移除的题解
    public char[] removeExtraSpaces(char[] chars) {
        int slow = 0;
        for (int fast = 0; fast < chars.length; fast++) {
            //先用 fast 移除所有空格
            if (chars[fast] != ' ') {
                //在用 slow 加空格。 除第一个单词外，单词末尾要加空格
                if (slow != 0) {
                    chars[slow++] = ' ';
                }
                //fast 遇到空格或遍历到字符串末尾，就证明遍历完一个单词了
                while (fast < chars.length && chars[fast] != ' ') {
                    chars[slow++] = chars[fast++];
                }
            }
        }
        //相当于 c++ 里的 resize()
        char[] newChars = new char[slow];
        System.arraycopy(chars, 0, newChars, 0, slow);
        return newChars;
    }

    //双指针实现指定范围内字符串反转，可参考字符串反转题解
    public void reverse_4(char[] chars, int left, int right) {
        if (right >= chars.length) {
            System.out.println("set a wrong right");
            return;
        }
        while (left < right) {
            chars[left] ^= chars[right];
            chars[right] ^= chars[left];
            chars[left] ^= chars[right];
            left++;
            right--;
        }
    }

    //3.单词反转
    public void reverseEachWord(char[] chars) {
        int start = 0;
        //end <= s.length() 这里的 = ，是为了让 end 永远指向单词末尾后一个位置，这样 reverse 的实参更好设置
        for (int end = 0; end <= chars.length; end++) {
            // end 每次到单词末尾后的空格或串尾,开始反转单词
            if (end == chars.length || chars[end] == ' ') {
                reverse_4(chars, start, end - 1);
                start = end + 1;
            }
        }
    }
}
