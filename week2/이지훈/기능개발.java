import java.util.*;

public class 기능개발 {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        List<Integer> result = new ArrayList<>();
        int head = 0;

        while (true) {
            if(head >= progresses.length) break;

            for (int i = head; i < progresses.length; i++) {
                progresses[i] += speeds[i];
            }

            int day = 0;
            if (progresses[head] >= 100) {
                for (int i = head; i < progresses.length; i++) {
                    if(progresses[i] >= 100) {
                        day++;
                        head++;
                    } else break;
                }

                result.add(day);
            }
        }

        return result;
    }

}


public class 기능개발_큐 {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < progresses.length; i++) {
            int count = 0;

            while (progresses[i] < 100) {
                progresses[i] += speeds[i];
                count++;
            }
            q.add(count);
        }

        while (!q.isEmpty()) {
            int tmp = q.poll();
            int day = 1;

            while (true) {
                if (!q.isEmpty() && tmp >= q.peek()) {
                    q.poll();
                    day++;
                } else break;
            }

            result.add(day);
        }

        return result;
    }

}

public class 기능개발_스택 {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();

        for (int i = progresses.length - 1; i >= 0; i--) {
            int n = progresses[i];
            int count = 0;

            while (true) {
                n += speeds[i];
                count++;
                if (n >= 100) {
                    stack.add(count);
                    break;
                }
            }
        }

        while (!stack.isEmpty()) {
            int day = 1;
            int tmp = stack.pop();

            while (!stack.isEmpty() && stack.peek() <= tmp) {
                day++;
                stack.pop();
            }

            result.add(day);
        }

        return result;
    }

}