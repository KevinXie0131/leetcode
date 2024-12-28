package com.template;

public class UseString {
    public static void main(String[] args) {
        /**
         * 在String中，subString()、concat()和replace()等等操作都不是在原有的字符串对象上进行的，而是生成了新的String对象,
         * 然后将原String的变量引用指向新生成的对象
         */
        /**
         * 字符串—>基本数据类型、包装类
         * Integer包装类的public static int parseInt(String s)可将由“数字”字符组成的字符串转换为整型
         */
        String valueStr = "123";
        int value = Integer.parseInt(valueStr);
        System.out.println( value );
        valueStr = "123.321";
        double value1 = Double.parseDouble(valueStr);
        System.out.println( value1 );
        /**
         * 基本数据类型、包装类—>字符串
         * 调用String类的public String valueOf(int n)可将int型转换为字符串
         */
        int num = 9876;
        String valueStr2 = num + ""; //方法1：字符串连接
        System.out.println( valueStr2 );
        String valueStr3 = String.valueOf(num);
        System.out.println( valueStr3 );
        float num1 = 4321.123f;
        String valueStr4 = String.valueOf(num1);
        System.out.println( valueStr4 );
        /**
         * String： 1.0 ，不可变的字符序列；底层使用char[]来保存
         * StringBuffer：1.0 ，可变的字符序列；线程安全的，效率低；底层使用char[]来保存
         * StringBuilder：JDK 5.0新增 ，可变的字符序列；线程不安全，效率高；底层使用char[]来保存
         * 效率从高到低排列：StringBuilder > StringBuffer> String
         */
        StringBuilder sb = new StringBuilder(20); // 为了提高效率，建议使用StringBuffer(int capacity) 或 StringBuilder(int capacity)指定初始容量，尽量避免自动扩容
        sb.append('a');
        sb.insert(0, 'b');
        System.out.println( sb.toString() );
        sb.append('d');
        sb.replace(1, 2,"c");
        System.out.println( sb.toString() );
        sb.deleteCharAt(0);
        System.out.println( sb.toString() );
        sb.reverse();
        System.out.println( sb.toString() );
        System.out.println( sb.indexOf("c") ); // return -1 if not found
        sb.setCharAt(0, 'f');
        System.out.println( sb.toString() );
        // 字符串长度
        int len = "Runoob".length();
        System.out.println( "length : " + len );
        // 连接字符串
        String string1 = "Runoob";
        System.out.println(string1.concat("!"));
        // 返回指定索引处的字符
        char result = string1.charAt(2);
        System.out.println(result);
        // 如果参数字符串等于此字符串，则返回值 0；小于，则返回一个小于 0 的值；大于，则返回一个大于 0 的值。
        int result0 = "Strings".compareTo( "Strings" );
        System.out.println(result0);
        int result1 = "STRINGS".compareToIgnoreCase( "Strings" );
        System.out.println(result1);
        // 如果给定对象与字符串相等，则返回 true，否则返回 false。
        String Str1 = new String("runoob");
        String Str2 = new String("runoob");
        boolean retVal = Str1.equals( Str2 );
        System.out.println( retVal );
        String Str3 = new String("runoob");
        String Str4 = new String("RUNOOB");
        boolean retVal2  = Str3.equalsIgnoreCase( Str4 );
        System.out.println( retVal2 );
        // 如果字符串以指定的前缀开始，则返回 true；否则返回 false。
        System.out.println("www.runoob.com".startsWith("www") );
        // 如果参数表示的字符序列是此对象表示的字符序列的后缀，则返回 true；否则返回 false。
        System.out.println("www.runoob".endsWith( "runoob" ));
        // 返回指定字符在字符串中第一次出现处的索引
        // 注：indexOf和lastIndexOf方法如果未找到都是返回-1
        String string = "aaa456ac";
        System.out.println(string.indexOf("b")); // indexOf(String str); 返回结果：-1，"b"不存在
        System.out.println(string.indexOf("a",3));//indexOf(String str, int fromIndex); 返回结果：6
        System.out.println(string.indexOf('c'));//indexOf(int ch)；返回结果：7
        System.out.println(string.indexOf('a',3));//indexOf(int ch, int fromIndex); 返回结果：6
        string = "abcdabcd";
        System.out.println(string.lastIndexOf("b"));
        System.out.println(string.lastIndexOf("a",3));
        System.out.println(string.lastIndexOf('c'));
        System.out.println(string.lastIndexOf('a',6));
        // 转换为小写的字符串。
        System.out.println( "WWW.RUNOOB.COM".toLowerCase() );
        // 字符转换为大写后的字符串。
        System.out.println( "www.runoob.com".toUpperCase() );
        // 删除头尾空白符的字符串。
        System.out.println( "    www.runoob.com    ".trim() );
        // 字符数组。
        String Str6 = new String("www.runoob.com");
        System.out.println( Str6.toCharArray() );
        // 返回字符串的子字符串
        String Str7 = new String("01234567890123456789");
        System.out.println(Str7.substring(4) ); // beginIndex -- 起始索引（包括）, 索引从 0 开始。
        System.out.println(Str7.substring(4, 10) ); //   endIndex -- 结束索引（不包括）。
        // 拆分字符串
        String str8 = new String("Welcome-to-Runoob");
        for (String retval: str8.split("-")){
            System.out.print(retval + " = ");
        }
        System.out.println();
        // 字符串替换
        String str9 = "hello";
        System.out.println(str9.replaceAll("l","L"));//输出heLLo
        System.out.println(str9.replaceFirst("e","E"));//输出hEllo
        System.out.println(str9); // 此时的replace是新创建的字符串，str9本身并没有改变
        // 判断字符串中是否包含指定的字符或字符串
        String myStr = "Runoob";
        System.out.println(myStr.contains("Run"));
        System.out.println(myStr.contains("s"));
    }
}
