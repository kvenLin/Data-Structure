import java.util.TreeMap;

class MapSum {

    private class Node{
        private int value;
        public TreeMap<Character, Node> next;

        public Node(int value){
            next = new TreeMap<>();
            this.value = value;
        }
        public Node(){
            this(0);
        }
    }

    private Node root;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new Node();
    }

    public void insert(String word, int val) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            //判断当前节点是否包含c这个节点的映射
            if (cur.next.get(c) == null){
                //没有c节点的映射就对当前节点添加c的映射
                cur.next.put(c,new Node());
            }
            cur = cur.next.get(c);
        }
        cur.value = val;
    }

    public int sum(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null){
                return 0;
            }
            cur = cur.next.get(c);
        }
        return sum(cur);
    }

    private int sum(Node node){
//        if (node.next.size() == 0){
//            return node.value;
//        }
        int res = node.value;
        for (char c : node.next.keySet()){
            res += sum(node.next.get(c));
        }
        return res;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */