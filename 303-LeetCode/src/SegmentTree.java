public class SegmentTree<E> {

    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0,0,data.length - 1);
    }

    /**
     * 在treeIndex位置创建标识区间[l...r]的线段树
     * @param treeIndex
     * @param l
     * @param r
     */
    private void buildSegmentTree(int treeIndex,int l,int r) {
        //区间长度为1时
        if(l == r){
            tree[treeIndex] = data[l];
            return;
        }
        //1.先创建好这个节点的左右子树
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;//为避免整型溢出,等同于(l + r)/2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex,mid + 1, r);
        //定义两个元素不一定是数字,所以需要根据自定义实现的merger接口来进行融合
        tree[treeIndex] = merger.merger(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    public E get(int index){
        if (index < 0 || index >= data.length){
            throw new IllegalArgumentException("Index is illegal");
        }
        return data[index];
    }

    public int getSize(){
        return data.length;
    }

    /**
     * 左孩子所在的索引
     * @param index
     * @return
     */
    private int leftChild(int index){
        return 2 * index + 1;
    }

    /**
     * 右孩子所在的索引
     * @param index
     * @return
     */
    private int rightChild(int index){
        return 2 * index + 2;
    }

    /**
     * 返回[queryL, queryR]区间的值
     * @param queryL
     * @param queryR
     * @return
     */
    public E query(int queryL,int queryR){
        if (queryL < 0 || queryL > data.length ||
                queryR < 0 || queryR > data.length || queryL > queryR){
            throw new IllegalArgumentException("Index is illegal");
        }
        return query(0,0,data.length - 1,queryL,queryR);
    }


    /**
     * 在以treeIndex为根的线段树data[l...r]的范围里,搜索区间data[queryL...queryR]的值
     * @param treeIndex
     * @param l
     * @param r
     * @param queryL
     * @param queryR
     * @return
     */
    private E query(int treeIndex,int l, int r,int queryL,int queryR){
        if (l == queryL && r == queryR){
            return tree[treeIndex];
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        //查询的结果都在有子树中
        if (queryL >= mid + 1){
            return query(rightTreeIndex,mid + 1, r, queryL, queryR);
        }else if (queryR <= mid){
            //查询结果都在左子树中
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }
        //若有一部分在左子树,有一部分在右子树
        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex,mid + 1, r, mid + 1,queryR);
        return merger.merger(leftResult,rightResult);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null){
                res.append(tree[i]);
            }else {
                res.append("null");
            }
            if (i != tree.length - 1){
                res.append(',');
            }else {
                res.append(']');
            }
        }
        return res.toString();
    }

    /**
     * 将index位置的值更新为e
     * @param index
     * @param e
     */
    public void set(int index,E e){
        if (index < 0 || index > data.length){
            throw new IllegalArgumentException("Index is illegal");
        }
        data[index] = e;
        set(0,0,data.length,index,e);
    }

    /**
     * 更新以treeIndex为根节点的线段树中data[l...r]的范围里,位置为index的元素为e
     * @param treeIndex
     * @param l
     * @param r
     * @param index
     * @param e
     */
    private void set(int treeIndex, int l, int r, int index, E e){
        if (l == r){
            tree[treeIndex] = e;
            return;
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (index <= mid){
            set(leftTreeIndex, l, mid, index, e);
        } else {
            set(rightTreeIndex, mid + 1, r, index, e);
        }
        //对更新后的子节点进行重新的merger操作
        tree[treeIndex] = merger.merger(tree[leftTreeIndex],tree[rightTreeIndex]);
    }
}
