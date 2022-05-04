package 박현국;

import java.util.*;

// 객체지향적으로 풀이해 보았으나, 효율성에서 탈락...
public class SearchRank_Timeout {
    public int[] solution(String[] infos, String[] queries) {
        int[] answer = new int[queries.length];
        int idx = 0;

        Database db = new Database();
        for (String info : infos) {
            String[] spl = info.split(" ");
            db.put(new Candidate(spl[0], spl[1], spl[2], spl[3], Integer.parseInt(spl[4])));
        }

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
        Map<String, List<Candidate>> language = new HashMap<>();
        Map<String, List<Candidate>> position = new HashMap<>();
        Map<String, List<Candidate>> career = new HashMap<>();
        Map<String, List<Candidate>> soulfood = new HashMap<>();


        void put(Candidate candidate) {
            if (! this.language.containsKey(candidate.language)) {
                this.language.put(candidate.language, new ArrayList<>());
            }
            this.language.get(candidate.language).add(candidate);

            if (! this.position.containsKey(candidate.position)) {
                this.position.put(candidate.position, new ArrayList<>());
            }
            this.position.get(candidate.position).add(candidate);

            if (! this.career.containsKey(candidate.career)) {
                this.career.put(candidate.career, new ArrayList<>());
            }
            this.career.get(candidate.career).add(candidate);

            if (! this.soulfood.containsKey(candidate.soulfood)) {
                this.soulfood.put(candidate.soulfood, new ArrayList<>());
            }
            this.soulfood.get(candidate.soulfood).add(candidate);
        }

        int query(String queryString) {
            String[] queries = queryString.split(" and ");
            String languageQ = queries[0];
            String positionQ = queries[1];
            String careerQ = queries[2];
            String soulfoodQ = queries[3].split(" ")[0];
            int pointQ = Integer.parseInt(queries[3].split(" ")[1]);

            Set<Candidate> querySet = new HashSet<>();
            if (this.language.containsKey(languageQ)) {
                querySet.addAll(this.language.get(languageQ));
            } else if ("-".equals(languageQ)) {
                for (List<Candidate> candidates : this.language.values()) {
                    querySet.addAll(candidates);
                }
            }

            if (this.position.containsKey(positionQ)) {
                querySet.retainAll(this.position.get(positionQ));
            }

            if (this.career.containsKey(careerQ)) {
                querySet.retainAll(this.career.get(careerQ));
            }

            if (this.soulfood.containsKey(soulfoodQ)) {
                querySet.retainAll(this.soulfood.get(soulfoodQ));
            }

            int ret = 0;
            for (Candidate candidate : querySet) {
                if (candidate.point >= pointQ) {
                    ret++;
                }
            }
            return ret;
        }
    }
}
