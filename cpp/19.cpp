/*
删除链表的倒数第N个节点：使用双指针
这道题的核心在于，链表是单向的，但是要求我们删除的是倒数第n个节点，所以最简单的便是先遍历链表统计个数，然后知道
应该从前向后遍历多少个节点到达待删除的节点位置。（我第一次就是这么过的）官方题解还有一种是使用栈来做的，当然也需要第一
次遍历。
*/
//双指针：使用快慢指针，核心是在不知到链表长度的时候，使用快指针去探测，不变量是链表长度。
// 方法：先创建虚拟头节点，快指针先走n+1步，慢指针这时和快指针一起走，快指针到头的时候，就是慢指针到达需要删减的节点
// 前一个节点的时候。n + 1等都是边界问题。
// 不过思路确实简单巧妙
class Solution {
public:
    ListNode* removeNthFromEnd(ListNode* head, int n) {
        ListNode* dummyhead = new ListNode(0, head);
        ListNode* fast = dummyhead, *slow = dummyhead;
        n++;
        while(n--) fast = fast -> next;
        while(fast != nullptr){
            fast = fast -> next;
            slow = slow -> next;
        }
        ListNode* tmp = slow -> next;
        slow -> next = slow -> next -> next;
        delete tmp;
        tmp = nullptr;
        return dummyhead -> next;
    }
};