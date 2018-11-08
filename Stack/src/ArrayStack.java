public class ArrayStack<E> implements Stack{

    Array<E> array;

    /**
     * 创建栈的数组,传入指定的空间大小
     * @param capacity
     */
    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayStack() {
        array = new Array<>();
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 进栈
     * @param e
     */
    @Override
    public void push(Object e) {
        array.addLast((E) e);
    }

    /**
     * 出栈
     * @return
     */
    @Override
    public E pop() {
        return array.removeLast();
    }

    /**
     * 查看栈顶元素
     * @return
     */
    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack : ");
        res.append('[');
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1){
                res.append(",");
            }
        }
        res.append(" ] top ");
        return res.toString();
    }
}
