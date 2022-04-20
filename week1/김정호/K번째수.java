import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(new int[]{1,5,2,6,3,7,4},new int[][]{{2,5,3}, {4,4,1}, {1,7,3}})));
    }

    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for(int i = 0; i < commands.length; i++) {
            int startSlice = commands[i][0] - 1; // 시작잠
            int endSlice = commands[i][1]; // 종료점
            int pick = commands[i][2]; // 선택한 수
            int[] tmp = new int[endSlice - startSlice];
            int count = 0;
            for(int j = startSlice; j < endSlice; j++) {
                tmp[count] = array[j];
                count++;
            }

            // 오름차순 정렬
            for(int k = 0; k < tmp.length; k++) {
                for(int l = k+1; l < tmp.length; l++) {
                    if(tmp[k] > tmp[l]) {
                        int tp = tmp[k];
                        tmp[k] = tmp[l];
                        tmp[l] = tp;
                    }
                }
            }
            // Arrays.sort(tmp);

            answer[i] = tmp[pick - 1];
        }

        return answer;
    }
}