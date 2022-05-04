public class 광고삽입 {

    public String solution(String play_time, String adv_time, String[] logs) {

        String [] playTime = play_time.split(":");
        String [] advTime = adv_time.split(":");

        int playTimes = 0;
        int advTimes = 0;
        for(int i = 2; i >= 0; i--) {
            playTimes += Integer.parseInt(playTime[i]) * Math.pow(60, Math.abs(i-2));
            advTimes += Integer.parseInt(advTime[i]) * Math.pow(60, Math.abs(i-2));
        }

        long [] totalTime = new long[playTimes+1];

        for(String log : logs) {
            String [] startEnd = log.split("-");
            String [] startString = startEnd[0].split(":");
            String [] endString = startEnd[1].split(":");

            int startTime = 0, endTime = 0;
            for(int i = 2; i >= 0; i--) {
                startTime += Integer.parseInt(startString[i]) * Math.pow(60, Math.abs(i-2));
                endTime += Integer.parseInt(endString[i]) * Math.pow(60, Math.abs(i-2));
            }
            
            totalTime[startTime] = totalTime[startTime] + 1;
            totalTime[endTime] = totalTime[endTime] - 1;
        }

        for(int i = 1; i <= playTimes; i++) {
            totalTime[i] += totalTime[i-1];
        }

        for(int i = 1; i <= playTimes; i++) {
            totalTime[i] += totalTime[i-1];
        }
        
        long maxTime = 0;
        long maxTimeIdx = 0;
        for(int i = advTimes - 1; i <= playTimes - 1; i++) {
            if(i >= advTimes) {
                if(maxTime < totalTime[i] - totalTime[i-advTimes]) {
                    maxTime = totalTime[i] - totalTime[i-advTimes];
                    maxTimeIdx = i - advTimes + 1;
                }
            } else {
                if(maxTime < totalTime[i]) {
                    maxTime = totalTime[i];
                    maxTimeIdx = i - advTimes + 1;
                }
            }
        }

        String answer = convertLongToStringTime(maxTimeIdx);
        return answer;
    }

    public String convertLongToStringTime(long res) {
        StringBuilder sb = new StringBuilder();

        int [] times = new int[3];

        for(int i = 0; i < 2; i++) {
            times[i] = (int) (res % 60);
            res /= 60;
        }
        times[2] = (int) res;

        for(int i = 2; i >= 0; i--) {
            if(times[i] < 10) {
                sb.append("0" + times[i]);
            } else {
                sb.append(times[i]);
            }
            if(i > 0) sb.append(":");
        }

        return sb.toString();
    }
    
}