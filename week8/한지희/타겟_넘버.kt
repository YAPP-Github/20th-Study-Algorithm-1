class TargetNumber {
    var target = 0
    var answer = 0
    fun solution(numbers: IntArray, target: Int): Int {
        this.target = target
        dfs(numbers, 0, 0)

        return answer
    }

    private fun dfs(numbers: IntArray, depth: Int, sum: Int) {
        if (depth == numbers.size) {
            if (target == sum) answer++
            return
        }

        dfs(numbers, depth + 1, sum + numbers[depth])
        dfs(numbers, depth + 1, sum - numbers[depth])
    }

    fun bestSolution(numbers: IntArray, target: Int): Int {
        return numbers.fold(listOf(0)) { list, i ->
            list.run {
                map { it + i } + map { it - i }
            }
        }.count { it == target }
    }
}