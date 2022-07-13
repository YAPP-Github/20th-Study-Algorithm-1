import java.util.Set;
import java.util.HashSet;

class Solution {
    private static Set<Integer> numberSet = new HashSet<>();

    public int solution(String numbers) {
        int answer = 0;
        solve("", numbers);

        for (int number : numberSet) {
            if (isPrime(number)) answer++;
        }

        return answer;
    }

    private static void solve(String tmp, String otherNumbers) {
        if(!tmp.equals("")) {
            numberSet.add(Integer.valueOf(tmp));
        }

        for(int i = 0; i < otherNumbers.length(); i++) {
            solve(tmp + otherNumbers.charAt(i), otherNumbers.substring(0, i) + otherNumbers.substring(i + 1));
        }
    }

    private static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}