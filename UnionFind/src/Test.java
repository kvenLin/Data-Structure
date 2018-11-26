public class Test {
    public static void main(String[] args) {
        int num = 0;//num<100000
        int q = 1;
        while (num < 100000){
            q ++;
            num  = q * q - 100;
            int q2 = (int) Math.sqrt(num + 100 + 168);
            if ( q2 * q2 - 100 - 168 == num){
                System.out.println(num);
            }

        }
    }
}
