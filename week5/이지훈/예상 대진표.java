public class 예상 대진표 {

    public int solution(int N, int A, int B) {
        int result = 0;

        while(true) {
            if(A==B) break;

            A = A/2 + A%2;
            B = B/2 + B%2;

            result++;
        }

        return result;
    }

}
