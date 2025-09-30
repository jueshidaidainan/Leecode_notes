// 两个链表存储的数字是逆序的，所以从链表头开始遍历即可。
// 先遍历两个链表非空的部分，各自的值加上进位，结果整除10，就是进位。结果%10就是当前位要存的值。
// 因为进位要一直用，所以声明在循环外，一个链表为空后，单独处理另一个非空链表。
// 注意最后当两个链表都为空的时候，还要单独处理最后的进位。

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