package 链表;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 *
 * @author goblin
 * @version 1.0.0
 * @since 2021-06-29 22:17
 */
public class _237_删除链表中的节点 {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

}
