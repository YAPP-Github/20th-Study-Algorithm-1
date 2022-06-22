class BurstBalloons {
    fun solution(a: IntArray): Int {
        var answer = 0
        var leftMin = Int.MAX_VALUE
        var rightMin = Int.MAX_VALUE

        for (i in a.indices) {
            if (a[i] < leftMin) {
                answer++
                leftMin = a[i]
            }

            if (a[a.size - 1 - i] < rightMin) {
                answer++
                rightMin = a[a.size - 1 - i]
            }

            if (leftMin == rightMin) break
        }

        return if (leftMin == rightMin) answer -1 else answer
    }
}