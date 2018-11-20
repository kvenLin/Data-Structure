import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>,V> {

    private class Node{
        public K key;
        public V value;
        public Node left,right;
        private int height;

        public Node(K key,V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }
    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    /**
     * 获得节点的node的平衡因子
     * @param node
     * @return
     */
    private int getHeight(Node node){
        if (node == null){
            return 0;
        }
        return node.height;
    }


    /**
     * 获得节点node的平衡因子
     * @return
     */
    private int getBalanceFactor(Node node){
        if (node == null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 判断是否是二分搜索树
     * @return
     */
    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root,keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 中序遍历将keys存入ArrayList中
     * @param node
     * @param keys
     */
    private void inOrder(Node node, ArrayList<K> keys){
        if (node == null){
            return;
        }
        inOrder(node.left,keys);
        keys.add(node.key);
        inOrder(node.right,keys);
    }

    /**
     * 判断该二叉树是否是一颗平衡二叉树
     * @return
     */
    private boolean isBalanced(){
        return isBalanced(root);
    }

    /**
     * 判断以node为根节点的二叉树是否是一颗平衡二叉树,递归算法
     * @param node
     * @return
     */
    private boolean isBalanced(Node node) {
        if (node == null){
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1){
            return false;
        }
        //继续查看子树中是否也满足平衡二叉树
        return isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * 向二分搜索树中添加新的元素(key,value)
     * @param key
     * @param value
     */
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

        // 更新height
        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));
        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        //LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
            return rightRotate(node);
        }
        //RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0){
            return leftRotate(node);
        }
        //LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0){
            //转换成LL的情况
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(node.left) > 0){
            //换转成RR的情况
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    /**
     * 对节点y进行右旋转操作,返回旋转后新的根节点x
     *       y                          x
     *      / \                       /   \
     *     x  T4                     z     y
     *    / \       向右旋转(y)      /  \  /  \
     *   z  T3  -------------->    T1 T2 T3 T4
     *  / \
     * T1 T2
     * @param y
     * @return
     */
    private Node rightRotate(Node y){
        Node x = y.left;
        Node T3 = x.right;
        //向右旋转过程
        x.right = y;
        y.left = T3;

        //更新height
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) + 1;

        return x;
    }

    /**
     * 对节点y进行左旋转操作,返回旋转后新的根节点x
     *    y                         x
     *   / \                      /   \
     *  T1 x    向左旋转(y)       y     z
     *    / \  ------------->   / \   / \
     *   T2 z                  T1 T2 T3 T4
     *     / \
     *    T3 T4
     * @param y
     * @return
     */
    public Node leftRotate(Node y){
        Node x = y.right;
        Node T2 = x.left;

        //向左旋转
        x.left = y;
        y.right = T2;

        //更新height
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) + 1;
        return x;
    }

    /**
     * 删除操作
     * @param key
     * @return
     */
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
        Node retNode;

        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left,key);
            retNode = node;
        }
        if (key.compareTo(node.key) > 0){
            node.right = remove(node.right,key);
            retNode = node;
        }else {//key.compareTo(node.key) == 0
            //待删除的节点的左子树为空
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                retNode = rightNode;
            } else if (node.right == null){//待删除的节点的右子树为空
                Node leftNode = node.left;
                node.left = null;
                size --;
                retNode = leftNode;
            }else {
                //待删除的节点的左右子树都不为空
                Node successor = miniNum(node.right);//找到node的后继,即右子树中最小值的节点
                //removeMin()中进行了size--操作,但实际上只是将节点移动了位置
                successor.right = remove(node.right,successor.key);
                successor.left = node.left;
                //因为之前removeMin()多余的size--操作,所以此处不再需要对移除node进行size--操作
                node.right = node.left = null;
                retNode = successor;
            }
        }

        if (retNode == null){
            return null;
        }
        // 更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left),getHeight(retNode.right));
        //计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        //平衡维护

        //LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0){
            return rightRotate(retNode);
        }
        //RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0){
            return leftRotate(retNode);
        }
        //LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){
            //转换成LL的情况
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.left) > 0){
            //换转成RR的情况
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
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

    public static void main(String[] args) {
        System.out.println("test the file ");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("/home/hk/testFile/test.txt", words)) {
            System.out.println("Total words:" + words.size());
            AVLTree<String,Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word)){
                    map.set(word,map.get(word) + 1);
                }else {
                    map.add(word,1);
                }
            }
            System.out.println("Total different words:" + map.getSize());
            System.out.println("hello of race:" + map.get("hello"));
            System.out.println("is BST ? " + map.isBST());
            System.out.println("is Balanced : " + map.isBalanced());
        }
    }
}
