import java.util.*;

class Solution {
    boolean[] visited;
//    int[][] graph; -> boolean 으로 바꿔줘야 함
    boolean[][] graph;
    int[] distance;

    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();
//      System.out.println(solution.solution(8, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}, {5,6}, {6,7}, {6,8}}));
        System.out.println(solution.solution(6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}));
    }

    public int solution(int n, int[][] edge) {
        int answer = 0;
        visited = new boolean[n + 1];
        graph = new boolean[n + 1][n + 1];
        distance = new int[n + 1];

        // 초기값 지정
        for (int i = 0; i < edge.length; i++) {
            int x = edge[i][0];
            int y = edge[i][1];

            graph[x][y] = true;
            graph[y][x] = true;
        }

        bfs();

        int max = 0;
        for(int i = 2; i <= n; i++) {
            if(max < distance[i]) max = distance[i];
        }

        for(int i = 2; i <= n; i++) {
            if(max == distance[i]) answer++;
        }

        return answer;
    }

    public void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1); // 1번, 초기값
        visited[1] = true;

        System.out.println(Arrays.deepToString(graph));

        while (!queue.isEmpty()) {
            int nIndex = queue.poll();
            System.out.println("nIndex :: " + nIndex);
            for (int j = 1; j < visited.length; j++) {
                if (!visited[j] && graph[nIndex][j]) {
                    visited[j] = true;
                    queue.add(j);
                    distance[j] = distance[nIndex] + 1; // 그 전 인덱스 값 + 1
                }
            }
        }
    }
}

//class Solution {
//    static boolean[] visited;
//    static int[][] graph;
//    static int[] distance;
//    public static void main(String[] args) {
//        // 테스트 코드
//        Solution solution = new Solution();
////        System.out.println(solution.solution(8, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}, {5,6}, {6,7}, {6,8}}));
//        System.out.println(solution.solution(6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}));
//    }
//
//    public int solution(int n, int[][] edge) {
//        visited = new boolean[n + 1];
//        graph = new int[n + 1][n + 1];
//        distance = new int[n + 1];
//        int answer = 0;
//
//        for(int i = 0; i <= n; i++) {
//            distance[i] = Integer.MAX_VALUE;
//        }
//
//        for(int i = 0; i < edge.length; i++) {
//            putEdge(graph, edge[i][0], edge[i][1]);
//        }
//
//        System.out.println(Arrays.deepToString(graph));
//
//        dfs(1, 0);
//
//        int max = 0;
//        for(int i = 2; i <= n; i++) {
//            if(max < distance[i]) max = distance[i];
//        }
//
//        for(int i = 2; i <= n; i++) {
//            if(max == distance[i]) answer++;
//        }
//
//        return answer;
//    }
//
//    public void putEdge(int[][] graph, int x, int y) {
//        graph[x][y] = 1;
//        graph[y][x] = 1;
//    }
//
//    public static void dfs(int nIndex, int cnt) {
//        distance[nIndex] = Math.min(distance[nIndex], cnt);
//
//        System.out.print(nIndex + " -> ");
//
//        for(int i = 1; i < visited.length; i++) {
//            if (graph[nIndex][i] == 1 && !visited[i]) {
//                visited[i] = true;
//                dfs(i, cnt + 1);
//                visited[i] = false;
//            }
//        }
//    }
//}