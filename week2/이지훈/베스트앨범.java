import java.util.*;

public class 베스트앨범 {
    private static class Song implements Comparable<Song>{
        int id;
        int play;

        public Song(int id, int play) {
            this.id = id;
            this.play = play;
        }

        @Override
        public int compareTo(Song o) {
            if(this.play == o.play) return Integer.compare(this.id, o.id);
            return Integer.compare(o.play, this.play);
        }
    }

    public List<Integer> solution(String[] genres, int[] plays) {
        Map<String, Integer> playByGenres = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            playByGenres.put(genres[i], playByGenres.getOrDefault(genres[i], 0) + plays[i]);
        }

        List<String> sortedGenres = new ArrayList<>(playByGenres.keySet());
        sortedGenres.sort(Comparator.comparing(playByGenres::get).reversed());

        List<Integer> result = new ArrayList<>();
        for (String genre : sortedGenres) {
            List<Song> songs = new ArrayList<>();

            for (int i = 0; i < genres.length; i++) {
                if(genre.equals(genres[i])) songs.add(new Song(i, plays[i]));
            }
            Collections.sort(songs);

            int idx = 1;
            for (Song song : songs) {
                if(idx++ >= 3) break;
                result.add(song.id);
            }
        }

        return result;
    }
}