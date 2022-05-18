package 박현국;
import java.util.*;
public class DistanceCheck {
    // 정석은 bfs를 이용한 풀이이지만.. 나의 경우는
    // P의 위치를 두 개 고르는 경우의 수를 모두 돌려보는 식으로 풀이
    // 31개 테스트 케이스 중 5개가 틀렸다는데.. 도무지 반례를 모르곘음
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for (int i = 0; i < places.length; i++) {
            List<List<Point>> points = new ArrayList<>();
            String[] place = places[i];

            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    if (place[x].charAt(y) != 'P') {
                        continue;
                    }

                    // x+1 y-1
                    if (x < 4 && 0 < y && (place[x+1].charAt(y-1) == 'P')) {
                        points.add(List.of(Point.of(x, y), Point.of(x+1, y-1)));
                    }
                    // x+1 y
                    if (x < 4 && (place[x+1].charAt(y) == 'P')) {
                        points.add(List.of(Point.of(x, y), Point.of(x+1, y)));
                    }
                    // x+2 y
                    if (x < 3 && (place[x+2].charAt(y) == 'P')) {
                        points.add(List.of(Point.of(x, y), Point.of(x+2, y)));
                    }
                    // x+1 y+1
                    if (x < 4 && y < 4 && (place[x+1].charAt(y+1) == 'P')) {
                        points.add(List.of(Point.of(x, y), Point.of(x+1, y+1)));
                    }
                    // x y+1
                    if (y < 4 && (place[x].charAt(y+1) == 'P')) {
                        points.add(List.of(Point.of(x, y), Point.of(x, y+1)));
                    }
                    // x y+2
                    if (y < 3 && (place[x].charAt(y+2) == 'P')) {
                        points.add(List.of(Point.of(x, y), Point.of(x, y+2)));
                    }
                }
            }


            boolean flag = true;
            for (List<Point> twopoints : points) {
                flag = isOK(place, twopoints.get(0), twopoints.get(1));
                if (! flag) {
                    break;
                }
            }
            answer[i] = flag ? 1 : 0;
        }
        return answer;
    }

    private boolean isOK(String[] place, Point point1, Point point2) {
        // 맨해튼 거리가 2 초과이면 ok
        if (Math.abs(point1.x - point2.x) + Math.abs(point1.y - point2.y) > 2) {
            return true;
        }

        // 맨해튼 거리가 1 이면 false
        if (Math.abs(point1.x - point2.x) + Math.abs(point1.y - point2.y) <= 1) {
            return false;
        }

        // 모든 파티션으로 막혀있으면 ok
        // x값이 같은 경우
        if (point1.x == point2.x) {
            int x = point1.x;
            int mid = (point1.y + point2.y) / 2;
            return place[x].charAt(mid) != 'O';
        }
        // y값이 같은 경우
        else if (point1.y == point2.y) {
            int y = point1.y;
            int mid = (point1.x + point2.x) / 2;
            return place[mid].charAt(y) != 'O';
        }
        // 둘 다 다른 경우
        else {
            int x1 = Math.min(point1.x, point2.x);
            int x2 = Math.max(point1.x, point2.x);
            int y1 = Math.min(point1.y, point2.y);
            int y2 = Math.max(point1.y, point2.y);

            return place[x1].charAt(y2) != 'O' && place[x2].charAt(y1) != 'O';
        }
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        static Point of(int x, int y) {
            return new Point(x, y);
        }
    }
}
