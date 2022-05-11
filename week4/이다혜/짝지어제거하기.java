import java.util.Stack;

public class 짝지어제거하기 {

    public int solution(String s) {

        int answer = -1;

        Stack<Character> st = new Stack<>();
        int len = s.length();

        for(int i = 0; i < len; i++) {
            if(st.isEmpty()) st.push(s.charAt(i));
            else {
                if(st.peek() == s.charAt(i)) st.pop();
                else st.push(s.charAt(i));
            }
        }

        if(st.isEmpty()) answer = 1;
        else answer = 0;

        return answer;
    }

}