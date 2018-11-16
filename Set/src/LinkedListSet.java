public class LinkedListSet<E> implements Set  {

    private LinkedList<E> list;

    public LinkedListSet(){
        list = new LinkedList<>();
    }

    @Override
    public void add(Object o) {
        if (!list.contains((E) o)){
            list.addFirst((E) o);
        }
    }

    @Override
    public void remove(Object o) {
        list.removeElement((E) o);
    }

    @Override
    public boolean contains(Object o) {
        return list.contains((E) o);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
