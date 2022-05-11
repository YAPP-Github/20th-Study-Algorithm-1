public class LV3 {

    private static int[] ryanInfos = new int[11];
    private static int[] apeachInfos;
    private static int[] result = new int[11];
    private static boolean isChampionTheRyan = false;
    private static int maxGap = 0;

    public int[] solution(int n, int[] info) {
        apeachInfos = info.clone();

        makeUpArrowsOfRyan(0, 0, n);

        return isChampionTheRyan ? result : new int[]{-1};
    }

    private static void makeUpArrowsOfRyan(int depth, int start, int arrowCnt) {
        if (depth == arrowCnt) {
            int ryanScore = 0;
            int apeachScore = 0;

            for (int i = 0; i <= 10; i++) {
                if(ryanInfos[i] == 0 && apeachInfos[i] == 0) continue;
                if(ryanInfos[i] > apeachInfos[i]) ryanScore += 10 - i;
                else apeachScore += 10 - i;
            }

            if (ryanScore > apeachScore) {
                isChampionTheRyan = true;
                resultSetting(ryanScore, apeachScore);
            }

            return;
        }

        for (int i = start; i < 11; i++) {
            ryanInfos[i]++;
            makeUpArrowsOfRyan(depth + 1, i, arrowCnt);
            ryanInfos[i]--;
        }
    }

    private static void resultSetting(int ryanScore, int apeachScore){
        int gap = ryanScore - apeachScore;

        if (gap > maxGap) { // 최대 점수 차이 갱신
            maxGap = gap;
            result = ryanInfos.clone();
        } else if (gap == maxGap) { // 점수 차이가 같은 경우, 가장 낮은 점수를 더 많이 맟힌 경우로 갱신
            for (int i = 10; i >= 0; i--) {
                if(ryanInfos[i] == result[i]) continue;
                else if(ryanInfos[i] < result[i]) return;
                else {
                    result = ryanInfos.clone();
                    return;
                }
            }
        }
    }

}
