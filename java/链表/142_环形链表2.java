/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
//这是官方快慢指针的解法，与环形链表一不同的是：这道题要求返回环形链表的入口节点，而不是判断链表是否有环。
//官方推导出了一个结论：当快慢指针相遇之后，新开一个指针从头开始走和慢指针再次相遇的时候，相遇点就是环形链表的入口节点。
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null) {//fast为slow趟过水了，所以只需要筛选fast是否为null即可
            slow = slow.next;
            if (fast.next == null) return null;
            fast = fast.next.next;
            if (fast == slow) {
                ListNode tag = head;
                while (tag != slow) {
                    slow = slow.next;
                    tag = tag.next;
                }
                return tag;
            }
        }
        return null;
    }
}