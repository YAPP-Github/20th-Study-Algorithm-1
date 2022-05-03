package programmers.week2

import kotlin.math.ceil

class FeatureDevelop {
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        val completedDay = IntArray(progresses.size)
        progresses.forEachIndexed { index, progress ->
            completedDay[index] = ceil((100.0 - progress) / speeds[index]).toInt()
            //if ((100 - progress) % speeds[index] != 0) completedDay[index] += 1
        }

        val answer = mutableListOf<Int>()
        var maxDay = completedDay[0]
        var releaseJob = 1


        for (i in 1 until completedDay.size) {
            if (completedDay[i] > maxDay) {
                maxDay = completedDay[i]
                answer.add(releaseJob)
                releaseJob = 1
            } else {
                releaseJob++
            }
        }
        answer.add(releaseJob)

        return answer.toIntArray()
    }
}