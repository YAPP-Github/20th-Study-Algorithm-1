import java.util.*;

class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();

        String[] genres = new String[]{"classic", "pop", "classic", "classic", "pop", "pop", "classic", "k-pop"};
        int[] plays = new int[]{500, 600, 150, 800, 2350, 240, 800, 3000};

        System.out.println(Arrays.toString(solution.solution(genres, plays)));
    }

    public int[] solution(String[] genres, int[] plays) {
        // 초기 데이터 세팅
        HashMap<String, HashMap<Integer, Integer>> genPlay = new HashMap<>();

        //        1. 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
        //        2. 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
        //        3. 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.

        for (int i = 0; i < genres.length; i++) {
            HashMap<Integer, Integer> value = new HashMap<>();
            if (genPlay.containsKey(genres[i])) { // 키 중복값이 있으면 함께 작성 (꼬리를 밟는 식으로 계속 . . . )
                value = genPlay.get(genres[i]);
                value.put(i, plays[i]);
            } else {
                value.put(i, plays[i]);
            }

            /*
            {classic={0=500}} <- else문 진입
            {pop={1=600}, classic={0=500}} <- else문 진입
            {pop={1=600}, classic={0=500, 2=150}}
            */

            genPlay.put(genres[i], value);
            // 이 과정에서 고유 번호가 낮은 노래를 먼저 수록 (3번 조건 충족)
        }

        HashMap<String, Integer> desGenres = new HashMap<>(); // 많이 재생된 순서대로 키값을 저장할 리스트
        // 각 장르 내 재생량의 합을 HashMap 에 저장하는 과정
        for (String key : genPlay.keySet()) {
            HashMap<Integer, Integer> value = genPlay.get(key);
            int playSize = 0;

            for (int k : value.keySet()) {
                playSize += value.get(k); // 노래의 재생량
            }

            desGenres.put(key, playSize);
        }

        // 가장 높은 값 순서대로 출력(내림차순) - 속한 노래가 많이 재생된 장르를 먼저 수록
        List<Map.Entry<String, Integer>> entryList = new LinkedList<>(desGenres.entrySet());
        entryList.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        ArrayList<Integer> ans = new ArrayList<>();

        // 장르 별로 가장 많이 재생된 노래를 두 개씩 출력
        for (int k = 0; k < genPlay.size(); k++) {
            if (genPlay.containsKey(entryList.get(k).getKey())) {
                String key = entryList.get(k).getKey(); // pop, classic

                HashMap<Integer, Integer> value = genPlay.get(key);


                // 장르 내에서 가장 많이 재생된 노래 순서대로 출력(내림차순)
                List<Map.Entry<Integer, Integer>> dList = new LinkedList<>(value.entrySet());
                dList.sort(Map.Entry.<Integer, Integer>comparingByValue().reversed());

                System.out.println("after Sorting :: " + dList);

                // 2개씩만 출력
                ans.add(dList.get(0).getKey());
                if (dList.size() >= 2)
                    ans.add(dList.get(1).getKey());

            }
        }

        int[] answer = new int[ans.size()];

        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }

        return answer;
    }
}