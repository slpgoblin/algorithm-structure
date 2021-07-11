package com.goblin.tree;

import com.goblin.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author goblin
 * @version 1.0.0
 * @className com.goblin.BinaryTree
 * @description 二叉树
 * @createTime 2021-07-11 18:37
 */
@SuppressWarnings("ALL")
public class BinaryTree<E> implements BinaryTreeInfo {

    protected int size;
    protected Node<E> root;

    /**
     * 获取size
     *
     * @return
     */
    int size() {
        return size;
    }

    /**
     * 是否为空
     *
     * @return
     */
    boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空元素
     */
    void clear() {
        root = null;
        size = 0;
    }

    /**
     * 层序遍历
     */
    public void levelOrder() {
        if (root == null) return;
        Queue<Node<E>> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()) {
            Node<E> node = nodeQueue.poll();
            System.out.println(node.element);
            if (node.left != null) {
                nodeQueue.offer(node.left);
            }
            if (node.right != null) {
                nodeQueue.offer(node.right);
            }
        }
    }

    public void levelOrder(Visitor visitor) {
        if (root == null) return;

        Queue<Node<E>> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);

        while (!nodeQueue.isEmpty()) {
            Node<E> node = nodeQueue.poll();
            if (visitor.visitor(node.element)) return;

            if (node.left != null) {
                nodeQueue.offer(node.left);
            }

            if (node.right != null) {
                nodeQueue.offer(node.right);
            }
        }
    }

    /**
     * 后序遍历
     */
    public void postorder(Visitor visitor) {
        postorder(root, visitor);
    }

    private void postorder(Node<E> node, Visitor visitor) {
        if (node == null || visitor == null) return;
        postorder(node.left, visitor);
        postorder(node.right, visitor);
        boolean stop = visitor.visitor(node.element);
    }

    /**
     * 中序遍历
     */
    public void inorder(Visitor visitor) {
        inorder(root, visitor);
    }

    private void inorder(Node<E> node, Visitor visitor) {
        if (node == null) return;
        inorder(node.left, visitor);
        visitor.visitor(node.element);
        inorder(node.right, visitor);
    }

    /**
     * 前序遍历
     */
    public void preorder(Visitor visitor) {
        preorder(root, visitor);
    }

    private void preorder(Node<E> node, Visitor visitor) {
        if (node == null) return;
        visitor.visitor(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    /**
     * 获取后继节点
     *
     * @param node 当前节点
     * @return 后继节点
     */
    protected Node<E> successor(Node<E> node) {
        if (node == null) return null;

        // 后继节点在右子树当中（right.left.left.left....）
        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        // 从父节点、祖父节点中寻找后继节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }

    /**
     * 获取前驱节点
     *
     * @param node
     * @return
     */
    protected Node<E> predesessor(Node<E> node) {
        if (node == null) return null;

        // 前驱结点在左子树当中（left.right.right.right...）
        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        // 从父节点中中寻找前驱节点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;

        }

        // node.parent == null
        // node == node.parent.right
        return node.parent;
    }

    /**
     * 递归计算高度
     *
     * @return
     */
    public int height2() {
        return height(root);
    }

    private int height(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * 层序遍历计算高度
     *
     * @return
     */
    public int height() {
        if (root == null) return 0;
        // 树的高度
        int height = 0;
        // 存储在每一层的元素数量
        int levelSize = 1;
        Queue<Node<E>> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);

        while (!nodeQueue.isEmpty()) {
            Node<E> node = nodeQueue.poll();
            levelSize--;
            if (node.left != null) {
                nodeQueue.offer(node.left);
            }

            if (node.right != null) {
                nodeQueue.offer(node.right);
            }
            // 如果等于零意味着即将访问下一层
            if (levelSize == 0) {
                levelSize = nodeQueue.size();
                height++;
            }
        }
        return height;
    }

    /**
     * 判断是否完全二叉树
     *
     * @return
     */
    public boolean isComplete() {
        if (root == null) {
            return false;
        }
        Queue<Node<E>> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        boolean leaf = false;
        while (!nodeQueue.isEmpty()) {
            Node<E> node = nodeQueue.poll();
            if (leaf && !node.isLeaf()) {
                return false;
            }
            if (node.left != null) {
                nodeQueue.offer(node);
            } else {
                if (node.right != null) {
                    return false;
                }
            }
            if (node.right != null) {
                nodeQueue.offer(node);
            } else {
                leaf = true;
            }
        }
        return true;
    }


    protected static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }

    public static interface Visitor<E> {
        /**
         * @param element
         * @return true 停止遍历
         */
        boolean visitor(E element);
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>) node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }
        return myNode.element + "_P(" + parentString + ")";
    }
}
