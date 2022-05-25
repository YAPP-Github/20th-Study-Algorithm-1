import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> data = new HashMap<>();

        for (String[] cloth : clothes) {
            String item = cloth[0];
            String type = cloth[1];

            int val = data.getOrDefault(type, 0);
            data.put(type, val + 1);
        }

        for (String key : data.keySet()) {
            answer *= data.get(key) + 1;
        }

        return answer - 1;
    }
}