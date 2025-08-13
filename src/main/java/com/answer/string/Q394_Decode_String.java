package com.answer.string;

import java.util.*;

public class Q394_Decode_String {
    /**
     * Given an encoded string, return its decoded string.
     * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being
     * repeated exactly k times. Note that k is guaranteed to be a positive integer.
     * 字符串解码: 给定一个经过编码的字符串，返回它解码后的字符串。
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
     * 示例 1：
     *  输入：s = "3[a]2[bc]"
     *  输出："aaabcbc"
     * 示例 2：
     *  输入：s = "3[a2[c]]"
     *  输出："accaccacc"
     */
    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        System.out.println(decodeString_3(s)); // 输出："aaabcbc"
    }
    /**
     * Approach 2: Using 1 Stack
     */
    static int ptr;

    public static String decodeString(String s) {
        LinkedList<String> stk = new LinkedList<>();
        ptr = 0;

        while (ptr < s.length()) {
            char cur = s.charAt(ptr);
            if (Character.isDigit(cur)) {
                String digits = getDigits(s); // 获取一个数字并进栈
                stk.addLast(digits);
            } else if (Character.isLetter(cur) || cur == '[') {
                stk.addLast(String.valueOf(s.charAt(ptr++)));   // 获取一个字母并进栈
            } else {
                ++ptr;
                LinkedList<String> sub = new LinkedList<String>();
                while (!"[".equals(stk.peekLast())) {
                    sub.addLast(stk.removeLast());
                }
                Collections.reverse(sub);
                stk.removeLast(); // 左括号出栈
                int repTime = Integer.parseInt(stk.removeLast()); // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                StringBuffer t = new StringBuffer();
                String o = getString(sub);

                while (repTime-- > 0) { // 构造字符串
                    t.append(o);
                }
                stk.addLast(t.toString()); // 将构造好的字符串入栈
            }
        }
        return getString(stk);
    }

    public static String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }

    public static String getString(LinkedList<String> v) {
        StringBuffer ret = new StringBuffer();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }
    /**
     * Approach 2: Using 2 Stack
     * 辅助栈法: 本题难点在于括号内嵌套括号，需要从内向外生成与拼接字符串，这与栈的先入后出特性对应。
     * 构建辅助栈 stack， 遍历字符串 s 中每个字符 c；
     *  当 c 为数字时，将数字字符转化为数字 multi，用于后续倍数计算；
     *  当 c 为字母时，在 res 尾部添加 c；
     *  当 c 为 [ 时，将当前 multi 和 res 入栈，并分别置空置 0：
     *      记录此 [ 前的临时结果 res 至栈，用于发现对应 ] 后的拼接操作；
     *      记录此 [ 前的倍数 multi 至栈，用于发现对应 ] 后，获取 multi × [...] 字符串。
 *          进入到新 [ 后，res 和 multi 重新记录。
     *  当 c 为 ] 时，stack 出栈，拼接字符串 res = last_res + cur_multi * res，其中:
     *      last_res是上个 [ 到当前 [ 的字符串，例如 "3[a2[c]]" 中的 a；
     *      cur_multi是当前 [ 到 ] 内字符串的重复倍数，例如 "3[a2[c]]" 中的 2。
     *  返回字符串 res。
     */
    public static String decodeString_1(String s) {
        StringBuffer res = new StringBuffer();
        int mul = 0;
        Deque<Integer> numStack = new ArrayDeque<>(); // 存储需要重复字符串的数字
        Deque<String> strStack = new ArrayDeque<>(); // 存储待重复的字符串
        for(char c : s.toCharArray()){
            if(c == '['){
                numStack.push(mul); // 把括号前的数字压栈
                strStack.push(res.toString()); //把括号前的字母——即将来等待被重复的字符串压栈
                mul = 0; //需重复数字归零（注意！因为 num 可能会是n位数，为了方便后续统一x10处理，必须归零
                res = new StringBuffer();// res 也要重置
            }else if(c == ']'){
                StringBuffer temp = new StringBuffer();
                int val = numStack.pop(); //此次要重复的次数数字
                for(int i = 0; i < val; i++){
                    temp.append(res); // 本次要重复的完整子串
                }
                res = new StringBuffer(strStack.pop() + temp); //紧贴着这一组括号外的还没执行重复的字符串
            }else if(c >= '0' && c <= '9'){
                mul = mul * 10 + Integer.parseInt(c + ""); // 把字符数字转换为数字
            }else if(c >= 'a' && c <= 'z'){
                res.append(c);
            }
        }

        return res.toString();
    }
    /**
     * 类似于制作一个能使用分配律的计算器。想象：如3[a2[c]b] 使用一次分配律-> 3[accb] 再使用一次分配律->accbaccbaccb
     * 字符串栈：用来弹出元素，作为拼接的起始内容
     * 数字栈：用来弹出元素，得到拼接的次数
     * num：[前的拼接次数，入数字栈
     * res：作为拼接的内容，入字符串栈，也是最终返回的结果
     */
    public String decodeString4(String s) {
        StringBuffer res = new StringBuffer();
        Deque<Integer> nums = new LinkedList<Integer>();
        Deque<String> strs = new LinkedList<String>();;
        int num = 0;
        int len = s.length();
        for(int i = 0; i < len; ++ i) {
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num = num * 10 + s.charAt(i) - '0';
            }
            else if((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') ||(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')) {
                res = res.append(s.charAt(i));
            }
            else if(s.charAt(i) == '[') {   //将‘[’前的数字压入nums栈内， 字母字符串压入strs栈内
                nums.push(num);
                num = 0;
                strs.push(res.toString());
                res = new StringBuffer();
            } else {    //遇到‘]’时，操作与之相配的‘[’之间的字符，使用分配律
                int times = nums.pop();
                StringBuffer temp = new StringBuffer(strs.pop());
                for(int j = 0; j < times; ++ j) {
                    temp.append(res);
                }
                res = temp;  //之后若还是字母，就会直接加到res之后，因为它们是同一级的运算
            }
        }
        return res.toString();
    }
    /**
     * Recursion 递归法
     * 总体思路与辅助栈法一致，不同点在于将 [ 和 ] 分别作为递归的开启与终止条件：
     *  当 s[i] == ']' 时，返回当前括号内记录的 res 字符串与 ] 的索引 i （更新上层递归指针位置）；
     *  当 s[i] == '[' 时，开启新一层递归，记录此 [...] 内字符串 tmp 和递归后的最新索引 i，并执行 res + multi * tmp 拼接字符串。
     *  遍历完毕后返回 res。
     */
    static public String decodeString_3(String s) {
        return dfs(s, 0)[0];
    }

    static private String[] dfs(String s, int i) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        while(i < s.length()) {
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                multi = multi * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
            }
            else if(s.charAt(i) == '[') {
                String[] tmp = dfs(s, i + 1);
                i = Integer.parseInt(tmp[0]);
                while(multi > 0) {
                    res.append(tmp[1]);
                    multi--;
                }
            }
            else if(s.charAt(i) == ']') {
                return new String[]{String.valueOf(i), res.toString()};
            }
            else {
                res.append(String.valueOf(s.charAt(i)));
            }
            i++;
        }
        return new String[] { res.toString() };
    }
}
