// 이해 포기..
public class 광고삽입 {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = convertToSecond(play_time);
        int advTime = convertToSecond(adv_time);
        long[] arr = new long[playTime + 1];

        // 동영상 시청 시작 시간, 시청 종료 시간 증감연산
        for (String log : logs) {
            String[] times = log.split("-");
            int startTime = convertToSecond(times[0]);
            int endTime = convertToSecond(times[1]);

            arr[startTime]++;
            arr[endTime]--;
        }

        for (int i = 1; i <= playTime; i++) arr[i] += arr[i - 1]; // i초에 동영상을 시청하는 인원
        for (int i = 1; i <= playTime; i++) arr[i] += arr[i - 1]; // i초 까지 동영상을 시청한 시간

        long maxTime = arr[advTime - 1];
        long startTime = 0;
        for (int i = 0; i + advTime <= playTime; i++) {
            long time = arr[i + advTime] - arr[i];

            if (time > maxTime) {
                maxTime = time;
                startTime = i + 1;
            }
        }

        return String.format("%02d:%02d:%02d", startTime / 3600, (startTime / 60) % 60, startTime % 60);
    }

    private static int convertToSecond(String time) {
        String[] times = time.split(":");
        return Integer.parseInt(times[0]) * 3600 + Integer.parseInt(times[1]) * 60 + Integer.parseInt(times[2]);
    }
}