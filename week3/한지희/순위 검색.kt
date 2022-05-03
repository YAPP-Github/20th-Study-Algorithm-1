import java.util.*
import kotlin.collections.ArrayList

// 개발언어: cpp, java, python
// 지원 직군: backend, frontend
// 지원 경력구분: junior, senior
// 소울푸드: chicken, pizza
// 점수

class RankSearch {
    private lateinit var usersHashMap: MutableMap<String, ArrayList<Int>>

    fun solution(info: Array<String>, query: Array<String>): IntArray {
        val answer = IntArray(query.size)
        usersHashMap = mutableMapOf()

        info.forEach { applicant ->
            val stringTokenizer = StringTokenizer(applicant)
            val case = Array(4) { Array(2) { "-" } }
            repeat(4) { case[it][0] = stringTokenizer.nextToken() }
            val score = Integer.parseInt(stringTokenizer.nextToken())
            findAllQueryCase(case, "", score, 0)
        }

        usersHashMap.forEach { it.value.sort() }

        query.forEachIndexed { index, queryLine ->
            val stringTokenizer = StringTokenizer(queryLine)
            var queryExceptAnd = ""

            for (i in 0..6) {
                val condition = stringTokenizer.nextToken()
                if (i % 2 == 0)
                    queryExceptAnd += condition
            }

            if (usersHashMap[queryExceptAnd] == null) answer[index] = 0
            else answer[index] = lowerBound(
                usersHashMap[queryExceptAnd]!!,
                Integer.parseInt(stringTokenizer.nextToken())
            )
        }
        return answer
    }

    private fun findAllQueryCase(case: Array<Array<String>>, key: String, score: Int, depth: Int) {
        if (depth == 4) {
            if (usersHashMap[key] == null) {
                usersHashMap[key] = arrayListOf()
            }
            usersHashMap[key]?.add(score)
            return
        }
        findAllQueryCase(case, key + case[depth][0], score, depth + 1)
        findAllQueryCase(case, key + case[depth][1], score, depth + 1)
    }

    private fun lowerBound(scores: List<Int>, targetScore: Int): Int {
        var low = 0
        var high = scores.size - 1
        while (low <= high) {
            val mid = (low + high) / 2
            if (scores[mid] < targetScore) low = mid + 1
            else high = mid - 1
        }
        return scores.size - low
    }

    fun timeoutSolution(info: Array<String>, query: Array<String>): IntArray {
        val answer = IntArray(query.size)
        query.forEachIndexed { index, queryString ->
            var count = 0
            val queryArray = queryString.split(" ")

            info.forEach { applicant ->
                val applicantArray = applicant.split(" ")
                if (
                    (queryArray[0] == applicantArray[0] || queryArray[0] == "-") && // language
                    (queryArray[2] == applicantArray[1] || queryArray[2] == "-") && // job
                    (queryArray[4] == applicantArray[2] || queryArray[4] == "-") && // career
                    (queryArray[6] == applicantArray[3] || queryArray[6] == "-") && // food
                    (queryArray[7].toInt() <= applicantArray[4].toInt()) // score
                ) count++
            }

            answer[index] = count
        }
        return answer
    }
}