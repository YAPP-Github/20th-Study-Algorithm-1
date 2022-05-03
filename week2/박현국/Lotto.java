package 박현국;

import java.util.*;

public class Lotto {
    public int[] solution(int[] lottos, int[] winNums) {
        int[] answer = new int[2];
        int hitCount = 0;
        int unknownCount = 0;
        Set<Integer> lottoSet = new HashSet<>();

        // lottos iterate
        for (int lotto : lottos) {
            if (lotto == 0) {
                unknownCount++;
                continue;
            }

            lottoSet.add(lotto);
        }

        // winNums iterate
        for (int winNum : winNums) {
            if (lottoSet.contains(winNum)) {
                hitCount++;
            }
        }

        answer[0] = Math.min(7 - (hitCount+unknownCount), 6);
        answer[1] = Math.min(7 - hitCount, 6);
        return answer;
    }
}
