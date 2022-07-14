import java.util.*;

class Solution {
    Set<Integer> intSet = new HashSet<>();

    public int solution(String numbers) {
        int answer = 0;
        int[] numArr = new int[numbers.length()];
        for (int i = 0; i < numbers.length(); i++) {
            numArr[i] = numbers.charAt(i) - '0';
        }

        for (int r = 1; r <= numbers.length(); r++) {
            Permutation perm = new Permutation(numbers.length(), r);
            perm.permutation(numArr, 0);
            ArrayList<ArrayList<Integer>> result = perm.getResult();
            for (ArrayList<Integer> arrays : result) {
                int num = Integer.parseInt(arrays.stream().map(String::valueOf).reduce((x, y) -> x + y).get());

                if (isPrimeNumber(num)) {
                    intSet.add(num);
                }
            }
        }

        return intSet.size();
    }

    private boolean isPrimeNumber(int n) {
        if (n < 2) {
            return false;
        }

        if (n == 2) {
            return true;
        }

        for (int i = 2; i<=(int)Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    static class Permutation {
        private int n;
        private int r;
        private int[] now; // 현재 순열
        private ArrayList<ArrayList<Integer>> result; // 모든 순열

        public ArrayList<ArrayList<Integer>> getResult() {
            return result;
        }

        public Permutation(int n, int r) {
            this.n = n;
            this.r = r;
            this.now = new int[r];
            this.result = new ArrayList<ArrayList<Integer>>();
        }

        public void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        public void permutation(int[] arr, int depth) {
            // 현재 순열의 길이가 r일 때 결과 저장
            if (depth == r) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (int i = 0; i < now.length; i++) {
                    temp.add(now[i]);
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