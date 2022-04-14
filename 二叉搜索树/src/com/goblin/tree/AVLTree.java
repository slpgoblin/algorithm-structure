package com.goblin.tree;

import java.util.Comparator;

/**
 * 平衡二叉树
 *
 * @author goblin
 * @version 1.0.0
 * @since 2021-07-26 23:39
 */
public class AVLTree<E> extends BST<E> {

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    public AVLTree() {
    }

    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            // 判断是否平衡
            if (isBalanced(node)) {
                //更新高度
                updateHeight(node);
            } else {
                //恢复平衡
                reBalance(node);
                // 第一个不平衡的节点恢复之后整棵树都会恢复平衡
                break;
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            // 判断是否平衡
            if (isBalanced(node)) {
                //更新高度
                updateHeight(node);
            } else {
                //恢复平衡
                reBalance(node);
            }
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    /**
     * 恢复平衡
     *
     * @param grand 高度最低的不平衡节点
     */
    private void reBalance2(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();

        if (parent.isLeftChild()) {
            //L
            if (node.isLeftChild()) {
                //LL
                rotateRight(grand);
            } else {
                //LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {//R
            if (node.isLeftChild()) {
                //RL
                rotateRight(parent);
                rotateLeft(grand);
            } else {
                //RR
                rotateLeft(grand);
            }
        }
    }

    private void reBalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();

        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
            } else {
                rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
            }
        } else {
            if (node.isLeftChild()) {
                rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
            } else {
                rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
            }
        }
    }

    private void rotate(Node<E> r,
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
        updateHeight(b);

        // d e f
        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        f.right = g;
        if (g != null) {
            g.parent = f;
        }
        updateHeight(f);

        // b d f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
        updateHeight(d);
    }

    /**
     * 左旋转
     * <p>
     * 用grand和parent是为了和概念中的图片对应
     * </p>
     *
     * @param grand 旋转的节点
     */
    private void rotateLeft(Node<E> grand) {
        // 节点旋转
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;

        // 更新父节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            root = parent;
        }

        // 更新child parent
        if (child != null) {
            child.parent = grand;
        }

        // 更新grand的 parent
        grand.parent = parent;

        //更新高度
        updateHeight(grand);
        updateHeight(parent);
    }

    /**
     * 右旋转
     *
     * @param node 旋转的节点
     */
    private void rotateRight(Node<E> node) {
        Node<E> leftNode = node.left;
        Node<E> childRightNode = leftNode.right;
        leftNode.right = node;
        node.left = childRightNode;

        leftNode.parent = node.parent;

        if (node.isLeftChild()) {
            node.parent.left = leftNode;
        } else if (node.isRightChild()) {
            node.parent.right = leftNode;
        } else {
            root = leftNode;
        }
        node.parent = leftNode;

        updateHeight(node);
        updateHeight(leftNode);
    }

    /**
     * 计算平衡因子
     *
     * @param node 节点
     * @return 是否平衡
     */
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    /**
     * 更新高度
     *
     * @param node 节点
     */
    private void updateHeight(Node<E> node) {
        AVLNode<E> avlNode = (AVLNode<E>) node;
        avlNode.updateHeight();
    }


    /**
     * 平衡二叉树节点
     *
     * @param <E>
     */
    private static class AVLNode<E> extends Node<E> {

        //叶子节点默认高度为1
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * 平衡因子
         *
         * @return 平衡因子
         */
        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        /**
         * 更新方高度
         */
        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        /**
         * 获取更高的子节点
         *
         * @return 更高的子节点
         */
        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) {
                return left;
            }
            if (leftHeight < rightHeight) {
                return right;
            }
            return isLeftChild() ? left : right;
        }

        @Override
        public String toString() {

            String parentString = "null";
            if (parent != null) {
                parentString = parent.element.toString();
            }
            return element + "_P(" + parentString + ")_h(" + height + ")";
        }
    }
}
 