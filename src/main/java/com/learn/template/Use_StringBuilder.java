package com.learn.template;

public class Use_StringBuilder {

    /**
     * 高效的字符串构建
     */
    public String fn(char[] arr) {
        StringBuilder sb = new StringBuilder();
        for (char c: arr) {
            sb.append(c);
        }

        return sb.toString();
    }
}
