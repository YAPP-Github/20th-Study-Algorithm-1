package 박현국;

import java.util.*;

// 풀이 참조 : https://loosie.tistory.com/265
// 다만 이전 풀이에서 solution 함수는 거의 변경 안하고 Database 클래스의 api 내부 구현만 변경해봤다.
public class SearchRank {
    public int[] solution(String[] infos, String[] queries) {
        int[] answer = new int[queries.length];
        int idx = 0;

        Database db = new Database();
        for (String info : infos) {
            String[] spl = info.split(" ");
            db.put(new Candidate(spl[0], spl[1], spl[2], spl[3], Integer.parseInt(spl[4])));
        }

        db.sortDb(); // 어쩔수 없이 sort 로직은 추가;;
        for (String query : queries) {
            answer[idx] = db.query(query);
            idx++;
        }

        return answer;
    }

    static class Candidate {
        String language;
        String position;
        String career;
        String soulfood;
        int point;

        Candidate(String language, String position, String career, String soulfood, int point) {
            this.language = language;
            this.position = position;
            this.career = career;
            this.soulfood = soulfood;
            this.point = point;
        }
    }

    static class Database {
        Map<String, List<Integer>> db = new HashMap<>();

        void put(Candidate candidate) {
            int point = candidate.point;
            addScoreToDb(candidate.language + candidate.position + candidate.career + candidate.soulfood, point);

            addScoreToDb("-" + candidate.position + candidate.career + candidate.soulfood, point);
            addScoreToDb(candidate.language + "-" + candidate.career + candidate.soulfood, point);
            addScoreToDb(candidate.language + candidate.position + "-" + candidate.soulfood, point);
            addScoreToDb(candidate.language + candidate.position + candidate.career + "-", point);

            addScoreToDb("--" + candidate.career + candidate.soulfood, point);
            addScoreToDb("-" + candidate.position + "-" + candidate.soulfood, point);
            addScoreToDb("-" + candidate.position + candidate.career + "-", point);
            addScoreToDb(candidate.language + "--" + candidate.soulfood, point);
            addScoreToDb(candidate.language + "-" + candidate.career + "-", point);
            addScoreToDb(candidate.language + candidate.position + "--", point);

            addScoreToDb("--" + candidate.career + "-", point);
            addScoreToDb("---" + candidate.soulfood, point);
            addScoreToDb("-" + candidate.position + "--", point);
            addScoreToDb(candidate.language + "---", point);

            addScoreToDb("----", point);
        }

        private void addScoreToDb(String key, int point) {
            if (! this.db.containsKey(key)) {
                this.db.put(key, new ArrayList<>());
            }
            this.db.get(key).add(point);
        }

        void sortDb() {
            List<String> keys = new ArrayList<>(this.db.keySet());
            for(String key : keys) {
                Collections.sort(this.db.get(key));
            }
        }

        int query(String queryString) {
            String[] queries = queryString.split(" and ");
            String languageQ = queries[0];
            String positionQ = queries[1];
            String careerQ = queries[2];
            String soulfoodQ = queries[3].split(" ")[0];
            int pointQ = Integer.parseInt(queries[3].split(" ")[1]);

            String key = languageQ + positionQ + careerQ + soulfoodQ;
            List<Integer> data = this.db.getOrDefault(key, Collections.emptyList());
            return data.size() - lowerBound(data, pointQ);
        }

        private int lowerBound(List<Integer> data, int target) {
            int begin = 0;
            int end = data.size();

            while(begin < end) {
                int mid = (begin + end) / 2;

                if(data.get(mid) >= target) {
                    end = mid;
                }
                else {
                    begin = mid + 1;
                }
            }
            return end;
        }
    }
}
