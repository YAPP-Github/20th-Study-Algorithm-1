import java.util.*;

public class 빛의경로사이클 {

    private static int R, C;
    private static int[] dr = {1, 0, -1, 0};
    private static int[] dc = {0, 1, 0, -1};
    private static boolean[][][] visit;

    public List<Integer> solution(String[] grid) {
        List<Integer> results = new ArrayList<>();

        R = grid.length;
        C = grid[0].length();
        visit = new boolean[R][C][4];

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                for (int d = 0; d < 4; d++) {
                    if (!visit[r][c][d]) {
                        results.add(getCycleLen(grid, r, c, d));
                    }
                }
            }
        }

        Collections.sort(results);
        return results;
    }

    public int getCycleLen(String[] grid, int r, int c, int d){
        int cnt = 0;

        while(true){
            if(visit[r][c][d]) break;

            cnt++;
            visit[r][c][d] = true;

            if (grid[r].charAt(c) == 'L') {
                d = (d + 3) % 4;
            } else if (grid[r].charAt(c) == 'R') {
                d = (d + 1) % 4;
            }

            r = (r + dr[d] + R) % R;
            c = (c + dc[d] + C) % C;
        }

        return cnt;
    }

}