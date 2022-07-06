public class N으로표현 {
    private static int result = Integer.MAX_VALUE;

    public int solution(int N, int number) {
        dfs(number, N, 0, 0);

        if(result == Integer.MAX_VALUE) return -1;
        return result;
    }

    private static void dfs(int target, int N, int val, int depth){
        if(depth > 8){
            result = -1;
            return;
        }

        if(target == val){
            result = Math.min(result, depth);
            return;
        }

        int tmp = N;
        for (int i = 0; i < 8 - depth; i++) {
            dfs(target, N, val + tmp, depth + i + 1);
            dfs(target, N, val - tmp, depth + i + 1);
            dfs(target, N, val * tmp, depth + i + 1);
            dfs(target, N, val / tmp, depth + i + 1);

            tmp = tmp * 10 + N;
        }
    }

}
