package com.answer.stack;

import java.util.ArrayDeque;

public class Q1047_Remove_All_Adjacent_Duplicates_In_String {

    public static void main(String[] args) {
        String s = "abbaca";
        System.out.println(removeDuplicates(s));
    }

    public static String removeDuplicates(String s) {
        StringBuffer stack = new StringBuffer();
        int top = -1;
        for(int i = 0 ; i < s.length(); i++){
            char ch = s.charAt(i);
            if(top >= 0 && ch == stack.charAt(top)){
                stack.deleteCharAt(top);
                top--;
            }else{
                stack.append(ch);
                top++;
            }
        }

        return stack.toString();
    }
    /**
     * Two Pointers
     */
    public String removeDuplicates_1(String s) {
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
     * Use stack
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
                deque.pop();
            }
        }
        String str = "";
        //剩余的元素即为不重复的元素
        while (!deque.isEmpty()) {
            str = deque.pop() + str;
        }
        return str;
    }
}
