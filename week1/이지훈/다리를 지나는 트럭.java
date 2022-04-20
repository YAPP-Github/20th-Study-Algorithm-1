import java.util.*;

public class 다리를지나는트럭 {

    private static class Truck{
        int move; // 트럭이 다리에서 이동한 거리
        int weight; // 트럭 무게

        public Truck(int weight) {
            this.move = 1;
            this.weight = weight;
        }
    }

    public int solution(int bridgeLen, int totalBridgeWeight, int[] truckWeights) {
        Queue<Truck> trucksWaiting = new LinkedList<>(); // 대기중인 트럭들
        Queue<Truck> trucksOnBridge = new LinkedList<>(); // 다리를 지나가는 트럭들

        // 트럭들 대기큐에 추가
        for (int weight : truckWeights) {
            trucksWaiting.add(new Truck(weight));
        }

        int nowBridgeWeight = 0; // 현재 다리위에 있는 트럭들 무게
        int result = 0; // 초

        while (true) {
            // 대기중인 트럭이 없고, 다리위에도 트럭이 없다면 무한루프 탈출 or 시간 초 증가
            if(trucksWaiting.isEmpty() && trucksOnBridge.isEmpty()) break;
            else result++;

            // 다리 비어있으면 트럭 추가
            if (trucksOnBridge.isEmpty()) {
                Truck truck = trucksWaiting.poll();
                trucksOnBridge.add(truck);
                nowBridgeWeight += truck.weight;
                continue;
            }

            // 다리위의 트럭 모두 한칸 이동
            for (Truck truck : trucksOnBridge) {
                truck.move++;
            }

            // 다리 제일 끝 트럭이 다리를 모두 통과한 경우
            if (trucksOnBridge.peek().move > bridgeLen) {
                Truck truck = trucksOnBridge.poll();
                nowBridgeWeight -= truck.weight;
            }

            // 무게를 초과하지 않으면 다음 트럭 추가
            if (isAddTruck(trucksWaiting, totalBridgeWeight, nowBridgeWeight)) {
                Truck truck = trucksWaiting.poll();
                trucksOnBridge.add(truck);
                nowBridgeWeight += truck.weight;
            }
        }

        return result;
    }

    // 다리에 트럭을 추가할 수 있는지 검사
    private static boolean isAddTruck(Queue<Truck> trucksWait, int totalBridgeWeight, int nowBridgeWeight){
        return !trucksWait.isEmpty() && totalBridgeWeight >= nowBridgeWeight + trucksWait.peek().weight;
    }

}
