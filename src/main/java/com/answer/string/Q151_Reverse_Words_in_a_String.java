package com.answer.string;

import java.util.*;

public class Q151_Reverse_Words_in_a_String {
    /**
     * Given an input string s, reverse the order of the words.
     * Return a string of the words in reverse order concatenated by a single space.
     * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
     * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
     * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
     * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
     */
    public static void main(String[] args) {
     //  String s = "the sky is blue"; // "blue is sky the"
     // String s = "a good   example"; // "example good a"
       String s= "  hello world  "; // "world hello"
       System.out.println(reverseWords_1(s));
    }
    /**
     * 用Java内置方法实现
     */
    public String reverseWords(String s) {
        s = s.trim();

        String[] str = s.split("\\s+");
        List<String> list = Arrays.asList(str);

        Collections.reverse(list);
        String result = String.join(" ", list);
        return result;
    }
    /**
     * 解决方法：倒序遍历单词列表，并将单词逐个添加至 StringBuilder ，遇到空单词时跳过。
     */
    public String reverseWords1(String s) {
        String[] strs = s.trim().split(" ");        // 删除首尾空格，分割字符串
        StringBuilder res = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) { // 倒序遍历单词列表
            if (strs[i].equals("")) continue;        // 遇到空单词则跳过
            res.append(strs[i] + " ");              // 将单词拼接至 StringBuilder
        }
        return res.toString().trim();               // 转化为字符串，删除尾部空格，并返回
    }
    /**
     * From 睡不醒的鲤鱼
     * 提交可通过
     */
    public static String reverseWords_5(String s) {
        StringBuffer sb = new StringBuffer(s);
        int start = 0;

        for(int i = 0; i < sb.length(); i++){
            if(sb.charAt(i) == ' ') { // 跳过空格
                continue;
            }
            int j = i;
            int end = start;
            while(j < sb.length() && sb.charAt(j) != ' '){
                sb.setCharAt(end, sb.charAt(j)); // 非空格字符向前移动
                end++;
                j++;
            }
            reverseString(sb, start, end - 1); // 反转字符串
            if(end <= sb.length() - 1){
                sb.setCharAt(end++, ' '); // 加空格
            }else{
                sb.append(' ');
            }
            i = j;
            start = end;
        }
        int count = sb.length() - start;
        while(count-- > 0) sb.deleteCharAt(sb.length() - 1); // 删除最后一个空格
        while(sb.charAt(sb.length() - 1) == ' ') sb.deleteCharAt(sb.length() - 1); // 删除最后一个空格
        reverseString(sb, 0, sb.length() - 1); // 整体反转
        return sb.toString();
    }
    /**
     * 解法1: 不使用Java内置方法实现. 先整体反转再局部反转
     * 1.去除首尾以及中间多余空格
     * 2.反转整个字符串
     * 3.反转各个单词
     * 对于字符串可变的语言，就不需要再额外开辟空间了，直接在字符串上原地实现。在这种情况下，反转字符和去除空格可以一起完成。
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
        while(left <= right && s.charAt(left) == ' '){ // 去掉字符串开头的空白字符
            left++;
        }
        while(left <= right && s.charAt(right) == ' '){  // 去掉字符串末尾的空白字符
            right--;
        }
       /* StringBuffer sb = new StringBuffer();   // 将字符串间多余的空白字符去除
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
            if(!(c == ' ' && sb.charAt(sb.length() - 1) == ' ')){ // not add space twice
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
            while(right < n && sb.charAt(right) != ' '){  // 循环至单词的末尾
                right++;
            }
            reverseString(sb, left, right - 1);  // 翻转单词
            left = right + 1;  // 更新start，去找下一个单词
            right++;
        }
    }
    /**
     * 双端队列
     * 由于双端队列支持从队列头部插入的方法，因此我们可以沿着字符串一个一个单词处理，然后将单词压入队列的头部，再将队列转成字符串即可。
     */
    public String reverseWords8(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }
        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }

        Deque<String> d = new ArrayDeque<String>();
        StringBuilder word = new StringBuilder();

        while (left <= right) {
            char c = s.charAt(left);
            if ((word.length() != 0) && (c == ' ')) {
                // 将单词 push 到队列的头部
                d.offerFirst(word.toString());
                word.setLength(0); // word = new StringBuilder(); // works too
            } else if (c != ' ') {
                word.append(c);
            }
            ++left;
        }
        d.offerFirst(word.toString());

        return String.join(" ", d);
    }
    /**
     * 双指针
     *  倒序遍历字符串 s ，记录单词左右索引边界 i , j 。
     *  每确定一个单词的边界，则将其添加至单词列表 res 。
     *  最终，将单词列表拼接为字符串，并返回即可。
     */
    public String reverseWords7(String s) {
        s = s.trim();                                    // 删除首尾空格
        int j = s.length() - 1, i = j;
        StringBuilder res = new StringBuilder();
        while (i >= 0) {
            while (i >= 0 && s.charAt(i) != ' '){
                i--;     // 搜索首个空格
            }
            res.append(s.substring(i + 1, j + 1) + " "); // 添加单词

            while (i >= 0 && s.charAt(i) == ' ') {
                i--;     // 跳过单词间空格
            }
            j = i;                                       // j 指向下个单词的尾字符
        }
        return res.toString().trim();                    // 转化为字符串并返回
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
        chars = removeExtraSpaces(chars); //1.去除首尾以及中间多余空格
        reverse_4(chars, 0, chars.length - 1);  //2.整个字符串反转
        reverseEachWord(chars); //3.单词反转
        return new String(chars);
    }

    //1.用 快慢指针 去除首尾以及中间多余空格，可参考数组元素移除的题解
    public char[] removeExtraSpaces(char[] chars) {
        int slow = 0;
        for (int fast = 0; fast < chars.length; fast++) {
            if (chars[fast] != ' ') {//先用 fast 移除所有空格
                if (slow != 0) {  //在用 slow 加空格。 除第一个单词外，单词末尾要加空格
                    chars[slow++] = ' ';
                }
                while (fast < chars.length && chars[fast] != ' ') {  //fast 遇到空格或遍历到字符串末尾，就证明遍历完一个单词了
                    chars[slow++] = chars[fast++];
                }
            }
        }
        char[] newChars = new char[slow];  //相当于 c++ 里的 resize()
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
            if (end == chars.length || chars[end] == ' ') { // end 每次到单词末尾后的空格或串尾,开始反转单词
                reverse_4(chars, start, end - 1);
                start = end + 1;
            }
        }
    }
}
