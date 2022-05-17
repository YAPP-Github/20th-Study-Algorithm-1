package 박현국;

import java.util.Arrays;

public class MatrixRotation {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        Matrix matrix = new Matrix(rows, columns);
        for (int i = 0; i < queries.length; i++) {
            matrix.rotate(queries[i][0] - 1, queries[i][1] - 1, queries[i][2] - 1, queries[i][3] - 1);
            answer[i] = matrix.smallest;
        }
        return answer;
    }

    static class Matrix {
        int[][] data;
        int smallest = 0;

        Matrix(int rows, int columns) {
            data = new int[rows][columns];
            int num = 1;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    data[i][j] = num;
                    num++;
                }
            }
        }

        void rotate(int x1, int y1, int x2, int y2) {
            int temp = data[x1][y2];
            smallest = temp;
            // 상단 회전
            for (int j = y2; j > y1; j--) {
                data[x1][j] = data[x1][j-1];
                smallest = Math.min(smallest, data[x1][j]);
            }

            // 좌측 회전
            for (int i = x1; i < x2; i++) {
                data[i][y1] = data[i+1][y1];
                smallest = Math.min(smallest, data[i][y1]);
            }

            // 하단 회전
            for (int j = y1; j < y2; j++) {
                data[x2][j] = data[x2][j+1];
                smallest = Math.min(smallest, data[x2][j]);
            }

            // 우측 회전
            for (int i = x2; i > x1+1; i--) {
                data[i][y2] = data[i-1][y2];
                smallest = Math.min(smallest, data[i][y2]);
            }

            data[x1+1][y2] = temp;
        }
    }
}
