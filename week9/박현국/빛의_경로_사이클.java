import java.util.*;

class Solution {
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;

    public int[] solution(String[] grid) {
        List<Integer> answer = new ArrayList<>();
        int row = grid.length;
        int col = grid[0].length();
        Node[][] nodes = new Node[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                char reflectDirection = grid[i].charAt(j);
                nodes[i][j] = new Node(reflectDirection);
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int n = 0; n < 4; n++) {
                    Node node = nodes[i][j];
                    if (node.isDirectionVisited[n] == true) {
                        continue;
                    }

                    int count = 0;
                    int x = i;
                    int y = j;
                    Movement mov = Movement.of(n);
                    while (true) {
                        if (node.isDirectionVisited[mov.idx]) {
                            break;
                        }
                        node.isDirectionVisited[mov.idx] = true;

                        x += mov.deltaX;
                        if (x < 0) {
                            x = row - 1;
                        } else if (x >= row) {
                            x = 0;
                        }

                        y += mov.deltaY;
                        if (y < 0) {
                            y = col - 1;
                        } else if (y >= col) {
                            y = 0;
                        }

                        node = nodes[x][y];
                        if (node.reflectDirection == 'L') {
                            mov = Movement.of(mov.idx - 1);
                        } else if (node.reflectDirection == 'R') {
                            mov = Movement.of(mov.idx + 1);
                        }
                        count++;
                    }
                    answer.add(count);
                }
            }
        }
        Collections.sort(answer);
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    static class Node {
        char reflectDirection;
        final boolean[] isDirectionVisited = new boolean[4];

        Node(char reflectDirection) {
            this.reflectDirection = reflectDirection;
        }
    }

    enum Movement {
        UP(0, -1, 0),
        RIGHT(1, 0, 1),
        DOWN(2, 1, 0),
        LEFT(3, 0, -1);

        int idx;
        int deltaX;
        int deltaY;
        Movement(int idx, int deltaX, int deltaY) {
            this.idx = idx;
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }

        static Movement of(int idx) {
            if (idx < 0) {
                idx = 3;
            } else if (idx > 3) {
                idx = 0;
            }

            for(Movement mov : Movement.values()) {
                if (mov.idx == idx) {
                    return mov;
                }
            }
            throw new RuntimeException();
        }
    }
}