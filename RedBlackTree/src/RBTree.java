public class RBTree<K extends Comparable<K>,V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        public K key;
        public V value;
        public Node left,right;
        public boolean color;

        public Node(K key,V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree(){
        root = null;
        size = 0;
    }

    /**
     * 向二分搜索树中添加新的元素(key,value)
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        root = add(root,key,value);
        root.color = BLACK;//添加完节点后保持根节点为黑色节点
    }


    /**
     *      node              x
     *      /  \    左旋转   /  \
     *     T1  x  ------->node  T3
     *        / \        /   \
     *       T2 T3      T1   T2
     * @param node
     * @return
     */
    private Node leftRotate(Node node){
        Node x = node.right;

        //左旋转
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    /**
     *      node                 x
     *     /   \    右旋转       / \
     *    x    T2 -------->    y  node
     *   / \                     /   \
     *  y  T3                   T1   T2
     * @param node
     * @return
     */
    private Node rightRotate(Node node){
        Node x = node.left;

        //右旋转
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    private boolean isRed(Node node){
        return node.color == RED;
    }

    private boolean isBlack(Node node){
        return node.color == BLACK;
    }


    /**
     * 颜色的翻转
     * @param node
     */
    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
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

        //判断当前根节点是否需要左旋转
        if (isRed(node.right) && !isRed(node.left)){
            node = leftRotate(node);
        }
        //判断当前节点是否需要右旋转
        if (isRed(node.left) && !isRed(node.left.left)){
            node = rightRotate(node);
        }
        //判断是否需要进行颜色的翻转
        if (isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }

        return node;
    }

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

    public boolean contains(K key) {
        return getNode(root,key) != null;
    }

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

    public void set(K key, V value) {
        Node node = getNode(root,key);
        if (node == null){
            throw new IllegalArgumentException(key + "doesn't exist");
        }
        node.value = value;
    }

    public int getSize() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }
}
