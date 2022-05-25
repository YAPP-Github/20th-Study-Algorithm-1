import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();

        String[][] clothes = new String[][]{{"yellowhat", "headgear"},
                {"bluesunglasses", "eyewear"},
                {"green_turban", "headgear"},
                {"smoky_makeup", "face"}};

        System.out.println(solution.solution(clothes));
    }

    public int solution(String[][] clothes) {
        int answer = 1;

        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();

        for (int i = 0; i < clothes.length; i++) {
            String key = clothes[i][1]; // 종류
            String value = clothes[i][0]; // 옷

            if(hashMap.containsKey(key)) {
                hashMap.get(key).add(value);
            } else {
                ArrayList<String> arr = new ArrayList<>();
                arr.add(value);
                hashMap.put(key, arr);
            }
        }

        String[] keyArray = hashMap.keySet().toArray(new String[0]);

        for(int i = 0; i < hashMap.size(); i++) {
            answer *= hashMap.get(keyArray[i]).size() + 1;
        }

        // 아무 것도 입지 않은 경우가 있으니 - 1
        return answer - 1;
    }
}
