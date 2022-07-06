import java.util.*

class RotateBracket {
    fun solution(s: String): Int {
        var answer = 0

        for (i in s.indices) {
            if (isBracketString(s.substring(i, s.length) + s.substring(0, i))) answer++
        }

        return answer
    }

    private fun isBracketString(s: String): Boolean {
        val openBracket = listOf('(', '[', '{')
        val closeBracket = listOf(')', ']', '}')
        val bracketStack = Stack<Char>()

        s.forEach { char ->
            if (char in openBracket) bracketStack.push(char)
            else {
                if (bracketStack.isEmpty()) return false
                if (char != closeBracket[openBracket.indexOf(bracketStack.peek())]) return false
                bracketStack.pop()
            }
        }
        return bracketStack.isEmpty()
    }

    fun bestSolution(s: String): Int {
        return s.indices.count { isBracketString(s.substring(it, s.length) + s.substring(0, it)) }
    }
}