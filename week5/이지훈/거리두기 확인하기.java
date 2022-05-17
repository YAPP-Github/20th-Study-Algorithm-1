public class 거리두기_확인하기 {
    private static char[][] map;
    private static boolean[][] visit;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    public int[] solution(String[][] places) {
        int[] result = new int[5];

        for (int i = 0; i < 5; i++) {
            map = new char[5][5];

            for (int j = 0; j < 5; j++) {
                String s = places[i][j];
                for (int k = 0; k < 5; k++) {
                    map[j][k] = s.charAt(k);
                }
            }

            result[i] = isKeepDistance() ? 1 : 0;
        }

        return result;
    }

    private static boolean isKeepDistance(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(map[i][j] == 'P'){
                    if(!bfs(i, j)) return false;
                }
            }
        }

        return true;
    }

    private static boolean bfs(int r, int c){
        visit = new boolean[5][5];
        visit[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (isAbleArea(nr, nc)) {

                if(isPeople(nr,nc)) return false;
                else if(isTable(nr, nc)){ // 테이블인경우 상하좌우 탐색 한번 더(맨해튼 거리가 2인 자리 모두 탐색)
                    for (int j = 0; j < 4; j++) {
                        int nnr = nr + dr[j];
                        int nnc = nc + dc[j];

                        if(isAbleArea(nnr, nnc) && isNotVisit(nnr, nnc) && isPeople(nnr, nnc)) return false;
                    }
                }

            }
        }

        return true;
    }

    private static boolean isAbleArea(int r, int c){
        return r >= 0 && c >= 0 && r < 5 && c < 5;
    }

    private static boolean isTable(int r, int c){
        return map[r][c] == 'O';
    }

    private static boolean isNotVisit(int r, int c){
        return !visit[r][c];
    }

    private static boolean isPeople(int r, int c){
        return map[r][c] == 'P';
    }

}
