package com.learn.template.trie;

import java.util.*;

public class TrieUsingMap {
    /**
     * Trie, also called prefix tree, is a special form of a Nary tree.
     * <p>
     * A trie is used to store strings. Each Trie node represents a string (a prefix).
     * Each node might have several children nodes while the paths to different children nodes represent different characters.
     * And the strings the child nodes represent will be the origin string represented by the node itself plus the character on the path.
     * <p>
     * It is worth noting that the root node is associated with the empty string.
     * <p>
     * One important property of Trie is that all the descendants of a node have a common prefix of the string
     * associated with that node. That's why Trie is also called prefix tree.
     * Trie is widely used in various applications, such as autocomplete, spell checker, etc.
     */

    public Map<Character, TrieNode> children = new HashMap<>();

    // you might need some extra values according to different cases
}
/** Usage:
 *  Initialization: TrieNode root = new TrieNode();
 *  Return a specific child node with char c: root.children.get(c)
 */

/**
 *  Insertion in Trie
 *
 * 1. Initialize: cur = root
 * 2. for each char c in target string S:
 * 3.      if cur does not have a child c:
 * 4.          cur.children[c] = new Trie node
 * 5.      cur = cur.children[c]
 * 6. cur is the node which represents the string S
 */

/**
 * Search in Trie
 *
 * 1. Initialize: cur = root
 * 2. for each char c in target string S:
 * 3.   if cur does not have a child c:
 * 4.     search fails
 * 5.   cur = cur.children[c]
 * 6. search successes
 */