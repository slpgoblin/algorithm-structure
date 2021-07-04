package com.goblin;

import com.goblin.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author goblin
 * @version 1.0.0
 * @className com.goblin.BinaryTree
 * @description 二叉搜索树
 * @createTime 2021-02-20 10:50
 */
@SuppressWarnings("ALL")
public class BinarySearchTree<E> implements BinaryTreeInfo {

    private int size;
    private Node<E> root;
    private Comparator<E> comparator;

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }
    public BinarySearchTree() {
        this(null);
    }

    /**
     * 获取size
     * @return
     */
    int size(){
        return size;
    }

    /**
     * 是否为空
     * @return
     */
    boolean isEmpty(){
        return size == 0;
    }

    /**
     * 清空元素
     */
    void clear(){

    }

    /**
     * 添加元素
     * @param element
     */
    void add(E element){
        elementNotNullCheck(element);
        // 添加根节点
        if (root == null){
            root = new Node<>(element,null);
            size++;
            return;
        }
        // 添加的不是根节点
        // 找到父节点
        Node<E> parent = root;
        Node<E> node  = root;
        int compare = 0;
        while (node != null){
            compare = compare(element, node.element);
            parent = node;
            if (compare > 0){
                node = node.right;
            } else if (compare < 0){
                node = node.left;
            } else {
                // 相同替换为最新值，防止自定义对象属性不相等
                node.element = element;
                return;
            }
        }
        if (compare > 0){
            parent.right = new Node<>(element,parent);
        } else {
            parent.left = new Node<>(element,parent);
        }
        size++;
    }


    /**
     * 层序遍历
     */
    public void levelOrderTraversal() {
        if (root == null) return;
        Queue<Node<E>> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()){
            Node<E> node = nodeQueue.poll();
            System.out.println(node.element);
            if (node.left != null){
                nodeQueue.offer(node.left);
            }
            if (node.right != null){
                nodeQueue.offer(node.right);
            }
        }
    }

    public void levelOrderTraversal(Visitor visitor) {
        if (root == null) return;

        Queue<Node<E>> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);

        while (!nodeQueue.isEmpty()){
            Node<E> node = nodeQueue.poll();
            if (visitor.visitor(node.element)) return;

            if (node.left != null){
                nodeQueue.offer(node.left);
            }

            if (node.right != null){
                nodeQueue.offer(node.right);
            }
        }
    }

    /**
     * 后序遍历
     */
    public void postorderTraversal(Visitor visitor) {
        postorderTraversal(root, visitor);
    }

    private void postorderTraversal(Node<E> node, Visitor visitor) {
        if (node == null || visitor == null) return;
        postorderTraversal(node.left, visitor);
        postorderTraversal(node.right, visitor);
        boolean stop = visitor.visitor(node.element);
    }

    /**
     * 中序遍历
     */
    public void inorderTraversal(Visitor visitor) {
        inorderTraversal(root,visitor);
    }

    private void inorderTraversal(Node<E> node,Visitor visitor) {
        if (node == null) return;
        inorderTraversal(node.left,visitor);
        visitor.visitor(node.element);
        inorderTraversal(node.right,visitor);
    }

    /**
     * 前序遍历
     */
    public void preorderTraversal(Visitor visitor) {
        preorderTraversal(root, visitor);
    }

    private void preorderTraversal(Node<E> node, Visitor visitor) {
        if (node == null) return;
        visitor.visitor(node.element);
        preorderTraversal(node.left, visitor);
        preorderTraversal(node.right, visitor);
    }

    /**
     * 判断是否完全二叉树
     * @return
     */
    public boolean isComplete(){
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

    /**
     * 层序遍历计算高度
     * @return
     */
    public int height(){
        if (root == null) return 0;
        // 树的高度
        int height = 0;
        // 存储在每一层的元素数量
        int levelSize = 1;
        Queue<Node<E>> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);

        while (!nodeQueue.isEmpty()){
            Node<E> node = nodeQueue.poll();
            levelSize--;
            if (node.left != null){
                nodeQueue.offer(node.left);
            }

            if (node.right != null){
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

    public void remove(E element) {
        remove(node(element));
    }

//    public boolean contains(E element) {
//        return node(element) != null;
//    }

    /**
     * 获取后继节点
     * @param node 当前节点
     * @return 后继节点
     */
    private Node<E> successor(Node<E> node) {
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

    private void remove(Node<E> node) {
        if (node == null) return;

        size--;

        if (node.hasTwoChildren()) { // 度为2的节点
            // 找到后继节点
            Node<E> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.element = s.element;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        Node<E> replacement = node.left != null ? node.left : node.right;

        if (replacement != null) { // node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) { // node是度为1的节点并且是根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else { // node == node.parent.right
                node.parent.right = replacement;
            }
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            root = null;
        } else { // node是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else { // node == node.parent.right
                node.parent.right = null;
            }
        }
    }

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
     * 递归计算高度
     * @return
     */
    public int height2(){
        return height(root);
    }

    private int height(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left),height(node.right));
    }

    /**
     * 比较元素大小
     * @param e1
     * @param e2
     * @return 0 代表e1和e2相等。大于0代表e1大于e2，小于0代表e1小于e2
     */
    private int compare(E e1, E e2){
        if (comparator != null){
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>)e1).compareTo(e2);
    }

    /**
     * 是否包含元素
     * @param element
     * @return
     */
    boolean contains(E element){
        return false;
    }

    /**
     * 空节点检测
     * @param element
     */
    private void elementNotNullCheck(E element) {
        if (element == null){
            throw new IllegalArgumentException("element must not be null ");
        }
    }

    /**
     * 获取前驱节点
     * @param node
     * @return
     */
    private Node<E> predesessor(Node<E> node) {
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

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>)node;
        String parentString = "null";
        if(myNode.parent != null){
            parentString = myNode.parent.element.toString();
        }
        return myNode.element + "_P(" + parentString + ")";
    }


    public static interface Visitor<E> {
        /**
         *
         * @param element
         * @return true 停止遍历
         */
        boolean visitor(E element);
    }

    private static class Node<E> {
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

    public static class Person {
        private String name;
        private Integer age;
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.age = 1;
        person.name = "zhang";

        Person person2 = new Person();
        person2.age = 2;
        person2.name = "zh22ang";

        person = person2;
        System.out.println();

    }

}
