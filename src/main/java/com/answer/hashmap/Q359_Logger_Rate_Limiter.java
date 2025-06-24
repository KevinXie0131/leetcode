package com.answer.hashmap;

public class Q359_Logger_Rate_Limiter {
    /**
     * Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.
     * Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.
     * It is possible that several messages arrive roughly at the same time.
     *
     * Example:
     * Logger logger = new Logger();
     * // logging string "foo" at timestamp 1
     * logger.shouldPrintMessage(1, "foo"); returns true;
     * // logging string "bar" at timestamp 2
     * logger.shouldPrintMessage(2,"bar"); returns true;
     * // logging string "foo" at timestamp 3
     * logger.shouldPrintMessage(3,"foo"); returns false;
     * // logging string "bar" at timestamp 8
     * logger.shouldPrintMessage(8,"bar"); returns false;
     * // logging string "foo" at timestamp 10
     * logger.shouldPrintMessage(10,"foo"); returns false;
     * // logging string "foo" at timestamp 11
     * logger.shouldPrintMessage(11,"foo"); returns true;
     *
     * 日志速率限制器
     * 请你设计一个日志记录系统，使得每次出现新日志消息时，你可以判断这个消息是否应该被打印（输出）。
     * 每条消息都带有一个时间戳，表示该消息出现的时间（以秒为单位）。在同一时间戳可能会出现多条不同消息。
     * 该系统应当实现如下方法：
     *  Logger() 初始化日志记录系统对象
     *  bool shouldPrintMessage(int timestamp, string message)
     *  如果这条消息在 timestamp 时刻可以被打印出来，则返回 true。 如果这条消息在最近的 10 秒之内（含）已经被打印过，则返回 false。
     * 示例：
     * Logger logger = new Logger();
     * logger.shouldPrintMessage(1, "foo"); // 返回 true
     * logger.shouldPrintMessage(2, "bar"); // 返回 true
     * logger.shouldPrintMessage(3, "foo"); // 返回 false
     * logger.shouldPrintMessage(8, "bar"); // 返回 false
     * logger.shouldPrintMessage(10, "foo"); // 返回 false
     * logger.shouldPrintMessage(11, "foo"); // 返回 true
     */
}
