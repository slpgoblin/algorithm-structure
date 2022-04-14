package com.goblin;

import com.goblin.bst.BinarySearchTree;
import com.goblin.printer.BinaryTrees;
import com.goblin.tree.AVLTree;

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
        test8();
    }

    static void test1() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        for (int i = 0; i < 10; i++) {
            bst.add((int) (Math.random() * 100));
        }
//        BinaryTrees.println(bst);
//        String s = BinaryTrees.printString(bst);
//        Files.writeToFile("F:/1.txt",s,true);
        BinaryTrees.println(bst);
        System.out.println(bst.isComplete());
    }

    static void test7() {
        Integer[] data = new Integer[]{
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

    static void test8() {
        Integer[] data = new Integer[]{
                76, 94, 25, 1, 67, 27, 44, 18, 77, 23, 97, 13, 53, 9, 32, 11, 55, 19, 24
        };

        AVLTree<Integer> avlTree = new AVLTree<>();
        for (Integer datum : data) {
            avlTree.add(datum);
        }

        BinaryTrees.println(avlTree);

    }

}
