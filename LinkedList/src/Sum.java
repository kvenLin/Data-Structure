public class Sum {

    public static int sum(int[] arr){
        return sum(arr,0);
    }

    /**
     * 计算arr[l...n]这个区间内所有数字的和
     * @param arr
     * @param l
     * @return
     */
    private static int sum (int[] arr,int l){
        //数组最后的索引为arr.length - 1,当l == arr.length时,说明已计算完毕
        if (l == arr.length){
            return 0;
        }
        return arr[l] + sum(arr,l + 1);
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8};
        System.out.println(sum(arr));
    }
}
