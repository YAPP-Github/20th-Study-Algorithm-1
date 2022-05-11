package 박현국;

import java.util.*;

public class GroupPhoto {
    public int solution(int n, String[] data) {
        char[] row = new char[] {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        Permutation perm = new Permutation(8, 8);
        perm.permutation(row, 0);
        return (int) perm.result.stream().filter(x -> isMatch(x, data)).count();
    }

    private boolean isMatch(List<Character> row, String[] datas) {
        for (String data : datas) {
            char first = data.charAt(0);
            char second = data.charAt(2);
            char type = data.charAt(3);
            int targetDist = data.charAt(4) - '0';
            int dist = Math.abs(row.indexOf(first) - row.indexOf(second)) - 1;

            if (('<' == type && !(dist < targetDist)) ||
                    ('>' == type && !(dist > targetDist)) ||
                    ('=' == type && !(dist == targetDist))) {
                return false;
            }
        }

        return true;
    }

    static class Permutation {
        private final int n;
        private final int r;
        private final char[] now;
        private final List<ArrayList<Character>> result;

        public Permutation(int n, int r) {
            this.n = n;
            this.r = r;
            this.now = new char[r];
            this.result = new ArrayList<>();
        }

        private void swap(char[] arr, int i, int j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        public void permutation(char[] arr, int depth) {
            // 현재 순열의 길이가 r일 때 결과 저장
            if (depth == r) {
                ArrayList<Character> temp = new ArrayList<>();
                for (char i : now) {
                    temp.add(i);
                }
                result.add(temp);
                return;
            }
            for (int i = depth; i < n; i++) {
                swap(arr, i, depth);
                now[depth] = arr[depth];
                permutation(arr, depth + 1);
                swap(arr, i, depth);
            }
        }
    }
}