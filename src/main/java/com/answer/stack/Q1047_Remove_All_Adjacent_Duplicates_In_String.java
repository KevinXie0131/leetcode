package com.answer.stack;

import java.util.ArrayDeque;

public class Q1047_Remove_All_Adjacent_Duplicates_In_String {
    /**
     * 删除字符串中的所有相邻重复项
     * 给出由小写字母组成的字符串 s，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
     * A duplicate removal consists of choosing two adjacent and equal letters and removing them.
     * 在 s 上反复执行重复项删除操作，直到无法继续删除。
     * We repeatedly make duplicate removals on s until we no longer can.
     * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
     * Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.
     *
     * 示例：
     *  输入："abbaca"
     *  输出："ca"
     *  解释：例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
     */
    public static void main(String[] args) {
        String s = "abbaca";
        System.out.println(removeDuplicates_1(s));
    }
    /**
     * Use StringBuffer as stack
     * 可以拿字符串直接作为栈，这样省去了栈还要转为字符串的操作
     * 也可以用 StringBuilder 来修改字符串，速度更快
     */
    public static String removeDuplicates(String s) {
        StringBuffer stack = new StringBuffer();
        int top = -1;  // top为 res 的长度
        for(int i = 0 ; i < s.length(); i++){
            char ch = s.charAt(i);
            // 当 top > 0,即栈中有字符时，当前字符如果和栈中字符相等，弹出栈顶字符，同时 top--
            if(top >= 0 && ch == stack.charAt(top)){
                stack.deleteCharAt(top);
                top--;
            // 否则，将该字符 入栈，同时top++
            }else{
                stack.append(ch);
                top++;
            }
        }
        return stack.toString();
    }
    /**
     * Two Pointers 双指针
     */
    public static String removeDuplicates_1(String s) {
        char[] ch = s.toCharArray();
        int fast = 0;
        int slow = 0;
        while(fast < s.length()){
            // 直接用fast指针覆盖slow指针的值
            ch[slow] = ch[fast];
            // 遇到前后相同值的，就跳过，即slow指针后退一步，下次循环就可以直接被覆盖掉了
            if(slow > 0 && ch[slow] == ch[slow - 1]){
                slow--;
            }else{
                slow++;
            }
            fast++;
        }
        return new String(ch,0,slow);

    }
    /**
     * Use stack 使用 Deque 作为堆栈
     */
    public String removeDuplicates_2(String s) {
        //ArrayDeque会比LinkedList在除了删除元素这一点外会快一点
        //参考：https://stackoverflow.com/questions/6163166/why-is-arraydeque-better-than-linkedlist
        ArrayDeque<Character> deque = new ArrayDeque<>();
        char ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            if (deque.isEmpty() || deque.peek() != ch) {
                deque.push(ch);
            } else {
                deque.pop(); // s 与 st.top()相等的情况
            }
        }
        String str = "";
        //剩余的元素即为不重复的元素
        while (!deque.isEmpty()) {
            str = deque.pop() + str;
        }
        return str;
        /*while(stack.size() > 0){
            sb.append(stack.pop()); // 将栈中元素放到result字符串汇总
        }
        return sb.reverse().toString();  // 此时字符串需要反转一下*/

/*        while(stack.size() > 0){
            sb.insert(0, stack.pop()); //字符串不需要反转
        }
        return sb.toString();*/
    }
}
