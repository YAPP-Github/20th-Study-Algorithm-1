public class 행렬_테두리_회전하기 {
    private static int[][] map;
    private static int minValue = 0;

    public int[] solution(int rows, int columns, int[][] queries) {

        map = new int[rows + 1][columns + 1];
        int[] result = new int[queries.length];

        int num = 1;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                map[i][j] = num++;
            }
        }

        for (int i = 0; i < queries.length; i++) {
            result[i] = spin(queries[i]);
        }

        return result;
    }

    public static int spin(int[] query) {
        int moveRowCnt = query[2] - query[0];
        int moveColCnt = query[3] - query[1];

        int r = query[0];
        int c = query[1];
        int value = map[r][c];
        minValue = value;

        // 오른쪽
        for (int i = 0; i < moveColCnt; i++) {
            c += 1;
            value = move(r, c, value);
        }

        // 아래
        for (int i = 0; i < moveRowCnt; i++) {
            r += 1;
            value = move(r, c, value);
        }

        // 왼쪽
        for (int i = 0; i < moveColCnt; i++) {
            c -= 1;
            value = move(r, c, value);
        }

        // 위
        for (int i = 0; i < moveRowCnt; i++) {
            r -= 1;
            value = move(r, c, value);
        }

        return minValue;
    }

    public static int move(int row, int col, int beforeValue) {
        int nextValue = map[row][col];
        minValue = Math.min(minValue, nextValue);
        map[row][col] = beforeValue;

        return nextValue;
    }

}