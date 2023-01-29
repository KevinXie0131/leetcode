package com.answer.hashmap;

import java.util.*;

public class Q705_Design_HashSet {

    private static final int BASE = 769;
    private LinkedList[] data;

    public Q705_Design_HashSet() {
        data = new LinkedList[BASE];
        for(int i = 0; i < BASE; i++){
            data[i] = new LinkedList<Integer>();
        }
    }

    public void add(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while(iterator.hasNext()){
            Integer element = iterator.next();
            if(element == key){
                return;
            }
        }
        data[h].offerLast(key);
    }

    public void remove(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while(iterator.hasNext()){
            Integer element = iterator.next();
            if(element == key){
                data[h].remove(element);
                return;
            }
        }
    }

    public boolean contains(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while(iterator.hasNext()){
            Integer element = iterator.next();
            if(element == key){
                return true;
            }
        }
        return false;
    }

    private static int hash(int key) {
        return key % BASE;
    }
}
