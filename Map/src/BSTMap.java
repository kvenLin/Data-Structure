import java.util.ArrayList;

public class BSTMap<K extends Comparable<K>,V> implements Map<K,V> {

    private class Node{
        public K key;
        public V value;
        public Node left,right;

        public Node(K key,V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }
    private Node root;
    private int size;

    public BSTMap(){
        root = null;
        size = 0;
    }

    /**
     * 向二分搜索树中添加新的元素(key,value)
     * @param key
     * @param value
     */
    @Override
    public void add(K key, V value) {
        root = add(root,key,value);
    }

    /**
     * 向以node为根节点的二分搜索树中插入元素(key,value),递归算法
     * 返回插入新节点后的二分搜索树的根节点
     * @param node
     * @param key
     * @param value
     * @return
     */
    private Node add(Node node, K key, V value) {
        if (node == null){
            size ++;
            return new Node(key,value);
        }
        if (key.compareTo(node.key) < 0){
            node.left = add(node.left,key,value);
        }else if (key.compareTo(node.key) > 0){
            node.right = add(node.right,key,value);
        }else {//key.compareTo(node.key) == 0
            node.value = value;
        }
        return node;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root,key);
        if (node != null){
            root = remove(root,key);
            return node.value;
        }
        return null;
    }

    /**
     * 删除掉以node为根的二分搜索树中键为key的节点,递归算法
     * 返回删除节点后新的二分搜索树的根
     * @param node
     * @param key
     * @return
     */
    private Node remove(Node node, K key) {
        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left,key);
            return node;
        }
        if (key.compareTo(node.key) > 0){
            node.right = remove(node.right,key);
            return node;
        }else {//key.compareTo(node.key) == 0
            //待删除的节点的左子树为空
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            //待删除的节点的右子树为空
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }
            //待删除的节点的左右子树都不为空
            Node successor = miniNum(node.right);//找到node的后继,即右子树中最小值的节点
            //removeMin()中进行了size--操作,但实际上只是将节点移动了位置
            successor.right = removeMin(node.right);
            successor.left = node.left;
            //因为之前removeMin()多余的size--操作,所以此处不再需要对移除node进行size--操作
            node.right = node.left = null;
            return successor;
        }

    }

    /**
     * 删除掉以node为根的这个二分搜索树的最小的节点
     * 同时返回删除这个节点后新的二分搜索树的根
     * @param node
     * @return
     */
    private Node removeMin(Node node) {
        //判断当前是否是最小元素的节点
        if (node.left == null){
            Node rightNode = node.right;
            //使当前节点与之脱离关系,才能被垃圾回收掉
            node.right = null;
            size --;
            return rightNode;
        }
        //若还存在左子树,则继续递归
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 返回以node为根节点的最小值所在的节点
     * @param node
     * @return
     */
    private Node miniNum(Node node){
        if (node.left == null){
            return node;
        }
        return miniNum(node.left);
    }

    @Override
    public boolean contains(K key) {
        return getNode(root,key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root,key);
        if (node == null){
            return null;
        }
        return node.value;
    }

    /**
     * 返回以node为根节点的二分搜索树中,key所在的节点
     * @param node
     * @param key
     * @return
     */
    private Node getNode(Node node,K key){
        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) == 0){
            return node;
        }else if (key.compareTo(node.key) < 0){
            return getNode(node.left,key);
        }else {
            return getNode(node.right,key);
        }
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root,key);
        if (node == null){
            throw new IllegalArgumentException(key + "doesn't exist");
        }
        node.value = value;
    }

    @Override
    public int getSize() {
        return 0;
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
            BSTMap<String,Integer> map = new BSTMap<>();
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
