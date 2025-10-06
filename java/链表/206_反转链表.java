class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;//指向前一个节点的指针
        ListNode cur = head;//指向当前的节点

        while(cur != null){
            ListNode nxt = cur.next;//先保存后一个节点
            cur.next = pre;//当前节点指针指向前面
            pre = cur;//维护pre和cur
            cur = nxt;
        }
        return pre;//返回最后一个节点为头节点
    }
}