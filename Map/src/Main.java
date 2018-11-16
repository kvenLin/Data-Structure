import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    private static double testMap(Map<String,Integer> map,String filename){
        long startTime = System.nanoTime();

        System.out.println("test the file :" + filename);

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words:" + words.size());
            for (String word : words) {
                if (map.contains(word)){
                    map.set(word,map.get(word) + 1);
                }else {
                    map.add(word,1);
                }
            }
            System.out.println("Total different words:" + map.getSize());
            System.out.println("hello of race:" + map.get("hello"));

        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        String filename = "/home/hk/testFile/test.txt";
        Map<String,Integer> bstMap = new BSTMap<>();
        double time1 = testMap(bstMap,filename);
        System.out.println("running BST map :" + time1 + "s");

        Map<String,Integer> listMap = new LinkedListMap<>();
        double time2 = testMap(listMap,filename);
        System.out.println("running BST map :" + time2 + "s");
    }
}
