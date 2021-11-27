package 链表;

/**
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 *
 * @author goblin
 * @version 1.0.0
 * @since 2021-03-22 22:17
 */
public class _21_合并两个有序链表 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l2.next, l1);
            return l2;
        }
    }

}
