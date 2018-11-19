import java.util.TreeMap;

public class WordDictionary {
    private class Node{
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }
        public Node(){
            this(false);
        }
    }

    private Node root;


    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            //判断当前节点是否包含c这个节点的映射
            if (cur.next.get(c) == null){
                //没有c节点的映射就对当前节点添加c的映射
                cur.next.put(c,new Node());
            }
            cur = cur.next.get(c);
        }
        //添加完成之后判断之前是否已经添加该单词
        if (!cur.isWord){
            //若之前的isWord为false,则表明之前没有该单词,更改为true
            cur.isWord = true;
        }
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(root,word,0);
    }

    private boolean match(Node node, String word, int index){
        if (index == word.length()){
            return node.isWord;
        }
        char c = word.charAt(index);
        if (c != '.'){
            if (node.next.get(c) == null){
                return false;
            }
            return match(node.next.get(c),word,index + 1);
        }else {
            //若等于.则需要对当前节点的映射集合进行一次循环,判断是存在满足的匹配
            for (char nextChar : node.next.keySet()){
                if (match(node.next.get(nextChar), word, index + 1)){
                    return true;
                }
            }
            return false;
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */