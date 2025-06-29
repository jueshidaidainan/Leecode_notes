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