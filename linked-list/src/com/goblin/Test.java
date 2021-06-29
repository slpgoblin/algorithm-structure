package com.goblin;

/**
 * @author goblin
 * @version 1.0.0
 * @className com.goblin.Test.java
 * @description
 * @createTime 2021年01月03日 09:17
 */
public class Test {

    public static void main(String[] args) {

        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);
        list.add(0,4);
        System.out.println(list);
        list.add(list.size(),5);
        System.out.println(list);
        list.remove(list.size()-1);
        System.out.println(list);
    }

}
