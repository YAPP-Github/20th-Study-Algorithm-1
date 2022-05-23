import java.util.*;

public class 모두 0으로 만들기{

    private static List<List<Integer>> graph = new ArrayList<>();
    private static int[] visit;
    private static long[] values;
    private static long result = 0;

    public long solution(int[] a, int[][] edges) {
        visit = new int[a.length];
        values = new long[a.length];

        long sum = 0;
        for (int i = 0; i < a.length; i++) {
            graph.add(new ArrayList<>());

            sum += a[i];
            values[i] = a[i];
        }

        if(sum != 0) return -1;

        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0];
            int y = edges[i][1];

            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        dfs(0);

        return result;
    }

    private static long dfs(int now){
        visit[now] = 1;

        for (int next : graph.get(now)) {
            if(visit[next] != 1){
                values[now] += dfs(next);
            }
        }

        result += Math.abs(values[now]);

        return values[now];
    }

}
