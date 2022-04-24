package com.goblin.tree;

import java.util.Comparator;

/**
 * balance binary search tree
 *
 * @author goblin
 * @version 1.0.0
 * @since 2022-04-24 21:40
 */
public class BBST<E> extends BST<E> {

    public BBST() {
        this(null);
    }

    public BBST(Comparator<E> comparator) {
        super(comparator);
    }


    /**
     * 左旋转
     * <p>
     * 用grand和parent是为了和概念中的图片对应
     * </p>
     *
     * @param grand 旋转的节点
     */
    protected void rotateLeft(Node<E> grand) {
        // 节点旋转
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    /**
     * 右旋转
     *
     * <p>
     * 没有对应图片，只是按照逻辑左右子树命名
     * </p>
     *
     * @param node 旋转的节点
     */
    protected void rotateRight(Node<E> node) {
        Node<E> leftNode = node.left;
        Node<E> childRightNode = leftNode.right;
        leftNode.right = node;
        node.left = childRightNode;
        afterRotate(node, leftNode, childRightNode);
    }

    /**
     * 旋转之后的操作
     *
     * @param grand  祖父节点
     * @param parent 父节点
     * @param child  当前节点
     */
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        // 让parent称为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else { // grand是root节点
            root = parent;
        }

        // 更新child的parent
        if (child != null) {
            child.parent = grand;
        }

        // 更新grand的parent
        grand.parent = parent;
    }


    /**
     * 旋转统一处理
     * <p>
     * todo 补充图片
     *
     * @param r 对应图片 r
     * @param a 对应图片 a
     * @param b 对应图片 b
     * @param c 对应图片 c
     * @param d 对应图片 d
     * @param e 对应图片 e
     * @param f 对应图片 f
     * @param g 对应图片 g
     */
    protected void rotate(Node<E> r,
                          Node<E> a, Node<E> b, Node<E> c,
                          Node<E> d,
                          Node<E> e, Node<E> f, Node<E> g) {
        // d
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }

        // a b c
        b.left = a;
        if (a != null) {
            a.parent = b;
        }
        b.right = c;
        if (c != null) {
            c.parent = b;
        }

        // d e f
        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        f.right = g;
        if (g != null) {
            g.parent = f;
        }

        // b d f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
    }

}
