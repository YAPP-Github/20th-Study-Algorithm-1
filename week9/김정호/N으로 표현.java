class Solution {

    static int min = 9;

    public int solution(int N, int number) {
        dfs(0, N, number, 0);

        if (min == 9) return -1;
        return min;
    }

    public void dfs(int depth, int N, int number, int current) {
        if (depth > 8) {
            return;
        }

        if (number == current) {
            min = Math.min(depth, min);
            return;
        }

        int temp = 0;

        for (int i = 0; i < 8; i++) {
            if (depth + i < 8) {
                temp = temp * 10 + N; // 자리수 늘리는 부분 (5, 55, 555)
                dfs(depth + i + 1, N, number, current + temp);
                dfs(depth + i + 1, N, number, current - temp);
                dfs(depth + i + 1, N, number, current / temp);
                dfs(depth + i + 1, N, number, current * temp);
            }
        }
    }
}