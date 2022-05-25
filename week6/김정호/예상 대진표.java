class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();

        int n = 8;
        int a = 4;
        int b = 7;

        System.out.println(solution.solution(n, 4, 7));
    }

    public int solution(int n, int a, int b) {
        int answer = 0;

        // while(Math.abs(a - b) == 1)
//         while(a != b) {
//             int tmp = a;
//             a = a / 2;
//             if(tmp % 2 != 0) a++;

//             tmp = b;
//             b = b / 2;
//             if(tmp % 2 != 0) b++;

//             answer++;
//         }

        do {
            a = (a + 1) / 2;
            b = (b + 1) / 2;
            answer++;
        } while (a != b);


        return answer;
    }
}
