import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max

class TheFarthestNode {
    private lateinit var isVisited: BooleanArray
    private lateinit var graph: Array<ArrayList<Int>>
    private lateinit var distance: IntArray

    fun solution(n: Int, edge: Array<IntArray>): Int {
        var answer = 0
        graph = Array(n + 1) { ArrayList() }
        isVisited = BooleanArray(n + 1)
        distance = IntArray(n + 1)

        edge.forEach { ints ->
            graph[ints[0]].add(ints[1])
            graph[ints[1]].add(ints[0])
        }

        bfs(1)

        var maxCount = 0
        for (i in 1..n) maxCount = max(maxCount, distance[i])
        for (i in 1..n) if (distance[i] == maxCount) answer++

        return answer
    }

    private fun bfs(idx: Int) {
        val queue: Queue<Int> = LinkedList()
        isVisited[idx] = true
        queue.offer(idx)

        while (queue.isNotEmpty()) {
            val now = queue.poll()
            for (i in graph[now].indices) {
                val next = graph[now][i]
                if (!isVisited[next]) {
                    distance[next] = distance[now] + 1
                    isVisited[next] = true
                    queue.offer(next)
                }
            }
        }
    }
}