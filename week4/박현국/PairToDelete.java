package 박현국;

import java.util.*;

public class PairToDelete {
    public int solution(String s)
    {
        int idx = 0;
        Stack<Character> stack = new Stack<>();

        while (idx < s.length()) {
            if (!stack.empty() && stack.peek() == s.charAt(idx)) {
                stack.pop();
            } else {
                stack.push(s.charAt(idx));
            }
            idx++;
        }

        return stack.empty() ? 1 : 0;
    }

    // 스택 사용할 생각을 못해서 무식하게 풂..
    public int i_am_babo(String s)
    {
        if (s.length() % 2 == 1) {
            return 0;
        }

        int idx = 0;
        while (idx < s.length() - 1) {
            if (s.charAt(idx) == s.charAt(idx+1)) {
                String temp = "";
                if (idx + 2 < s.length()) {
                    temp = s.substring(idx+2);
                }
                s = s.substring(0, idx) + temp;
                idx--;
                if (idx < 0) {
                    idx = 0;
                }
            } else {
                idx++;
            }
        }

        return s.length() == 0 ? 1 : 0;
    }
}