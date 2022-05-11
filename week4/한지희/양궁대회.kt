class ArcheryGames {
    private lateinit var apeach: IntArray
    private lateinit var lion: IntArray
    private var answer = intArrayOf(-1)
    private var max = Int.MIN_VALUE
    private var remainArrow = 0

    fun solution(n: Int, info: IntArray): IntArray {
        apeach = info
        remainArrow = n
        lion = IntArray(11)
        dfs(0)
        return answer
    }

    private fun dfs(depth: Int) {
        if (depth == remainArrow) {
            val difScore = getLionApeachDiffScore()
            if (difScore > 0 && difScore >= max) {
                this.answer = lion.clone()
                max = difScore
            }
            return
        }

        for (i in 0..10) {
            if (lion[i] > apeach[i]) break
            lion[i]++
            dfs(depth + 1)
            lion[i]--
        }
    }

    private fun getLionApeachDiffScore(): Int {
        var apeachTotal = 0
        var lionTotal = 0
        for (i in 0..10) {
            if (apeach[i] != 0 || lion[i] != 0) {
                if (apeach[i] < lion[i]) lionTotal += 10 - i
                else apeachTotal += 10 - i
            }
        }
        return lionTotal - apeachTotal
    }
}