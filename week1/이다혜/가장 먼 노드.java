import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    
    public int [] dist;

    class Point {
        int dist;
        int number;

        public Point(int dist, int number) {
            this.dist = dist;
            this.number = number;
        }
    }

    public int solution(int n, int[][] edge) {
        int answer = 0;
        dist = new int[n+1];

        List<List<Integer> > map = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            map.add(new ArrayList<>());
        }

        for(int [] relation : edge) {
            map.get(relation[0]).add(relation[1]);
            map.get(relation[1]).add(relation[0]);
        }

        bfsSearch(1, map);

        int maxDist = 0;
        for(int i = 1; i <= n; i++) {
            maxDist = Math.max(maxDist, dist[i]);
        }

        for(int i = 1; i <= n; i++) {
            // System.out.print(dist[i] + " , ");
            if(maxDist == dist[i]) answer++;
        }

        return answer;
    }
    
    public void bfsSearch(int current, List<List<Integer> > map) {

        Queue<Point> q = new LinkedList<>();
        
        dist[current] = 1;
        q.add(new Point(1, 1));

        while(!q.isEmpty()) {
            Point p = q.poll();

            for(int next : map.get(p.number)) {
                // System.out.println("next: " + next);
                if(dist[next] == 0) {
                    dist[next] = p.dist + 1;
                    q.add(new Point(dist[next], next));
                }
            }
        }
    }
}
