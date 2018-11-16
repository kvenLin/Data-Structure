import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("test the file ");

        ArrayList<String> words1 = new ArrayList<>();
        FileOperation.readFile("/home/hk/testFile/test.txt",words1);
        System.out.println("Total words:" + words1.size());

    }
}
