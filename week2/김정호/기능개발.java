import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();

        int[] progresses = new int[]{93,30,55};
        int[] speeds = new int[]{1,30,5};
//        int[] progresses = new int[]{95, 90, 99, 99, 80, 99};
//        int[] speeds = new int[]{1,1,1,1,1,1};

        System.out.println(Arrays.toString(solution.solution(progresses, speeds)));
    }

    public int[] solution(int[] progresses, int[] speeds) {


        Queue<Integer> pQueue = new LinkedList<Integer>();

        // 큐에 초기값 입력
        for(int pro : progresses) {
            pQueue.add(pro);
        }

        ArrayList<Integer> days = new ArrayList<>();

        // 할 일을 처리하는데 걸리는 시간을 계산하고 ArrayList 에 담는 과정
        int c = 0;
        while(!pQueue.isEmpty()) {
            int current = pQueue.peek();
            int currentDay = 0;

            while(current < 100) {
                current += speeds[c];
                currentDay++;
            }

            days.add(currentDay);;
            c++;
            pQueue.poll();
        }

        ArrayList<Integer> answerAr = new ArrayList<>();

        // 배포 리스트를 추출해내는 과정
        Loop1 :
        for(int i = 0; i < days.size(); i++) {
            int dayCounting = 1;

            // 마지막 숫자이면 뒤에 더 비교할 수가 없으니 바로 1을 넣고 반복문을 종료시킨다.
            if(i == days.size() - 1) {
                answerAr.add(1);
                break;
            }

            // i 인덱스를 기준으로 탐색
            for(int j = i+1; j < days.size(); j++) {
                if(days.get(i) < days.get(j)) {
                    // 뒤의 작업을 처리하는 시간이 더 오래걸리면, 앞의 작업들을 뭉쳐서 배포한다. (3 -> 1 -> 1 -> 4) : X일 째에 3개 배포
                    i = j - 1;
                    answerAr.add(dayCounting);
                    break;
                } else {
                    // (3 -> 1 -> 1)
                    // 앞의 작업보다 뒤의 작업이 더 빨리 처리됐으면, 함께 배포하게끔 값을 배치한다.
                    dayCounting++;
                    if(j == days.size() -1) {
                        // 비교 과정에서 마지막 값에 도달하면 카운트를 그대로 리스트에 넣어주고, i 인덱스를 j 인덱스로 맞춰준다.
                        // 첫 반복문은 작동하지 않게 된다.
                        // i = j;
                        answerAr.add(dayCounting);
                        break Loop1; // 첫 반복문 종료
                    }
                }
            }
        }

        int[] answer = new int[answerAr.size()];

        for(int i = 0; i < answerAr.size(); i++) {
            answer[i] = answerAr.get(i);
        }

        return answer;
    }
}