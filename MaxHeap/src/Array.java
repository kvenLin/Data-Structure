
public class Array<E> {

    private E[] data;
    private int size;//描述data中究竟有多少个有效的元素
    //data.length 就是capacity所以不必再设置一个capacity的成员变量

    /**
     * 构造函数,传入数组的容量capacity构造Array
     * @param capacity
     */
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public Array() {
        this(10);
    }

    public Array(E[] arr){
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
            size = arr.length;
        }
    }

    /**
     * 获取数组中的元素个数
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组容量
     * @return
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 返回数组是否为空
     * @return
     */
    public boolean isEmpty(){
        return data.length == 0;
    }

    /**
     * 在末尾添加一个元素
     * @param e
     */
    public void addLast(E e){
        add(size, e);
    }


    /**
     * 在起始位置添加一个元素
     * @param e
     */
    public void addFirst(E e){
        add(0,e);
    }

    /**
     * 在数组中index插入一个元素e
     * @param index
     * @param e
     */
    public void add(int index,E e){
        if (size == data.length){
            resize(2 * data.length);
        }
        //判断索引是否小于0,插入元素必须紧密排列,不能大于size
        if (index < 0 || index > size){
            throw new IllegalArgumentException("添加失败,索引参数异常");
        }

        //index以后的所有元素向后挪一个位置
        for (int i = size - 1; i >= index; i --){
            data[i + 1] = data[i];
        }
        data[index] = e;
        size ++;
    }

    /**
     * 获取index索引位置的元素
     * @param index
     * @return
     */
    public E get(int index){
        //通过封装的方式保证数据的安全
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("获取失败,索引非法");
        }
        return data[index];
    }

    public void set(int index,E e){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("获取失败,索引非法");
        }
        data[index] = e;
    }

    /**
     * 查询数组中是否包含元素e
     * @param e
     * @return
     */
    public boolean contains(E e){
        for (int i = 0; i < size; i++) {
            //使用equals进行值比较
            if (data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    /**
     * 查询数组中元素e的索引,如果不存在元素e,则返回-1
     * @param e
     * @return
     */
    public int find(E e){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除指定位置的元素,返回删除的元素,以免备用
     * @param index
     */
    public E remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("删除失败,索引非法");
        }
        E ret = data[index];
        /**
         * 从 index + 1 开始遍历而不是从index开始
         * 若要从index开始遍历,则要使用 i < size - 1 作为条件判断
         * 否则会在最后一个元素移动的时候出现空指针异常
         */
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size --;
        /**
         * 当删除某元素后,坐标为size处还存在一个对象的引用,
         * 所以不能被JVM的垃圾回收器进行回收,
         * 这里使用data[size] = null将引用的对象和data[size]断开联系
         * 所以才能及时被垃圾回收
         */
        data[size] = null; //(游荡的对象)loitering objects != (内存泄漏)memory leak

        if (size == data.length / 4 && data.length / 2 != 0){
            resize(data.length / 2);
        }
        return ret;
    }

    /**
     * 删除第一个元素,返回删除的元素
     * @return
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * 删除最后一个元素,返回删除的元素
     * @return
     */
    public E removeLast(){
        return remove(size - 1);
    }


    /**
     * 删除指定的元素e,只删除一个
     * @param e
     */
    public void removeElement(E e){
        int index = find(e);
        if (index != -1){
            remove(index);
        }
    }

    public void swap(int i,int j){
        if (i < 0 || i >= size || j < 0 || j >= size){
            throw new IllegalArgumentException("index is illegal");
        }
        E tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Arrary: size = %d , capacity = %d\n",size,data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            //最后一个元素不需要加", "
            if (i != size - 1){
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }

    /**
     * 将数组空间的容量变成newCapacity大小
     * @param newCapacity
     */
    private void resize(int newCapacity){

        E[] newData = (E[])new Object[newCapacity];
        for(int i = 0 ; i < size ; i ++)
            newData[i] = data[i];
        data = newData;
    }
}
