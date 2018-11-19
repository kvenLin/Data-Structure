class NumArray2 {

    private SegmentTree<Integer> segmentTree;

    public NumArray2(int[] nums) {
        if (nums.length > 0){
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < data.length; i++) {
                data[i] = nums[i];
            }
            segmentTree = new SegmentTree<>(data,(a, b) -> a + b);
        }
    }

    public void update(int i, int j){
        if (segmentTree == null){
            throw new IllegalArgumentException("Segment tree is null");
        }
        segmentTree.set(i,j);
    }

    public int sumRange(int i, int j) {
        if (segmentTree == null){
            throw new IllegalArgumentException("Segment tree is null");
        }
        return segmentTree.query(i,j);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */