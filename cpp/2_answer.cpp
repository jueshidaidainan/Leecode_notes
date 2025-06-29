#include<iostream>
#include<vector>
using namespace std;
struct ListNode
{
    int val;
    ListNode *next;
    ListNode(int x=0){
        val = x;
    }
};

ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
    ListNode* l = l1, *r = l2;
    int carry = 0;
    ListNode* head = nullptr, *tail = nullptr;
    while(l && r){
        if(!head){
            head = tail = new ListNode((l->val + r->val + carry) % 10);
            carry = (l->val + r->val + carry) / 10;
            l = l->next, r = r->next;
        }else{
            tail->next = new ListNode((l->val + r->val + carry) % 10);
            carry = (l->val + r->val + carry) / 10;
            l = l->next, r = r->next;
            tail = tail-> next;
        }
    }
    while(l){
        tail->next = new ListNode((l->val + 0 +carry) % 10);
        carry = (l->val + 0 + carry) / 10;
        tail = tail-> next;
        l = l->next;
    }
    while(r){
        
        tail->next = new ListNode((r->val + 0 +carry) % 10);
        carry = (r->val + 0 + carry) / 10;
        tail = tail-> next;
        r = r->next;
    }

    if(carry > 0){
        tail->next = new ListNode(carry);
    }
    return head;
}

int main()
{
    vector<int> a = {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1};
    vector<int> b = {5,6,4};
    ListNode *l1 = new ListNode, *l2 = new ListNode;
    ListNode *ptr = l1; // 要置于循环的外边
    for (int i = 0; i < a.size(); i++)
    {
        ListNode *p = new ListNode;
        p->next = nullptr;
        p->val = a[i];
        ptr->next = p;
        ptr = ptr->next;
    }
    ptr = l2;
    for (int j = 0; j < b.size(); j++)
    {
        ListNode *p = new (ListNode);
        p->next = nullptr;
        p->val = b[j];
        ptr->next = p;
        ptr = ptr->next;
    }
    addTwoNumbers(l1->next, l2->next);
    system("pause");
    return 0;
}