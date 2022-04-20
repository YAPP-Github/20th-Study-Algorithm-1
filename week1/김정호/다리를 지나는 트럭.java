import java.io.IOException;
import java.util.*;

class Solution {
    public static void main(String[] args) throws IOException {
        // 테스트 코드
        Solution solution = new Solution();

        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = new int[]{7, 4, 5, 6};
        System.out.println(solution.solution(bridge_length, weight, truck_weights));
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<Integer>(); // 다리 위에 있는 트럭을 담기 위한 큐
        Queue<Integer> ready = new LinkedList<Integer>(); // 대기중인 트럭을 담기 위한 큐

        int answer = 0; // 소요 시간
        int nWeight = 0; // 전체 무게

        // 큐 초기화
        for (int i = 0; i < bridge_length; i++) bridge.add(0);
        for (int truck : truck_weights) ready.add(truck);

        // bridge 큐에 값이 있을 때만 반복문 실행
        while (!bridge.isEmpty()) {
            answer++;
            if(bridge.peek() != 0) { // 0이 아니면 실제 트럭이 빠져나왔다는 소리이므로
                nWeight -= bridge.peek(); // 전체 무게 - 빠져나간 트럭의 무게
            }

            bridge.poll(); // 큐 값 제거

            if (!ready.isEmpty()) { // ready 큐에 값이 있을 때만 bridge 큐에 값 추가
                if (nWeight + ready.peek() <= weight) { // 현재까지의 무게와 ready 큐에서 뺄 값의 합이 다리가 견딜 수 있는 무게보다 작으면
                    nWeight += ready.peek(); // ready 큐에서 뺄 값을 무게에 추가하고,
                    bridge.add(ready.poll()); // bridge 큐에 값 추가
                } else { // 무게보다 크면
                    bridge.add(0); // 0 추가
                }
            }
        }
        return answer;
    }
}