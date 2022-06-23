import java.util.HashSet;

class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();

        int[] a = new int[]{-16, 27, 65, -2, 58, -92, -71, -68, -61, -33};

        System.out.println(solution.solution(a));
    }

    public int solution(int[] a) {
        HashSet<Integer> hs = new HashSet<>();

        int minLeft = a[0];
        int minRight = a[a.length - 1];

        for (int i = 1; i < a.length; i++) {
            hs.add(minLeft);
            minLeft = Math.min(minLeft, a[i]);
        }
        for (int i = a.length - 1; i >= 0; i--) {
            hs.add(minRight);
            minRight = Math.min(minRight, a[i]);
        }
        return hs.size();
    }
}