import java.util.Stack;

public class Solution {

    /**
     * 括号匹配
     * @param s
     * @return
     */
    public boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        //ArrayStack<Character> stack = new ArrayStack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[' || c == '{' || c == '('){
                stack.push(c);
            }else {
                if (stack.isEmpty()){
                    return false;
                }
                char topChar = stack.pop();
                if (c == ')' && topChar != '('){
                    return false;
                }
                if (c == ']' && topChar != '['){
                    return false;
                }
                if (c == '}' && topChar != '{'){
                    return false;
                }
            }
        }
        //最后对栈查看是否为空,若不为空说明扩号不完全匹配,有未进行匹配括号
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isValid("([{}])"));
    }
}
