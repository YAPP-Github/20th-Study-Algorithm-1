package 박현국;

import java.util.Arrays;
import java.util.stream.IntStream;

// 풀이 참조... https://dev-note-97.tistory.com/156
public class InsertAdvertise {
    // 누적합을 이용한 풀이. 근데 17번 테스트케이스 하나만 실패한다. 왜지...?
    public String solution(String playTime, String advTime, String[] logs) {
        int playTimestamp = toTimestamp(playTime);
        int advTimestamp = toTimestamp(advTime);
        int[] timeLine = new int[360000];

        for (String log : logs) {
            String[] split = log.split("-");
            int startTime = toTimestamp(split[0]);
            int endTime = toTimestamp(split[1]);

            timeLine[startTime]++;
            timeLine[endTime]--;
        }

        for (int i = 1; i < playTimestamp; i++) {
            timeLine[i] += timeLine[i-1];
        }

        for (int i = 1; i < playTimestamp; i++) {
            timeLine[i] += timeLine[i-1];
        }

        int maxValue = timeLine[advTimestamp - 1];
        int answer = 0;
        for (int i = advTimestamp; i < playTimestamp+1; i++) {
            int value = timeLine[i] - timeLine[i-advTimestamp];
            if (maxValue < value) {
                maxValue = value;
                answer = i - advTimestamp + 1;
            }
        }
        return toTimeFormat(answer);
    }

    private int toTimestamp(String timeFormat) {
        String[] split = timeFormat.split(":");
        int ret = Integer.parseInt(split[0]) * 3600;
        ret += Integer.parseInt(split[1]) * 60;
        ret += Integer.parseInt(split[2]);
        return ret;
    }

    private String toTimeFormat(int timestamp) {
        int hour = timestamp / 3600;
        int minute = timestamp % 3600 / 60;
        int second = timestamp % 60;
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public String failed_solution(String playTime, String advTime, String[] logs) {
        int[] timeLine = new int[toTimestamp(playTime)+1];

        for (String log : logs) {
            String[] split = log.split("-");
            int startTime = toTimestamp(split[0]);
            int endTime = toTimestamp(split[1]);

            for (int i = startTime;i <= endTime; i++) {
                timeLine[i]++;
            }
        }

        // 나름? 투포인터 알고리즘
        int advLength = toTimestamp(advTime);
        int arraySum = IntStream.of(Arrays.copyOfRange(timeLine, 0, advLength + 1)).sum();
        int maxValue = arraySum;
        int maxIdx = 0;
        for (int i = 1; i < timeLine.length - advLength; i++) {
            arraySum = arraySum - timeLine[i-1] + timeLine[i+advLength];
            if (maxValue < arraySum) {
                maxValue = arraySum;
                maxIdx = i;
            }
        }

        return toTimeFormat(maxIdx);
    }
}
