import java.util.Stack;

class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();

        String s = "";
        System.out.println(solution.solution(s));
    }


    public int solution(String s)
    {

        Stack<Character> box = new Stack<>();

        if(s.length() == 0) return 1;

        for(int i = 0; i < s.length(); i++) {
            if(!box.isEmpty()) {
                if(box.peek() == s.charAt(i)) {
                    box.pop();
                } else {
                    box.push(s.charAt(i));
                }
            } else {
                box.push(s.charAt(i));
            }
        }

        return box.isEmpty() ? 1 : 0;
    }
}