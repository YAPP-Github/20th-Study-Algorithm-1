class Solution {
    fun solution(numbers: IntArray): String {
        val numberToStrings = numbers.map { it.toString() }.toTypedArray()
        numberToStrings.sortWith { o1, o2 ->
            when (o1.length) {
                o2.length -> o2.compareTo(o1)
                else -> (o2 + o1).compareTo(o1 + o2)
            }
        }

        if (numberToStrings[0] == "0") return "0"

        var answer = ""
        numberToStrings.forEach { numString ->
            answer += numString
        }
        return answer
    }
}