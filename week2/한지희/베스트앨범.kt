package programmers.week2

class BestAlbum {
    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        data class Song(val genre: String, val index: Int, val playTime: Int)

        val answer = mutableListOf<Int>()
        val allSongs = mutableListOf<Song>()
        genres.forEachIndexed { index, genre ->
            allSongs.add(Song(genre, index, plays[index]))
        }

        val genreGroup = allSongs.groupBy { it.genre }.toList()
            .sortedByDescending {
                var sum = 0
                it.second.map { song ->
                    sum += song.playTime
                }
                sum
            }

        genreGroup.forEach { genre ->
            val play = genre.second.sortedByDescending { it.playTime }
            answer.add(play[0].index)
            if (play.size > 1) answer.add(play[1].index)
        }

        return answer.toIntArray()
    }

    fun bestSolution(genres: Array<String>, plays: IntArray): IntArray {
        return genres.indices.groupBy { genres[it] }
            .toList()
            .sortedByDescending { it.second.sumBy { plays[it] } }
            .map { it.second.sortedByDescending { plays[it] }.take(2) }
            .flatten()
            .toIntArray()
    }
}