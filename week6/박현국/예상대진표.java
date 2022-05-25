class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;
        a--;
        b--;

        while (a != b) {
            a /= 2;
            b /= 2;
            answer++;
        }

        // INDEX / 2 + 1
        return answer;
    }
}