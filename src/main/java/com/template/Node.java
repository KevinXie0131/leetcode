package com.template;

import java.util.ArrayList;

public class Node {

    public ArrayList<Node> neighbors;
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
        this.right = null;
        this.left = null;
    }

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
