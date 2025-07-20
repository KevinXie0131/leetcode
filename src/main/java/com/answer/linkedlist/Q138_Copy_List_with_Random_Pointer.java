package com.answer.linkedlist;

import java.util.*;

public class Q138_Copy_List_with_Random_Pointer {
    /**
     * 随机链表的复制
     * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点(null)。
     * 构造这个链表的 深拷贝(deep copy)。 深拷贝应该正好由 n 个 全新(brand new) 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。
     * 复制链表中的指针都不应指向原链表中的节点(None of the pointers in the new list should point to nodes in the original list.)
     * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
     * 返回复制链表的头节点。
     * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
     *  val：一个表示 Node.val 的整数。
     *  random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
     * 你的代码 只 接受原链表的头节点 head 作为传入参数。
     *
     * 示例 1：
     *  输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
     *  输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
     */
    /**
     * 哈希表
     * 本题中因为随机指针的存在，当我们拷贝节点时，「当前节点的随机指针指向的节点」可能还没创建
     */
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node cur = head;
        Map<Node, Node> map = new HashMap<>(); // 原节点 -> 新节点映射
        // 3. 复制各节点，并建立 “原节点 -> 新节点” 的 Map 映射
        while(cur != null) {
            map.put(cur, new Node(cur.val));    // 第一次遍历：创建新节点并建立映射
            cur = cur.next;
        }
        cur = head;
        // 4. 构建新链表的 next 和 random 指向
        while(cur != null) {
            map.get(cur).next = map.get(cur.next);     // 第二次遍历：设置next和random指针
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        // 5. 返回新链表的头节点
        return map.get(head);  // 返回新的链表的头节点
    }
    /**
     * 回溯 + 哈希表
     * 使用回溯的方式遍历整个链表，让每个节点的复制相互独立。对于当前节点 node，首先复制当前节点 node，
     * 而后对当前节点的后继节点和随机指针指向的节点进行复制，最后让复制节点的 next 指针和 random 指针指向这两个复制的节点，即可完成边的复制。
     *
     * 为了防止多次遍历同一个节点，我们需要建立一个哈希表， 来记录源节点到克隆节点之间的映射关系。在回溯搜索过程中，
     * 如果当前正在搜索的节点出现在了哈希表中，就说明我们已经遍历完了整个链表，此时就可以直接从哈希表中取出复制后的节点的指针并返回。
     */
    Map<Node, Node> cachedNode = new HashMap<Node, Node>();

    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        if (!cachedNode.containsKey(head)) {
            Node headNew = new Node(head.val);   //复制节点
            cachedNode.put(head, headNew); //建立源节点到复制节点的映射
            headNew.next = copyRandomList2(head.next);  //复制边
            headNew.random = copyRandomList2(head.random);
        }
        return cachedNode.get(head);  //node节点已经被访问过了,直接从哈希表hash中取出对应的复制节点返回。
    }
    // Definition for a Node.
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
