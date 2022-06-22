class OpenChatRoom {
    fun solution(record: Array<String>): Array<String> {
        val answer = mutableListOf<String>()
        val userNicknameHashMap = mutableMapOf<String, String>()
        val tempLog = mutableListOf<Pair<String, Boolean>>()
        record.forEach { log ->
            val logElement = log.split(" ")
            val commend = logElement[0]
            val userId = logElement[1]

            when(commend) {
                "Enter" -> {
                    userNicknameHashMap[userId] = logElement[2]
                    tempLog.add(Pair(userId, true))
                }
                "Leave" -> {
                    tempLog.add(Pair(userId, false))
                }
                "Change" -> {
                    userNicknameHashMap[userId] = logElement[2]
                }
            }
        }

        tempLog.forEach { idStatePair ->
            if (idStatePair.second) answer.add("${userNicknameHashMap[idStatePair.first]}님이 들어왔습니다.")
            else answer.add("${userNicknameHashMap[idStatePair.first]}님이 나갔습니다.")
        }

        return answer.toTypedArray()
    }
}