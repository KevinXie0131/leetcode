package com.answer.trie;

public class _TrieTemplate {

}

class MyTrie {
    private Node root;

    public MyTrie() {
        root = new Node();
    }

    public void insert(String word) {
        Node cur = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (cur.children[index] == null) {
                cur.children[index] = new Node();
            }
            cur = cur.children[index];
        }
        cur.isEnd = true;
    }

    public boolean search(String word) {
        Node node = find(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        Node node = find(prefix);
        return node != null;
    }

    private Node find(String word) {
        Node cur = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (cur.children[index] == null) {
                return null;
            }
            cur = cur.children[index];
        }
        return cur;
    }

    class Node {
        Node[] children = new Node[26];
        boolean isEnd;

    }
}


