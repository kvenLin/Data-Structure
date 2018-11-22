import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String filename = "/home/hk/testFile/test.txt";
        long startTime = System.nanoTime();
        System.out.println("test the file :" + filename);

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words:" + words.size());
            HashTable<String,Integer> map = new HashTable<>(85);
            for (String word : words) {
                if (map.contains(word)){
                    map.set(word,map.get(word) + 1);
                }else {
                    map.add(word,1);
                }
            }
        }
        long endTime = System.nanoTime();
        System.out.println("HashTable need time: " + (endTime - startTime) / 1000000000.0);

        long startTime1 = System.nanoTime();

        ArrayList<String> words2 = new ArrayList<>();
        if (FileOperation.readFile(filename, words2)) {
            System.out.println("Total words:" + words2.size());
            HashTable<String,Integer> map = new HashTable<>();
            for (String word : words2) {
                if (map.contains(word)){
                    map.set(word,map.get(word) + 1);
                }else {
                    map.add(word,1);
                }
            }
        }
        long endTime1 = System.nanoTime();
        System.out.println("BST need time: " + (endTime1 - startTime1) / 1000000000.0);


    }
}
