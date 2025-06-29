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

//官方的题解是使用归并排序的方法
class Solution {//自己的写法：先比较合并，然后将剩余的链表连上
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode cur1 = list1;
        ListNode cur2 = list2;
        ListNode head = null;
        ListNode ptr = null;
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        if(cur1.val <= cur2.val){
            head = cur1;
            cur1 = cur1.next;
        }else{
            head = cur2;
            cur2 = cur2.next;
        }
        ptr = head;
        while(cur1 != null && cur2 != null){//将较小节点连上
            if(cur1.val <= cur2.val){
                ptr.next = cur1;
                ptr = ptr.next;
                cur1 = cur1.next;
            }else{
                ptr.next = cur2;
                ptr = ptr.next;
                cur2 = cur2.next;
            }
        }
        //将剩余节点连上
        ptr.next = cur1 == null ? cur2 : cur1;
        // while(cur1 != null ){
        //     ptr.next = cur1;
        //     ptr = ptr.next;
        //     cur1 = cur1.next;
        // }
        // while(cur2 != null ){
        //     ptr.next = cur2;
        //     ptr = ptr.next;
        //     cur2 = cur2.next;
        // }
        return head;
    }

}

//官方的解法，使用归并排序的写法
//归并排序本质是递归，将问题分解成更小的问题，然后合并结果
//而递归最终的是什么呢：我认为有三点
//1. 递归的结束条件，也就是递归出口
//2. 递归的过程
//3. 递归的返回值，有没有返回值，如果有，返回什么（这样保证递归调用链的连续）
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null){//链表为空时的情况，准确的来说这里应该是递归出口
            return list2;
        }
        if(list2 == null){
            return list1;
        }
        if(list1.val <= list2.val){//链表不空的时候
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;//问题规模被分解返回后，如果这里没有return，那函数就直接退出了，递归调用链就断了
        }else{
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}