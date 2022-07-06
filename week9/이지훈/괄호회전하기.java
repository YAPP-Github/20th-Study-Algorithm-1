import java.util.*;

public class 괄호회전하기 {
    private static int len;
    private static String str;

    public int solution(String s) {
        int result = 0;
        str = s;
        len = s.length();

        for (int i = 0; i < len - 1; i++) {
            if(isBalance()) result++;
            str = moveLeft();
        }

        return result;
    }

    private static boolean isBalance(){
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);

            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }

            if(stack.isEmpty() && (c == ']' || c == '}' || c == ')')) return false;

            if ((stack.peek() == '[' && c == ']') ||
                    (stack.peek() == '{' && c == '}') ||
                    (stack.peek() == '(' && c == ')')) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    private static String moveLeft(){
        return str.substring(1) + str.charAt(0);
    }

}

