package 박현국;

import java.util.*;

public class FarestNode {
    public static void main(String[] args) {
        System.out.println(solution(6, new int[][]{new int[] {3, 6 }, new int[] { 4, 3}, new int[] {3, 2 }, new int[] {1, 3 },
                new int[] { 1, 2}, new int[] { 2, 4}, new int[] { 5, 2}}));
    }

    public static int solution(int n, int[][] edge) {
        // 1. 노드를 그래프에 등록
        Graph graph = new Graph(n);

        // 2. 그 노드에서 인접한 노드들 등록
        for (int[] e : edge) {
            graph.setAbsentNode(e[0], e[1]);
        }

        // 3. 첫 노드 및 근접 노드의 거리 입력 (1)
        int[] dist = new int[n+1];
        dist[1] = 1;
        for (Node absent : graph.nodes.get(1).absentNode) {
            dist[absent.num] = 1;
        }

        // 4. BFS
        Queue<Node> q = new LinkedList<>(graph.nodes.get(1).absentNode);
        while (! q.isEmpty()) {
            Node node = q.remove();
            for (Node absent : node.absentNode) {
                if (dist[absent.num] != 0) {
                    continue;
                }

                q.add(absent);
                dist[absent.num] = dist[node.num] + 1;
            }
        }

        // 5. 최대 거리 개수
        int max = 0;
        int answer = 1;
        for (int i = 1; i < n+1; i++) {
            if (max < dist[i]) {
                max = dist[i];
                answer = 1;
            } else if (max == dist[i]) {
                answer++;
            }
        }

        return answer;
    }

    static class Node {
        private final int num;
        private final Set<Node> absentNode = new HashSet<>();

        Node(int num) {
            this.num = num;
        }

        void putAbsentNode(Node node) {
            this.absentNode.add(node);
        }
    }

    static class Graph {
        private final Map<Integer, Node> nodes = new HashMap<>();

        Graph(int n) {
            for (int i = 1; i <= n; i++) {
                nodes.put(i, new Node(i));
            }
        }

        void setAbsentNode(int i, int j) {
            this.nodes.get(i).putAbsentNode(this.nodes.get(j));
            this.nodes.get(j).putAbsentNode(this.nodes.get(i));
        }
    }
}
