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

// 这是我的写法，遍历所有节点存入List里，然后调用sort方法，再遍历List维护指针就行
// 只是刚开始sb了用LinkeList，这个双向链表来排序，
// 要知道大多的排序算法是为了数组设计的，只有归并排序算法是针对链表的，而且排序大多需要随机访问，链表太慢了，这是第一次有一个用例过不去的原因
class Solution {
    public ListNode sortList(ListNode head) {
        List<ListNode> list = new ArrayList<>();//用ArrList替换LinkedList ArrayList效率更高一些(本来用的是LinkedList 最后一个用例会超时)
        for (ListNode cur = head; cur != null; cur = cur.next) {
            list.add(cur);
        }

        Collections.sort(list, new Comparator<ListNode>() {
            public int compare(ListNode node1, ListNode node2) {
                return node1.val - node2.val;
            }
        });

        // list.sort((a, b) -> Integer.compare(a.val, b.val)); // 使用 lambda表达式，效率稍高于匿名Compartor

        // for(ListNode node : list){
        //     System.out.println(node.val);
        // }

        ListNode dummyhead = new ListNode(0, head);
        ListNode pre = dummyhead;
        for (int i = 0; i < list.size(); i++) {
            pre.next = list.get(i);
            pre = pre.next;
            // System.out.println(list.get(i).val);
        }

        pre.next = null;

        return dummyhead.next;
    }
}

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

// 这是官方的归并排序解法：先分治 再 合并  我理解就是归并
// 需要注意的是：两点
// 1. 区间是左闭右开的，所以head.next = null表示区间只有一个节点，返回即可，并且注意要断开连接
// 2. 区间的中点是使用快慢指针来找的，而快慢指针要注意快链表节点为奇数和偶数的不同
// 合并呢 可以有两种  一是21题的递归写法  二是迭代写法
// 我这里是递归写法，迭代稍微代码多点，但是21题我的题解也有，但是迭代快一点点，最快的可能是自下而上的写法，我还没看
class Solution {
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head, ListNode tail){
        if(head == null){
            return head;
        }
        if(head.next == tail){//表示这是左闭右开区间
            head.next = null;//所以这里只有head一个节点，要断开本来的连接，防止合并的时候产生脏数据
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        while(fast != tail){
            slow = slow.next;
            fast = fast.next;
            if(fast != tail){
                fast = fast.next;
            }
        }

        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode sorted = merge(list1, list2);
        return sorted;

    }

    private ListNode merge(ListNode list1, ListNode list2){
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }

        if(list1.val <= list2.val){
            list1.next = merge(list1.next, list2);
            return list1;
        }else{
            list2.next = merge(list1, list2.next);
            return list2;
        }
    }
}