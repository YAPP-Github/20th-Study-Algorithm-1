package 박현국;

import java.util.*;
import java.util.stream.Collectors;

public class BestAlbum {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Genre> genreMap = new HashMap<>();
        Map<String, List<Song>> genreSongs = new HashMap<>();

        for (int i = 0; i < plays.length; i++) {
            String genre = genres[i];
            int play = plays[i];

            if (! genreMap.containsKey(genre)) {
                genreMap.put(genre, new Genre(genre));
            }
            genreMap.get(genre).addPlay(play);

            if (! genreSongs.containsKey(genre)) {
                genreSongs.put(genre, new ArrayList<>());
            }
            genreSongs.get(genre).add(new Song(i, genre, play));
        }

        List<Genre> genreList = new ArrayList<>(genreMap.values());
        sortGenreList(genreList);

        for(Genre genre : genreList) {
            int count = 0;
            sortSongList(genreSongs.get(genre.name));
            for (Song genreSong : genreSongs.get(genre.name)) {
                answer.add(genreSong.idx);

                count++;
                if (count >= 2) {
                    break;
                }
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private void sortGenreList(List<Genre> genres) {
        genres.sort((o1, o2) -> {
            if (o1.totalPlays > o2.totalPlays) return -1;
            else if (o1.totalPlays < o2.totalPlays) return 1;
            return 0;
        });
    }

    private void sortSongList(List<Song> songs) {
        songs.sort(((o1, o2) -> {
            if (o1.plays > o2.plays) return -1;
            else if (o1.plays < o2.plays) return 1;
            return 0;
        }));
    }

    static class Genre {
        String name;
        int totalPlays = 0;

        Genre(String name) {
            this.name = name;
        }

        void addPlay(int play) {
            totalPlays += play;
        }
    }

    static class Song {
        int idx;
        String genre;
        int plays;

        Song(int idx, String genre, int plays) {
            this.idx = idx;
            this.genre = genre;
            this.plays = plays;
        }
    }
}
