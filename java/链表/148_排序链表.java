package 链表;

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
//使用分治的解法。
// 将这个题拆成  = 返回链表的中间节点 + 合并两个有序链表 + 递归  来实现分治的思想。
// 注意返回链表的中间节点时：使用pre指针保留slow的前一个节点，最终用来将两个链表分开。 以及在合并链表时善用虚拟头结点来简化逻辑。
class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){//空节点或只有一个节点的情况不需要排序,递归出口
            return head;
        }
        ListNode head2 = middle(head);//分割链表

        //分治
        head = sortList(head);
        head2 = sortList(head2);

        //合并
        return merge(head, head2);
    }

    private ListNode middle(ListNode head){//使用快慢指针来找链表的中间节点
        ListNode pre = head;
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;//将链表的前半部分和后半部分分开，避免干扰
        return slow;
    }

    private ListNode merge(ListNode list1, ListNode list2){
        ListNode dummy = new ListNode(0);//虚拟头结点简化逻辑
        ListNode cur = dummy;
        while(list1 != null && list2 != null){
            if(list1.val < list2.val){
                cur.next = list1;
                list1 = list1.next;
            }else{
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 == null ? list2 : list1;//拼接剩余的链表
        return dummy.next;
    }
}



 //使用ArrayList保存节点并使用list接口的lambda表达式进行排序的写法。
class Solution {
    public ListNode sortList(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        for (ListNode cur = head; cur != null; cur = cur.next) {
            list.add(cur);
        }

        list.sort((a, b) -> Integer.compare(a.val, b.val)); // 使用 lambda


        ListNode dummyhead = new ListNode(0, head);
        ListNode pre = dummyhead;
        for (int i = 0; i < list.size(); i++) {
            pre.next = list.get(i);
            pre = pre.next;
        }
        pre.next = null;

        return dummyhead.next;
    }
}
