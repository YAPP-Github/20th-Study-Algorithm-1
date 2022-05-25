import java.util.ArrayList;

class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();

        int[] a = new int[]{-5, 0, 2, 1, 2};
        int[][] edges = new int[][]{{0, 1}, {3, 4}, {2, 3}, {0, 3}};

        System.out.println(solution.solution(a, edges));
    }

    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static long[] values;
    static long answer;

    public long solution(int[] a, int[][] edges) {

        list = new ArrayList[a.length];
        visited = new boolean[a.length];
        values = new long[a.length];
        answer = 0;

        long sum = 0;
        for(int i = 0; i < a.length; i++) {
            sum += a[i];
            values[i] = a[i];
            list[i] = new ArrayList<>();
        }

        // 합이 0이 아니면 가중치를 0으로 만들 수 없음
        if(sum != 0) return -1;

        for(int i = 0; i < edges.length; i++) {
            list[edges[i][0]].add(edges[i][1]);
            list[edges[i][1]].add(edges[i][0]);
        }

        visited[0] = true;

        dfs(0);

        return answer;
    }

    static long dfs(int now) {
        for (int i = 0; i < list[now].size(); i++) {
            int next = list[now].get(i);
            if(visited[next]) continue;
            visited[next] = true;
            values[now] += dfs(next);
        }

        answer += Math.abs(values[now]);

        return values[now];
    }
}
