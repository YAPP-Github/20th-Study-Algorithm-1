package 박현국;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class KthNum {
    public int[] solution(int[] array, int[][] commands) {
        List<Integer> answer = new ArrayList<>();
        for (int[] command : commands) {
            int i = command[0];
            int j = command[1];
            int k = command[2];

            int[] slice = Arrays.copyOfRange(array, i-1, j);
            Arrays.sort(slice);
            answer.add(slice[k-1]);
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
