import java.util.*;

public class K번째수 {
    public int[] solution(int[] array, int[][] commands) {
        int[] result = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            List<Integer> list = new ArrayList<>();
            int start = commands[i][0] - 1;
            int end = commands[i][1] - 1;
            int k = commands[i][2] - 1;

            for (int j = start; j <= end; j++) {
                list.add(array[j]);
            }

            Collections.sort(list);
            result[i] = list.get(k);
        }

        return result;
    }

}