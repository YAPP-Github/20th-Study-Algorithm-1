import java.util.ArrayList;
import java.util.Collections;

class Solution {

    // 좌 - 상 - 우 - 하
    static int[] myX = {-1,0,1,0};
    static int[] myY = {0,1,0,-1};

    public int[] solution(String[] grid) {
        ArrayList<Integer> al = new ArrayList<>();

        int X = grid.length; // 가로 크기
        int Y = grid[0].length(); // 세로 크기

        boolean[][][] visited = new boolean[X][Y][4];

        for(int x = 0; x < X; x++){
            for(int y = 0; y < Y; y++){
                for(int d = 0; d < 4; d++){
                    if(!visited[x][y][d]){
                        int cnt = 0;
                        while (!visited[x][y][d]) {

                            visited[x][y][d] = true; // 현재 그리드의 현재 방향 방문 처리

                            if (grid[x].charAt(y) == 'L') { // 좌회전
                                // 0 -> 3, 1 -> 0, 2 -> 1, 3 -> 2
                                d = d == 0 ? 3 : d - 1;
                            } else if (grid[x].charAt(y) == 'R') { // 우회전
                                // 0 -> 1, 1 -> 2, 2 -> 3, 3 -> 0
                                d = d == 3 ? 0 : d + 1;
                            }

                            // 다음 방문 위치 계산
                            x = (x + myX[d] + X) % X;
                            y = (y + myY[d] + Y) % Y;

                            cnt++;
                        }
                        al.add(cnt);
                    }
                }
            }
        }

        int[] answer = new int[al.size()];

        Collections.sort(al);

        for(int i = 0; i < al.size(); i++){
            answer[i] = al.get(i);
        }

        return answer;
    }
}