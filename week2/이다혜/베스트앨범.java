import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class 베스트앨범 {

    static class Genre implements Comparable<Genre> {
        String type;
        int playCount;

        public Genre(String type, int playCount) {
            this.type = type;
            this.playCount = playCount;
        }

        @Override
        public int compareTo(Solution.Genre g) {
            return g.playCount - this.playCount;
        }
    }

    static class Music implements Comparable<Music> {
        int number;
        int playCount;

        public Music(int number, int playCount) {
            this.number = number;
            this.playCount = playCount;
        }

        @Override
        public int compareTo(Solution.Music m) {
            if(this.playCount == m.playCount) return this.number - m.number;
            return m.playCount - this.playCount;
        }  
    }

    public List<Integer> solution(String[] genres, int[] plays) {

        List<Integer> answer = new ArrayList<>();

        Map<String, Integer> genrePlayCount = new HashMap<>();
        List<Genre> genreOrder = new ArrayList<>();
        Map<String, List<Music> > musics = new HashMap<>();

        int n = plays.length;
        for(int i = 0; i < n; i++) {
            genrePlayCount.put(genres[i], genrePlayCount.getOrDefault(genres[i], 0) + plays[i]);
            if(musics.containsKey(genres[i])) {
                musics.get(genres[i]).add(new Music(i, plays[i]));
            } else {
                musics.put(genres[i], new ArrayList<>());
                musics.get(genres[i]).add(new Music(i, plays[i]));
            }
        }

        Set<String> genreKey = genrePlayCount.keySet();
        for(String key : genreKey) {
            genreOrder.add(new Genre(key, genrePlayCount.get(key)));
        }

        Collections.sort(genreOrder);

        int maxBestMusicCount = musics.get(genreOrder.get(0).type).size();

        for(Genre g : genreOrder) {
            List<Music> genreMusicList = musics.get(g.type);
            Collections.sort(genreMusicList);
            maxBestMusicCount = genreMusicList.size() >= 2 ? 2 : 1;
            for(int i = 0; i < maxBestMusicCount; i++) {
                answer.add(genreMusicList.get(i).number);
            }
        }
        
        return answer;
    }
    
}
