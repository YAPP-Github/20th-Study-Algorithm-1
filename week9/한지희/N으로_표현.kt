import kotlin.math.min

class ExpressInN {
    var target = 0
    var n = 0
    var answer = Int.MAX_VALUE

    fun solution(N: Int, number: Int): Int {
        n = N
        target = number

        logic(0, 0)

        if (answer > 8) answer = -1

        return answer
    }

    private fun logic(count: Int, current: Int) {
        if (count > 8) return
        if (current == target) {
            answer = min(answer, count)
            return
        }

        var value = n
        for (i in 0 until (8 - count)) {
            logic(count + 1 + i, current + value)
            logic(count + 1 + i, current - value)
            logic(count + 1 + i, current * value)
            logic(count + 1 + i, current / value)
            value = ("$value" + "$n").toInt()
        }
    }
}