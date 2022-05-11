package 박현국;

import java.util.Arrays;

public class Archery { // 테스트 케이스 18번만이 실패함.. 왜지??
    int maxScore = 0;
    int[] answer;

    public int[] solution(int n, int[] apeach) {
        int[] ryan = new int[11];
        dfs(0, n, apeach, ryan);
        return maxScore != 0 ? answer : new int[] { -1 };
    }

    void dfs(int depth, int arrowCount, int[] apeach, int[] ryan) {
        if (depth == 10) {
            // 최대 점수 계산
            ryan[10] += arrowCount;
            int score = calcScore(ryan, apeach);
            if (maxScore <= score) {
                maxScore = score;
                answer = ryan.clone();
            }
            ryan[10] -= arrowCount;
            return;
        }

        // 현재 depth에서 득점
        if (arrowCount > 0) {
            ryan[depth] += 1;
            dfs(depth, arrowCount - 1, apeach, ryan);
            ryan[depth] -= 1;
        }

        // 현재 depth에서 미득점
        dfs(depth+1, arrowCount, apeach, ryan);
    }

    int calcScore(int[] ryan, int[] apeach) {
        int ret = 0;
        for(int i = 0 ; i < 11; i++) {
            if (ryan[i] > apeach[i]) {
                ret += (10 - i);
            } else if (apeach[i] != 0) {
                ret -= (10 - i);
            }
        }
        return ret;
    }
}
