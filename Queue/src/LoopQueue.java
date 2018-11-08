
public class LoopQueue<E> implements Queue {

    private E[] data;
    private int front, tail;//用于指向队首和队尾的索引坐标
    private int size;

    public LoopQueue(int capacity){
        //因为判断队列是否为满需要浪费一个空间,所以这里在用户定义需要的空间的基础上再做+1操作
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    /**
     * 获取队列的空间,
     * 队列中有一个空间用于判断队列是否为满,
     * 所以只有真实长度做-1操作才是真正的容量大小
     * @return
     */
    public int getCapacity(){
        return data.length - 1;
    }

    /**
     * 入队
     * @param e
     */
    @Override
    public void enqueue(Object e) {
        //首先判断队列是否满的
        if ((tail + 1) % data.length == front){
            //进行扩容操作,使用getCapacity()是获取真正的容量大小
            resize(getCapacity() * 2);
        }
        //在队尾进行添加元素
        data[tail] = (E) e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    /**
     * 扩容队列
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        //生成一个新的数组
        E[] newData = (E[]) new Object[newCapacity + 1];
        //对每一个元素进行赋值到新数组
        for (int i = 0; i < size; i++) {
            //使用 (i + front) % data.length 避免出现data[i + front]越界
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    /**
     * 出队
     * @return
     */
    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("队列对空,不能进行出队操作");
        }
        E ret = data[front];
        data[front] = null;
        //移动front的索引
        front = (front + 1) % data.length;
        size --;
        //判断是否需要缩容操作,以及缩容不能等于0
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0){
            //进行缩容
            resize(getCapacity() / 2);
        }
        return ret;
    }

    /**
     * 获取队首元素
     * @return
     */
    @Override
    public E getFront() {
        if (isEmpty()){
            throw new IllegalArgumentException("当前队列为空");
        }
        return data[front];
    }

    /**
     * 获取队列大小
     * @return
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("LoopQueue: size = %d , capacity = %d\n",size,getCapacity()));
        res.append("front [");

        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            //最后一个元素不需要加", "
            if ((i + 1) % data.length != tail){
                res.append(", ");
            }
        }
        //第二种遍历的方式
//        for (int i = 0; i < size; i++) {
//            res.append(data[(i + front) % data.length]);
//            //最后一个元素不需要加", "
//            if (i != size - 1){
//                res.append(", ");
//            }
//        }
        res.append("] tail ");
        return res.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        Integer front = queue.getFront();
        System.out.println(front);

        queue.enqueue(110);
        queue.enqueue(120);
        queue.dequeue();
        System.out.println(queue);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        System.out.println(queue);
    }
}
