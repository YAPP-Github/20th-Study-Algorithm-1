import java.util.Stack;

class Solution {
    public int solution(String s) {
        int answer = 0;

        Stack<String> stack = new Stack<>();
        String[] tmp = s.split("");

        for (int i = 0; i < s.length(); i++) {
            stack.clear();
            boolean isAnswer = true;

            if (i != 0) {
                tmp = rotateString(tmp);
            }

            for (int j = 0; j < tmp.length; j++) {
                if (tmp[j].equals("[") || tmp[j].equals("(") || tmp[j].equals("{")) {
                    stack.push(tmp[j]);
                } else if (tmp[j].equals("]") || tmp[j].equals(")") || tmp[j].equals("}")) {
                    if (stack.isEmpty()) {
                        isAnswer = false;
                        break;
                    }
                    switch (tmp[j]) {
                        case "]": {
                            if (!stack.pop().equals("[")) {
                                isAnswer = false;
                                break;
                            }
                        }
                        break;
                        case ")": {
                            if (!stack.pop().equals("(")) {
                                isAnswer = false;
                                break;
                            }
                        }
                        break;
                        case "}": {
                            if (!stack.pop().equals("{")) {
                                isAnswer = false;
                                break;
                            }
                        }
                        break;
                    }
                }

            }
            if(isAnswer && stack.isEmpty()) answer++;
        }
        return answer;
    }

    public static String[] rotateString(String[] tmp) {
        String first = tmp[0];

        for (int i = 1; i < tmp.length; i++) {
            tmp[i - 1] = tmp[i];
        }

        tmp[tmp.length - 1] = first;

        return tmp;
    }
}