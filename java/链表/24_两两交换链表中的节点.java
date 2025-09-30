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
// 两两交换，还得让交换后的节点可以连上后续节点，这样头节点就会比较特殊，故引入虚拟头节点
// 因为每次交换的指针要和已经交换后的指针相连，所以需要一个prev指针指向前一个节点，cur指针指向现在遍历的节点。每次交换的是cur和cur.next，有一个为空，则退出循环。
// 交换节点的时候，刚开始注释那种写法，一堆next，但是next修改之后，两次next就会改变所指位置，故使用临时变量first和second来进行存储，方便赋值也方便理解

class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1, head);
        // ListNode tmp = null;
        ListNode cur = head;
        ListNode prev = dummy;//使用虚拟头节点
        while(cur != null && cur.next != null){
            ListNode first = cur;
            ListNode second = cur.next;

            //交换节点
            prev.next = second;
            first.next = second.next;
            second.next = first;

            //更新指针
            prev = first;
            cur = first.next;
            
            // prev.next = cur.next;
            // tmp = cur.next.next;
            // cur.next.next = cur;
            // cur.next = tmp;

            // prev = cur.next;
            // cur = tmp;           
        }
        return dummy.next;
    }
}