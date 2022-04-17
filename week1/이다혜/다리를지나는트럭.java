import java.util.LinkedList;
import java.util.Queue;

public class 프로그래머스_다리를지나는트럭 {

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        int answer = 0; 
        int currentBridgeWeight = 0, currentTruckIdx=0;
        int lastTruckIdx = truck_weights.length;
        
        Queue<Integer> q = new LinkedList<>();           
        
        while(currentTruckIdx < lastTruckIdx){
            if(q.size() == bridge_length){
                currentBridgeWeight -= q.poll();                
            }

            else if(currentBridgeWeight + truck_weights[currentTruckIdx] > weight){
                q.offer(0);
                answer++;
            }else{
                q.offer(truck_weights[currentTruckIdx]);
                currentBridgeWeight += truck_weights[currentTruckIdx++];
                answer++;
            }            
        }

        return answer + bridge_length;
    }
    
}