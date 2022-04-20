class KthNumber {
    fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
        val answer = IntArray(commands.size)
        commands.forEachIndexed { index, ints ->
            answer[index] = array.sliceArray((ints[0] - 1) until ints[1]).sorted()[ints[2] - 1]
        }
        return answer
    }

    // map 함수 활용하기
    fun bestSolution(array: IntArray, commands: Array<IntArray>): IntArray {
        return commands.map { command ->
            array.slice(IntRange(command[0] - 1, command[1] - 1)).sorted()[command[2] - 1]
        }.toIntArray()
    }
}