public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(){
        data = new Array<>();
    }

    /**
     * 返回堆中的元素个数
     * @return
     */
    public int getSize(){
        return data.getSize();
    }

    /**
     * 返回一个布尔值,表示堆中是否为空
     * @return
     */
    public boolean isEmpty(){
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中,一个索引所表示的元素的父亲节点的索引
     * @param index
     * @return
     */
    private int parent(int index){
        if (index == 0){
            throw new IllegalArgumentException("index 0 doesn't have parent node.");
        }
        return (index - 1) / 2;
    }

    /**
     * 返回完全二叉树的数组表示中,一个索引所表示的元素的左孩子节点的索引
     * @param index
     * @return
     */
    private int leftChild(int index){
        return index * 2 + 1;
    }

    /**
     * 返回完全二叉树的数组表示中,一个索引所表示的元素的右孩子节点的索引
     * @param index
     * @return
     */
    private int rightChild(int index){
        return index * 2 + 2;
    }

    /**
     * 向堆中添加元素
     * @param e
     */
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 堆元素进行上浮
     * @param k
     */
    private void siftUp(int k) {
        //比较堆中当前节点的父亲节点的元素是否小于当前节点的值
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0){
            //交换两个元素的位置
            data.swap(k,parent(k));
            //重新赋值元素的索引
            k = parent(k);
        }
    }

    /**
     * 取出堆中的最大元素
     * @return
     */
    public E extractMax(){
        E ret = findMax();
        //先将最大的元素一道
        data.swap(0,data.getSize() - 1);
        //删除最大的元素
        data.removeLast();
        siftDown(0);
        return ret;
    }

    /**
     * 堆元素进行下沉
     * @param k
     */
    private void siftDown(int k) {
        //当元素的左孩子的index小于总长度时,进行遍历
        while (leftChild(k) < data.getSize()){
            //获取左孩子的index
            int j = leftChild(k);
            //判断右孩子的index是否超出总长度
            if (j + 1 < data.getSize() &&
                    //如果右孩子的值比左孩子的值大,则标记需要进行替换的index为右孩子
                    data.get(j + 1).compareTo(data.get(j)) > 0){
                //data[j] 是 leftChild 和 rightChild中的最大值
                j = rightChild(k);
            }
            //若当前节点的元素的值比左右孩子节点中最大的还大,则不再需要进行下沉操作
            if (data.get(k).compareTo(data.get(j)) > 0){
                break;
            }
            data.swap(k,j);
            //交换index,判断下沉之后是否需要新的下沉操作
            k = j;
        }
    }

    /**
     * 查看堆中最大元素
     * @return
     */
    public E findMax(){
        if (data.getSize() == 0){
            throw new IllegalArgumentException("Can not findMax when heap is empty");
        }
        return data.get(0);
    }

    /**
     * 取出最大的元素,并且替换成元素e
     * @param e
     * @return
     */
    public E replace(E e){
        E ret = findMax();
        data.set(0,e);
        siftDown(0);
        return ret;
    }

    /**
     * 在构造函数中进行heapify,使得传入的数组元素的顺序变成堆的结构
     * @param arr
     */
    public MaxHeap(E[] arr){
        data = new Array<>(arr);
        //从最后的非叶子节点进行
        for (int i = parent(arr.length - 1); i >= 0; i --) {
            //进行下称操作
            siftDown(i);
        }
    }


}
