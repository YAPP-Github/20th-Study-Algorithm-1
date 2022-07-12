import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        for (int i = 0; i < citations.length; i++) {
            int h = citations.length - i;
            int num = citations[i];
            if (num >= h) {
                return h;
            }
        }

        return answer;
    }
}