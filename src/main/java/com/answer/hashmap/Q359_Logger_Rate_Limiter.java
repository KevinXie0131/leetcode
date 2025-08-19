package com.answer.hashmap;

import java.util.*;

public class Q359_Logger_Rate_Limiter {
    /**
     * Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.
     * Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.
     * It is possible that several messages arrive roughly at the same time.
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
    /**
     * 用 HashMap 存储每条消息最近一次打印的时间戳。
     * 如果消息从未出现过，或者距离上一次打印超过10秒，则允许打印，并更新时间戳。否则不允许打印。
     */
    private Map<String, Integer> messageTimestamp;  // 存储每条消息最近一次打印的时间戳
    /** Initialize your data structure here. */
    public Q359_Logger_Rate_Limiter() {
    }
    /**
     * Returns true if the message should be printed in the given timestamp,
     * otherwise returns false.
     * The timestamp is in seconds granularity.
     */
    public boolean shouldPrintMessage(int timestamp, String message) {
        // 如果消息从未出现过，或者上一次打印距离现在超过10秒，则可以打印
        if (!messageTimestamp.containsKey(message) || timestamp - messageTimestamp.get(message) >= 10) {
            messageTimestamp.put(message, timestamp);
            return true;
        } else {
            return false;
        }
    }
    /**
     * queue保存10秒窗口内的消息和时间戳。
     * recentMessages用于快速查找消息是否在窗口内。
     * 每次调用shouldPrintMessage时，先清理10秒前的消息，然后判断当前消息是否可以打印。
     *
     * 优势: 更适合如果你想维护一个滑动窗口，且消息量不大时。只存储10秒内的消息，空间更可控。
     */
    private Queue<LogEntry> queue;// 用于存储消息和其时间戳
    private Set<String> recentMessages;

    private static class LogEntry {
        int timestamp;
        String message;
        LogEntry(int timestamp, String message) {
            this.timestamp = timestamp;
            this.message = message;
        }
    }

    public void Logger1() {
        queue = new LinkedList<>();
        recentMessages = new HashSet<>();
    }

    public boolean shouldPrintMessage1(int timestamp, String message) {
        while (!queue.isEmpty() && timestamp - queue.peek().timestamp >= 10) {// 清理10秒前的日志
            LogEntry old = queue.poll();
            recentMessages.remove(old.message);
        }
        if (!recentMessages.contains(message)) {    // 如果消息不在10秒窗口内，则可以打印
            queue.offer(new LogEntry(timestamp, message));
            recentMessages.add(message);
            return true;
        }
        return false;
    }
}
