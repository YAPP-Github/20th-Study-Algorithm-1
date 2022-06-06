public class 타겟 넘버 {
    private static int result;

    public int solution(int[] numbers, int target) {
        dfs(0, target, numbers);

        return result;
    }

    private void dfs(int depth, int target, int[] numbers) {
        if (depth == numbers.length) {
            if (isTarget(numbers, target)) {
                result++;
            }

            return;
        }

        dfs(depth + 1, target, numbers);

        numbers[depth] *= -1;
        dfs(depth + 1, target, numbers);
        numbers[depth] *= -1;
    }

    private static boolean isTarget(int[] numbers, int target) {
        int sum = 0;

        for (int num : numbers) {
            sum += num;
        }

        if (sum == target) return true;

        return false;
    }

}
