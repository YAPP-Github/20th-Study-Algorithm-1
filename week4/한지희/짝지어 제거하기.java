import java.util.ArrayList;
import java.util.Stack;

class PairDelete {
    public int solution(String s) {
        Stack<Character> characterStack = new Stack<>();

        for (char c: s.toCharArray()) {
            if (!characterStack.isEmpty() && characterStack.peek() == c) characterStack.pop();
            else characterStack.push(c);
        }

        return characterStack.isEmpty() ? 1 : 0;
    }

    public int timeoutSolution(String s) {
        if (s.length() <= 1) return 0;
        int answer = 1;
        ArrayList<Character> characters = new ArrayList();

        for (int i = 0; i < s.length(); i++) {
            characters.add(s.charAt(i));
        }

        while (!characters.isEmpty()) {
            boolean stop = true;

            for (int i = 0; i < characters.size() - 1; i++) {
                if (characters.get(i) == characters.get(i + 1)) {
                    characters.remove(i + 1);
                    characters.remove(i);
                    stop = false;
                    break;
                }
            }

            if (stop) {
                answer = 0;
                break;
            }
        }

        return answer;
    }
}
