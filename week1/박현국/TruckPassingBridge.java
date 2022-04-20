package 박현국;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 감 잡기용으로 객체지향적으로 풀어보긴 했는데,
 * 아마 실전에선 이렇게 못 풀듯..? 효율도 좋은 편은 아님..
 */
public class TruckPassingBridge {
    public static void main(String[] args) {
        System.out.println(solution(2, 10, new int[] {7, 4, 5, 6})); // 8
        System.out.println(solution(100, 100, new int[] {10})); // 101
    }

    public static int solution(int bridgeLength, int weight, int[] truckWeights) {
        int answer = 0;
        int truckIdx = 0;
        BridgeStatus bridgeStatus = new BridgeStatus(weight);

        while (truckIdx < truckWeights.length || bridgeStatus.getCurrentBridgeCount() > 0) {
            if (truckIdx < truckWeights.length && bridgeStatus.isInsertable(truckWeights[truckIdx])) {
                bridgeStatus.push(truckWeights[truckIdx]);
                truckIdx++;
            }

            bridgeStatus.increaseTime();
            if (bridgeStatus.getOldestTime() > bridgeLength) {
                bridgeStatus.pop();
            }

            answer++;
        }

        return answer + 1; // 다리에서 모두 지난 시간
    }

    public static class BridgeStatus {
        final int weight;
        int bridgeWeightSum = 0;
        Queue<Integer> currentBridge = new LinkedList<>();
        List<Integer> elapsedTime = new LinkedList<>();

        BridgeStatus(int weight) {
            this.weight = weight;
        }

        int getCurrentBridgeCount() {
            return this.currentBridge.size();
        }

        boolean isInsertable(int currentWeight) {
            return currentWeight + bridgeWeightSum <= this.weight;
        }

        void increaseTime() {
             for (int i = 0 ; i < elapsedTime.size(); i++) {
                 elapsedTime.set(i, elapsedTime.get(i) + 1);
             }
        }

        int getOldestTime() {
            return elapsedTime.get(0);
        }

        void push(Integer num) {
            currentBridge.add(num);
            elapsedTime.add(1);
            bridgeWeightSum += num;
        }

        void pop() {
            int ret = currentBridge.remove();
            bridgeWeightSum -= ret;
            elapsedTime.remove(0);
        }
    }
}
