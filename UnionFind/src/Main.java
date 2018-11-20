import java.util.Random;

public class Main {

    /**
     * 测试并查集
     * @param uf
     * @param m
     * @return
     */
    private static double testUF(UF uf, int m){
        int size = uf.getSize();
        Random random = new Random();
        long startTime = System.nanoTime();
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a,b);
        }
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a,b);
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        //设置并查集的大小为10000
        int size = 10000000;
        //设置对并查集的操作为10000
        int m = 10000000;
//        double time1 = testUF(new UnionFind1(size), m);
//        double time2 = testUF(new UnionFind2(size), m);
        double time3 = testUF(new UnionFind3(size), m);
        double time4 = testUF(new UnionFind4(size), m);
        double time5 = testUF(new UnionFind5(size), m);
        double time6 = testUF(new UnionFind6(size), m);


//        System.out.println("UF1 need time :" + time1 + "s");
//        System.out.println("UF2 need time :" + time2 + "s");
        System.out.println("UF3 need time :" + time3 + "s");
        System.out.println("UF3 need time :" + time4 + "s");
        System.out.println("UF3 need time :" + time5 + "s");
        System.out.println("UF3 need time :" + time6 + "s");
    }
}
