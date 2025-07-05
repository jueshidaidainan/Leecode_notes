//刚开始这是我自己鼓捣的解法：题目要求实现LRU缓存，达到容量之后删除最老的缓存。
//元素本身的访问，使用HashMap就可以解决,时间复杂度是O（1）的，符合要求，map的key存储题目给的key就可以，但是访问顺序不能存储，map是无序的。
//首先我考虑到的是队列，每次访问一个缓存，就移动到队列的末尾。但是这样每次访问一个缓存，都要遍历队列，时间复杂度O（n）。
//但是题目要求get和put方法的复杂度都是O（1），这也就是下面我自己的方法过不去的原因了（会超时）。
//解决这个问题需要两点：其一是使用双向链表，插入和删除是O（1）的，（为什么不是单链表，因为单链表删除需要知道前一个节点，还是需要遍历）
//其二是Map的value存储Node，这样通过map可以直接获取到node，然后通过指针操作链表，就无序遍历就可以拿到元素在链表中的位置了。
//灵山大人的dummy哨兵节点，可以用来简化操作，通过dummy.next可以获取到链表头，dummy.prev可以获取到链表尾。
class LRUCache {
    private HashMap<Integer, Integer> map;
    private LinkedList<Integer> list;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
        this.list = new LinkedList<>();
    }

    public int get(int key) {
        if (this.map.containsKey(key)) {
            this.list.remove((Integer) key);
            this.list.offer(key);
            return this.map.get(key);
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        // HashMap 和linkedList会有性能问题，主要是Linkedlist的remove是O（n）的
        if (this.map.containsKey(key)) {
            // this.map.put(key, value);
            this.list.remove((Integer) key);
            // this.list.offer(key);
        } else if (this.list.size() >= this.capacity) {
            int oldkey = this.list.poll();
            this.map.remove(oldkey);
            // this.map.put(key, value);
            // this.list.offer(key);
        }
        this.map.put(key, value);
        this.list.offer(key);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */





class LRUCache {
    private static class Node{
        int key, value;
        Node prev, next;
        Node(int k, int v){
            this.key = k;
            this.value = v;
        }
    }

    private final int capacity;
    private final Node dummy = new Node(0,0);//哨兵节点
    private final Map<Integer, Node> map = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dummy.prev = dummy;
        dummy.next = dummy;
    }
    
    public int get(int key) {
        Node node = getNode(key);
        return node == null ? -1 : node.value;
    }
    
    public void put(int key, int value) {
        Node node = getNode(key);
        if(node != null){
            node.value = value;
            return;
        }
        node = new Node(key, value);
        map.put(key, node);
        pushFront(node);
        if(map.size() > this.capacity){//节点超了，先放再移除
            Node backNode = dummy.prev;
            map.remove(backNode.key);
            remove(backNode);
        }
    }

    
    // 会将对应链表的值也移除，如果存在的话
    private Node getNode(int key){
        if(!map.containsKey(key)){
            return null;
        }
        Node node = map.get(key);
        remove(node);
        pushFront(node);
        return node;
    }

    //双向链表的操作
    private void remove(Node x){
        x.prev.next = x.next;
        x.next.prev = x.prev;
    }

    private void pushFront(Node x){
        x.prev = dummy;
        x.next = dummy.next;
        x.prev.next = x;
        x.next.prev = x;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */