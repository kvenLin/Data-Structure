import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class FileOperation {

    /**
     * 读取文件名为filename中的内容,并将七宗包含的所有词语放进words中
     * @param filename
     * @param words
     * @return
     */
    public static boolean readFile(String filename, ArrayList<String> words){
        if (filename == null || words == null){
            System.out.println("filename is null or words is null");
            return false;
        }
        //文件读取
        Scanner scanner;
        try {
            File file = new File(filename);
            if (file.exists()){
                FileInputStream inputStream = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(inputStream),"UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            }else {
                System.out.println("file not exist");
                return false;
            }
        }catch (IOException e){
            System.out.println("cannot open file:"+filename);
            return false;
        }

        if (scanner.hasNextLine()){
            String contents = scanner.useDelimiter("\\A").next();

            int start = firstCharacterIndex(contents,0);
            for (int i = start + 1; i <= contents.length(); ) {
                if (i == contents.length() || !Character.isLetter(contents.charAt(i))){
                    String word = contents.substring(start,i).toLowerCase();
                    words.add(word);
                    start = firstCharacterIndex(contents,i);
                    i = start + 1;
                }else {
                    i++;
                }
            }
        }
        return true;
    }

    /**
     * 寻找字符串s中,从start的位置开始的第一个字母字符的位置
     * @param s
     * @param start
     * @return
     */
    private static int firstCharacterIndex(String s, int start) {
        for (int i = start; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))){
                return i;
            }
        }
        return s.length();
    }
}
