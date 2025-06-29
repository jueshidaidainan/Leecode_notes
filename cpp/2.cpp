#include <iostream>
#include <stdio.h>
#include <vector>
#include <math.h>
using namespace std;

struct ListNode
{
    int val;
    ListNode *next;
};

/*
这个思路最大的问题是会越界，即使是ll类型的数据，如现在的例子所示，和是无法表示出来的
*/
ListNode *addTwoNumbers(ListNode *l1, ListNode *l2)
{ 
    long long sum = 0;
    ListNode *l = l1, *r = l2;
    int k = 0;//用来记录当前的和的权
    while (l != nullptr && r != nullptr)
    {
        sum += (l->val + r->val) * pow(10, k);
        l = l->next, r = r->next;
        k++;
    }

    //若是两个加数的位数不相等 单独处理
    while (l != nullptr)
    {
        sum += l->val * pow(10, k);
        l = l->next;
        k++;
    }

    while (r != nullptr)
    {
        sum += r->val * pow(10, k);
        r = r->next;
        k++;
    }

    ListNode *head = new (ListNode);
    head->next = nullptr;
    ListNode *L = head;
    //虽然不含前导0 但是若两个加数都为0 则直接返回0 但是while（sum）的逻辑无法判定这个
    if (sum == 0)
    {
        head->val = 0;
        return head;
    }
    else
    {
        while (sum)
        {
            long long n = sum / 10;
            int r = sum % 10;
            sum = n;
            ListNode *tmp = new ListNode;
            tmp->val = r, tmp->next = nullptr;
            L->next = tmp;
            L = L->next;
        }
        return head->next;
    }
}

int main()
{
    vector<int> a = {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1};
    vector<int> b = {5,6,4};
    ListNode *l1 = new ListNode, *l2 = new ListNode;
    ListNode *ptr = l1; // 要置于循环的外边
    for (int i = 0; i < a.size(); i++)
    {
        ListNode *p = new (ListNode);
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