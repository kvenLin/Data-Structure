public class UnionFind1 implements UF {

    private int[] id;

    public UnionFind1(int size){
        id = new int[size];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        if (find(p) == find(q)){
            return true;
        }
        return false;
    }

    /**
     * 合并元素p和元素q的集合
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if (pID == qID){
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID){
                id[i] = qID;
            }
        }
    }

    //查询元素p对应的集合的编号
    private int find(int p){
        if (p < 0 || p > id.length){
            throw new IllegalArgumentException("p is out of bound.");
        }
        return id[p];
    }
}
