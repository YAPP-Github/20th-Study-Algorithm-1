import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();

        int[] lottos = new int[]{44, 1, 0, 0, 31, 25};
        int[] win_nums = new int[]{31, 10, 45, 1, 6, 19};

        System.out.println(Arrays.toString(solution.solution(lottos, win_nums)));
    }

    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int correct = 0; // 맞춘 번호
        int zeroCount = 0; // 알 수 없는 번호 카운팅

        for (int lotto : lottos) {
            if (lotto == 0) {
                zeroCount++;
                continue; // 0이면 제외
            }

            for (int win : win_nums) {
                if (lotto == win) { // 번호가 같을 경우
                    correct++;
                    break; // 찾으면 바로 배열을 빠져나옴
                }
            }
        }

        if (correct == 0) { // 맞는 숫자가 없을 때
            answer[1] = 6; // 최소값은 무조건 6
            answer[0] = zeroCount == 6 ? 1 : 6; // 알 수 없는 숫자 조건문
        } else {
            answer[0] = 6 - (correct + zeroCount - 1);
            answer[1] = 6 - (correct - 1);
        }

//         int max = correct + zeroCount;
//         int min = correct;

//         answer = new int[]{Math.min(7 - max, 6), Math.min(7 - min, 6)};

        return answer;
    }
}