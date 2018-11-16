import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {
    //二分搜索树可以存放范型元素E,
    // 但是E必须满足科比较性,
    // 继承接口Comparable<E>来控制
    private class Node{
        public E e;
        public Node left,right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E e){
        root = add(root,e);
    }

    /**
     * 向以node为根节点的二分搜索树中插入元素E,递归算法
     * 返回插入新节点后二分搜索树的根
     * @param node
     * @param e
     * @return
     */
    private Node add(Node node,E e){
        //判断插入的元素是否已经存在e
        if (node == null){
            size ++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0){
            node.left = add(node.left,e);
        }else if (e.compareTo(node.e) > 0){
            node.right = add(node.right,e);
        }
        return node;
    }

    /**
     * 查看二分搜索树中是否包含了元素e
     * @param e
     * @return
     */
    public boolean contains(E e){
        return contains(root,e);
    }

    /**
     * 查看以node为根节点的二分搜索树中是包含元素e,递归算法
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node node,E e){
        if (node == null){
            return false;
        }
        if (e.compareTo(node.e) == 0){
            return true;
        }else if (e.compareTo(node.e) < 0){
            return contains(node.left,e);
        }else {
            return contains(node.right,e);
        }
    }

    /**
     * 二分搜索树的前序遍历
     */
    public void preOrder(){
        preOrder(root);
    }

    /**
     * 前序遍历以node为根的二分搜索树,递归算法
     * @param node
     */
    public void preOrder(Node node){
        //递归终止的条件
        if (node == null){
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }


    /**
     * 二分搜索树的非递归方式的前序遍历
     */
    public void preOrderNR(){
        //利用栈的方式来进行记录遍历的节点
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);
            if (cur.right != null){
                stack.push(cur.right);
            }
            if (cur.left != null){
                stack.push(cur.left);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void inOrder(){
        inOrder(root);
    }

    public void inOrder(Node node){
        if (node == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 后序遍历
     */
    public void postOrder(){
        postOrder(root);
    }

    public void postOrder(Node node){
        if (node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }


    /**
     * 二分搜索树的层序遍历
     */
    public void levelOrder(){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            Node cur = q.remove();
            System.out.println(cur.e);
            if (cur.left != null){
                q.add(cur.left);
            }
            if (cur.right != null){
                q.add(cur.right);
            }
        }
    }


    /**
     * 寻找二分搜索树中的最小的元素
     * @return
     */
    public E miniNum(){
        if (size == 0){
            throw new IllegalArgumentException("BST is empty");
        }
        return miniNum(root).e;
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

    /**
     * 寻找二分搜索树最大的元素
     * @return
     */
    public E maxiNum(){
        if (size == 0){
            throw new IllegalArgumentException("BST is empty");
        }
        return maxiNum(root).e;
    }

    /**
     * 寻找以node节点为根节点的最大值所在节点
     * @param node
     * @return
     */
    private Node maxiNum(Node node){
        if (node.right == null){
            return node;
        }
        return maxiNum(node.right);
    }

    /**
     * 删除最小值所在的节点,并且返回最小值
     * @return
     */
    public E removeMin(){
        E ret = miniNum();
        //返回新的根节点需要进行新的赋值操作
        root = removeMin(root);
        return ret;
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
     * 删除最大值所在的节点,并且返回最大值
     * @return
     */
    public E removeMax(){
        E ret = maxiNum();
        root = removeMax(root);
        return ret;
    }

    /**
     * 删除以node为根的这个二分搜索树的最大的节点
     * 同时返回删除这个节点后新的二分搜索树的根
     * @param node
     * @return
     */
    private Node removeMax(Node node) {
        if (node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e){
        root = remove(root,e);
    }

    /**
     * 删除指定的节点
     * @param node
     * @param e
     * @return
     */
    private Node remove(Node node, E e) {
        if (node == null){
            return null;
        }
        //比较当前的e和node.e
        if (e.compareTo(node.e) < 0){
            node.left = remove(node.left,e);
            return node;
        }else if (e.compareTo(node.e) > 0){
            node.right = remove(node.right,e);
            return node;
        }else {//e == node.e,删除当前节点
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


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    /**
     * 生成以node为根节点,深度为depth的描述二叉树的字符串
     * @param node
     * @param depth
     * @param res
     */
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left,depth + 1,res);
        generateBSTString(node.right,depth + 1,res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }
}
