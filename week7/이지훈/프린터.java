import java.util.*;

public class 프린터 {

    private static PriorityQueue<Integer> printer = new PriorityQueue<>(Collections.reverseOrder());

    public int solution(int[] priorities, int location) {
        int result = 1;

        for (int num : priorities) {
            printer.offer(num);
        }

        while (!printer.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                if (isPrint(i, priorities)) {

                    if (isTarget(i, location)) return result;

                    result++;
                    printer.poll();
                }
            }
        }

        return result;
    }

    private boolean isPrint(int now, int[] priorities) {
        return priorities[now] == printer.peek();
    }

    private boolean isTarget(int now, int location) {
        return location == now;
    }

}