import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();

        String[] info = new String[]{"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150",
                "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = new String[]{"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150", "- and - and - and - 0", "cpp and frontend and junior and chicken 500"
        };

        System.out.println(Arrays.toString(solution.solution(info, query)));
    }

    public int[] solution(String[] info, String[] query) {

        HashMap<String, ArrayList<Integer>> information = new HashMap<>();

        // 지원자의 모든 경우의 수를 넣어버린다.
        // java backend junior pizza 150 -> - backend junior pizza 150 에도 반응할 수 있게끔
        for (String s : info) {
            String[] data = s.split(" ");
            String[] langs = {data[0], "-"};
            String[] jobs = {data[1], "-"};
            String[] exps = {data[2],"-"};
            String[] foods = {data[3],"-"};
            int score = Integer.parseInt(data[4]);

            for(String lang : langs) {
                for(String job : jobs) {
                    for(String exp : exps) {
                        for(String food : foods) {
                            String keyValue = lang + " " + job + " " + exp + " " + food;

                            ArrayList<Integer> values = information.getOrDefault(keyValue, new ArrayList<Integer>());
                            // 이 과정에서 동일 조건을 만족하는 지원자가 있으면 점수를 추가해주는 로직
                            values.add(score);
                            information.put(keyValue, values);
                        }
                    }
                }
            }
        }

        // 나중에 이분 탐색을 하기 위해 오름차순으로 정렬
        for (ArrayList<Integer> arr : information.values()) {
            arr.sort(null);
        }


        int[] answer = new int[query.length];
        int i = 0;

        // Query 배열 정리
        for(String q : query) {
            String[] datas = q.split(" and ");

            // 데이터 변환
            int score = Integer.parseInt(datas[3].split(" ")[1]); // chicken 500 -> 500, 이 점수를 넘어야 조건에 부합함
            datas[3] = datas[3].split(" ")[0]; // chicken 500 -> chicken

            String key = String.join(" ", datas);

            System.out.println(key + " :: " + information.get(key));
            System.out.println("이 점수는 넘어야됨 :: " + score);

            System.out.println("--------------------------------------------------------");

            if(information.containsKey(key)) { // 조건에 부합한 지원자가 없을 경우가 있음을 대비
                ArrayList<Integer> infoValues = information.get(key);
                // 이분 탐색 진입
                int low = 0;
                int high = infoValues.size();
                while(high > low) {

                    int mid = (low + high) / 2;

                    System.out.println("mid :: " + mid);
                    System.out.println("score :: " + score);
                    System.out.println("info mid value :: " + infoValues.get(mid));

                    if(score > infoValues.get(mid)) {
                        low = mid + 1;
                    } else {
                        high = mid;
                    }

                    // 과정을 거쳐 low의 값과 high의 값은 동일해짐
                    System.out.println("low :: " + low);
                    System.out.println("high :: " + high);

                    System.out.println("--------------------------------------------------------");
                }
                answer[i] = infoValues.size() - low; // 전체 사이즈 - 조건을 만족하는 값의 시작점
            }
            i++;
        }
        return answer;
    }
}
    /*
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        String[][] infoArr = new String[info.length][5];
        String[][] queryArr = new String[query.length][5];

        // [i][0] -> 언어
        // [i][1] -> 직군
        // [i][2] -> 경력
        // [i][3] -> 소울푸드
        // [i][4] -> 코테 점수

        // 정보 배열 세팅
        for (int i = 0; i < infoArr.length; i++) {
            StringTokenizer st = new StringTokenizer(info[i], " ");
            for (int j = 0; j < 5; j++) {
                infoArr[i][j] = st.nextToken();
            }
        }

        // 쿼리 배열 세팅
        for (int i = 0; i < queryArr.length; i++) {
            query[i] = query[i].replace("and", "");
            StringTokenizer st = new StringTokenizer(query[i], " ");
            for (int j = 0; j < 5; j++) {
                queryArr[i][j] = st.nextToken();
            }
        }


        for (int i = 0; i < answer.length; i++) {
//            System.out.println("first For :: " + i);

            for (int k = 0; k < infoArr.length; k++) {
                int count = 1;
                for (int j = 0; j < 5; j++) {

                    String q = queryArr[i][j];
                    String in = infoArr[k][j];

//                    System.out.println(q + " ----- " + in);

                    if (j == 4) {
                        if (Integer.parseInt(q) > Integer.parseInt(in)) {
//                            System.out.println("조건 불만족");
                            count = 0;
                            break;
                        }
                    } else {
                        if (!q.equals(in)) {
                            if (q.equals("-")) {
                                continue;
                            }
//                            System.out.println("조건 불만족");
                            count = 0;
                            break;
                        }
                    }
                }

                if (count == 1) {
//                    System.out.println("조건 만족");
                    answer[i]++;
                }
            }
        }
        return answer;
    }
     */
