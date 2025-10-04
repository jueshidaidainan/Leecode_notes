import org.omg.CosNaming._BindingIteratorImplBase;

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

//快慢指针来找到链表的中间节点，同时将slow遍历的前半段存入list。然后倒序比较后半段和list里的节点是否相等。
// 快慢指针遍历：偶数长度，最后fast指向null，slow指向后半段第一个；奇数长度，fast指向最后一个，slow指向最中间的，所以奇数要特殊处理。
 class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        List<Integer> list = new ArrayList<>();
        while(fast != null && fast.next != null){
            list.add(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }

        if(fast != null){//偶数长度，最后fast指向null，slow指向后半段第一个；奇数长度，fast指向最后一个，slow指向最中间的，所以奇数要特殊处理
            slow = slow .next;
        }

        for(int i = list.size() - 1; i >= 0; i--){//倒序遍历list与后半截链表比较
            if(list.get(i) == slow.val){
                slow = slow.next;
                continue;
            }else{
                return false;
            }
        }
        return true;
        
    }
}

//先找到链表的中间节点，然后从中间节点开始，将后半截链表倒序，然后从两头比较二者是否想等。
//优点在于空间复杂度O(1)
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode mid = middleNode(head);
        ListNode head2 = reverse(mid);
        while(head2 != null){//这里是head2，不是head。因为要是偶数个节点，反转后的链表个数偏少。
            if(head2.val != head.val){
                return false;
            }
            head = head.next;
            head2 = head2.next;
        }
        return true;
    }

    private ListNode middleNode(ListNode head){//返回链表的中间节点，偶数是中间两个靠后的那个
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head){//返回反转后的链表头节点
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode nxt = cur.next;
            cur.next = pre;
            
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
}

// 遍历单链表，得到链表的长度，同时将链表存入ArrayList中
// 根据链表的长度，从后往前遍历ArrayList，与回文链表的前半部分进行比较，看是否相等
// 找链表的中间节点有更优秀的方法，详见876（利用快慢指针的速度差进行求解）
class Solution {
    public boolean isPalindrome(ListNode head) {
        int len = 0;
        boolean tag = true;
        ArrayList<Integer> list = new ArrayList<>();
        ListNode pointer = head;
        while(pointer != null){
            list.add(pointer.val);
            len++;
            pointer = pointer.next;
        }

        // int mid = (len % 2) == 0 ? (len / 2) - 1 : len / 2;
        int mid = len / 2;
        System.out.println(mid);
        ListNode cur = head;
        for(int i = len - 1; i >= mid; i--){
            if(list.get(i) == cur.val){
               cur = cur.next;
            }else{
                tag = false;
            }
        }
        return tag;
    }
}  



