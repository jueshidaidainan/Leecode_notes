/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode();
        ListNode p = res;
        int r = 0;//进位
        while(l1 != null && l2 != null){
            int sum = l1.val + l2.val + r;
            r = sum > 9 ? 1 : 0;
            p.next = new ListNode(sum % 10);

            p = p.next;
            l1  = l1.next;
            l2 = l2.next;
        }
        //处理较长的那个链表
        while(l1 != null){
            p.next = new ListNode((l1.val + r) % 10);
            r = (l1.val + r) > 9 ? 1 : 0;
            
            p = p.next;
            l1  = l1.next;
        }

        while(l2 != null){
            p.next = new ListNode((l2.val + r) % 10);
            r = (l2.val + r) > 9 ? 1 : 0;
            
            p = p.next;
            l2  = l2.next;
        }

        //有可能最后的结果因为进位导致产生的位数多于原来最长的链表
        if(r != 0){
            p.next = new ListNode(1);
        }

        return res.next;
    }
}