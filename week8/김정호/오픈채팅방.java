import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();

        String[] record = new String[]{"Enter uid1234 Muzi",
                "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan", "Leave uid1234"};

        System.out.println(Arrays.toString(solution.solution(record)));
    }

    public String[] solution(String[] record) {

        HashMap<String, String> hs = new HashMap<>();
        ArrayList<String[]> map = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();

        for(int i = 0; i < record.length; i++) {
            String[] records = record[i].split(" ");

            String behavior = records[0];
            String user_id = records[1];
            String user_nickname = null;

            if(records.length == 3) {
                user_nickname = records[2];
            }

            switch (behavior) {
                case "Enter" ->
                    hs.put(user_id, user_nickname);
                case "Change" ->
                    hs.replace(user_id, user_nickname);
            }

            String[] tmp = new String[2];
            tmp[0] = behavior;
            tmp[1] = user_id;

            map.add(tmp);
        }

        for(int i = 0; i < map.size(); i++) {
            String behavior = map.get(i)[0];
            String user_id = map.get(i)[1];

            switch (behavior) {
                case "Enter" -> {
                    result.add(hs.get(user_id) + "님이 들어왔습니다.");
                }
                case "Leave" -> {
                    result.add(hs.get(user_id) + "님이 나갔습니다.");
                }
            }
        }

        return result.toArray(new String[0]);
    }
}