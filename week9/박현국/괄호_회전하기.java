package 박현국;

import java.util.*;

class 괄호_회전하기 {
    public int solution(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            if (isValid(s)) {
                answer++;
            }
            s = rotate(s);
        }

        return answer;
    }

    private String rotate(String s) {
        return s.substring(1, s.length()) + s.substring(0, 1);
    }

    private boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[' || s.charAt(i) == '{' || s.charAt(i) == '(') {
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == ']') {
                if (stack.isEmpty()) {
                    return false;
                }

                if (stack.pop() != '[') {
                    return false;
                }
            } else if (s.charAt(i) == '}') {
                if (stack.isEmpty()) {
                    return false;
                }

                if (stack.pop() != '{') {
                    return false;
                }
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    return false;
                }

                if (stack.pop() != '(') {
                    return false;
                }
            }
        }

        if (! stack.isEmpty()) {
            return false;
        }
        return true;
    }
}