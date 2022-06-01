class Printer {
    fun solution(priorities: IntArray, location: Int): Int {
        var answer = 0
        val documents = mutableListOf<Pair<Int, Int>>()

        priorities.forEachIndexed { index, priority ->
            documents.add(Pair(index, priority))
        }

        while (documents.isNotEmpty()) {
            val current = documents.first()
            documents.removeFirst()

            if (documents.any { it.second > current.second }) documents.add(current)
            else {
                answer++
                if (location == current.first) break
            }
        }

        return answer
    }
}