package 박현국;

public class TargetNumber {
    int[] numbers;
    int target;

    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        return dfs(0, 0);
    }

    int dfs(int depth, int sum) {
        if (depth == this.numbers.length) {
            if (sum == this.target) {
                return 1;
            }
            return 0;
        }

        return dfs(depth+1, sum + this.numbers[depth]) +
                dfs(depth+1, sum - this.numbers[depth]);
    }
}
