/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
// 这是我的写法，因为重叠部分的肯定要从两个链表中短的那个来开始判断的，所以需要先求出两个链表的长度，然后长的链表先走，短的链表不动，找到地址和值相等的节点返回即可
// 缺点是需要首先遍历求出链表的长度
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = getLength(headA);
        int lenB = getLength(headB);
        ListNode pointerA = headA;
        ListNode pointerB = headB;
        if(lenA > lenB){
            for(int i = 0; i < lenA - lenB; i++){
                pointerA = pointerA.next;
            }
        }
        if(lenB > lenA){
            for(int i = 0; i < lenB - lenA; i++){
                pointerB = pointerB.next;
            }
        }
        
        while(pointerA != null && pointerB != null){
            if(pointerA.val == pointerB.val && pointerA == pointerB){
                return pointerA;
            }
            pointerA = pointerA.next;
            pointerB = pointerB.next;
        }

        return null;
    }

    public int getLength(ListNode head){
        int count = 0;
        while(head != null){
            count++;
            head = head.next;
        }
        return count;
    }
}

//题解里的hash算是比较容易理解的，hash放入ListNode只考虑了地址是否相等
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode cur = headA;
        while(cur != null){
            set.add(cur);
            cur = cur.next;
        }

        cur = headB;
        while(cur != null){
            if(set.contains(cur)){
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }
}
//第二个解法是双指针，很妙，核心思想：若是两个链表有重叠部分，每个指针先遍历自己的链表，然后再遍历另一个链表，如果两个指针相等，则返回该节点，否则返回null
// （因为两个指针相当于都走了完自己的链表，然后又开始遍历对方的链表中不重合的部分，所以二者路程相等，可以看灵神的题解图，很明朗）若是二者无相交部分，那么最后都会指向null，两个指针的指向也是相等的。
//因为，如果两个链表有重叠部分，那么两个指针肯定走过了相同的步数，就是利用这点来写的，代码也很简练优雅
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p = headA;
        ListNode q = headB;

        while(p != q){
            p = p != null ? p.next : headB;//若是p为null，则表明自己的链表遍历结束，则p = headB，遍历另一个
            q = q != null ? q.next : headA;
        }
        return q;//相等时，返回任意一个都行
    }
}