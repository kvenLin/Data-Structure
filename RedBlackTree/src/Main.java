public class Main {
    public static void main(String[] args) {
        int sum = 1;
        System.out.println("final result : "+ getNum(2,sum) * 2);

    }
    private static double getNum(int n, double sum){
        if (n == 100) {
            return sum;
        }
        sum = sum * n / (double)(n - 1) * n / (double)(n + 1);
        n = n + 2;
        return getNum(n,sum);
    }
//    public static void main(String[] args) {
//        double sum = 1;
//        for (int n = 2, i  = 1; n <= 100; i++,n = 2 * i) {
//            sum *=  n / (double)(n - 1) * n / (double) (n + 1);
//        }
//        sum = sum*2;
//        System.out.println(sum);
//    }


}
