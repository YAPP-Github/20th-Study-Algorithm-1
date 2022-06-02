import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int answer = 1;

        for (int i = 0; i < s.length(); i++) {
            // 홀수인 경우
            int offSet = 1;
            int temp = 1;

            while (i - offSet >= 0 && i + offSet < s.length()) {
                if (s.charAt(i - offSet) == s.charAt(i + offSet)) {
                    offSet++;
                    temp += 2;
                } else {
                    break;
                }
            }

            answer = Math.max(answer, temp);

            // 짝수인 경우
            offSet = 0;
            temp = 0;

            while (i - offSet >= 0 && i + offSet + 1 < s.length()) {
                if (s.charAt(i - offSet) == s.charAt(i + offSet + 1)) {
                    offSet++;
                    temp += 2;
                } else {
                    break;
                }
            }

            answer = Math.max(answer, temp);
        }

        return answer;
    }
}