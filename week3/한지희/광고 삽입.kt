class Advertisement {
    fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
        val totalSeconds = IntArray(360_000)
        val playTimeSeconds = timeFormatToSeconds(play_time)
        val advTimeSeconds = timeFormatToSeconds(adv_time)

        logs.forEach { log ->
            val screenTime = log.split("-")
            val start = timeFormatToSeconds(screenTime[0])
            val end = timeFormatToSeconds(screenTime[1])

            for (i in start until end) {
                totalSeconds[i]++
            }
        }

        var currentTime = 0L
        for (i in 0 .. advTimeSeconds) {
            currentTime += totalSeconds[i]
        }

        var maxTimeStartSeconds = 0
        var maxTime = currentTime

        for (i in advTimeSeconds + 1 .. playTimeSeconds) {
            currentTime = currentTime + totalSeconds[i] - totalSeconds[i - advTimeSeconds]
            if (currentTime > maxTime) {
                maxTime  = currentTime
                maxTimeStartSeconds = i - advTimeSeconds + 1
            }
        }

        return convertTimeFormat(maxTimeStartSeconds)
    }

    private fun timeFormatToSeconds(time: String): Int {
        val (hour, min, sec) = time.split(":").map { it.toInt() }
        return (hour * 3600) + (min * 60) + sec
    }

    private fun convertTimeFormat(time: Int): String = String.format("%02d:%02d:%02d", time / 3600, time / 60 % 60, time % 60)
}