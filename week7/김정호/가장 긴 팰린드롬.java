class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();

        String s = "abcdcba";

        System.out.println(solution.solution(s));
    }

    public int solution(String s)
    {
        int strLength = s.length() - 1;

        for (int i = strLength; i > -1; i--) {
            for (int j = 0; j + i <= strLength; j++) {
                boolean isCorrection = true;

                int start = j;
                int end = j + i;
                System.out.println(start);
                System.out.println(end);

                while(start < end){
                    if(s.charAt(start) != s.charAt(end)){
                        isCorrection = false;
                        break;
                    }
                    start++;
                    end--;
                }
                if(isCorrection)
                    return i + 1;
            }
        }

        return 0;
    }
}