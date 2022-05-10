class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();

        String s = "baabaa";
        System.out.println(solution.solution(s));
    }


    public int solution(String s)
    {
        for(int i = 0; i < s.length() - 1; i++) {
            if(s.charAt(i) == s.charAt(i +1)) {
                s = s.substring(0, i) + s.substring(i + 2);
                i = -1;
            }
        }

        return s.isEmpty() ? 1 : 0;
    }
}