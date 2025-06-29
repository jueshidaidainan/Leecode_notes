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

 // 寻找单链表中间节点的快慢指针的写法，利用二者之间的速度点差恒定的原理
 // 快指针每次移动两个单位，慢指针移动一个单位，当快指针到头的时候，慢指针的位置就是中点
 // 对于奇数长度没有问题，但是偶数长度，中点有两个，那是哪一个，就取决于while循环的判断条件了
 // 要是fast != null && fast.next.next != null 就会停在左边
 // 可以理解嘛，两个next会更早地停止
class Solution {
    public ListNode middleNode(ListNode head) { 
        ListNode fast = head, slow = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}