package com.answer.hashmap;

import java.util.*;

public class Q380_Insert_Delete_GetRandom_O1 {
    /**
     * O(1) 时间插入、删除和获取随机元素
     * 实现RandomizedSet 类：
     *   RandomizedSet() 初始化 RandomizedSet 对象
     *   bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
     *   bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
     *   int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率( the same probability) 被返回。
     * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
     *
     * 示例：
     * 输入: ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
     *       [[], [1], [2], [2], [], [1], [2], []]
     * 输出: [null, true, false, true, 2, true, false, 2]
     * 解释: RandomizedSet randomizedSet = new RandomizedSet();
     *       randomizedSet.insert(1); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
     *       randomizedSet.remove(2); // 返回 false ，表示集合中不存在 2 。
     *       randomizedSet.insert(2); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
     *       randomizedSet.getRandom(); // getRandom 应随机返回 1 或 2 。
     *       randomizedSet.remove(1); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
     *       randomizedSet.insert(2); // 2 已在集合中，所以返回 false 。
     *       randomizedSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
     */
    /**
     * 变长数组 + 哈希表
     * 哈希表可以在 O(1) 的时间内完成插入和删除操作，但是由于无法根据下标定位到特定元素，因此不能在 O(1) 的时间内完成获取随机元素操作。
     * 需要将变长数组和哈希表结合
     */
    List<Integer> nums; // 存储元素
    Map<Integer, Integer> indices; // 存储每个元素在变长数组中的下标
    Random random;

    public Q380_Insert_Delete_GetRandom_O1() {
        nums = new ArrayList<Integer>();
        indices = new HashMap<Integer, Integer>();
        random = new Random();
    }
    // 插入元素，如果元素已存在则不插入。时间复杂度O(1)。
    // 思路精髓：利用HashMap记录元素值到索引的映射，直接插入ArrayList末尾，并更新HashMap。
    public boolean insert(int val) {
        if (indices.containsKey(val)) {
            return false;
        }
        // 将新元素值映射到其索引位置（末尾）, 将新元素添加到ArrayList末尾
        int index = nums.size();
        nums.add(val); // 将元素放入ArrayList中
        indices.put(val, index); // 把元素和下标一起放入哈希表
        return true;
    }
    // 删除元素，如果元素不存在则不进行删除。时间复杂度O(1)。
    // 思路精髓：利用HashMap找到要删除元素的索引，用ArrayList末尾元素替换它并移除末尾元素，更新HashMap。
    public boolean remove(int val) {
        if (!indices.containsKey(val)) {
            return false;
        }
        // 从哈希表中获得 val 的下标 index
        // 将变长数组的最后一个元素 last 移动到下标 index 处，在哈希表中将 last 的下标更新为 index
        // 在变长数组中删除最后一个元素，在哈希表中删除 val
        int index = indices.get(val); // 获取要删除元素的索引
        int last = nums.get(nums.size() - 1);  // 获取ArrayList末尾元素
        // 用末尾元素替换要删除的元素
        nums.set(index, last); // 如果直接删除某个元素，会引起后续元素的大幅度移动。所以为了实现常量时间，我们选择将要删除的元素调换到最后一位
        // 更新末尾元素在新位置的索引
        indices.put(last, index); // 要同步修改哈希表中的元素
        nums.remove(nums.size() - 1); // 移除ArrayList末尾元素
        indices.remove(val);  // 从HashMap中移除已删除的元素
        return true;
    }

    public int getRandom() {
        int randomIndex = random.nextInt(nums.size()); // 随机获取ArrayList中的一个元素
        return nums.get(randomIndex);
    }
    /**
     * 哈希表 + 删除交换
     */
    static int[] nums1 = new int[200010]; // At most 2 * 105 calls
    Random random1 = new Random();
    Map<Integer, Integer> map = new HashMap<>();
    int idx = -1; // 记录当前使用到哪一位（即下标在 [0,idx] 范围内均是存活值）

    public boolean insert1(int value) {
        if (map.containsKey(value)) {
            return false;
        }
        // 添加到 nums，更新 idx，同时更新哈希表
        nums1[++idx] = value;
        map.put(value, idx);
        return true;
    }
    // 从哈希表中将 value 删除，同时取出其所在 nums 的下标 loc，然后将 nums[idx] 赋值到 loc 位置，并更新 idx
    // （含义为将原本处于 loc 位置的元素删除），同时更新原本位于 idx 位置的数在哈希表中的值为 loc
    // （若 loc 与 idx 相等，说明删除的是最后一个元素，这一步可跳过）
    public boolean remove1(int value) {
        if (!map.containsKey(value)) {
            return false;
        }
        int loc = map.remove(value); // 删除交换
        if (loc != idx) {
            map.put(nums1[idx], loc);
        }
        nums1[loc] = nums1[idx--];
        return true;
    }

    public int getRandom1() {
        return nums1[random1.nextInt(idx + 1)]; // 由于我们人为确保了 [0,idx] 均为存活值，因此直接在 [0,idx+1) 范围内进行随机即可。
    }
}
