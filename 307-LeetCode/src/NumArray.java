class NumArray {

    private int[] sum;//sum[i]存储的是前i个元素的和sum[0] = 0
    //sum[i]存储nums[0...i-1]的和
    private int[] data;

    public NumArray(int[] nums) {
        data = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            data[i] = nums[i];
        }

        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }


    /**
     * 更新data数组中index位置的元素,同时更新sum数组中以index + 1以后的值
     * @param index
     * @param val
     */
    public void update(int index, int val) {
        data[index] = val;
        for (int i = index + 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + data[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        //sum[i]存储nums[0...i-1]的和,即nums[0...j + 1 -1] - nums[0...i-1] = nums[i...j]
        return sum[j + 1] - sum[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */