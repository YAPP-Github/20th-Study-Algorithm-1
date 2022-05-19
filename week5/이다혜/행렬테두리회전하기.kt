import kotlin.math.min

class Solution {
    fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
        var answer = intArrayOf()
        var board: Array<IntArray> = Array(rows){i -> IntArray(columns){j -> i*columns + j + 1}}

        queries.forEach {
            val startX = it[0] - 1
            val startY = it[1] - 1
            val endX = it[2] - 1
            val endY = it[3] - 1

            val topArr = IntArray(endY - startY){diff -> board[startX][startY+diff]}
            val rightArr = IntArray(endX - startX){diff -> board[startX+diff][endY]}
            val bottomArr = IntArray(endY - startY){diff -> board[endX][endY - diff]}
            val leftArr = IntArray(endX - startX){diff -> board[endX-diff][startY]}

            var min = rows * columns

            topArr.forEachIndexed { diff, element ->
                board[startX][startY+diff+1] = element
                min = min(min, element)
            }

            rightArr.forEachIndexed { diff, element ->
                board[startX+diff+1][endY] = element
                min = min(min, element)
            }

            bottomArr.forEachIndexed { diff, element ->
                board[endX][endY-diff-1] = element
                min = min(min, element)
            }

            leftArr.forEachIndexed { diff, element ->
                board[endX-diff-1][startY] = element
                min = min(min, element)
            }

            answer += min
        }

        return answer
    }
}

fun main(args: Array<String>) {
    val queries: Array<IntArray> = Array(3){ IntArray(4) }
    queries[0][0] = 2
    queries[0][1] = 2
    queries[0][2] = 5
    queries[0][3] = 4

    queries[1][0] = 3
    queries[1][1] = 3
    queries[1][2] = 6
    queries[1][3] = 6

    queries[2][0] = 5
    queries[2][1] = 1
    queries[2][2] = 6
    queries[2][3] = 3

    Solution().solution(6, 6, queries).forEach { println(it) }
}