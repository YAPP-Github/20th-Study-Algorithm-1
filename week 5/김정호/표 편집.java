import java.util.Arrays;
import java.util.Stack;

class Solution {
    public static void main(String[] args) {
        // 테스트 코드
        Solution solution = new Solution();

        int n = 15;
        int k = 12;
        String[] cmd = new String[]{"D 2","C","U 3","C","D 4","C","U 2","D3","C","Z","Z"};

        System.out.println(solution.solution(n, k, cmd));
    }

    // 요구사항
    //  "U X": 현재 선택된 행에서 X칸 위에 있는 행을 선택합니다.
    //  "D X": 현재 선택된 행에서 X칸 아래에 있는 행을 선택합니다.
    //  "C" : 현재 선택된 행을 삭제한 후, 바로 아래 행을 선택합니다. 단, 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택합니다.
    //  "Z" : 가장 최근에 삭제된 행을 원래대로 복구합니다. 단, 현재 선택된 행은 바뀌지 않습니다.

    public String solution(int n, int k, String[] cmd) {
        StringBuilder sb = new StringBuilder();
        int size = n;

        String[] result = new String[n];
        Stack<Integer> index = new Stack<>();

//        for(int i = 0; i < n; i++) {
//            result[i] = "O";
//        }

        int ptr = k;

        for(String command : cmd) {
            String[] temp = command.split(" ");

            String behavior = temp[0];

            // 올라가거나 내려가는 명령어일 때
            if(temp.length == 2) {
                int offset = Integer.parseInt(temp[1]);
                // 내려갈 때
                if(behavior.equals("D")) {
                    ptr += offset;
                }
                // 올라갈 때
                else {
                    ptr -= offset;
                }
            }else {
                // 삭제 및 되돌리는 명령어일 때
                if(behavior.equals("C")) {
                    index.push(ptr);
                    result[ptr] = "X";

                    // 마지막 행을 삭제한 경우
                    size -= 1;
                    if(size == ptr) {
                        ptr -= 1;
                    }
                } else if(behavior.equals("Z")) {
                    int out = index.pop();
                    result[out] = "O";

                    // 현재 포인터보다 더 작은 수를 삭제했을 때
                    if(ptr >= out) {
                        ptr++;
                    }
                    size += 1;
                }
            }
        }

//        System.out.println(n);
//        System.out.println(size);

        for (int i = 0; i < size; i++) {
            sb.append("O");
        }
        while(!index.empty()){
            sb.insert(index.pop().intValue(),'X');
        }

//        for(String s : result) {
//            sb.append(s);
//        }

        return sb.toString();
    }
}
