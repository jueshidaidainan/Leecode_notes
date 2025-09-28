//将k个升序链表合并成一个升序链表并返回。
//很容易我们可以考虑使用k个指针来指向k个链表，每次找最小的，然后加入结果链表，然后指针后移，重复这个过程，直到所有链表都遍历完。
//但是这个思路不好实现，我们就想到了最小堆，先将k个链表的头结点加入链表，如果next不为空就将next加入堆中（因为next可能是下一个最小的），然后一直遍历知道堆为空。
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for(ListNode head : lists){
            if(head != null){
                pq.offer(head);
            }
        }

        ListNode dummy = new ListNode();//构造虚拟头结点方便访问
        ListNode cur = dummy;

        while(!pq.isEmpty()){//循环结束条件是pq为空
            ListNode node = pq.poll();
            if(node.next != null){//加入下一个节点
                pq.offer(node.next);
            }
            cur.next = node;
            cur = cur.next;
        }
        return dummy.next;
    }
}