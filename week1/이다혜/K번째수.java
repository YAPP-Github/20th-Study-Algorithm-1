import java.util.Arrays;

public class 프로그래머스_K번째수 {
    public int[] solution(int[] array, int[][] commands) {
        int n = commands.length;
        int[] answer = new int[n];

        int idx = 0;
        for(int [] command : commands) {
            int s = command[0], e = command[1], k = command[2];

            int [] partialArr = new int[e-s+1];
            for(int i = s; i <= e; i++) {
                partialArr[i-s] = array[i-1];
            }

            Arrays.sort(partialArr);

            answer[idx++] = partialArr[k-1];
        }

        return answer;
    }
}
