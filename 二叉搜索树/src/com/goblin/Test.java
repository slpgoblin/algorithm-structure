package com.goblin;

import com.goblin.file.Files;
import com.goblin.printer.BinaryTrees;

import java.util.Collections;

/**
 * @author goblin
 * @version 1.0.0
 * @className com.goblin.Test
 * @description 测试
 * @createTime 2021-02-20 10:50
 */
public class Test {

    public static void main(String[] args) {
//        Integer[] data = new Integer[]{7, 4, 9, 2, 5, 8, 11, 3};
//
//        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
//
//        for (int i = 0; i < data.length; i++) {
//            bst.add(data[i]);
//        }
//        BinaryTrees.println(bst);
////        bst.preorderTraversal();
////        test1();
////        bst.levelOrderTraversal(element -> {
////            System.out.println("_"+element+"_ ");
////        });
//        System.out.println(bst.height());
//        System.out.println(bst.isComplete());
        test7();
    }

    static void test1(){
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        for (int i = 0; i < 10; i++) {
            bst.add((int) (Math.random()*100));
        }
//        BinaryTrees.println(bst);
//        String s = BinaryTrees.printString(bst);
//        Files.writeToFile("F:/1.txt",s,true);
        BinaryTrees.println(bst);
        System.out.println(bst.isComplete());
    }

    static void test7() {
        Integer[] data = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (Integer datum : data) {
            bst.add(datum);
        }

        BinaryTrees.println(bst);

        bst.remove(9);

        BinaryTrees.println(bst);
    }
    
}
