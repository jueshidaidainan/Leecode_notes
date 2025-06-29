/*
环形链表Ⅱ：这道题我觉得数学的成分就很浓厚了，如果不考虑哈希表的做法而考虑双指针的做法的话
双指针需要定义快慢指针，快指针是慢指针速率的2倍，也只能是2倍，因为若是3倍的话，相对速率大于1，二者可能会在环里跳过
核心：慢指针在环里走一圈之前就被快指针追上了，当然快指针可能在环里走了很多圈了，相遇之后需要找入点，这里经过数学分析，
我们知道从相遇点开始，若是有两个指针，一个从相遇点出发，一个从head出发，每步走一个单位，相遇后的位置就是入点。
*/

//双指针
class Solution {
public:
    ListNode *detectCycle(ListNode *head) {
        ListNode* fast = head, *slow = head;
        while(fast != nullptr && fast -> next != nullptr){// 快慢指针开始遍历
            fast = fast -> next -> next;
            slow = slow -> next;
            ListNode* index1, *index2;
            if(fast == slow){//快慢指针相遇
                index1 = fast;
                index2 = head;
                while(index1 != index2){//寻找入点
                    index1 = index1 -> next;
                    index2 = index2 -> next;
                }
                return index2;
            }
            
        }
        return NULL;
    }
};