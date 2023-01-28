package com.answer.stack;

public class Q622_Design_Circular_Queue {
    int front;
    int rear;
    int capacity;
    int[] elements;

    public Q622_Design_Circular_Queue(int k) {
        capacity = k + 1;
        front = rear = 0;
        elements = new int[capacity];
    }

    public boolean enQueue(int value) {
        if(isFull()){
            return false;
        }
        elements[rear] = value;
        rear = (rear + 1) % capacity;
        return true;
    }

    public boolean deQueue() {
        if(isEmpty()){
            return false;
        }
        front = (front + 1) % capacity;
        return true;
    }

    public int Front() {
        if(isEmpty()){
            return -1;
        }
        return elements[front];
    }

    public int Rear() {
        if(isEmpty()){
            return -1;
        }
        return elements[(rear - 1 + capacity) % capacity];
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public boolean isFull() {
        return ((rear + 1) % capacity) == front;
    }

}
