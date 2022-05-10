import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();

        int n = 5;
        int[] info = new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(Arrays.toString(solution.solution(n, info)));
    }

    // a가 b보다 더 좋은 배치일 경우 true
    public boolean cmp(int[] a, int[] b) {
        for (int i = 11; i >= 0; i--)
            if (a[i] != b[i]) return a[i] > b[i];
        return false;
    }


    public int[] solution(int n, int[] info) {
        int[] mn = new int[12];
        Arrays.fill(mn, -1);


        for (int brute = 0; brute < 1024; brute++) {
            int[] arrow = new int[12];
            int score = 0;
            int left = n; // 남아있는 화살의 수
            for (int i = 0; i < 10; i++) {
                if ((brute & (1 << i)) != 0) { // i번째 비트 ON
                    score += (10 - i);
                    left -= (info[i] + 1);
                    arrow[i] = info[i] + 1;
                } else if (info[i] != 0)
                    score -= (10 - i);
            }

            if (score <= 0 || left < 0) continue;
            arrow[10] = left;
            arrow[11] = score;

            if (cmp(arrow, mn)) {
                for (int i = 0; i < 12; i++)
                    mn[i] = arrow[i];
            }
        }
        if (mn[0] == -1) {
            int[] ret = new int[1];
            ret[0] = -1;
            return ret;
        }
        int[] ret = new int[11];
        for (int i = 0; i < 11; i++)
            ret[i] = mn[i];
        return ret;
    }
}
