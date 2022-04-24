package com.goblin.tree;

import java.util.Comparator;

/**
 * 红黑树
 * <p>
 * red black tree
 * </P>
 *
 * @author goblin
 * @version 1.0.0
 * @since 2022-04-21 23:16
 */
public class RBTree<E> extends BBST<E> {

    private static final boolean RED = false;

    private static final boolean BLACK = true;


    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }


    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) {
            return node;
        }
        ((RBNode<E>) node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }


    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;

        // 添加根节点
        if (parent == null) {
            black(node);
            return;
        }

        // 父节点是黑色的情况
        if (isBlack(parent)) {
            return;
        }

        // 叔父节点
        Node<E> uncle = parent.sibling();
        // 祖父节点
        Node<E> grand = parent.parent;
        // 叔父节点是红色   上溢
        if (isRed(uncle)) {
            black(parent);
            black(uncle);

            // 把祖父节点当做是新添加的节点
            afterAdd(red(grand));
            return;
        }

        // 叔父节点不是红色
        if (parent.isLeftChild()) {
            red(grand);
            if (node.isLeftChild()) {
                // LL
                black(parent);
            } else {
                // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else {
            red(grand);
            if (node.isLeftChild()) {
                // RL
                black(node);
                rotateRight(parent);
            } else {
                // RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        super.afterRemove(node);
    }

    private static class RBNode<E> extends Node<E> {

        // 默认为红色，能更快满足红黑树性质
        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }
    }


}
