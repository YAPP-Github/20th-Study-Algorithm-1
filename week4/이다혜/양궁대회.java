public class 양궁대회 {

    public int[] lion;
    public int max = -1;
    public int [] res = {-1, };
    public int[] solution(int n, int[] info) {

        lion = new int[11];

        recursiveSearch(n, info, 0);

        return res;
    }

    public void recursiveSearch(int n, int [] info, int cnt) {

        if(cnt == n) {
            int lionScore = 0, apeachScore = 0;
            for(int i = 0; i <= 10; i++) {
                if(info[i] > 0 || lion[i] > 0) {
                    if(lion[i] > info[i]) {
                        lionScore += 10 - i;
                    } else apeachScore += 10 - i;
                }
            }

            if(lionScore > apeachScore) {
                if(lionScore - apeachScore >= max) {
                    res = lion.clone();
                    max = lionScore - apeachScore;
                }
            }

            return ;
        }

        for(int i = 0; i <= 10 && lion[i] <= info[i]; i++) {
            lion[i]++;
            recursiveSearch(n, info, cnt+1);
            lion[i]--;
        }
    }
}