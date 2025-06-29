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
// 我是利用了一个HashMap的额外空间来标记链表是否被遍历过，来判断是否有环的存在
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null) return false;
        HashMap<Object, Boolean> tag = new HashMap<>();
        ListNode cur = head;
        while(cur != null){
            if(tag.containsKey(cur) && tag.get(cur) == true) return true;
            tag.put(cur, true);
            cur = cur.next;
        }
        return false;

    }
}

// 利用快慢指针的方法： 如果有环的存在，在环内快慢指针终会相遇。如果二者速度差为1的话。 就是注意fast和slow如果刚开始都指向head，在if语句里容易直接判断为true
// 所以，让快慢指针位置错开了。错开多少都无所谓，只要指针移动到环内，就会相遇。引申一下，环的长度恰好是从第一次相遇到第二次相遇，二者之间的距离。

public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast;
        if (head != null && head.next != null) {
            fast = head.next;
        } else {
            return false;
        }

        while (fast != null && fast.next != null) {
            if (fast == slow)
                return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}