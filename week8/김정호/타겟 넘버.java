class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();

        int[] numbers = new int[]{1,1,1,1,1};
        int target = 3;

        System.out.println(solution.solution(numbers, target));
    }

    static int answer = 0;
    public int solution(int[] numbers, int target) {
        DFS(numbers, 0, 0, target);

        return answer;
    }

    public static void DFS(int[] numbers, int depth, int sum, int target) {
        if (depth == numbers.length) {
            if(sum == target) {
                answer++;
            }
            return;
        }

        DFS(numbers,depth + 1, sum + numbers[depth], target);
        DFS(numbers,depth + 1, sum - numbers[depth], target);
    }
}