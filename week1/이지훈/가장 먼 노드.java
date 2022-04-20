import java.util.*;

public class LV3 {
    private static List<List<Integer>> graph = new ArrayList<>(); // 각 노드간의 경로 저장
    private static int[] dist; // 1번 노드 부터 각 노드들까지의 거리 저장
    private static int max; // 1번 노드로부터 가장 멀리 떨어진 노드와의 거리

    public int solution(int n, int[][] edge) {
        dist = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edge.length; i++) {
            int x = edge[i][0];
            int y = edge[i][1];

            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        bfs(n);

        int result = 0;
        for (int num : dist) {
            if(num == max) result++;
        }

        return result;
    }

    private static void bfs(int n){
        Queue<Integer> q = new LinkedList<>();
        boolean[] visit = new boolean[n + 1];
        q.add(1);
        visit[1] = true;

        while (!q.isEmpty()) {
            Integer now = q.poll();

            for (int next : graph.get(now)) {
                if (!visit[next]) {
                    visit[next] = true;
                    q.add(next);
                    dist[next] = dist[now] + 1;
                    max = Math.max(max, dist[next]);
                }
            }
        }
    }
}
