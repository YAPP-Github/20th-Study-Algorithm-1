import java.util.*

data class WeightDistance(val truckWeight: Int, var distance: Int)

class BridgeTruck {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        val readyTrucks = truck_weights.toMutableList()
        val bridgeTrucks = mutableListOf<WeightDistance>()
        var time = 0
        var currentWeight = 0
        var currentTrucksCount = 0

        while (readyTrucks.isNotEmpty() || bridgeTrucks.isNotEmpty()) {
            time++
            if (bridgeTrucks.isNotEmpty()) {
                bridgeTrucks.map { it.distance++ }
                if (bridgeTrucks[0].distance >= bridge_length) {
                    val removeTruck = bridgeTrucks.removeFirst()
                    currentWeight -= removeTruck.truckWeight
                    currentTrucksCount--
                }
            }

            if (readyTrucks.isNotEmpty()) {
                val firstTruck = readyTrucks[0]
                if (currentWeight + firstTruck <= weight && currentTrucksCount + 1 <= bridge_length) {
                    readyTrucks.removeFirst()
                    bridgeTrucks.add(WeightDistance(firstTruck, 0))
                    currentWeight += firstTruck
                    currentTrucksCount++
                }
            }
        }

        return time
    }

    // 다리의 길이만큼의 크기를 가진 큐 생성 -> 한 칸씩 빼고, 0을 넣으면서 앞으로 이동
    fun bestSolution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var answer = 0
        val bridgeQueue: Queue<Int> = LinkedList(List(bridge_length) { 0 })
        val waitingQueue: Queue<Int> = LinkedList(truck_weights.toList())

        while (bridgeQueue.isNotEmpty()) {
            answer++
            bridgeQueue.poll()
            if (waitingQueue.isNotEmpty()) {
                if (bridgeQueue.sum() + waitingQueue.peek() <= weight) {
                    bridgeQueue.add(waitingQueue.poll())
                } else {
                    bridgeQueue.add(0)
                }
            }
        }
        return answer
    }
}