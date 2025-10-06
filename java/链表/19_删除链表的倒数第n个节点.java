package 链表;

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);//创建虚拟头结点，fast指针先移动n个节点（注意判空条件要是fast.next != null），然后slow指针再移动，这样slow指针指向的节点就是倒数第n-1个节点。可以直接删除第n个节点。
        ListNode slow = dummy;                  //自己动手模拟一下就会很清楚。
        ListNode fast = dummy;

        while(n-- != 0){
            fast = fast.next;
        }

        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }
}