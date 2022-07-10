import java.util.*;

public class N으로표현 {
    private static Set<Integer> nums = new HashSet<>();
    private static boolean[] visit = new boolean[7];

    public int solution(String numbers) {
        for (int i = 1; i <= numbers.length(); i++) {
            generatePrime(numbers, i, "");
        }

        int count = 0;
        for (int num : nums) {
            if (isPrime(num)) count++;
        }

        return count;
    }

    private static void generatePrime(String numbers, int depth, String tmp) {
        if (tmp.length() == depth) {
            nums.add(Integer.parseInt(tmp));
            return;
        }

        for (int i = 0; i < numbers.length(); i++) {
            if (visit[i]) continue;

            visit[i] = true;
            tmp += numbers.charAt(i);

            generatePrime(numbers, depth, tmp);

            visit[i] = false;
            tmp = tmp.substring(0, tmp.length() - 1);
        }
    }

    private static boolean isPrime(int num) {
        if (num < 2) return false;

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }

        return true;
    }

}