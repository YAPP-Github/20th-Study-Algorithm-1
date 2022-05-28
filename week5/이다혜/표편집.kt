class 표편집 {
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        var currentIdx = k
        val removeList = ArrayDeque<Int>()
        var size = n

        for (idx in cmd.indices) {
            when(cmd[idx][0]) {
                'U' -> currentIdx -= Integer.valueOf(cmd[idx].substring(2))
                'D' -> currentIdx += Integer.valueOf(cmd[idx].substring(2))
                'C' -> {
                    removeList.addLast(currentIdx)
                    size -= 1
                    if (currentIdx == size) currentIdx -= 1
                }
                'Z' -> {
                    val prevIdx = removeList.removeLast()
                    if (prevIdx <= currentIdx) currentIdx += 1
                    size += 1
                }
            }
        }

        /**
         * 아래 코드는 자꾸 실패가 나는데 왜그런지 모르겠다...
          */
//        for(command in cmd) {
//            when(command[0]) {
//                'U' -> currentIdx -= Character.getNumericValue(command[2])
//                'D' -> currentIdx += Character.getNumericValue(command[2])
//                'C' -> {
//                    removeList.addLast(currentIdx)
//                    size--
//                    currentIdx = if(size == currentIdx) currentIdx - 1 else currentIdx
//                }
//                'Z' -> {
//                    val prevIdx = removeList.removeLast()
//                    size++
//                    currentIdx = if(prevIdx <= currentIdx) currentIdx + 1 else currentIdx
//                }
//            }
//        }

        val sb: StringBuilder = StringBuilder()

        for(i in 0 until size) {
            sb.append("O")
        }

        while (!removeList.isEmpty()) {
            sb.insert(removeList.removeLast(), "X")
        }

        return sb.toString()
    }
}
