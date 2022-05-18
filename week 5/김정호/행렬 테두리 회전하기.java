import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();

        int rows = 6;
        int columns = 6;
        int[][] queries = new int[][]{{2,2,5,4}, {3,3,6,6}, {5,1,6,3}};
        System.out.println(Arrays.toString(solution.solution(rows, columns, queries)));
    }

    // 요구사항
    // 1. 기본값 배치하기
    // 2. 시계 방향으로 회전시키기
    // 3. 최소값 찾아내기

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        int[][] table = new int[rows][columns];

        int count = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                table[i][j] = count;
                count++;
            }
        }

        for(int query = 0; query < queries.length; query++) {
            int min = Integer.MAX_VALUE;

            int startRow = queries[query][0] - 1;
            int startColumn = queries[query][1] - 1;
            int endRow = queries[query][2] - 1;
            int endColumn = queries[query][3] - 1;

            // 시작점이 기준값
            int tmp = table[startRow][startColumn];

            // 왼 -> 아래 -> 오른 -> 위 (시계 반대방향)
            for(int i = startRow; i < endRow; i++) {
                table[i][startColumn] = table[i + 1][startColumn];
            }

            for(int i = startColumn; i < endColumn; i++) {
                table[endRow][i] = table[endRow][i + 1];
            }

            for(int i = endRow; i > startRow; i--) {
                table[i][endColumn] = table[i - 1][endColumn];
            }

            for(int i = endColumn; i > startColumn; i--) {
                table[startRow][i] = table[startRow][i-1];
            }

            // 기준값 돌려내기
            table[startRow][startColumn + 1] = tmp;

            // 최소값 찾아내기 (중간에 겹치는 값들이 있는데, 제외하면 시간이 더 빠를듯 ??)
            for(int i = startRow; i <= endRow; i++) {
                if(min > table[i][startColumn]) min = table[i][startColumn];
                if(min > table[i][endColumn]) min = table[i][endColumn];
            }
            for(int i = startColumn; i <= endColumn; i++) {
                if(min > table[startRow][i]) min = table[startRow][i];
                if(min > table[endRow][i]) min = table[endRow][i];
            }

            answer[query] = min;
        }


        return answer;
    }
}
