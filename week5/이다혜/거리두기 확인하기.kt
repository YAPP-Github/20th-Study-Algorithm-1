package programmers

class `거리두기 확인하기` {
    fun solution(places: Array<Array<String>>): IntArray {
        var answer = IntArray(places.size)

        for (placeNum in places.indices) {
            var place = places[placeNum]
            var flag = true
            for (i in place.indices) {
                for (j in place[0].indices) {
                    if (!flag)
                        break
                    if (place[i][j] == 'P')
                        flag = checkDistance(place, i, j, 0, 0)
                }
                if (!flag)
                    break
            }
            answer[placeNum] = if (flag) 1 else 0
        }

        return answer
    }

    private fun checkDistance(place: Array<String>, i: Int, j: Int, direction: Int, depth: Int): Boolean {
        var result = true
        if(result && direction != 1 && i < place.lastIndex) {
            when(place[i+1][j]) {
                'P' -> return false
                'O' -> if(depth == 0) result = checkDistance(place, i+1, j, 0, 1)
            }
        }

        if(result && direction != 2 && j > 0) {
            when(place[i][j-1]) {
                'P' -> return false
                'O' -> if(depth == 0) result = checkDistance(place, i, j-1, 3, 1)
            }
        }

        if(result && direction != 3 && j < place[0].lastIndex) {
            when(place[i][j+1]) {
                'P' -> return false
                'O' -> if(depth == 0) result = checkDistance(place, i, j+1, 2, 1)
            }
        }

        return result
    }
}