import java.util.ArrayList;

public class LinkedListMap<K,V> implements Map<K,V> {

    //将节点设为内部类
    private class Node{
        public K key;
        public V value;
        public Node next;

        public Node(K key,V value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key){
            this(key,null,null);
        }

        public Node(){
            this(null,null,null);
        }

        @Override
        public String toString() {
            return key.toString() + ":" + value.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap(){
        dummyHead = new Node();
        size = 0;
    }


    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null){
            dummyHead.next = new Node(key,value,dummyHead.next);
            size ++;
        }else{
            node.value = value;
        }
    }

    @Override
    public V remove(K key) {
        Node prev = dummyHead.next;
        while (prev.next != null){
            if (prev.next.key.equals(key)){
                break;
            }
            prev = prev.next;
        }
        if (prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
            return delNode.value;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    private Node getNode(K key){
        Node cur = dummyHead.next;
        while (cur != null){
            if (cur.key.equals(key)){
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        if (node == null){
            throw new IllegalArgumentException(key + "doesn't exist");
        }
        node.value = value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
    public static void main(String[] args) {
        System.out.println("test the file ");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("/home/hk/testFile/test.txt", words)) {
            System.out.println("Total words:" + words.size());
            LinkedListMap<String,Integer> map = new LinkedListMap<>();
            for (String word : words) {
                if (map.contains(word)){
                    map.set(word,map.get(word) + 1);
                }else {
                    map.add(word,1);
                }
            }
            System.out.println("Total different words:" + map.getSize());
            System.out.println("hello of race:" + map.get("hello"));

        }
    }
}
