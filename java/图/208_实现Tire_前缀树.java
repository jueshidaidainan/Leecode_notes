//由于字母都是小写的，这道题的核心需求就是前缀匹配（完全匹配也是一种嘛）
//所以，构建一个前缀树。每个节点存储一个26个字母的子节点，每个节点的end表示是否是一个单词的结束。
//insert的时候，遍历要插入的字符串，构建树节点，最后一个节点的end为true。这样可以方便区分是前缀匹配还是完全匹配。

class Trie {

    private static class Node{
        Node[] son = new Node[26];
        boolean end = false;
    }

    private final Node root;

    public Trie() {
        root = new Node();
    }
    
    public void insert(String word) {
        Node cur = root;
        for(char c : word.toCharArray()){
            c -= 'a';
            if(cur.son[c] == null){//无路可走
                cur.son[c] = new Node();//new出来
            }
            cur = cur.son[c];//指针移动
        }
        cur.end = true;
    }
    
    public boolean search(String word) {
        return find(word) == 2;
    }
    
    public boolean startsWith(String prefix) {
        return find(prefix) != 0;
    }

    private int find(String word){
        Node cur = root;
        for(char c : word.toCharArray()){
            c -= 'a';
            if(cur.son[c] == null){
                return 0;//找不到，路不通
            }
            cur = cur.son[c];
        }
        return cur.end ? 2 : 1;//2代表完全匹配，1代表前缀匹配
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */