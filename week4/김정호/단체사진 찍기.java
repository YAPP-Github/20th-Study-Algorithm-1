import java.util.HashMap;

class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();

        int n = 2;
        String[] data = new String[]{"N~F=0", "R~T>2"};
        System.out.println(solution.solution(n, data));
    }


    static int[] location; // 위치
    static boolean[] visited; // 방문 여부 판단
    static int cnt;
    static HashMap<Character, Integer> character;
    static boolean check;

    public int solution(int n, String[] data) {
        int answer;

        location = new int[8];
        visited = new boolean[8];

        cnt = 0;
        character = new HashMap<>();
        check = true;

        character.put('A', 0);
        character.put('C', 1);
        character.put('F', 2);
        character.put('J', 3);
        character.put('M', 4);
        character.put('N', 5);
        character.put('R', 6);
        character.put('T', 7);

        perm(0, data);
        answer = cnt;
        return answer;
    }

    // 모든 경우의 수 생성 후 조건에 일치하는지 파악
    public static void perm(int idx, String[] data) {
        if (idx == 8) {
            if (checkAnswer(data)) {
                cnt++;
            }
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (!visited[i]) {
                visited[i] = true;
                location[idx] = i;
                perm(idx + 1, data);
                visited[i] = false;
            }
        }
    }

    public static boolean checkAnswer(String[] data) {
        for (int i = 0; i < data.length; i++) {
            int X = character.get(data[i].charAt(0));
            int Y = character.get(data[i].charAt(2));

            char operator = data[i].charAt(3);
            int diff = data[i].charAt(4) - '0';

            int xPos = location[X];
            int yPos = location[Y];

            if (operator == '=') {
                if (Math.abs(xPos - yPos) - 1 != diff) {
                    return false;
                }
            } else if (operator == '>') {
                if (Math.abs(xPos - yPos) - 1 <= diff) {
                    return false;
                }
            } else if (operator == '<') {
                if (Math.abs(xPos - yPos) - 1 >= diff) {
                    return false;
                }
            }
        }
        return true;
    }
}