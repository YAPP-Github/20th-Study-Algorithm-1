import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();

        String[][] places = new String[][]{{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {
        "PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},{"OOOOO","OOOOO","OOOOO","XXXPP","OOOOO"}};
        System.out.println(Arrays.toString(solution.solution(places)));
    }

    // 요구사항
    // 1. 기본값 배치하기
    // 2. 맨해튼 거리 (r1 - r2) (c1 - c2)
    // 3. P끼리의 맨해튼 거리값 구하기

    // 5, 11, 17 통과 못함 ㅠ

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        ArrayList<int[]> pLocation = new ArrayList<>();

        for(int i = 0; i < places.length; i++) {

            int answerCount = 1;
            String[][] place = new String[5][5];
            for(int j = 0; j < places[i].length; j++) {

                String[] str = places[i][j].split("");
                for(int k = 0; k < str.length; k++) {
                    place[j][k] = str[k];
                    if(str[k].equals("P")) {
                        pLocation.add(new int[]{j, k});
                    }
                }
            }

            Loop1 :
            for(int n = 0; n < pLocation.size(); n++) {
                int[] first = pLocation.get(n);

                for(int m = n + 1; m < pLocation.size(); m++) {
                    int[] second = pLocation.get(m);

                    int fX = first[0];
                    int fY = first[1];
                    int sX = second[0];
                    int sY = second[1];

                    int diff = Math.abs(fX - sX) + Math.abs(fY - sY);
                    if(diff <= 2) {

                        // 행이 같을 때
                        if(fX == sX) {
                            if(fY + 1 < 5) {
                                if(!place[fX][Math.abs(fY + 1)].equals("X")) {
                                    answerCount = 0;
                                    break Loop1;
                                }
                            }
                        }
                        // 열이 같을 때
                        else if(fY == sY) {
                            if(fX + 1 < 5) {
                                if(!place[Math.abs(fX + 1)][fY].equals("X")) {
                                    answerCount = 0;
                                    break Loop1;
                                }
                            }

                        }
                        // 대각선일 때
                        else {
                            if(!place[sX][fY].equals("X") && !place[fX][sY].equals("X")) {
                                answerCount = 0;
                                break Loop1;
                            }
                        }
                    }
                }
            }

            answer[i] = answerCount;
            pLocation.clear();
        }

        return answer;
    }
}
