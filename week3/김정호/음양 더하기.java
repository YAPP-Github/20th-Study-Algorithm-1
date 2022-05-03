class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[]{4,7,12}, new boolean[]{true,false,true}));
    }

    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        for(int i = 0; i < absolutes.length; i++) answer += signs[i] ? absolutes[i] : -absolutes[i];
        return answer;
    }
}