class NegativePositivePlus {
    fun solution(absolutes: IntArray, signs: BooleanArray): Int {
        var sum = 0
        absolutes.forEachIndexed { index, number ->
            sum += if (signs[index]) number else -number
        }
        return sum
    }

    fun bestSolution(absolutes: IntArray, signs: BooleanArray) =
        absolutes.foldIndexed(0) { index, accumulator, number ->
            accumulator + if (signs[index]) number else -number
        }
}