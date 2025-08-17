package com.answer.stack;

import java.util.*;

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
        StringBuffer stack = new StringBuffer(); // 将 StringBuffer 当做栈
        int top = -1;  // top为 stack 的长度
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
     * anther form StringBuffer模拟栈
     */
    public String removeDuplicates_0(String s) {
        StringBuffer sb = new StringBuffer();
        char[] chars = s.toCharArray();

        for (char ch : chars) {
            int size = sb.length();
            if (size > 0 && sb.charAt(size - 1) == ch) {
                sb.deleteCharAt(size - 1);
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
    /**
     * Two Pointers 双指针 / 数组模拟
     * 本题要点：
     * 1. 两个相邻且相同字符会被删除。（注意：是两个！）
     * 2. 删除字符串中两个相邻并且相同的字符可能会产生新的相邻并且相同的字符。 比如对于 abba，删除 bb之后， aa会碰到一起，也需要继续把 aa删掉。
     */
    public static String removeDuplicates_1(String s) {
        char[] ch = s.toCharArray();
        int fast = 0;
        int slow = 0;
        while(fast < s.length()){
            // 直接用fast指针覆盖slow指针的值
            ch[slow] = ch[fast]; // 尝试将元素入栈
            // 遇到前后相同值的，就跳过，即slow指针后退一步，下次循环就可以直接被覆盖掉了
            if(slow > 0 && ch[slow] == ch[slow - 1]){  // 如果栈顶有元素, 和尝试入栈的元素进行比较
                slow--; // 遇到和栈顶相同的元素了, 非但不能入栈, 还要弹出栈顶元素
            }else{
                slow++; // 与栈顶元素不同, 入栈成功
            }
            fast++;  // 继续探索数组
        }
        return new String(ch,0, slow);

    }
    /**
     * Use stack 使用 Deque 作为堆栈
     * 消除一对相邻重复项可能会导致新的相邻重复项出现，如从字符串 abba 中删除 bb 会导致出现新的相邻重复项 aa 出现。
     * 因此我们需要保存当前还未被删除的字符
     */
    public String removeDuplicates_2(String s) {
        // ArrayDeque会比LinkedList在除了删除元素这一点外会快一点
        // 参考：https://stackoverflow.com/questions/6163166/why-is-arraydeque-better-than-linkedlist
        ArrayDeque<Character> deque = new ArrayDeque<>();
        char ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            if (deque.isEmpty() || deque.peek() != ch) {
                deque.push(ch);
            } else {
                // 相邻且相同，因此 不入栈，并且要把栈顶元素弹出
                deque.pop(); // s 与 st.top()相等的情况
            }
        }
        String str = "";
        //剩余的元素即为不重复的元素
        while (!deque.isEmpty()) {
            str = deque.pop() + str;
        }
        return str;

        /*
        StringBuffer sb = new StringBuffer();
        while(deque.size() > 0){
            sb.append(deque.pop()); // 将栈中元素放到result字符串汇总
        }
        return sb.reverse().toString();  // 此时字符串需要反转一下
        */
        /*
        StringBuffer sb = new StringBuffer();
        while(deque.size() > 0){
            sb.insert(0, deque.pop()); //字符串不需要反转
        }
        return sb.toString();
        */
        /*
        StringBuffer sb = new StringBuffer();
        while(deque.size() > 0){
            sb.append(deque.pollLast()); //字符串不需要反转
        }
        return sb.toString();*/
    }
    /**
     * 双端队列，因为队列的两头都可以操作
     */
    public String removeDuplicates5(String s) {
        //双端队列
        Deque<Character> deque = new ArrayDeque<>();
        char[] chars = s.toCharArray();

        for (char ch : chars) {
            if (!deque.isEmpty() && deque.peekLast() == ch) {
                deque.pollLast();//从队列的尾部出队
            } else {
                deque.offer(ch);//加入到队列的头部
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : deque) {
            sb.append(c);
        }
        return sb.toString();
    }
}
