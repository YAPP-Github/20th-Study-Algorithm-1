package 박현국;

import java.util.*;

public class FuncDev {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        Queue<Job> jobQueue = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            jobQueue.add(new Job(progresses[i], speeds[i]));
        }

        int releaseCount = 0;
        while (! jobQueue.isEmpty()) {
            for (Job job : jobQueue) {
                job.proceed();
            }

            while (! jobQueue.isEmpty() && jobQueue.peek().progress >= 100) {
                jobQueue.remove();
                releaseCount++;
            }

            if (releaseCount > 0) {
                answer.add(releaseCount);
                releaseCount = 0;
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    static class Job {
        int progress = 0;
        int speed = 0;

        Job(int progress, int speed) {
            this.progress = progress;
            this.speed = speed;
        }

        void proceed() {
            this.progress += this.speed;
        }
    }
}
