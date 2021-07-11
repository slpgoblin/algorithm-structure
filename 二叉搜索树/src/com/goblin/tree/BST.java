package com.goblin.tree;

import java.util.Comparator;

/**
 * @author goblin
 * @version 1.0.0
 * @className com.goblin.BinaryTree
 * @description 二叉搜索树
 * @createTime 2021-02-20 10:50
 */
@SuppressWarnings("ALL")
public class BST<E> extends BinaryTree<E> {

    private Comparator<E> comparator;

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BST() {
        this(null);
    }

    /**
     * 添加元素
     *
     * @param element
     */
    public void add(E element) {
        elementNotNullCheck(element);
        // 添加根节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }
        // 添加的不是根节点
        // 找到父节点
        Node<E> parent = root;
        Node<E> node = root;
        int compare = 0;
        while (node != null) {
            compare = compare(element, node.element);
            parent = node;
            if (compare > 0) {
                node = node.right;
            } else if (compare < 0) {
                node = node.left;
            } else {
                // 相同替换为最新值，防止自定义对象属性不相等
                node.element = element;
                return;
            }
        }
        if (compare > 0) {
            parent.right = new Node<>(element, parent);
        } else {
            parent.left = new Node<>(element, parent);
        }
        size++;
    }


    /**
     * 删除元素
     *
     * @param element
     */
    public void remove(E element) {
        remove(node(element));
    }


    /**
     * 删除节点
     *
     * @param node
     */
    private void remove(Node<E> node) {
        if (node == null) return;

        size--;

        // 度为2的节点
        if (node.hasTwoChildren()) {
            // 找到后继节点
            Node<E> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.element = s.element;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        Node<E> replacement = node.left != null ? node.left : node.right;

        // node是度为1的节点
        if (replacement != null) {
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) {
                // node是度为1的节点并且是根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                // node == node.parent.right
                node.parent.right = replacement;
            }
        } else if (node.parent == null) {
            // node是叶子节点并且是根节点
            root = null;
        } else {
            // node是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                // node == node.parent.right
                node.parent.right = null;
            }
        }
    }

    /**
     * 根据元素查找节点
     *
     * @param element
     * @return
     */
    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) return node;
            if (cmp > 0) {
                node = node.right;
            } else { // cmp < 0
                node = node.left;
            }
        }
        return null;
    }


    /**
     * 比较元素大小
     *
     * @param e1
     * @param e2
     * @return 0 代表e1和e2相等。大于0代表e1大于e2，小于0代表e1小于e2
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    /**
     * 是否包含元素
     *
     * @param element
     * @return
     */
    boolean contains(E element) {
        return (node(element)) != null;
    }

    /**
     * 空节点检测
     *
     * @param element
     */
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null ");
        }
    }


}
