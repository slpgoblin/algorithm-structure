package 链表;

/**
 * @author goblin
 * @version 1.0.0
 * @className 链表._237_删除链表中的节点.java
 * @description https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 */
public class _237_删除链表中的节点 {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

}
