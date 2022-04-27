import java.util.HashSet;
import java.util.Set;

public class Solution {
    
    public int[] solution(int[] lottos, int[] win_nums) {
        int [] answer = new int[2];

        Set<Integer> numberGroup = new HashSet<>();
        for(int num : win_nums) numberGroup.add(num);
        int cntOfZero = 0, cntOfCurrentWins = 0;
        for(int i = 0; i < 6; i++) {
            if(lottos[i] == 0) {
                cntOfZero++;
            }

            if(numberGroup.contains(lottos[i])) cntOfCurrentWins++;
        }

        int maxWinRank = (cntOfCurrentWins+cntOfZero <= 1 ? 6 : Math.abs(cntOfCurrentWins + cntOfZero - 6) + 1);
        int minWinRank = cntOfCurrentWins <= 1 ? 6 : Math.abs(cntOfCurrentWins - 6) + 1;

        answer[0] = maxWinRank;
        answer[1] = minWinRank;

        return answer;
    }
}
