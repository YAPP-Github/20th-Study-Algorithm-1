import java.util.*;

public class 짝지어제거하기 {
    public int solution(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char input = s.charAt(i);

            if (stack.size() == 0) {
                stack.push(input);
                continue;
            }

            if (input == stack.peek()) stack.pop();
            else stack.push(input);
        }

        return stack.isEmpty() ? 1 : 0;
    }

}
