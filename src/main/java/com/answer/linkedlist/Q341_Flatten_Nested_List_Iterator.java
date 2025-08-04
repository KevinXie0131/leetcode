package com.answer.linkedlist;

import java.util.*;

public class Q341_Flatten_Nested_List_Iterator{
    /**
     * 扁平化嵌套列表迭代器
     * 一个嵌套的整数列表 nestedList 。每个元素要么是一个整数，要么是一个列表；该列表的元素也可能是整数或者是其他列表。请你实现一个迭代器将其扁平化，使之能够遍历这个列表中的所有整数。
     *
     * 实现扁平迭代器类 NestedIterator:
     *  NestedIterator(List<NestedInteger> nestedList) 用嵌套列表 nestedList 初始化迭代器。
     *  int next() 返回嵌套列表的下一个整数。
     *  boolean hasNext() 如果仍然存在待迭代的整数，返回 true ；否则，返回 false 。
     *
     * 你的代码将会用下述伪代码检测：
     *  initialize iterator with nestedList
     *  res = []
     *  while iterator.hasNext()
     *      append iterator.next() to the end of res
     *  return res
     * 如果 res 与预期的扁平化列表匹配，那么你的代码将会被判为正确。
     *
     * 示例 1：
     * 输入：nestedList = [[1,1],2,[1,1]]
     * 输出：[1,1,2,1,1]
     * 解释：通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
     * 示例 2：
     * 输入：nestedList = [1,[4,[6]]]
     * 输出：[1,4,6]
     * 解释：通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
     */
}
/**
 * 完全展开法（非惰性）：
 *  递归遍历所有子嵌套，将所有整数存入一个一维数组，迭代器仅维护指针。
 *  缺点：内存占用高，不适合超大输入。
 */
class NestedIterator1 implements Iterator<Integer> {
    private List<Integer> list;
    private Iterator<Integer> iter;

    public NestedIterator1(List<NestedInteger> nestedList) {
        list = new ArrayList<>();
        dfs(nestedList);
        iter = list.iterator();
    }

    @Override
    public Integer next() {
        return iter.next();
    }

    @Override
    public boolean hasNext() {
        return iter.hasNext();
    }

    private void dfs(List<NestedInteger> nestedList){
        for(NestedInteger item : nestedList){
            if(item.isInteger()){
                list.add(item.getInteger());
            } else {
                dfs(item.getList());
            }
        }
    }
}
/**
 * 深度优先搜索: 嵌套的整型列表是一个树形结构，树上的叶子节点对应一个整数，非叶节点对应一个列表。
 * 等价于遍历一棵 N 叉树的所有「叶子节点」: 叶子节点是 Integer 类型，其 val 字段非空；其他节点都是 List<NestedInteger> 类型，其 val 字段为空，但是 list 字段非空，装着孩子节点。
 */
class NestedIterator3 implements Iterator<Integer> {
    private Iterator<Integer> it;

    public NestedIterator3(List<NestedInteger> nestedList) {
        List<Integer> result = new LinkedList<>(); // 存放将 nestedList 打平的结果
        for (NestedInteger node : nestedList) {
            traverse(node, result);   // 以每个节点为根遍历
        }
        this.it = result.iterator(); // 得到 result 列表的迭代器
    }

    public Integer next() {
        return it.next();
    }

    public boolean hasNext() {
        return it.hasNext();
    }
    // 遍历以 root 为根的多叉树，将叶子节点的值加入 result 列表
    private void traverse(NestedInteger root, List<Integer> result) {
        if (root.isInteger()) {
            result.add(root.getInteger());  // 到达叶子节点
            return;
        }
        for (NestedInteger child : root.getList()) {   // 遍历框架
            traverse(child, result);
        }
    }
}
/**
 * 进阶思路: 调用 hasNext 时，如果 nestedList 的第一个元素是列表类型，则不断展开这个元素，直到第一个元素是整数类型。
 */
class NestedIterator5 implements Iterator<Integer> {
    private LinkedList<NestedInteger> list;

    public NestedIterator5(List<NestedInteger> nestedList) {
        // 不直接用 nestedList 的引用，是因为不能确定它的底层实现
        // 必须保证是 LinkedList，否则下面的 addFirst 会很低效
        list = new LinkedList<>(nestedList);
    }

    public Integer next() {
        return list.remove(0).getInteger();  // hasNext 方法保证了第一个元素一定是整数类型
    }

    public boolean hasNext() {
        while (!list.isEmpty() && !list.get(0).isInteger()) { // 循环拆分列表元素，直到列表第一个元素是整数类型
            List<NestedInteger> first = list.remove(0).getList();  // 当列表开头第一个元素是列表类型时，进入循环
            for (int i = first.size() - 1; i >= 0; i--) {  // 将第一个列表打平并按顺序添加到开头
                list.addFirst(first.get(i));
            }
        }
        return !list.isEmpty();
    }
}
/**
 * 用栈（LinkedList/Deque）维护惰性展开。核心思路为：构造时把完整嵌套的列表倒序入栈，每次访问时只保证栈顶为整数即可
 * 这种写法空间和时间都很优，适合大数据量按需展开
 *
 * 惰性展开法（推荐，按需展开）：
 *  利用栈（stack）实现，当需要下一个整数时动态展开嵌套列表至栈顶是整数。
 *  保证 next 总是直接返回下一个整数，hasNext 保证栈顶为整数
 *
 * 递归 + 栈
 */
class NestedIterator implements Iterator<Integer> {
    private Deque<NestedInteger> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new LinkedList<>();
        for (int i = nestedList.size() - 1; i >= 0; i--) {   // 入栈要倒序，保证第一个元素在栈顶
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();  // hasNext 保证栈顶为整数
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            NestedInteger top = stack.peek();
            if (top.isInteger()) {
                return true;
            }
            stack.pop();
            List<NestedInteger> list = top.getList(); // 嵌套列表也要倒序入栈
            for (int i = list.size() - 1; i >= 0; i--) {
                stack.push(list.get(i));
            }
        }
        return false;
    }
}
/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
interface NestedInteger {
    public boolean isInteger();
    public Integer getInteger();
    public List<NestedInteger> getList();
}
