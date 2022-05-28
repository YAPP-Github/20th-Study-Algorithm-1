import java.util.*;

// 테스트케이스 7, 8번 실패가 뜨는데, 스택 오버플로우 문제라는 것 같습니다.
class Solution {
    static long answer = 0;
    static boolean[] visited;
    static ArrayList<Integer>[] vertex;
    static long[] a;

    public long solution(int[] a, int[][] edges) {
        this.visited = new boolean[a.length];
        this.vertex = new ArrayList[a.length];
        this.a = new long[a.length];

        long sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            this.vertex[i] = new ArrayList<>();
            this.a[i] = (long)a[i];
        }

        if (sum != 0) {
            return -1;
        }

        for (int[] edge : edges) {
            vertex[edge[0]].add(edge[1]);
            vertex[edge[1]].add(edge[0]);
        }

        dfs(0);

        return this.answer;
    }

    long dfs(int i) {
        if (this.visited[i]) {
            return 0;
        }

        this.visited[i] = true;

        long ret = 0;
        for (Integer j : vertex[i]) {
            this.a[i] += dfs(j);
        }
        this.answer += Math.abs(this.a[i]);

        return this.a[i];
    }
}