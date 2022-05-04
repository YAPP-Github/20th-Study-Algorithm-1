import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {

    public Map<String, List<Integer> > scores = new HashMap<>();
    public int[] solution(String[] info, String[] query) {

        
        int n = query.length;
        int[] answer = new int[n];

        scores.put("", new ArrayList<>());

        for(String information : info) {
            String [] infos = information.split(" ");
            int m = infos.length;
            recursiveSearch(0, 0, m, infos, Integer.parseInt(infos[m-1]), new ArrayList<>());
        }

        Set<String> keys = scores.keySet();
        for(String key : keys) {
            // System.out.println("key: " + key);
            Collections.sort(scores.get(key));
        }

        for(int idx = 0; idx < n; idx++) {
            String [] condition = query[idx].split(" and ");
            int cLen = condition.length;
            String [] soulFoodAndScore = condition[cLen-1].split(" ");
            condition[cLen-1] = soulFoodAndScore[0];

            StringBuilder sb = new StringBuilder();
            for(String c : condition) {
                if(!c.equals("-")) sb.append(c);
            }
            String command = sb.toString();

            // System.out.println("command: " + command);

            if(scores.containsKey(command)) {
                List<Integer> participants = scores.get(command);
                int score = Integer.parseInt(soulFoodAndScore[1]);

                int numberOfParticipants = participants.size();
                int lastIdx = findLastSmallIndex(0, numberOfParticipants-1, participants, score);
                // System.out.println(lastIdx);
                answer[idx] = numberOfParticipants - lastIdx;
            }
        }

        return answer;
    }

    public void recursiveSearch(int idx, int cnt, int m, String[] infos, int score, List<String> temp) {
        if(cnt >= m) return ;

        StringBuilder sb = new StringBuilder();
        for(String s : temp) sb.append(s);

        if(!scores.containsKey(sb.toString())) scores.put(sb.toString(), new ArrayList<>());
        scores.get(sb.toString()).add(score);

        for(int i = idx; i < m-1; i++) {
            if(!temp.contains(infos[i])) {
                temp.add(infos[i]);
                recursiveSearch(idx+1, cnt+1, m, infos, score, temp);
                temp.remove(temp.size()-1);
            }
        }
    }

    public int findLastSmallIndex(int start, int end, List<Integer> participants, int score) {
        while(start <= end) {
            int mid = (start + end) / 2;

            if(participants.get(mid) >= score) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }

        return start;
    }
}