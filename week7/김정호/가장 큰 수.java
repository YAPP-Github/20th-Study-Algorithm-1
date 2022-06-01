import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();

        int[] numbers = new int[]{3, 30, 34, 5, 9};

        System.out.println(solution.solution(numbers));
    }

    public String solution(int[] numbers) {
        String[] arr = new String[numbers.length];

        for(int i = 0; i < numbers.length; i++){
            arr[i] = String.valueOf(numbers[i]);
        }

        // 더한 값을 사전적으로 비교 . . . .
        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));

        // "000" 방지
        if(arr[0].equals("0")) return "0";

        StringBuilder sb = new StringBuilder();
        for(String s : arr) sb.append(s);

        return sb.toString();
    }
}