import java.util.TreeMap;

/**
 * 默认Trie中每一个节点的存储只能是Character对象
 */
public class Trie {
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
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    /**
     * 向Trie中添加一个单词word
     * @param word
     */
    public void add(String word){
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
            //对应的size进行+1操作
            size ++;
        }


//        Node endNode = add(root,word,0);//递归习惯是进行添加
//        if (!endNode.isWord){
//            //若之前的isWord为false,则表明之前没有该单词,更改为true
//            endNode.isWord = true;
//            //对应的size进行+1操作
//            size ++;
//        }
    }

    /**
     * 递归的形式进行添加
     * @param node
     * @param word
     * @param i
     * @return
     */
    private Node add(Node node,String word,int i){
        if (i == word.length()){
            return node;
        }
        Node cNode = node.next.get(word.charAt(i));
        //判断当前节点中是否含有映射字符的节点
        if (cNode == null ){
            Node newNode = new Node();
            node.next.put(word.charAt(i),newNode);
            return add(newNode,word,i++);
        }else{
            //若存在,则在存在的节点后进行添加后一个字符的映射节点
            return add(cNode,word,i++);
        }
    }

    /**
     * 查看是否包含某一个单词word
     * @param word
     * @return
     */
    public boolean contains(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    /**
     * 查询是否存在Trie中有单词以prefix为前缀
     * @param prefix
     * @return
     */
    public boolean isPrefix(String prefix){
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }
}
