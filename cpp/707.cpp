/*
设计链表：单链表的增删改查
*/


class MyLinkedList {
    
public:
    ;
    struct LinkedNode{
        int val;
        LinkedNode* next;
        LinkedNode(int x):val(x), next(nullptr){}; // 构造函数初始值列表，只能用在构造函数，感觉比较方便
    };

    LinkedNode* _dummyHead;
    int _size;

    MyLinkedList() {
        _dummyHead = new LinkedNode(0);//在c++使用下划线命名一般是为了避免冲突
        _size = 0;
    }
    
    int get(int index) {
        if(index > _size - 1 || index < 0) return -1;
        LinkedNode* cur = _dummyHead -> next;
        while(index--){
            cur = cur -> next;
        }
        return cur -> val;
    }
    
    void addAtHead(int val) {
        LinkedNode* newhead = new LinkedNode(val);
        newhead -> next = _dummyHead -> next;
        _dummyHead -> next = newhead;
        _size++;
    }
    
    void addAtTail(int val) {
        LinkedNode* cur = _dummyHead;
        while(cur -> next != nullptr){
            cur = cur -> next;
        }
        LinkedNode* newtail = new LinkedNode(val);
        cur -> next = newtail;
        _size++;
    }
    
    void addAtIndex(int index, int val) {
        if(index > _size) return; //这里需要注意索引时可以等于链表长度的，代表在尾部插入
        LinkedNode* cur = _dummyHead;
        LinkedNode* newnode = new LinkedNode(val);
        while(index--){
            cur = cur -> next;
        }
        newnode -> next = cur -> next;
        cur -> next = newnode;
        _size++;
    }
    
    void deleteAtIndex(int index) {
        if(index > _size -1 || index < 0) return ;
         LinkedNode* cur = _dummyHead;
         while(index--){
            cur = cur -> next;
        }
        LinkedNode* tmp = cur -> next;
        cur -> next = cur -> next -> next;
        delete tmp;
        tmp = nullptr;
        // delete释放指针所指地址的空间
        // 要是没有将指针继续赋值为null，其会成为野指针，因为值是随机值，要是后续被使用就完犊子了
        //不过加上这句之后，耗时变多了还，不过更安全了吧
        _size--;
    }
};

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList* obj = new MyLinkedList();
 * int param_1 = obj->get(index);
 * obj->addAtHead(val);
 * obj->addAtTail(val);
 * obj->addAtIndex(index,val);
 * obj->deleteAtIndex(index);
 */