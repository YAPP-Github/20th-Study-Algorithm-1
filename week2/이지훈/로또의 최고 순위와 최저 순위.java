public class 로또의 최고 순위와 최저 순위 {
    public int[] solution(int[] lottos, int[] win_nums) {
        int matchCnt = 0;
        int zeroCnt = 0;
        int[] check = new int[46];

        for (int i = 0; i < 6; i++) {
            check[lottos[i]]++;
            check[win_nums[i]]++;
        }

        for (int i = 1; i < 46; i++) {
            if (check[i] == 2) matchCnt++;
        }
        zeroCnt = check[0];

        int[] result = new int[2];
        result[0] = Math.min(7 - matchCnt + zeroCnt, 6); // 최고순위
        result[1] = Math.min(7 - matchCnt, 6); // 최저순위

        return result;
    }

}
