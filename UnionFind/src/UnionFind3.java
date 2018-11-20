public class UnionFind3 implements UF {
    private int[] parent;
    private int[] sz;// sz[i] 表示以i为根的集合中元素的个数

    public UnionFind3(int size){
        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            //初始化时每个每个节点只有一个元素
            sz[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    /**
     * 查找过程,查找元素p所对应的集合编号即根节点
     * O(h)复杂度,h为树的高度
     * @param p
     * @return
     */
    private int find(int p){
        if (p < 0 || p > parent.length){
            throw new IllegalArgumentException("p is out of bound");
        }
        while (p != parent[p]){
            p = parent[p];
        }
        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 合并元素p和元素q所属的集合
     * O(h)复杂度,h为树的高度
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (qRoot == pRoot){
            return;
        }
        //让元素个数比较少的根节点去指向元素个数比较多的根节点
        if (sz[pRoot] < sz[qRoot]){
            parent[pRoot] = qRoot;
            //更新一下qRoot这棵数的元素个数的值
            sz[qRoot] += sz[pRoot];
        }else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
}
