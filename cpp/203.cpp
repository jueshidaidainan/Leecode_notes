/*
移除链表元素：
使用虚拟头节点和不使用虚拟头节点的区别：使用虚拟头节点可以让代码逻辑统一，不用对头节点进行特殊处理
而在遍历链表的时候，我第一次使用的是两个指针，一个进行遍历，一个留存前驱；而我们可以通过两个next的两跳来进行一个指针的遍历
*/

// 无虚拟头节点 + 两个指针
ListNode* removeElements(ListNode* head, int val) {
    while(head != nullptr && head -> val == val){// 处理头节点
        ListNode* tmp = head;
        head = head -> next;
        delete tmp;//回收
    }
    if(head == nullptr) return nullptr;

    ListNode* tmp = head;
    ListNode* pro = head;
    while(tmp != nullptr){//双指针进行遍历
        if(tmp -> val == val){
            ListNode* del = tmp;
            pro -> next = tmp -> next;
            tmp = tmp -> next;
            delete del;
        }else{
            pro = tmp;
            tmp = tmp -> next;
        }
    }
    return head;
}

// 无虚拟头节点 + 1个指针
    ListNode* removeElements(ListNode* head, int val) {
        while(head != nullptr && head -> val == val){
            ListNode* tmp = head;
            head = head -> next;
            delete tmp;
        }
        if(head == nullptr) return nullptr;

        ListNode* cur = head;
        while(cur -> next != nullptr){
            if(cur -> next -> val == val){
                ListNode* del = cur -> next;
                cur -> next = cur -> next -> next;
                delete del;
            }else{
                cur = cur -> next;
            }
        }
        return head;
    }

    // 有虚拟头节点 + 1个指针
     ListNode* removeElements(ListNode* head, int val) {
       ListNode* dummyhead = new ListNode(0);
       dummyhead -> next = head;

        ListNode* cur = dummyhead;
        while(cur -> next != nullptr){
            if(cur -> next -> val == val){
                ListNode* del = cur -> next;
                cur -> next = cur -> next -> next;
                delete del;
            }else{
                cur = cur -> next;
            }
        }
        return dummyhead -> next;
    }