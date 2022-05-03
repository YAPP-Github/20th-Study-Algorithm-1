class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();

        String play_time = "02:03:55";
        String adv_time = "00:14:15";
        String[] logs = new String[]{"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};

        System.out.println(solution.solution(play_time, adv_time, logs));
    }


    public String solution(String play_time, String adv_time, String[] logs) {
        // 누적합
        // String -> int 의 능력이 있는지..
        // 투포인터...

        String answer = "";

        String[] temp = play_time.split(":");
        int[] play_num = new int[timeToSec(temp[0], temp[1], temp[2]) + 1];

        for (int i = 0; i < logs.length; i++) {
            logs[i] = logs[i].replaceAll("-", ":");
            temp = logs[i].split(":");

            int start = timeToSec(temp[0], temp[1], temp[2]);
            int end = timeToSec(temp[3], temp[4], temp[5]);
            for (int j = start; j < end; j++) {
                play_num[j]++;
            }
        }

        temp = adv_time.split(":");

        int adv = timeToSec(temp[0], temp[1], temp[2]);
        int sT = 0; // 시작시간
        int eT = adv; // 종료시간
        long sum = 0;
        for (int i = sT; i < eT; i++) {
            sum += play_num[i];
        }

        long max = sum;
        int secAnswer = 0;
        for (int i = 0; i < play_num.length - adv; i++) {
            sum -= play_num[sT];
            sum += play_num[eT];
            if (sum > max) {
                max = sum;
                secAnswer = sT + 1;
            }
            sT++; // 시작시간을 한 칸 우측으로 이동
            eT++; // 종료시간도 한 칸 우측으로 이동
        }

        return secondToStr(secAnswer);
    }

    static int timeToSec(String a, String b, String c) {
        int h = Integer.parseInt(a);
        int m = Integer.parseInt(b);
        int s = Integer.parseInt(c);

        int sec = 0;
        sec += h * 3600;
        sec += m * 60;
        sec += s;

        return sec;
    }

    static String secondToStr(int time) {
        int hour = time / 3600;
        time %= 3600;
        int minute = time / 60;
        int second = time % 60;

        String strHour = hour > 9 ? String.valueOf(hour) : "0" + hour;
        String strMinute = minute > 9 ? String.valueOf(minute) : "0" + minute;
        String strSecond = second > 9 ? String.valueOf(second) : "0" + second;

        return String.join(":", strHour, strMinute, strSecond);
    }
}