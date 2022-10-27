package com.leetcode;

import java.util.*;

public class Test5_sort {

    public static void main(String[] args) {

        Integer[] unsortedArray = new Integer[]{4,6,1,2,8,9,0,5,3};

        String[] unsortedArray1 = {"a", "b", "t", "e", "n", "f", "d", "w"};

        Arrays.sort(unsortedArray);
        for(int item : unsortedArray){
            System.out.println(item);
        }

        Arrays.sort(unsortedArray, ( a,  b) ->  b-a);
        for(int item : unsortedArray){
            System.out.println(item);
        }

        Arrays.sort(unsortedArray1);
        for(String item : unsortedArray1){
            System.out.println(item);
        }
        Arrays.sort(unsortedArray1, (a, b) -> b.compareTo(a));
        for(String item : unsortedArray1){
            System.out.println(item);
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(1);
        list.add(5);
        list.add(0);

        Collections.sort(list);
        System.out.println(list);
        Collections.sort(list, Collections.reverseOrder());
        System.out.println(list);

        ArrayList<String> list1 = new ArrayList<>();
        list1.add("g");
        list1.add("r");
        list1.add("e");
        list1.add("b");

        Collections.sort(list1);
        System.out.println(list1);
        Collections.sort(list1, Collections.reverseOrder());
        System.out.println(list1);


        Integer[] arrayToList = new Integer[] {3,2,1};
        List<Integer> toList = Arrays.asList(arrayToList);
        for (Integer i : toList) {
            System.out.println(i);
        }
        Integer[] listToArray = (Integer [])toList.toArray();
        System.out.println(listToArray.length);
        System.out.println(listToArray[1]);

        String[] arrayToList1 = new String[] {"rr","xx","yy"};
        List<String> toList1 = Arrays.asList(arrayToList1);
        for (String i : toList1) {
            System.out.println(i);
        }
        String[] listToArray1 = (String [])toList1.toArray();
        System.out.println(listToArray1.length);
        System.out.println(listToArray1[1]);




        ArrayList<Integer> unswapList = new ArrayList<>();
        unswapList.add(3);
        unswapList.add(9);
        unswapList.add(2);
        System.out.println(unswapList);
        Collections.swap(unswapList,1, 2);
        System.out.println(unswapList);

        ArrayList<String> unswapList1 = new ArrayList<>();
        unswapList1.add("t");
        unswapList1.add("s");
        unswapList1.add("n");
        unswapList1.add("c");
        System.out.println(unswapList1);
        Collections.swap(unswapList1,1, 2);
        System.out.println(unswapList1);

        Integer[] unswap = new Integer[]{4, 3, 2, 1};
        List<Integer> unswapList2 = Arrays.asList(unswap);
        Collections.swap( unswapList2,1, 2);
        unswap = (Integer[])unswapList2.toArray();
        System.out.println(unswap[0]);
        System.out.println(unswap[1]);
        System.out.println(unswap[2]);
        System.out.println(unswap[3]);

        Set<Integer> set = new HashSet<>();
        System.out.println("111: " + set.add(111));
        System.out.println("222: " + set.add(222));
        System.out.println("111: " + set.add(111));
        System.out.println("111: " + set.add(111));
        System.out.println("333: " + set.add(333));
        System.out.println("333: " + set.add(333));

        Map<Integer, String> map = new HashMap<>();
        String s = map.put(1,"a");
        System.out.println("s: " +  s);
        s = map.put(1,"a");
        System.out.println("s: " +  s);
        s = map.put(2,"b");
        System.out.println("s: " +  s);
        s = map.put(2,"b");
        System.out.println("s: " +  s);
        s = map.put(1,"aa");
        System.out.println("s: " +  s);
        System.out.println("s: " +  map.get(1));
        s = map.put(1,"aa");
        System.out.println("s: " +  s);
    }
}
