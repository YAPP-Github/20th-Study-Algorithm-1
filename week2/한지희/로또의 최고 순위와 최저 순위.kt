package programmers.week2

class LottoBestWorst {
    fun solution(lottos: IntArray, win_nums: IntArray): IntArray {
        var zeroCount = 0
        var sameCount = 0

        lottos.forEach { lotto ->
            if (lotto == 0) {
                zeroCount++
            } else {
                for (win in win_nums) {
                    if (win == lotto) {
                        sameCount++
                        break
                    }
                }
            }
        }

        return intArrayOf(getRank(sameCount + zeroCount), getRank(sameCount))
    }

    private fun getRank(sameCount: Int): Int {
        var rank = 7 - sameCount
        if (sameCount < 2) rank = 6
        return rank
    }

    fun bestSolution(lottos: IntArray, winNums: IntArray): IntArray {
        return intArrayOf(
            (lottos.size.plus(1)) - lottos.filter { winNums.contains(it) || it == 0 }.size,
            (lottos.size.plus(1)) - lottos.filter(winNums::contains).size
        ).map { if (it > 6) it - 1 else it }.toIntArray()
    }
}