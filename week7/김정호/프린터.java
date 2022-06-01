import java.util.*;

class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();

        int[] priorities = new int[]{1,1,1,4,1,9,1};
        int location = 1;

        System.out.println(solution.solution(priorities, location));
    }
    public int solution(int[] priorities, int location) {
        int answer = 0;
        ArrayList<Integer> index = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        // 인덱스를 큐에 삽입
        for (int i = 0; i < priorities.length; i++) {
            queue.add(i);
        }

        while(!queue.isEmpty()) {
            // 가장 크면 큐에서 빼고 해당 값의 인덱스 기록
            if (isMax(queue, priorities)) {
                index.add(queue.poll());
            }
            // 크지 않으면 빼고 다시 삽입
            else {
                queue.add(queue.poll());
            }
        }

        for (int i = 0; i < index.size(); i++) {
            if (location == index.get(i))
                answer = i + 1;
        }
        return answer;
    }

    // 배열 탐색 후 가장 큰 값인지 확인
    public boolean isMax(Queue<Integer> queue, int[] priorities) {
        Queue<Integer> temp = new LinkedList<>(queue);
        int max = priorities[temp.poll()]; // 현재 큐에서 가장 앞에 있는 값
        while(!temp.isEmpty()) {
            if (priorities[temp.peek()] > max) // 가장 큰 값인지 확인
                return false;
            temp.poll();
        }
        return true;
    }
}