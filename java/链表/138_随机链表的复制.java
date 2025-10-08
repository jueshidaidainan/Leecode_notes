package 链表;
//使用hash表两遍扫描。第一次根据原来的链表，创建一个新链表，并保存起来。所以保存的是旧节点和新节点的映射关系。
//第二次根据原来的链表的random指向，修改新链表的random指针。
//就是遍历的时候需要两个指针
class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();

        Node dummy = new Node(0);
        Node curNew = dummy;
        Node cur = head;
        while(cur != null){
            curNew.next = new Node(cur.val);
            map.put(cur, curNew.next);
            cur = cur.next;
            curNew = curNew.next;
        }

        curNew = dummy.next;
        cur = head;
        while(curNew != null){
            curNew.random = map.get(cur.random);
            cur = cur.next;
            curNew = curNew.next;
        }

        return dummy.next;
    }
}

//这是之前采用递归的写法。
//核心还是采用hash表和两次扫描，在递归的过程中创建新的节点并存入map中。对于每一个节点无论是next指针还是random指针，其实逻辑都只是需要新旧对应即可。要是递归调用的逻辑一致，map里没有就创建，map有就获取。
//那两行递归调用的代码顺序换一下也行，我试过了。
class Solution {
    Map<Node, Node> map = new HashMap<>();

    public Node copyRandomList(Node head) {
        if(head == null) return null;

        if(!map.containsKey(head)){
            Node headNew = new Node(head.val);
            map.put(head, headNew);
            headNew.next = copyRandomList(head.next);//递归调用
            headNew.random = copyRandomList(head.random);
        }
        return map.get(head);
    }
}


//灵神的题解，没有使用额外的hash表，但是进行了三次遍历。先将新的节点，新建在原来节点的后面，混合在一起。然后遍历一次，将新节点的random指针指向对应的新节点。
//最后将新旧链表分开。虽然降低了空间复杂度，但是代码逻辑很不容易懂。这不就是数据结构的意义么？
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // 复制每个节点，把新节点直接插到原节点的后面
        for (Node cur = head; cur != null; cur = cur.next.next) {
            cur.next = new Node(cur.val, cur.next);
        }

        // 遍历交错链表中的原链表节点
        for (Node cur = head; cur != null; cur = cur.next.next) {
            if (cur.random != null) {
                // 要复制的 random 是 cur.random 的下一个节点
                cur.next.random = cur.random.next;
            }
        }

        // 把交错链表分离成两个链表
        Node newHead = head.next;
        Node cur = head;
        for (; cur.next.next != null; cur = cur.next) {
            Node copy = cur.next;
            cur.next = copy.next; // 恢复原节点的 next
            copy.next = copy.next.next; // 设置新节点的 next
        }
        cur.next = null; // 恢复原节点的 next
        return newHead;
    }
}

