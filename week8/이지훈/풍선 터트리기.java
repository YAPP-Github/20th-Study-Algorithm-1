public class 풍선 터트리기 {
    public int solution(int[] a) {
        int maxLeft = 1000000000;
        int maxRight = 1000000000;
        int result = 0;

        for (int i = 0; i < a.length; i++) {
            int nowLeft = a[i];
            int nowRight = a[a.length - i - 1];

            if (nowLeft < maxLeft) {
                maxLeft = nowLeft;
                result++;
            }

            if (nowRight < maxRight) {
                maxRight = nowRight;
                result++;
            }

            if(maxLeft == maxRight) {
                result--;
                break;
            }
        }

        return result;
    }

}