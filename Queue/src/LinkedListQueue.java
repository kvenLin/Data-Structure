public class LinkedListQueue<E> implements Queue {

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

    private Node head,tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void enqueue(Object e) {
        if (tail == null){
            //添加第一个元素时head和tail指向同一个位置
            tail = new Node((E) e);
            head = tail;
        }else {
            //tail不为空在队尾的下一个元素进行添加,并移动tail
            tail.next = new Node((E) e);
            tail = tail.next;
        }
        size ++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("出队失败,队列为空");
        }
        Node delNode = head;
        head = head.next;
        delNode.next = null;
        //当链表队列中只有一个元素,删除后需要都设置为null
        if (head == null){
            tail = null;
        }
        return delNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()){
            throw new IllegalArgumentException("出队失败,队列为空");
        }
        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front [");
        Node cur = head;
        while (cur != null){
            res.append(cur + "-> ");
            cur = cur.next;
        }
        res.append("NULL ] tail ");
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        Integer front = queue.getFront();
        System.out.println(front);
    }
}
