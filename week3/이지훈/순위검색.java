import java.util.*;

public class 순위검색 {

    private static Map<String, List<Integer>> conditionToScores = new HashMap<>();

    public List<Integer> solution(String[] infos, String[] queries) {
        List<Integer> results = new ArrayList<>();

        for (String info : infos) {
            getComb(0, info.split(" "), "");
        }

        for (String condition : conditionToScores.keySet()) {
            Collections.sort(conditionToScores.get(condition));
        }

        for (String query : queries) {
            String separationStr = query.replaceAll(" and ", "");
            String[] arr = separationStr.split(" ");

            String condition = arr[0];
            int score = Integer.parseInt(arr[1]);

            results.add(binarySearch(condition, score));
        }

        return results;
    }

    private static void getComb(int depth, String[] infos, String condition) {
        if (depth == 4) {
            if (!conditionToScores.containsKey(condition)) {
                conditionToScores.put(condition, new ArrayList<>());
            }

            conditionToScores.get(condition).add(Integer.parseInt(infos[4]));
            return;
        }

        getComb(depth + 1, infos, condition + "-");
        getComb(depth + 1, infos, condition + infos[depth]);
    }

    private static int binarySearch(String condition, int score) {
        if(!conditionToScores.containsKey(condition)) return 0;

        List<Integer> scores = conditionToScores.get(condition);
        int start = 0;
        int end = scores.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (scores.get(mid) < score) start = mid + 1;
            else end = mid - 1;
        }

        return scores.size() - start;
    }
}