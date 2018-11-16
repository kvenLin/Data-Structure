public class LinkedList<E> {

    //将节点设为内部类
    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

//    private Node head;
    private Node dummyHead;
    private int size;

    public LinkedList(){
        dummyHead = new Node(null,null);
        size = 0;
    }

    /**
     * 链表中元素个数
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 返回链表是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 在链表的index(0-based)位置添加新的元素e
     * 在链表中不是一个常用的操作,练习用
     * @param index
     * @param e
     */
    public void add(int index,E e){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("添加失败,非法的index");
        }
        Node prev = dummyHead;
        //index - 1才是prev
//        for (int i = 0; i < index - 1; i++) {
        /**
         * 获取index位置元素的前一个元素
         * 引入dummyHead后不需要再对index做-1操作
         */
        for (int i = 0; i < index; i++) {
            //依次遍历
            prev = prev.next;
        }
//        Node node = new Node(e);
//        node.next = prev.next;
//        prev.next = node;
        //同上
        prev.next = new Node(e,prev.next);
        size ++;
    }

    /**
     * 在链表头添加元素e
     * @param e
     */
    public void addFirst(E e){
//        Node node = new Node(e);
//        node.next = head;
//        head = node;
        //相当于是上面的实现形式
//        head = new Node(e, head);
//        size ++;
        //引入dummyHead后
        add(0,e);
    }

    /**
     * 在链表末尾添加新的元素e
     * @param e
     */
    public void addLast(E e){
        add(size,e);
    }

    /**
     * 获取链表的第index(0-based)位置的元素
     * 在链表中不是一个常用的操作,练习使用
     * @param index
     * @return
     */
    public E get(int index){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("获取失败,非法index");
        }
        //获取某一元素是从dummyHead.next开始进行遍历
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    /**
     * 获取第一个元素
     * @return
     */
    public E getFirst(){
        return get(0);
    }

    /**
     * 获取最后一个元素
     * @return
     */
    public E getLast(){
        return get(size - 1);
    }

    /**
     * 更新index位置的元素
     * 练习使用
     * @param index
     * @param e
     */
    public void set(int index,E e){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("更新失败,非法index");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;

    }

    /**
     * 查找是否存在e元素
     * @param e
     * @return
     */
    public boolean contains(E e){
        Node cur = dummyHead.next;
        while (cur!=null){
            if (cur.e.equals(e)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    /**
     * 从链表中删除index位置的元素,返回删除的元素
     * 练习使用
     * @param index
     * @return
     */
    public E remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("删除失败,非法index");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        size --;
        return delNode.e;
    }

    /**
     * 删除第一个元素
     * @return
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * 删除最后一个元素
     * @return
     */
    public E removeLast(){
        return remove(size - 1);
    }

    /**
     * 删除元素e
     * @param e
     */
    public void removeElement(E e){
        Node prev = dummyHead;
        while (prev.next != null){
            if (prev.next.e.equals(e)){
                break;
            }
            prev = prev.next;
        }
        if (prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node cur = dummyHead.next;
        while (cur != null){
            res.append(cur+"-> ");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
