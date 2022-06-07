package 박현국;

import java.util.*;

public class PoppingBalloon {
    int[] leftMin; // i번째 인덱스에서 왼쪽 값의 최솟값
    int[] rightMin; // i번째 인덱스에서 오른쪽 값의 최솟값

    public int solution(int[] a) {
        if (a.length == 1) {
            return 1;
        }

        // 맨 왼쪽, 오른쪽 풍선은 남길 수 있다.
        int answer = 2;

        this.leftMin = new int[a.length];
        this.rightMin = new int[a.length];
        int leftMinNum = a[0];
        int rightMinNum = a[a.length-1];

        for (int i = 1; i < a.length; i++) {
            leftMinNum = Math.min(leftMinNum, a[i-1]);
            this.leftMin[i] = leftMinNum;
        }

        for (int i = a.length-2; i >= 0; i--) {
            rightMinNum = Math.min(rightMinNum, a[i+1]);
            this.rightMin[i] = rightMinNum;
        }

        for (int i = 1; i < a.length-1; i++) {
            if (isPopable(i, a[i])) {
                answer++;
            }
        }

        return answer;
    }

    boolean isPopable(int i, int num) {
        return this.leftMin[i] > num || this.rightMin[i] > num;
    }
}
