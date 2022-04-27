import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public ArrayList<Integer> solution(int[] progresses, int[] speeds) {

        ArrayList<Integer> answer = new ArrayList<>();

        int n = progresses.length;
        int [] daysOfProgress = new int[n];
        
        for(int i = 0; i < n; i++) {
            int left = 100 - progresses[i];
            daysOfProgress[i] = (left % speeds[i] == 0 ? left / speeds[i] : left / speeds[i] + 1);
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if(q.isEmpty()) q.add(daysOfProgress[i]);
            else {
                if(q.peek() >= daysOfProgress[i]) q.add(daysOfProgress[i]);
                else {
                    answer.add(q.size());
                    q.clear();
                    q.add(daysOfProgress[i]);
                }
            }
        }

        if(!q.isEmpty()) answer.add(q.size());

        return answer;
    }
    
}
